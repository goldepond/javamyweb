package com.team404.reply.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team404.command.UsersVO;
import com.team404.reply.mapper.UserMapper;
@Service("userService")
public class UserServiceimpl implements UserService {

	@Autowired
	private UserMapper usermapper;
	
	@Override
	public int idCheck(UsersVO vo) {
		
		return usermapper.idCheck(vo);
	}

	@Override
	public int join(UsersVO vo) {
		// TODO Auto-generated method stub
		System.out.println(vo);
		return usermapper.join(vo);
	}

	@Override
	public UsersVO login(UsersVO vo) {
		
		return usermapper.login(vo);
	}

	

}
