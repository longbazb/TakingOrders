package good.job.pj.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain=true)
public class SysLogEntity implements Serializable {
	/**
	 *封装的SysLog实例
	 */
	private static final long serialVersionUID = 7067660802375485548L;
	private Integer id;
	private String username;
	private String operation;
	private String method;
	private String params;
	private Long time;
	private String  ip;
	private Date createdTime;
}
