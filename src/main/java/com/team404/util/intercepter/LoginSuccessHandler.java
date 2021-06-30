package com.team404.util.intercepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.team404.command.UsersVO;

public class LoginSuccessHandler extends HandlerInterceptorAdapter{

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
		//매개변수 modelandview에는 컨트롤러에서 반환하는 mv객체가 들어있음.
		ModelMap mv = modelAndView.getModelMap();//모델엔뷰 객체에 들어있는 정보를 map형식으로 변환 
		
		UsersVO vo =(UsersVO)mv.get("login");
		System.out.println("실행!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		
		
		if(vo != null) {
			//세션에 저장
			HttpSession session = request.getSession();
			session.setAttribute("userVO", vo);
			
			response.sendRedirect(request.getContextPath());
		}
			//리다이렉트를 만나더라도 뷰네임은 일단 필요함
		modelAndView.setViewName("user/userLogin");
		
	}

}
