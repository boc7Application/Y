package cn.boysama.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


//import cn.boysama.dto.UserMap;
import cn.boysama.utils.JwtToken;


@Component
@ServletComponentScan
//@WebFilter(urlPatterns = {"/user/getUserInfo","/user/logout","/booking/*","/regularContest/*"},filterName = "loginFilter")
@WebFilter(urlPatterns = "/*",filterName = "loginFilter")
public class LoginFilter implements Filter {

	private static final Logger logger = LoggerFactory.getLogger(LoginFilter.class);
	
	//不用进行登录状态检查的url列表
	private static final List<String> urlsNoNeedToBeFiltered = new ArrayList<String>();
	
	public LoginFilter() {
		urlsNoNeedToBeFiltered.add("/user/login");
		urlsNoNeedToBeFiltered.add("/user/validToken");
		urlsNoNeedToBeFiltered.add("/vpn/getVpnList");
		urlsNoNeedToBeFiltered.add("/vpn/submitVpn");
//		urlsNoNeedToBeFiltered.add("/advices/uploadFile");
	}
	
//	private UserMap userMap = new UserMap();
	
	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		//过滤/user/login，其他需要检查token
		if (urlsNoNeedToBeFiltered.contains(req.getServletPath())) {
//			logger.info("loginFilter matches {}, go on.",req.getServletPath());
			chain.doFilter(req, res);
			return;
		}
		//其他请求
		String token = req.getHeader("Authorization");
		if (StringUtils.isEmpty(token)) {
			logger.info("token为空，未登录");
			res.setStatus(403);
			return;
		}
		Integer userId = JwtToken.getAppUID(token);
		if(userId == null){
			logger.info("token校验失败，未登录");
			res.setStatus(403);
		}else{
			logger.info("token校验成功，用户ID为"+userId+"已登录");
			chain.doFilter(req, res);
		}
		/* 
		//跨域时允许本机请求通过 生产
//		if (!StringUtils.isEmpty(req.getHeader("origin"))) {
//			String origin = req.getHeader("origin").split("//")[1].toString();
//			if("boysama.cn".equals(origin) || "haidyy.com".equals(origin)){
//				res.setHeader("Access-Control-Allow-Origin", "*");//若要发送cookie，则不能用*
//				res.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");//很重要，不能用*
//			    res.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
//			    res.setHeader("Access-Control-Max-Age", "86400");
//			}
//			if ("OPTIONS".equals(req.getMethod())) {
////				logger.info("options by"+req.getRequestURL());
//				res.setStatus(200);
//				return;
//			}
//		} */
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {}

}
