package good.job.pj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import good.job.pj.common.vo.JsonResult;
import good.job.pj.common.vo.PageObject;
import good.job.pj.sys.entity.SysLogEntity;
import good.job.pj.sys.service.SysLogService;

@RestController
@RequestMapping("/log/")
public class SysLogController {
	  @Autowired
		private SysLogService sysLogService;
	    
	    @RequestMapping("doFindPageObjects")
		public JsonResult doFindPageObjects(String username,Integer pageCurrent) {
	    	PageObject<SysLogEntity> pageObject=
	    	sysLogService.findPageObjects(username, pageCurrent);
	    	return new JsonResult(pageObject);
		}
	    //删除日志
	    @RequestMapping("doDeleteObjects")
	    @ResponseBody
	    public JsonResult doDeleteObjects(Integer...ids) {
	    	System.out.println("ids="+ids.toString());
	       sysLogService.deleteObjects(ids);
	       return new JsonResult("delete ok");
	    }
	    //http://localhost/ProjectOne/log/doDeleteObjects?ids=9,11
	    //http://localhost/ProjectOne/log/doFindPageObjects?username=admin&pageCurrent=1
	    
}
