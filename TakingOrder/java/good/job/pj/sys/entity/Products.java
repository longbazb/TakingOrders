package good.job.pj.sys.entity;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class Products implements Serializable{
	private static final long serialVersionUID = -8816995463304139129L;
	
	private Integer id;
	private String productname;
	private Double price;
	private String url;
}
