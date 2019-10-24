package good.job.pj.sys.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import good.job.pj.common.vo.JsonResult;
import good.job.pj.common.vo.PageObject;
import good.job.pj.sys.entity.Products;
import good.job.pj.sys.service.SysProdctService;



@RequestMapping("/product/")
@RestController
public class SysProductController {
	@Autowired
	private SysProdctService sysProdctService;
	
	/*显示页面*/
	@RequestMapping("doFindPageObjects")
	public JsonResult doFindPageObjects(
			String productname,Integer pageCurrent){
		PageObject<Products> pageObject = 
				sysProdctService.findPageObjects(productname, pageCurrent);
			return new JsonResult(pageObject);
	}
	/*  保存数据      */
	@RequestMapping("doSaveObject")
	public JsonResult doSaveObject(Products entity) {
		System.out.println(entity);
		sysProdctService.saveObject(entity);
		return new JsonResult("save ok");
	}
	/*修改数据*/
	/* 1.查询数据 */
	@RequestMapping("doFindObjectById")
	public JsonResult doFindOBjectById(Integer id) {
		System.out.println("根据id去查页面：");
		Products entity = sysProdctService.findObjectById(id);
		System.out.println(entity);
		return new JsonResult(entity);
	}
	/*2.修改*/
	@RequestMapping("doUpdateObject")
	public JsonResult doupdate(Products entity) {
		sysProdctService.updateObjects(entity);
		return new JsonResult("update ok");
	}
	
	/*删除数据*/
	@RequestMapping("doDeleteObjects")
	public JsonResult dodeleteobjects(Integer...ids) {
		sysProdctService.deleteObjects(ids);
		return new JsonResult("delete ok");
	}
	
}
