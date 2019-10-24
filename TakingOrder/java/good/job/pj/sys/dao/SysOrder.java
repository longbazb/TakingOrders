package good.job.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import good.job.pj.sys.entity.SumCar;
import good.job.pj.sys.entity.SysOrderEntity;

@Mapper
public interface SysOrder {
	//删除
int deleteObjects(Integer...ids);
	//查询总页数
	int getRowCount(@Param("productname")String productname);
	//找到页面数据
	List<SysOrderEntity> findPageObjects(
			@Param("productname")String productname,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	//删除
	//增加订单
	int insertObject(SumCar entity);
	
}
