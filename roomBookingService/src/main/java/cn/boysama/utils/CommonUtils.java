package cn.boysama.utils;

import java.util.HashMap;

import cn.boysama.dto.ResponseDto;

public class CommonUtils {
	/**
	 * 返回失败
	 * @param resp
	 * @param errMsg
	 * @param errInfo
	 * @return
	 */
	public static ResponseDto returnFalse(ResponseDto resp, HashMap<String,Object> errMsg, String errInfo) {
		errMsg.put("msg", errInfo);
		resp.setSuccess(false);
		resp.setData(errMsg);
		return resp;
	}

}
