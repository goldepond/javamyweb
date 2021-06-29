package com.team404.reply.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team404.command.FreeReplyVO;
import com.team404.reply.mapper.ReplyMapper;
import com.team404.util.Criteria;
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
	public ArrayList<FreeReplyVO> getList(int bno, Criteria cri) {
		return replymapper.getList(bno , cri);
	}

	@Override
	public int pwCheck(FreeReplyVO vo) {
		return replymapper.pwCheck(vo);
	}

	@Override
	public int update(FreeReplyVO vo) {
		return replymapper.update(vo);
	}

	@Override
	public int delete(FreeReplyVO vo) {
		return replymapper.delete(vo);
	}

	@Override
	public int getTotal(int bno) {
		System.out.println();
		return replymapper.getTotal(bno);
	}


}
