package com.pachiraframework.scheduler.interceptor;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.curator.shaded.com.google.common.collect.Maps;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pachiraframework.scheduler.util.JWTUtil;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

/**
 * @author wangxuzheng
 *
 */
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter{
	private static final String AUTH_HEADER_KEY = "Authorization";
	private static final String TOKEN_PREFIX = "Bearer ";
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (CorsUtils.isPreFlightRequest(request)) {
			return true;
		}
		String authHeader = request.getHeader(AUTH_HEADER_KEY);
		if (authHeader == null || !authHeader.startsWith(TOKEN_PREFIX)) {
			 write(response, "非法的请求，请求头中没有包含["+AUTH_HEADER_KEY+"]或者["+AUTH_HEADER_KEY+"]的值没有以["+TOKEN_PREFIX+"]开头");
			 return false;
		}
		// The part after "Bearer "
		final String token = authHeader.substring(TOKEN_PREFIX.length()); 
		try {
            final Claims claims = Jwts.parser().setSigningKey(JWTUtil.SECRET_KEY)
                .parseClaimsJws(token).getBody();
            request.setAttribute("claims", claims);
        }
        catch (final SignatureException e) {
            write(response, "非法的token参数");
            return false;
        }
		return true;
	}

	private void write(HttpServletResponse response,String error) throws IOException {
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
	    Map<String, Object> map = Maps.newHashMap();
	    map.put("error", error);
	    map.put("success", false);
		Gson gson = new GsonBuilder().create();
		String json = gson.toJson(map);
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.getWriter().write(json);
	}
}
