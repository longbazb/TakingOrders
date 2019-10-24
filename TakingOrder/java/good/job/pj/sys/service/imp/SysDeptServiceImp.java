package good.job.pj.sys.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import good.job.pj.common.annotation.RequiredLog;
import good.job.pj.common.exception.ServiceException;
import good.job.pj.common.vo.Node;
import good.job.pj.sys.dao.SysDeptDao;
import good.job.pj.sys.dao.SysUserDao;
import good.job.pj.sys.entity.SysDeptEntity;
import good.job.pj.sys.service.SysDeptService;
import io.micrometer.core.instrument.util.StringUtils;
@Service
public class SysDeptServiceImp implements SysDeptService {

	@Autowired
	private SysDeptDao sysDeptDao;
	@Autowired
	private SysUserDao sysUserDao;
	@Override
	public List<Node> findZtreeDeptNodes() {
		// TODO Auto-generated method stub
		return sysDeptDao.findZtreeDeptNodes();
	}
	@Transactional(readOnly = true)
	@RequiredLog("查询部门")
	@Override
	public List<Map<String, Object>> findObjects() {
		
		return sysDeptDao.findObjects();
	}

	@RequiredLog("删除部门")
	@Override
	public int deleteObject(Integer id) {
		if (id==null||id<0) {
			throw new ServiceException("数据不合法,id="+id);
		}
		int childCount = sysDeptDao.getChildCount(id);
		if (childCount>0) {
			throw new ServiceException("次元素有子元素，不允许删除");
		}
		int userCount = sysUserDao.getUserCountByDeptId(id);
		if (userCount>0) {
			throw new ServiceException("此部门有员工,还不能进行删除");
		}
		int rows=sysDeptDao.deleteObject(id);
		if(rows==0)
		throw new ServiceException("此信息可能已经不存在");
		return rows;
		
	}
	@RequiredLog("新建部门")
	@Override
	public int saveObject(SysDeptEntity entity) {
		//1.合法验证
				if(entity==null)
				throw new ServiceException("保存对象不能为空");
				if(StringUtils.isEmpty(entity.getName()))
				throw new ServiceException("部门名不能为空");
				int rows;
				//2.保存数据
				try{
				rows=sysDeptDao.insertObject(entity);
				}catch(Exception e){
				e.printStackTrace();
				throw new ServiceException("保存失败");
				}
				//3.返回数据
				return rows;
	
	}
	@RequiredLog("更新部门")
	@Override
	public int updateObject(SysDeptEntity entity) {
		//1.合法验证
				if(entity==null)
				throw new ServiceException("保存对象不能为空");
				if(StringUtils.isEmpty(entity.getName()))
				throw new ServiceException("部门名不能为空");
				
				//2.更新数据
				int rows=sysDeptDao.updateObject(entity);
				if(rows==0)
				throw new ServiceException("记录可能已经不存在");
				//3.返回数据
				return rows;
	}
}
