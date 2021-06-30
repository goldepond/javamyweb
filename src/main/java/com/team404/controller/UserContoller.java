package com.team404.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.team404.command.UsersVO;
import com.team404.reply.service.UserService;

@Controller
@RequestMapping("/user")
public class UserContoller {

	@Autowired
	@Qualifier("userService")
	private UserService userService;

	// 가입화면
	@RequestMapping("/userJoin")
	public String userJoin() {
		return "user/userJoin";
	}

	@RequestMapping("/userLogin")
	public String userLogin() {
		return "user/userLogin";
	}

	@RequestMapping("/userMypage")
	public String userMypage(HttpSession session, Model model) {
		
		UsersVO vo = (UsersVO)session.getAttribute("userVO");
		String userID = vo.getUserID();
		
		UsersVO userInfo = userService.getinfo(userID);
		System.out.println(userInfo);
		model.addAttribute("userInfo",userInfo);
			return "user/userMypage";			
		
	}

	// ResponseBody = 요청 처리한곳으로 다시 되돌려줌
	@ResponseBody
	@PostMapping(value = "/idCheck", produces = "application/json")
	public int idCheck(@RequestBody UsersVO vo) {
		int result = userService.idCheck(vo);
//		System.out.println(result + "  가나다라마바사");
		return result;
	}

	@RequestMapping(value = "/joinForm", method = RequestMethod.POST)
	public String joinForm(UsersVO vo, RedirectAttributes ra) {
		int result = userService.join(vo);
		if (result == 1) {
			ra.addFlashAttribute("msg", "가입을 축하합니다");
		} else {
			ra.addFlashAttribute("msg", "가입 실패");
		}

		return "redirect:/user/userLogin";

	}

//	@RequestMapping(value = "/loginForm", method = RequestMethod.POST)
//	public String loginForm(UsersVO vo, HttpSession session, Model model) {
//		UsersVO vo2 = userService.login(vo);
//		System.out.println(vo2);
//
//		if (vo2 != null) {
//			session.setAttribute("userVO", vo2);
//			// 로그인 성공
//			return "redirect:/";
//		} else {
//			model.addAttribute("msg", "아이디 비번을 확인하세요");
//			return "user/userLogin";
//
//		}
//	}
	
	//post핸들러를 이용한 로그인 요청 
	@RequestMapping(value = "/loginForm", method = RequestMethod.POST)
	public ModelAndView loginForm(UsersVO vo) {
		UsersVO vo2 = userService.login(vo);
		ModelAndView mv = new ModelAndView();
		mv.addObject("login", vo2 );
		System.out.println("로그인 성공!!!!!!!!!!!!!!");
		if (vo2 != null) {
			mv.addObject("login",vo2);
			// 로그인 성공
		} else {
			mv.addObject("msg", "아이디 비번을 확인하세요");
		}
		
		return mv;
	}
	
	
	
	
	
	
	
	
	@RequestMapping("/userLogout")
	public String userLogOut(HttpSession session) {

		session.invalidate();
		return "redirect:/";
	}
}
