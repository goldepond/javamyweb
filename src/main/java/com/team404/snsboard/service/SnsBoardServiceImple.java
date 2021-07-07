package com.team404.snsboard.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team404.command.SnsBoardVo;
import com.team404.snsboard.mapper.SnsBoardMapper;
@Service("SnsBoardService")
public class SnsBoardServiceImple implements SnsBoardService {
	
	@Autowired
	private SnsBoardMapper snsmapper;
	@Override
	public int insert(SnsBoardVo vo) {
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		System.out.println(vo);
		System.out.println(snsmapper.insert(vo));
		return snsmapper.insert(vo);
	}
	@Override
	public ArrayList<SnsBoardVo> getList() {
		
		return snsmapper.getList();
	}

}
