package com.team404.reply.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team404.command.FreeReplyVO;
import com.team404.reply.mapper.ReplyMapper;
@Service("replyService")
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	private ReplyMapper replymapper;
	
	@Override
	public int regist(FreeReplyVO vo) {
		// TODO Auto-generated method stub
		return replymapper.regist(vo);
	}

	@Override
	public ArrayList<FreeReplyVO> getList(int bno) {
		System.out.println(replymapper.getList(bno));
		return replymapper.getList(bno);
	}

}
