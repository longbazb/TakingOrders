package good.job.pj.common.vo;

import java.io.Serializable;
import java.util.Date;

import good.job.pj.sys.entity.SysDeptEntity;
import lombok.Data;
@Data
public class SysUserDeptVo implements Serializable{

	private static final long serialVersionUID = -3427038616130183864L;
	private Integer id;
	private String username;
	private String password;//md5
	private String salt;
	private String email;
	private String mobile;
	private Integer valid=1;
	private SysDeptEntity sysDeptEntity; //private Integer deptId;
	private Date createdTime;
	private Date modifiedTime;
	private String createdUser;
	private String modifiedUser;

}
