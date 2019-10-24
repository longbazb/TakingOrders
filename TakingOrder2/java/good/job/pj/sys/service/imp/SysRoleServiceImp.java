package good.job.pj.sys.service.imp;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import good.job.pj.common.annotation.RequiredLog;
import good.job.pj.common.exception.ServiceException;
import good.job.pj.common.vo.CheckBox;
import good.job.pj.common.vo.Node;
import good.job.pj.common.vo.PageObject;
import good.job.pj.common.vo.SysRoleMenuVo;
import good.job.pj.sys.dao.SysRoleDao;
import good.job.pj.sys.dao.SysRoleMenuDao;
import good.job.pj.sys.dao.SysUserRoleDao;
import good.job.pj.sys.entity.SysRoleEntity;
import good.job.pj.sys.service.SysRoleService;
import io.micrometer.core.instrument.util.StringUtils;
@Service
public class SysRoleServiceImp implements SysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;
	@Transactional(readOnly = true)
	@RequiredLog("查询角色")
	public PageObject findPages(String name, Integer pageCurrent) {
		// TODO Auto-generated method stub
		//1.判断参数的正确性
		if(pageCurrent==null || pageCurrent<1)
			throw new ServiceException("当前页码错误");
		//2.基于条件查询记录的总记录数
		int rowCount = sysRoleDao.getRows(name);
		if(rowCount<=0) 
			throw new ServiceException("没有信息显示");
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		List<SysRoleEntity> records=sysRoleDao.findPages(name, startIndex, pageSize);
		return new PageObject<>(rowCount, records,pageCurrent, pageSize);
	}
	@RequiredLog("新建角色")
	@Override
	public int savObject(SysRoleEntity entity,Integer[] menuIds) {
		// TODO Auto-generated method stub
		if(entity==null)
			throw new ServiceException("保存数据不能为null");
		if(StringUtils.isEmpty(entity.getName()))
			throw new ServiceException("角色名字不能为空");
		if(menuIds==null||menuIds.length==0)
			throw new ServiceException("必须为角色赋予权限");
		int rows = sysRoleDao.insertObject(entity);
		sysRoleMenuDao.insertObjects(entity.getId(), menuIds);
		return rows;
	}
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;
	@Autowired
	public SysUserRoleDao sysUserRoleDao;
	@RequiredLog("删除角色")
	@Override
	public int deleteObject(Integer id) {
		// TODO Auto-generated method stub
		//System.out.println("id="+id);
		if(id==null||id<1)
			throw new ServiceException("id的值不正确"+id);
		sysRoleMenuDao.deleteObjectsByRoleId(id);
		sysUserRoleDao.deleteObjectById(id);
		int rows = sysRoleDao.deleteObject(id);
		if(rows==0)
			throw new ServiceException("记录可能不存在");
		return rows;
	}
	@RequiredLog("更新角色")
	@Override
	public int updateObject(SysRoleEntity entity, Integer[] menuIds) {
		// TODO Auto-generated method stub
		//1.合法性验证
    	if(entity==null)
    throw new ServiceException("更新的对象不能为空");
    	if(entity.getId()==null)
    	throw new ServiceException("id的值不能为空");
    	
    	if(StringUtils.isEmpty(entity.getName()))
    	throw new ServiceException("角色名不能为空");
    	if(menuIds==null||menuIds.length==0)
    	throw new ServiceException("必须为角色指定一个权限");
    	
    	//2.更新数据
    	int rows=sysRoleDao.updateObject(entity);
    	if(rows==0)
    throw new ServiceException("对象可能已经不存在");
    	sysRoleMenuDao.deleteObjectsByRoleId(entity.getId());
    	sysRoleMenuDao.insertObjects(entity.getId(),menuIds);
 
    	//3.返回结果
    	return rows;
	}
	@RequiredLog("查询角色")
	@Override
	public SysRoleMenuVo findObjectById(Integer id) {
		// TODO Auto-generated method stub
		if(id==null||id<=0)
			throw new ServiceException("id值不合法");
		SysRoleMenuVo result = sysRoleDao.findObjectById(id);
		if(result==null)
			throw new ServiceException("此记录不存在");
		
		return result;
	}

	@Override
	public List<Node> findZtreeMenuNodes() {
		// TODO Auto-generated method stub
		List<Node> list =sysRoleDao.findZtreeMenuNodes();
		return  list;
	}

	@Override
	public List<CheckBox> findObjects() {
		// TODO Auto-generated method stub
		return sysRoleDao.findObjects();
	}


}
