package good.job.pj.sys.service;

import good.job.pj.common.vo.PageObject;
import good.job.pj.sys.entity.Products;

public interface SysProdctService {
	/*查找数据*/
	PageObject<Products> findPageObjects(
			String productname,
			Integer pageCurrent);
	/*增加数据*/
	void saveObject(Products entity);
	/*修改数据---跟据id找到单个数据*/
	Products findObjectById(Integer id);
	
	void updateObjects(Products entity);
	/*删除数据*/
	void  deleteObjects(Integer...ids);

}
