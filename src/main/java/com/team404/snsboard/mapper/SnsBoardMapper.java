package com.team404.snsboard.mapper;

import java.util.ArrayList;

import com.team404.command.SnsBoardVo;

public interface SnsBoardMapper {
	public int insert(SnsBoardVo vo);
	public ArrayList<SnsBoardVo> getList();
}
