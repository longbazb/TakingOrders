package good.job.pj.sys.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import good.job.pj.common.vo.Node;
import good.job.pj.sys.entity.SysDeptEntity;

@Mapper
public interface SysDeptDao{
	List<Node> findZtreeDeptNodes();
	
	int findById();
	
	List<Map<String,Object>> findObjects();
	 /**
	  * 根据部门id统计子部门的个数
	  * @param id
	  * @return
	  */
	 int getChildCount(Integer id);
	 /**
	  * 根据id 删除部门
	  * @param id
	  * @return
	  */
	 int deleteObject(Integer id);
	 
	 //基于请求获取数据库对应的部门表中的所有部门信息(id,name,parentId)
	 List<Node> findZTreeDeptNodes();
	 
	 int insertObject(SysDeptEntity entity);
	 
	 int updateObject(SysDeptEntity entity);
}
