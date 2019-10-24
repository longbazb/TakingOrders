package good.job.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysMenuDao {
	List<String> findPermissions(
			@Param("menuIds")
			Integer[] menuIds);
}
