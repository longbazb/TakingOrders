package good.job.pj.sys.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import good.job.pj.common.vo.JsonResult;
import good.job.pj.common.vo.PageObject;
import good.job.pj.common.vo.SysUserDeptVo;
import good.job.pj.sys.entity.SysUserEntity;
import good.job.pj.sys.service.SysRoleService;
import good.job.pj.sys.service.SysUserService;

@RequestMapping("/user/")
@RestController
public class SysUserController {

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysRoleService sysRoleService;;
	@RequestMapping("doFindPageObjects")
	@ResponseBody
	public JsonResult doFindPageObjects(
			String username,Integer pageCurrent){
		PageObject<SysUserDeptVo> pageObject=
				sysUserService.findPageObjects(username,
						pageCurrent);
		return new JsonResult(pageObject);
	}

	@RequestMapping("doValidById")
	@ResponseBody
	public JsonResult doValidById(
			Integer id,
			Integer valid){
		sysUserService.validById(
				id,
				valid, 
				"admin");//"admin"用户将来是登陆用户
		return new JsonResult("update ok");
	}

	@RequestMapping("doFindObjectById")
	@ResponseBody
	public JsonResult doFindObjectById(
			Integer id){
		Map<String,Object> map=
				sysUserService.findObjectById(id);
		return new JsonResult(map);
	}

	@RequestMapping("doFindRoles")
	@ResponseBody
	public JsonResult doFindObjects(){
		return new JsonResult(sysRoleService.findObjects());
	}
	
	@RequestMapping("doSaveObject")
	@ResponseBody
	public JsonResult doSaveObject(
			SysUserEntity entity,
			Integer[] roleIds){
		sysUserService.saveObject(entity,roleIds);
		return new JsonResult("save ok");
	}
	
	@RequestMapping("doUpdateObject")
	@ResponseBody
	public JsonResult doUpdateObject(
	    SysUserEntity entity,Integer[] roleIds){
		sysUserService.updateObject(entity,
				roleIds);
		return new JsonResult("update ok");
	}
	@RequestMapping("doLogin")
	@ResponseBody
	public JsonResult doLogin(String username,String password){
		//1.获取Subject对象
		Subject subject=SecurityUtils.getSubject();
		//2.通过Subject提交用户信息,交给shiro框架进行认证操作
		//2.1对用户进行封装
		UsernamePasswordToken token=
				new UsernamePasswordToken(
						username,//身份信息
						password);//凭证信息
		//2.2对用户信息进行身份认证
		subject.login(token);
		//分析:
		//1)token会传给shiro的SecurityManager
		//2)SecurityManager将token传递给认证管理器
		//3)认证管理器会将token传递给realm
		return new JsonResult("login ok");
	}
	
	@RequestMapping("doUpdatePassword")
	 @ResponseBody
	 public JsonResult doUpdatePassword(
			 String pwd,
			 String newPwd,
			 String cfgPwd) {
		 sysUserService.updatePassword(pwd, newPwd, cfgPwd);
		 return new JsonResult("update ok");
	 }
}