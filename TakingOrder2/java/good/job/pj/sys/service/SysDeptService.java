package good.job.pj.sys.service;

import java.util.List;
import java.util.Map;

import good.job.pj.common.vo.Node;
import good.job.pj.sys.entity.SysDeptEntity;

public interface SysDeptService {
	
	List<Node> findZtreeDeptNodes();
	
	List<Map<String,Object>> findObjects();
	int deleteObject(Integer id);
	int saveObject(SysDeptEntity entity);
	int updateObject(SysDeptEntity entity);
}
