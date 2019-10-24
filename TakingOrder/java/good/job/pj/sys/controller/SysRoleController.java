package good.job.pj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import good.job.pj.common.vo.JsonResult;
import good.job.pj.sys.entity.SysRoleEntity;
import good.job.pj.sys.service.SysRoleService;

@RequestMapping("/role/")
@RestController
public class SysRoleController {

	@Autowired
	private SysRoleService sysRoleservice;
	@RequestMapping("doFindePages")
	public JsonResult findePages(String name,Integer pageCurrent) {
		return new JsonResult(sysRoleservice.findPages(name, pageCurrent));
	}
	@RequestMapping("doDeleteObject")
	public JsonResult deleteObject(Integer id) {
		System.out.println("roleId="+id);
		sysRoleservice.deleteObject(id);
		return new JsonResult("delete ok");
	}

	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(SysRoleEntity entity,Integer[] menuIds) {
		sysRoleservice.savObject(entity, menuIds);
	return new JsonResult("save ok");
	}
	@RequestMapping("doFindObjectById")
	 public JsonResult doFindObjectById(Integer id){
	    	return new JsonResult(sysRoleservice.findObjectById(id));
	 }
	@RequestMapping("doUpdateObject")
	public JsonResult doUpdateObject(SysRoleEntity entity,
	Integer[] menuIds){
			  sysRoleservice.updateObject(entity, menuIds);
	 return new JsonResult("update ok");
	}
	@RequestMapping("doFindZtreeMenuNodes")
	public JsonResult doFindZtreeMenuNodes() {
		return new JsonResult(sysRoleservice.findZtreeMenuNodes());
	}
	@RequestMapping("doFindObjects")
	public JsonResult doFindObjects(){
		 return new JsonResult(sysRoleservice.findObjects());
	}
}
