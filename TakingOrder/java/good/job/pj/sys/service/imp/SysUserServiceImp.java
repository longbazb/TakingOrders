package good.job.pj.sys.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import good.job.pj.common.annotation.RequiredLog;
import good.job.pj.common.exception.ServiceException;
import good.job.pj.common.vo.PageObject;
import good.job.pj.common.vo.SysUserDeptVo;
import good.job.pj.sys.dao.SysUserDao;
import good.job.pj.sys.dao.SysUserRoleDao;
import good.job.pj.sys.entity.SysUserEntity;
import good.job.pj.sys.service.SysUserService;
import io.micrometer.core.instrument.util.StringUtils;

/**
 * @author Administrator
 *
 */
@Service
public class SysUserServiceImp implements SysUserService{

	@Resource
	private SysUserDao sysUserDao;

	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	@Transactional(readOnly = true)
	@RequiredLog("查询用户")
	@Override
	public PageObject<SysUserDeptVo> findPageObjects(String username, 
			Integer pageCurrent) {
		//1.数据合法性验证
		if(pageCurrent==null||pageCurrent<=0)
			throw new ServiceException("参数不合法");
		//2.依据条件获取总记录数
		int rowCount=sysUserDao.getRowCount(username);
		if(rowCount==0)
			throw new ServiceException("记录不存在");
		//3.计算startIndex的值
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		//4.依据条件获取当前页数据
		List<SysUserDeptVo> records=sysUserDao.findPageObjects(
				username, startIndex, pageSize);
		//5.封装数据
		PageObject<SysUserDeptVo> pageObject=new PageObject<>();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setRowCount(rowCount);
		pageObject.setPageSize(pageSize);
		pageObject.setRecords(records);
		pageObject.setPageCount((rowCount-1)/pageSize+1);
		return pageObject;
	}
	@RequiredLog("更新用户")
	@Override
	public int validById(Integer id, Integer valid, String modifiedUser) {
		//1.合法性验证
		if(id==null||id<=0)
			throw new ServiceException("参数不合法,id="+id);
		if(valid!=1&&valid!=0)
			throw new ServiceException("参数不合法,valie="+valid);
		if(StringUtils.isEmpty(modifiedUser))
			throw new ServiceException("修改用户不能为空");
		//2.执行禁用或启用操作
		int rows=0;
		try{
			rows=sysUserDao.validById(id, valid, modifiedUser);
		}catch(Throwable e){
			e.printStackTrace();
			//报警,给维护人员发短信
			throw new ServiceException("底层正在维护");
		}
		//3.判定结果,并返回
		if(rows==0)
			throw new ServiceException("此记录可能已经不存在");
		return rows;
	}
	@RequiredLog("查询用户")
	@Override
	public Map<String, Object> findObjectById(Integer userId) {
		//1.合法性验证
		if(userId==null||userId<=0)
			throw new ServiceException(
					"参数数据不合法,userId="+userId);
		//2.业务查询
		SysUserDeptVo user=
				sysUserDao.findObjectById(userId);
		if(user==null)
			throw new ServiceException("此用户已经不存在");
		List<Integer> roleIds=
				sysUserRoleDao.findRoleIdsByUserId(userId);
		//3.数据封装
		Map<String,Object> map=new HashMap<>();
		map.put("user", user);
		map.put("roleIds", roleIds);
		return map;
	}
	@RequiredLog("新建用户")
	@Override
	public int saveObject(SysUserEntity entity, Integer[] roleIds) {
		//1.验证数据合法性
		if(entity==null)
			throw new ServiceException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername()))
			throw new ServiceException("用户名不能为空");
		if(StringUtils.isEmpty(entity.getPassword()))
			throw new ServiceException("密码不能为空");
		if(roleIds==null || roleIds.length==0)
			throw new ServiceException("至少要为用户分配角色");

		//2.将数据写入数据库
		String salt=UUID.randomUUID().toString();
		entity.setSalt(salt);
		//加密(先了解,讲shiro时再说)
//		SimpleHash sHash=
//				new SimpleHash("MD5",entity.getPassword(), salt);
//		entity.setPassword(sHash.toHex());

		int rows= sysUserDao.insertObject(entity);
		sysUserRoleDao.insertObjects(
				entity.getId(),
				roleIds);//"1,2,3,4";
		//3.返回结果
		return rows;
	}
	@RequiredLog("更新用户")
	@Override
	public int updateObject(SysUserEntity entity, Integer[] roleIds) {
		//1.参数有效性验证
		if(entity==null)
			throw new IllegalArgumentException("保存对象不能为空");
		if(StringUtils.isEmpty(entity.getUsername()))
			throw new IllegalArgumentException("用户名不能为空");
		if(roleIds==null||roleIds.length==0)
			throw new IllegalArgumentException("必须为其指定角色");
		//其它验证自己实现，例如用户名已经存在，密码长度，...
		//2.更新用户自身信息
		int rows=sysUserDao.updateObject(entity);
		//3.保存用户与角色关系数据
		sysUserRoleDao.deleteObjectsByUserId(entity.getId());
		sysUserRoleDao.insertObjects(entity.getId(),
				roleIds);
		//4.返回结果
		return rows;
	}
	@Override
	public int updatePassword(String password, String newPassword, String cfgPassword) {
		// TODO Auto-generated method stub
		//1.判定新密码与密码确认是否相同
		if(StringUtils.isEmpty(newPassword))
			throw new IllegalArgumentException("新密码不能为空");
		if(StringUtils.isEmpty(cfgPassword))
			throw new IllegalArgumentException("确认密码不能为空");
		if(!newPassword.equals(cfgPassword))
			throw new IllegalArgumentException("两次输入的密码不相等");
		//2.判定原密码是否正确
		if(StringUtils.isEmpty(password))
			throw new IllegalArgumentException("原密码不能为空");
		//获取登陆用户
		SysUserEntity user=(SysUserEntity)SecurityUtils.getSubject().getPrincipal();
		SimpleHash sh=new SimpleHash("MD5",
				password, user.getSalt(), 1);
		if(!user.getPassword().equals(sh.toHex()))
			throw new IllegalArgumentException("原密码不正确");
		//3.对新密码进行加密
		String salt=UUID.randomUUID().toString();
		sh=new SimpleHash("MD5",newPassword,salt, 1);
		//4.将新密码加密以后的结果更新到数据库
		int rows=sysUserDao.updatePassword(sh.toHex(), salt,user.getId());
		if(rows==0)
			throw new ServiceException("修改失败");
		return rows;
	}

}

