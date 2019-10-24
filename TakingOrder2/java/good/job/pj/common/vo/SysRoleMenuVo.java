package good.job.pj.common.vo;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/*
 * 封装角色对应菜单信息
 */
@Data
public class SysRoleMenuVo implements Serializable {

	private static final long serialVersionUID = 4258002876842201827L;
	/**角色id*/
	private Integer id;
	/**角色名称*/
	private String name;
/**角色备注*/
	private String note;
	/**角色对应的菜单id*/
	private List<Integer> menuIds;
}
