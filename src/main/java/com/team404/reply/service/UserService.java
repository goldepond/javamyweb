package com.team404.reply.service;

import com.team404.command.UsersVO;

public interface UserService {
	
	public int idCheck(UsersVO vo);
	public int join(UsersVO vo);
	public UsersVO login(UsersVO vo);

}
