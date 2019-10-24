package good.job.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysUserRoleDao {

	/**
	 * 根据角色id删除记录
	 * @param roleId
	 * @return
	 */
	@Delete("delete from sys_user_roles where role_id=#{roleId}")
	int deleteObjectById(@Param("roleId")Integer roleId);

	List<Integer> findRoleIdsByUserId(Integer id);

	/**
	 * 负责将用户与角色的关系数据写入到数据库
	 * @param userId 用户id
	 * @param roleIds 多个角色id
	 * @return
	 */
	int insertObjects(
			@Param("userId")Integer userId,
			@Param("roleIds")Integer[]roleIds);

	int deleteObjectsByUserId(Integer userId);
}
