package com.team404.util.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.team404.command.UsersVO;

public class UserAuthHandler  extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//현재 세션을 얻는다
		HttpSession session = request.getSession();
		
		//로그인될ㄸ ㅐ 생성
		UsersVO userVO =  (UsersVO)session.getAttribute("userVO");
		if(userVO == null)
		{
			response.sendRedirect(request.getContextPath()+"/user/userLogin");
			return false;
		}
		else {
			return true;
		}
	}
	

}
