package good.job.pj.sys.service.imp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import good.job.pj.common.exception.ServiceException;
import good.job.pj.common.vo.PageObject;
import good.job.pj.sys.dao.SysLogDao;
import good.job.pj.sys.entity.SysLogEntity;
import good.job.pj.sys.service.SysLogService;
@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
@Async("asyncPoolExecutor")
public class SysLogServiceImp implements SysLogService {
	@Autowired
	private SysLogDao sysLogDao;
	
	@Override
	public PageObject<SysLogEntity> findPageObjects(String username, Integer pageCurrent) {
		//1.验证当前页码值是否合法
		if(pageCurrent==null||pageCurrent<1)
		throw new IllegalArgumentException("当前页码值不正确");
		//2.基于用户名查询总记录数并进行校验
		int rowCount=sysLogDao.getRowCount(username);
		if(rowCount==0)
		throw new ServiceException("记录不存在");
		//3.查询当前页记录
		Integer pageSize=3;
		Integer startIndex=(pageCurrent-1)*pageSize;
		List<SysLogEntity> records=
		sysLogDao.findPageObjects(username,
				startIndex, pageSize);
		//4.封装查询结果并返回
		return new PageObject<>(rowCount, records,pageCurrent, pageSize);
	}
	//删除
	@Override
	public int deleteObjects(Integer... ids) {
		//1.参数校验
		if(ids==null||ids.length==0)
		throw new IllegalArgumentException("必须先选中"+ids.toString());
		//2.执行删除
		int rows=sysLogDao.deleteObjects(ids);
		//3.判定结果
		if(rows==0)
		throw new ServiceException("记录可能已经不存在");
		return rows;
	}
	//保存用户行为日志
	@Async("asyncPoolExecutor")
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public int saveObject(SysLogEntity entity) {
		// TODO Auto-generated method stub
		   System.out.println("SysLogServiceImpl.save:"+Thread.currentThread().getName());
			  int rows=sysLogDao.insertObject(entity);
		//	  try{Thread.sleep(5000);}catch(Exception e) {}
			  return rows;
	}

}
