package io.demo.crud.vo.response;

//import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@ToString
public class Response {
	/**
	 * 状态码：0->成功
	 * 				!0 -> 失败
	 */
	private Integer Code = 0;
	
	/**
	 * 错误消息(如果成功则为success)
	 */
	private String message = "sucess";
	/**
	 * 成功时返回的数据(失败为null)
	 */
	private Object data;
	
	
	
	/**
	 * 正常返回
	 * @param data: 返回的数据
	 * @return {@link Response}
	 */
	public static Response success(Object data) {
		Response res = new Response();
		res.setData(data);
		return res;
	}

	/**
	 * 有异常的返回
	 * @param code: 错误码
	 * @param msg: 错误消息
	 * @return {@link Response}
	 */
	public static Response fail(Integer code, String msg) {
		Response res = new Response();
		res.setCode(0 == code ? -9999 : code);
		res.setMessage(msg);
//		res.setData(data);
		return res;
	}
	
	/**
	 * 可以有错误具体信息
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 */
	public static Response fail(Integer code, String msg, Object data) {
		Response res = Response.fail(code, msg);
		res.setData(data);
		return res;
	}
}
