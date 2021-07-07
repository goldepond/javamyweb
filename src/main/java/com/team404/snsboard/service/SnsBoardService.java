package com.team404.snsboard.service;

import java.util.ArrayList;

import com.team404.command.SnsBoardVo;

public interface SnsBoardService {
	public int insert(SnsBoardVo vo);
	public ArrayList<SnsBoardVo> getList();
}
