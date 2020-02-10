package cn.boysama.controller;

import java.util.HashMap;

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
import cn.boysama.entity.User;
import cn.boysama.repository.UserRepository;
import cn.boysama.utils.CommonUtils;
import cn.boysama.utils.JwtToken;


@RestController
@RequestMapping(path="/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);

//	private UserMap userMap = new UserMap();
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	@RequestMapping(path="/login")
	@ResponseBody
	public ResponseDto login(@RequestBody JSONObject jsonObject, HttpServletRequest request){
		ResponseDto resp = new ResponseDto();
		HashMap<String,Object> errMsg = new HashMap<String,Object>();
		String username = jsonObject.getString("username");
		String password = jsonObject.getString("password");
		if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
			CommonUtils.returnFalse(resp, errMsg, "用户名或密码上送为空");
			return resp;
		}
		log.info("工号为"+username+"的用户登录");
		User result = userRepository.findByUserNo(username.trim());
		
		if (result == null) {
			log.info(username + "用户不存在");
			CommonUtils.returnFalse(resp, errMsg, "用户名不存在，请联系Boysama创建用户~");
			return resp;
		}
		if (result.getUserPhoneNum().toString().equals(password)) {
			log.info("用户"+ username +"登录成功！");
			//放入userMap
//			userMap.insertUser(result); 
			//将用户信息放入session
//			HttpSession session = request.getSession();
//			session.setAttribute("userId",result);
			resp.setSuccess(true);
			try {
				String token = JwtToken.createToken(result.getUserId());
				errMsg.put("token", token);
			} catch (Exception e) {
				log.error("token生成异常，" + e);
			}
			resp.setData(errMsg);
			return resp;
		}
		log.info("用户"+ username +"登录失败！");
		CommonUtils.returnFalse(resp, errMsg, "用户名或密码错误");
		return resp;
	}
	
	/**
	 * 用户登出 假注销
	 * @param request
	 * @return
	 */
	@RequestMapping(path="/logout")
	@ResponseBody
	public ResponseDto logout(HttpServletRequest request) {
		ResponseDto resp = new ResponseDto();
//		HttpSession session = request.getSession();
//		User userInfoSession = (User) session.getAttribute("userId");
		Integer userId = JwtToken.getAppUID(request.getHeader("Authorization"));
		if(userId == null){
			resp.setSuccess(false);
			return resp;
		}
//		User userInfo = userMap.getUserInfo(userId);
//		if (userInfo == null) {
//			log.info("userMap中无该用户信息");
//			resp.setSuccess(true);
//			return resp;
//		}
//		log.info("工号为"+userInfo.getUserNo()+"的用户退出");
//		userMap.delUser(userInfo);
		log.info("userId为" + userId + "的用户退出");
		resp.setSuccess(true);
		return resp;
	}
	
	/**
	 * 根据session中的userId查询用户信息
	 * @param request
	 * @return
	 */
	@RequestMapping(path="/getUserInfo")//, method=RequestMethod.POST
	@ResponseBody
	public JSONObject getUserInfo(HttpServletRequest request){
		JSONObject resp = new JSONObject();
//		HttpSession session = request.getSession();
//		User userInfoSession = (User) session.getAttribute("userId");
		Integer userId = JwtToken.getAppUID(request.getHeader("Authorization"));
		if(userId == null){
			log.info("查询token中的用户信息为空，一般为token过期或服务器重启了");
			resp.put("success", false);
			resp.put("msg", "用户信息为空，请重新登录");
			return resp;
		}
		User userInfo = userRepository.findByUserId(userId);
		if (userInfo == null) {
			log.info("查询数据库用户信息为空");
			resp.put("success", false);
			resp.put("msg", "用户信息为空，请重新登录");
			return resp;
		}
		userInfo.setUserPhoneNum(phoneNumHide(userInfo.getUserPhoneNum()));
		resp.put("success", true);
		resp.put("data", userInfo);
		return resp;
	}
	
	/**
	 * 校验token是否有效
	 * @param request
	 * @return
	 */
	@RequestMapping(path="/validToken")//, method=RequestMethod.POST
	@ResponseBody
	public JSONObject validToken(HttpServletRequest request){
		JSONObject resp = new JSONObject();
		Integer userId = JwtToken.getAppUID(request.getHeader("Authorization"));
		if(userId == null){
			log.info("查询token中的用户信息为空，token无效");
			resp.put("success", false);
			resp.put("msg", "token无效，请重新登录");
			return resp;
		}
		User userInfo = userRepository.findByUserId(userId);
		if (userInfo == null) {
			log.info("查询数据库用户信息为空");
			resp.put("success", false);
			resp.put("msg", "用户信息为空，请重新登录");
			return resp;
		}
		resp.put("success", true);
		resp.put("data", "");
		return resp;
	}
	
	private String phoneNumHide(String phoneNum) {
		if (StringUtils.isEmpty(phoneNum)) {
			return "";
		}
		String phoneStr = phoneNum.substring(0, 3) + "****" + phoneNum.substring(phoneNum.length()-4);
		return phoneStr;
	}
}