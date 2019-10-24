package good.job.pj.sys.service;
import java.util.List;

import good.job.pj.common.vo.CheckBox;
import good.job.pj.common.vo.Node;

import good.job.pj.common.vo.PageObject;
import good.job.pj.common.vo.SysRoleMenuVo;
import good.job.pj.sys.entity.SysRoleEntity;


public interface SysRoleService {
	
	
	PageObject findPages(String name,Integer pageCurrent);
	
	int savObject(SysRoleEntity entity,Integer[] menuIds);
	
	int deleteObject(Integer id);
	
	SysRoleMenuVo findObjectById(Integer id);
	
	int updateObject(SysRoleEntity entity,Integer[] menuIds);

	 List<Node> findZtreeMenuNodes();
	 
		List<CheckBox> findObjects();
}
