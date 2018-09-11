package com.imooc.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 转发到不同的html页面
 * 
 * @author 优雅而痞（Xiexiang)
 *
 */
@Controller
@RequestMapping(value = "/shopadmin", method = { RequestMethod.GET })
public class ShopAdminController {
	@RequestMapping(value = "shopOperation")
	public String shopOperation() {
		// shop/shopoperation.html
		// 转发到店铺注册/编辑也页面
		return "shop/shopoperation";

	}

	@RequestMapping(value = "shoplists")
	public String shopList() {
		// 转发到店铺列表页面
		return "shop/shoplist";

	}

	@RequestMapping(value = "shopmanagement")
	public String shopManagement() {
		// 转发至店铺管理页面
		return "shop/shopmanagement";

	}

	@RequestMapping(value = "productcategorymanagement")
	public String productCategoryManagement() {
		// 转发至商品类别管理页面
		return "shop/productcategorymanagement";

	}

	@RequestMapping(value = "productoperation")
	public String productOperation() {
		// 转发至商品添加/编辑页面
		return "shop/productoperation";

	}

	@RequestMapping(value = "productmanagement")
	public String productManagement() {
		// 转发至商品添加/编辑页面
		return "shop/productmanagement";

	}
	@RequestMapping(value = "infomanagement")
	public String info() {

		return "shop/info";

	}
	@RequestMapping(value = "login")
	public String login() {
		return "shop/login";

	}

}
