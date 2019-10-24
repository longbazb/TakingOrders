package good.job.pj.sys.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import good.job.pj.common.vo.JsonResult;
import good.job.pj.sys.entity.SumCar;



@RestController
@RequestMapping("/order/")
public class CommodityController {
	
	/*购物车实现，获取购物车对象*/
	/*具体实现在order中*/
	@RequestMapping("orderCar")
	public JsonResult addddd(SumCar a) {
		System.out.println(a+"     :!*****:!    ");
		return new JsonResult("ok");
	}
	

}
