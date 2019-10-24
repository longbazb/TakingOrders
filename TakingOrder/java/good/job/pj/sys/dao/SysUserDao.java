package good.job.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import good.job.pj.common.vo.SysUserDeptVo;
import good.job.pj.sys.entity.SysUserEntity;
@Mapper
public interface SysUserDao {
	List<SysUserDeptVo> findPageObjects(
			@Param("username") String username,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize);
	
	int getRowCount(@Param("username") String username);
	
	int validById(
			@Param("id")Integer id,
			@Param("valid")Integer valid,
			@Param("modifiedUser")String modifiedUser);
	
	SysUserDeptVo findObjectById(Integer id);
	
	/**
	 * 负责将用户信息写入到数据库
	 * @param entity
	 * @return
	 */
	int insertObject(SysUserEntity entity);
	
	int updateObject(SysUserEntity entity);
	
	@Delete("select count(*) from sys_users where deptId=#{id} ")
	int getUserCountByDeptId(Integer id);
	
	/**
	 * 基于用户id修改用户的密码
	 * @param password 新的密码
	 * @param salt 密码加密时使用的盐值
	 * @param id 用户id
	 * @return
	 */
	int updatePassword(
			@Param("password")String password,
			@Param("salt")String salt,
			@Param("id")Integer id);
	
	
	SysUserEntity findUserByUserName(String username);
}
