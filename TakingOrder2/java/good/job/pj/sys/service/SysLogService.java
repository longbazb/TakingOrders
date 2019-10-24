package good.job.pj.sys.service;

import good.job.pj.common.vo.PageObject;
import good.job.pj.sys.entity.SysLogEntity;

public interface SysLogService {

	  /**
     * 通过此方法实现分页查询操作
     * @param name 基于条件查询时的参数名
     * @param pageCurrent 当前的页码值
     * @return 当前页记录+分页信息
     */
	 PageObject<SysLogEntity> findPageObjects(
			 String username,
			 Integer pageCurrent);
	 int deleteObjects(Integer...ids);
	 //保存用户行为日志
	 int saveObject(SysLogEntity entity);
}
