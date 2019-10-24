package good.job.pj.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class pageController {
	@RequestMapping("doIndexUI")
	public String doIndexUI() {
		return "index";
	}
	@RequestMapping("doPageUI")
	public String doPage() {
		return "common/page";
	}
	@RequestMapping("doLoginUI")
	public String doLoginUI(){
		return "login";
	}
	@RequestMapping("longBa")
	public String shopcar() {
		return "demo";
	}

	@RequestMapping("user/user_list")
	public String doUserListUI() {
		return "sys/user_list";
	}
	@RequestMapping("user/user_edit")
	public String doUserEditUI() {
		return "sys/user_edit";
	}
	@RequestMapping("role/role_list")
	public String doRoleListUI() {
		return "sys/role_list";
	}
	@RequestMapping("role/role_edit")
	public String doRoleEditUI() {
		return "sys/role_edit";
	}
	@RequestMapping("order/order_list")
	public String doOrderListUI() {
		return "sys/order_list";
	}
	@RequestMapping("order/order_edit")
	public String doOrderEdit() {
		return "sys/order_edit";
	}
	@RequestMapping("dept/dept_list")
	public String doDeptListUI(){
		return "sys/dept_list";
	}
	@RequestMapping("dept/dept_edit")
	public String doDeptEdit(){
		return "sys/dept_edit";
	}
	@RequestMapping("log/log_list")
	public String doLogEdit(){
		return "sys/log_list";
	}
	@RequestMapping("user/pwd_edit")
	public String doPwsEdit(){
		return "sys/pwd_edit";
	}
	@RequestMapping("product/product_list")
	public String doProduct1() {
		return "sys/product_list";
	}

	@RequestMapping("product/product_edit")
	public String doProduct2() {
		return "sys/product_edit";
	}	


}
