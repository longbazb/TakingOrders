package good.job.pj.sys.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
@Data
public class SysOrderEntity implements Serializable{
	private static final long serialVersionUID = 52530713455708602L;
	private Integer id;
	private String productname;
	private Integer number;
	private Double price;
	private String taste;
	private Double account;
	private Date orderTime;
}
