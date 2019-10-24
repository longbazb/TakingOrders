package good.job.pj.sys.dao;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysRoleMenuDao {

	/**
	 * 根据角色id删除记录
	 * @param roleId
	 * @return
	 */
	int deleteObjectsByRoleId(Integer roleId);
	
	int findMenuIdsByRoleId(Integer id);
	
	int insertObjects(@Param("roleId")Integer roleId,
			@Param("menuIds")Integer[] menuIds);
	List<Integer> findMenuIdsByRoleIds(
			@Param("roleIds")Integer[] roleIds);
}
