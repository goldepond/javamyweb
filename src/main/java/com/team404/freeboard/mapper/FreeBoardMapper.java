package com.team404.freeboard.mapper;

import java.util.ArrayList;

import com.team404.command.FreeBoardVO;
import com.team404.util.Criteria;

public interface FreeBoardMapper {
	public int getTotal(Criteria cri);
	public int regist(FreeBoardVO vo);
	public ArrayList<FreeBoardVO> getList(Criteria cri);
	public FreeBoardVO  getDetail(int bno);
	public int update(FreeBoardVO vo);
	public int delete (int bno);
	int getTotal();

}
