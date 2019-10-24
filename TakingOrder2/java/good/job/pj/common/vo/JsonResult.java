package good.job.pj.common.vo;

import java.io.Serializable;

public class JsonResult implements Serializable{
	private static final long serialVersionUID = -774519908883109829L;
	//状态码
	private int state=1;
	//状态码对应的信息
	private String message="ok";
	//正常信息
	private Object data;
	public JsonResult() {
		super();
	}
	public JsonResult(String message) {
		this.message=message;
	}
	public JsonResult(Object data) {
		this.data=data;
	}
	//封装异常信息
	public JsonResult(Throwable e) {
		this.state=0;
		this.message=e.getMessage();
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
