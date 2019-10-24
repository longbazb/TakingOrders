package good.job.pj.sys.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import good.job.pj.common.annotation.RequiredLog;
import good.job.pj.common.exception.ServiceException;
import good.job.pj.common.vo.PageObject;
import good.job.pj.sys.dao.SysOrder;
import good.job.pj.sys.entity.SumCar;
import good.job.pj.sys.entity.SysOrderEntity;
import good.job.pj.sys.service.SysOrderService;
@Service
@Transactional(isolation = Isolation.READ_COMMITTED
,rollbackFor = Throwable.class,
timeout = 30)
public class SysOrderServiceImp implements SysOrderService {

	@Autowired
	private SysOrder sysOrder;
	@Override
	@RequiredLog("删除订单")
	public int deleteObjects(Integer... ids) {
		if(ids==null||ids.length==0)
			throw new IllegalArgumentException("请先选中");
		int rows=sysOrder.deleteObjects(ids);
		if(rows==0)
			throw new ServiceException("订单可能已经不存在");
		return rows;
	}
	@Transactional(readOnly = true)
	@Override
	@RequiredLog("查询订单")
	public PageObject<SysOrderEntity> findPageObjects(String productname, Integer pageCurrent) {
		//1.验证当前页码值是否合法
				if(pageCurrent==null||pageCurrent<1)
				throw new IllegalArgumentException("当前页码值不正确");
				//2.基于用户名查询总记录数并进行校验
				int rowCount=sysOrder.getRowCount(productname);
				if(rowCount==0)
					System.out.println(0);
				//throw new ServiceException("记录不存在");
				//3.查询当前页记录
				Integer pageSize=3;
				Integer startIndex=(pageCurrent-1)*pageSize;
				List<SysOrderEntity> records=
				sysOrder.findPageObjects(productname,
						startIndex, pageSize);
				//4.封装查询结果并返回
				return new PageObject<>(
				rowCount, records,pageCurrent, pageSize);
	}
	@Override
	public void doSaveOrder(SumCar entity) {
		if (entity==null) 
			throw new ServiceException("订单异常，错误");
		int rows = sysOrder.insertObject(entity);
		if (rows==0) 
			throw new ServiceException("订单插入异常，错误");
	}

}
