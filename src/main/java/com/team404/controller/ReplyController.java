package com.team404.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team404.command.FreeReplyVO;
import com.team404.reply.service.ReplyService;
import com.team404.util.Criteria;

@RestController
@RequestMapping("/reply")
public class ReplyController {
	
	@Autowired
	@Qualifier("replyService")
	private ReplyService replyService;
	
	
	@PostMapping(value = "/repltRegist", produces = "application/json")
	public int replyRegist(@RequestBody FreeReplyVO vo) 
	{
		return replyService.regist(vo);
	}
	
	
	@GetMapping("/getList/{bno}/{pageNum}")
	public HashMap<String, Object> getList(@PathVariable("bno") int bno , @PathVariable("pageNum") int pageNum) {
		
		Criteria cri = new Criteria(pageNum, 20);
		ArrayList<FreeReplyVO> list = replyService.getList(bno, cri);
		
		int total = replyService.getTotal(bno);
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list",list);
		map.put("total",total);
		System.out.println(map);
		return map;
	}
	
	
	@PostMapping(value = "update", produces = "application/json")
	public int update(@RequestBody FreeReplyVO vo) 
	{
//		System.out.println(vo + "  1");
		int result = replyService.pwCheck(vo);
		
		if(result == 1)
		{
			return replyService.update(vo);
		}
		else {
			return 0;
		}
		
		
	
	}
	
	@PostMapping(value = "delete", produces = "application/json")
	public int delete(@RequestBody FreeReplyVO vo) 
	{
		System.out.println(vo + " 삭제할 대상");
		int result = replyService.pwCheck(vo);
		if(result == 1)
		{
			System.out.println("삭제 성공");
			return replyService.delete(vo);
		}
		else {
			return 0;
		}
	}
}
