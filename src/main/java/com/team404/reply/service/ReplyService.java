package com.team404.reply.service;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.team404.command.FreeReplyVO;
import com.team404.util.Criteria;

public interface ReplyService {
	public int regist (FreeReplyVO vo);
	public ArrayList<FreeReplyVO> getList(int bno ,Criteria cri);
	
	public int pwCheck (FreeReplyVO vo);//비번확인
	public int update (FreeReplyVO vo);
	public int getTotal(int bno);
	public int delete (FreeReplyVO vo);

}
