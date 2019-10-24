package good.job.pj.sys.service;

import good.job.pj.common.vo.PageObject;
import good.job.pj.sys.entity.SumCar;
import good.job.pj.sys.entity.SysOrderEntity;

public interface SysOrderService {
	
	int deleteObjects(Integer...ids);
	
	PageObject<SysOrderEntity> findPageObjects(
			String productname,
			 Integer pageCurrent);
	/*增加订单*/
	void doSaveOrder(SumCar entity);

}
