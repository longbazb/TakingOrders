package good.job.pj.sys.service;

import java.util.Map;

import good.job.pj.common.vo.PageObject;
import good.job.pj.common.vo.SysUserDeptVo;
import good.job.pj.sys.entity.SysUserEntity;

public interface SysUserService {
	PageObject<SysUserDeptVo> findPageObjects(
			String username,
			Integer pageCurrent);
	
	int validById(Integer id,
			Integer valid,
			String modifiedUser);
	
	Map<String, Object> findObjectById(Integer userId);
	
	int saveObject(SysUserEntity entity, Integer[] roleIds);
	
	int updateObject(SysUserEntity entity,Integer[] roleIds);
	
	int updatePassword(String password,
	           String newPassword,
	           String cfgPassword);
}
