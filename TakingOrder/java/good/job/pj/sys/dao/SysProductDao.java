package good.job.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import good.job.pj.sys.entity.Products;


@Mapper
public interface SysProductDao {
	/*页面呈现*/
	List<Products> findPageObjects(
			@Param("productname") String productname,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	/*找到总行数*/
	int getRowCount(@Param("productname") String productname);
	
	/*保存增加的数据*/
	void insertObject(Products entity);
	
	/*修改数据  ----根据id找到单个数据        等待后续改动*/
	Products findObjectById(Integer id);
	/**/
	int updateObject(Products entity);
	
	/*删除商品*/
	/*执行删除*/
	int deleteObjects(Integer...id);
	
	
}
