package com.team404.util.intercepter;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.team404.command.UsersVO;

//implements HandlerInterceptor도됨
public class BoardAuthHandler  extends HandlerInterceptorAdapter {

		//게시글 변경 수정 삭제가 일어날떄 해당 사용자만 처리하도록 싦행시키는 핸들러
	//화면에서 변경 수정 삭제가 일어날떄 userID가 넘어오도록 처리
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//화면에 넘어오는 작성자명
		 String writer = request.getParameter("writer");
		 
		 
			HttpSession session = request.getSession();
			UsersVO userVO =  (UsersVO)session.getAttribute("userVO");
//		 System.out.println(userVO);
//		 System.out.println(writer);
		 
		 if(userVO != null)
		 {
			 if(writer != null)
			 {
				 if(userVO.getUserID().equals(writer))
				 {
					 return true;
				 }
			 }
		 }
		 response.setContentType("text/html; charset=utf-8");
		 PrintWriter out = response.getWriter();
		 
		 out.println("<script>");
		 out.println("alert('권한이 없습니다');");
		 out.println("history.go(-1);");
		 out.println("</script>");
		 
		 return false;
	}
	
	

}
