package cn.boysama.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cn.boysama.dto.ResponseDto;
import cn.boysama.entity.Vpn;
import cn.boysama.repository.VpnRepository;
import cn.boysama.utils.CommonUtils;


@RestController
@RequestMapping(path="/vpn")
public class VpnController {
	
	@Autowired
	private VpnRepository vpnRepository;
	
	private static final Logger log = LoggerFactory.getLogger(VpnController.class);
	
	/**
	 * 查询列表
	 * @param date
	 * @return
	 */
	@RequestMapping(path="/getVpnList")
	@ResponseBody
	public ResponseDto getVpnList(@RequestBody JSONObject jsonObject, HttpServletRequest request){
		ResponseDto resp = new ResponseDto();
		HashMap<String,Object> errMsg = new HashMap<String,Object>();
		String date = jsonObject.getString("date");
		if(StringUtils.isEmpty(date)){
			CommonUtils.returnFalse(resp, errMsg, "日期上送为空");
			return resp;
		}
		List<Vpn> resultList= new ArrayList<Vpn>();
		resultList = vpnRepository.findByDate(date);
		log.info("查询vpn list成功");
//		if (resultList.size() == 0) {
//			log.info("查询vpn list为空");
//			CommonUtils.returnFalse(resp, errMsg, "用户名不存在，请联系Boysama创建用户~");
//			return resp;
//		}
		
		resp.setSuccess(true);
		errMsg.put("list", resultList);
		resp.setData(errMsg);
		return resp;
	}
	
	/**
	 * 提交vpn预定
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(path="/submitVpn")
	@ResponseBody
	public ResponseDto submitVpn(@RequestBody JSONObject jsonObject, HttpServletRequest request){
		ResponseDto resp = new ResponseDto();
		HashMap<String,Object> errMsg = new HashMap<String,Object>();
		String date = jsonObject.getString("date");
		String time = jsonObject.getString("time");
		String reserveInfo = jsonObject.getString("reserveInfo");
		String ip = jsonObject.getString("ip");
		if(StringUtils.isEmpty(date) || StringUtils.isEmpty(time) || StringUtils.isEmpty(reserveInfo) || StringUtils.isEmpty(ip)){
			CommonUtils.returnFalse(resp, errMsg, "上送项不完整，请重试");
			return resp;
		}
		//上送项是否valid
		
		List<Vpn> resultList= new ArrayList<Vpn>();
		resultList = vpnRepository.findByDateAndTimeAndIp(date, time, ip);
		
		if (resultList.size() == 0) {
			log.info("新增一个vpn预定");
			Vpn vpnTmp = new Vpn();
			vpnTmp.setDate(date);
			vpnTmp.setTime(time);
			vpnTmp.setReserveInfo(reserveInfo);
			vpnTmp.setIp(ip);
			try {
				vpnRepository.save(vpnTmp);
			} catch (Exception e) {
				log.error("vpn预定主交易数据库操作异常，" + e);
				CommonUtils.returnFalse(resp, errMsg, "数据库操作异常");
				return resp;
			}
			log.info("====新增vpn预定成功！新增信息：" + jsonObject.toString());
			resp.setSuccess(true);
			return resp;
		}
		
		log.info("更新一个vpn预定");
		int updateId = resultList.get(0).getId();
		vpnRepository.updateReserveInfoById(reserveInfo, updateId);
		log.info("====更新vpn预定成功！更新信息：" + jsonObject.toString());
		resp.setSuccess(true);
		return resp;
	}
	
}