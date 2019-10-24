package good.job.pj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import good.job.pj.common.vo.JsonResult;
import good.job.pj.sys.entity.SysDeptEntity;
import good.job.pj.sys.service.SysDeptService;

@RequestMapping("/dept/")
@RestController
public class SysDeptController {

	@Autowired
	private SysDeptService sysDeptService;
	@RequestMapping("doFindZTreeDeptNodes")
	 public JsonResult doFindZtreeDeptNodes(){
		 return new JsonResult(
		 sysDeptService.findZtreeDeptNodes());
	 }
	@RequestMapping("doFindObjects")
	public JsonResult doFindObjects() {
		return new JsonResult(sysDeptService.findObjects());
	}
	
	
	@RequestMapping("doDeleteObject")
	public JsonResult doDeleteObject(Integer id) {
		sysDeptService.deleteObject(id);
		return new JsonResult("delete OK");
	}
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(SysDeptEntity entity){
		sysDeptService.saveObject(entity);
		return new JsonResult("save ok");
	}
	
	 @RequestMapping("doDeptEditUI")
	 public String doDeptEditUI(){
		 return "sys/dept_edit";
	 }
	 
	 @RequestMapping("doUpdateObject")
		public JsonResult doUpdateObject(SysDeptEntity entity){
		    sysDeptService.updateObject(entity);
		    return new JsonResult("update ok");
		}

}
