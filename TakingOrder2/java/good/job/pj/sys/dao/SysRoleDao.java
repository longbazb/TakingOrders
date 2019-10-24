package good.job.pj.sys.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import good.job.pj.common.vo.CheckBox;
import good.job.pj.common.vo.Node;
import good.job.pj.common.vo.SysRoleMenuVo;
import good.job.pj.sys.entity.SysRoleEntity;

@Mapper
public interface SysRoleDao {
/**
 * 分页显示数据
 * @param name
 * @param startIndex 开始页面 默认为1
 * @param pageSize  每个页面显示的条数
 * @return list集合封装的数据
 */
	List<SysRoleEntity> findPages(@Param("name")String name,
			@Param("startIndex")Integer startIndex,
			@Param("pageSize")Integer pageSize );
	int getRows(@Param("name")String name);
	/**
	 * 插入数据
	 * @param entity
	 * @return
	 */
	int insertObject(SysRoleEntity entity);

	/**
	 * 根据角色id删除记录
	 * @param role
	 * @return int 
	 */
	int deleteObject (Integer id);

	@Select("select id,name,parentId from sys_menus")
	List<Node> findZtreeMenuNodes();
	/***
	 * 将数据更新到数据库
	 * @param entity
	 * @return
	 */
	int updateObject(SysRoleEntity entity);
	/**
	 * 基于角色id查询角色信息的方法
	 * @param id
	 * @return
	 */
	SysRoleMenuVo findObjectById(Integer id);
	
	List<CheckBox> findObjects();
}
