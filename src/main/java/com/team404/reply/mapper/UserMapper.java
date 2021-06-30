package com.team404.reply.mapper;

import com.team404.command.UsersVO;

public interface UserMapper {
	public int idCheck(UsersVO vo);
	public int join(UsersVO vo);
	public UsersVO login(UsersVO vo);
	public UsersVO getinfo(String userID);
}
