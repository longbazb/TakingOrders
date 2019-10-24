package good.job.pj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import good.job.pj.common.vo.JsonResult;
import good.job.pj.common.vo.PageObject;
import good.job.pj.sys.entity.SysOrderEntity;
import good.job.pj.sys.service.SysOrderService;

@RestController
@RequestMapping("/order/")
public class SysOrderController {

	@Autowired
	private SysOrderService sysOrderService;
	
	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(
			String productname,Integer pageCurrent) {
    	PageObject<SysOrderEntity> pageObject=sysOrderService.findPageObjects(productname, pageCurrent);
    	return new JsonResult(pageObject);
	}
	@RequestMapping("doDeleteObjects")
	public JsonResult doDeleteObjects(Integer...ids) {
		sysOrderService.deleteObjects(ids);
		return new JsonResult("删除成功");
		
	}
	
	
}
