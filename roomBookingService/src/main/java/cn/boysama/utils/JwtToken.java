package cn.boysama.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.util.StringUtils;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
 
/**
 * APP登录Token的生成和解析
 * 
 */
public class JwtToken {
 
	/** token秘钥，请勿泄露，请勿随便修改 backups:JKKLJOoasdlfjtest */
	public static final String SECRET = "JKKLJOoasdlfjtest";
	/** token 过期时间: 1天 */
	public static final int calendarField = Calendar.DATE;
	public static final int calendarInterval = 7;
//	public static final int calendarField = Calendar.MINUTE;
//	public static final int calendarInterval = 1;
 
	
	private static final Logger log = LoggerFactory.getLogger(JwtToken.class);

	/**
	 * JWT生成Token.<br/>
	 * 
	 * JWT构成: header, payload, signature
	 * 
	 * @param user_id
	 *            登录成功后用户user_id, 参数user_id不可传空
	 */
	public static String createToken(Integer user_id) throws Exception {
		Date iatDate = new Date();
		// expire time
		Calendar nowTime = Calendar.getInstance();
		nowTime.add(calendarField, calendarInterval);
		Date expiresDate = nowTime.getTime();
 
		// header Map
		Map<String, Object> map = new HashMap<>();
		map.put("alg", "HS256");
		map.put("typ", "JWT");
 
		// build token
		// param backups {iss:Service, aud:APP}
		String token = JWT.create().withHeader(map) // header
				.withClaim("iss", "Service") // payload
				.withClaim("aud", "APP").withClaim("user_id", null == user_id ? null : user_id.toString())
				.withIssuedAt(iatDate) // sign time
				.withExpiresAt(expiresDate) // expire time
				.sign(Algorithm.HMAC256(SECRET)); // signature
 
		return token;
	}
 
	/**
	 * 解密Token
	 * 
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Claim> verifyToken(String token) {
		DecodedJWT jwt = null;
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
			jwt = verifier.verify(token);
		} catch (Exception e) {
			log.error("token校验异常,"+ e);
			// e.printStackTrace();
			// token 校验失败, 抛出Token验证非法异常
		}
		if(jwt == null){
			return null;
		}
		return jwt.getClaims();
	}
 
	/**
	 * 根据Token获取user_id
	 * 
	 * @param token
	 * @return user_id
	 */
	public static Integer getAppUID(String token) {
		Map<String, Claim> claims = verifyToken(token);
		if(claims == null){
			return null;
		}
		Claim user_id_claim = claims.get("user_id");
		if (null == user_id_claim || StringUtils.isEmpty(user_id_claim.asString())) {
			// token 校验失败, 抛出Token验证非法异常
			return null;
		}
		return Integer.valueOf(user_id_claim.asString());
	}
}
