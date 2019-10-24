package good.job.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import good.job.pj.sys.entity.SysLogEntity;

@Mapper
public interface SysLogDao {

	///用户行为写入日志
	int insertObject(SysLogEntity entity);
	
	int deleteObjects(@Param("ids")Integer...ids);
	
	int getRowCount(@Param("username")String username);

	List<SysLogEntity> findPageObjects(
			@Param("username")String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
}
