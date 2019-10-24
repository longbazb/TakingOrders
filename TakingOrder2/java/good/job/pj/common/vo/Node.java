package good.job.pj.common.vo;

import java.io.Serializable;

import lombok.Data;
@Data
public class Node implements Serializable{

	private static final long serialVersionUID = 5298405103450837073L;
	private Integer id;
	private String name;
	private Integer parentId;
	
	@Override
	public String toString() {
		return "Node [id=" + id + ", name=" + name + ", parentId=" + parentId + "]";
	}
	
}


