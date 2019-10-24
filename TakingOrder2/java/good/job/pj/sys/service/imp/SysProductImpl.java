package good.job.pj.sys.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import good.job.pj.common.exception.ServiceException;
import good.job.pj.common.vo.PageObject;
import good.job.pj.sys.dao.SysProductDao;
import good.job.pj.sys.entity.Products;
import good.job.pj.sys.service.SysProdctService;


@Service
public class SysProductImpl implements SysProdctService{

	@Autowired
	private SysProductDao sysProductDao;
	/**************显示页面**************************/	
	@Override
	public PageObject<Products> findPageObjects(String productname, Integer pageCurrent) {
		//1.数据合法性验证
		if(pageCurrent==null||pageCurrent<=0)
			throw new ServiceException("参数不合法");
		//2.依据条件获取总记录数
		int rowCount=sysProductDao.getRowCount(productname);
		if(rowCount==0)
			throw new ServiceException("记录不存在");
		//3.计算startIndex的值
		int pageSize=3;
		int startIndex=(pageCurrent-1)*pageSize;
		//4.依据条件获取当前页数据
		List<Products> records = 
				sysProductDao.findPageObjects
				(productname, startIndex, pageSize);
		//5.封装数据
		PageObject<Products> pageObject = 
				new PageObject();
		pageObject.setPageCurrent(pageCurrent);
		pageObject.setPageSize(pageSize);
		pageObject.setRowCount(rowCount);
		pageObject.setPageCount((rowCount-1)/pageSize+1);
		pageObject.setRecords(records);
		return pageObject;
	}
	/*****************保存数据****************************************/
	@Override
	public void saveObject(Products entity) {
		//验证
		if (entity==null) 
			throw new ServiceException("保存对象不能为空");
		sysProductDao.insertObject(entity);

	}
	/****************修改数据   查找单个数据***************/
	@Override
	public Products findObjectById(Integer id) {
		if (id==null||id<=0)
			throw new ServiceException("数据不合法：ids"+id);
		Products entity = sysProductDao.findObjectById(id);
		if (entity==null) 
			throw new ServiceException("此商品可能已下架");
		return entity;
	}
	@Override
	public void deleteObjects(Integer... ids) {
		//参数判断可行性
		if (ids==null||ids.length==0) 
			throw new IllegalArgumentException("请选择一个");
		//执行删除
		int rows;
		try {
			rows=sysProductDao.deleteObjects(ids);
		} catch (Throwable e) {
			e.printStackTrace();
			throw new ServiceException("无法正常删除，请重试");
		}
		if (rows==0) {
			throw new ServiceException("记录可能不存在");
		}
		
	}
	@Override
	public void updateObjects(Products entity) {
		if (entity==null) 
			throw new ServiceException("修改对象不能为空");
		//更新
		int rows=sysProductDao.updateObject(entity);
		if (rows==0) {
			throw new ServiceException("该记录可能已经不存在了");
		}	
	}

}
