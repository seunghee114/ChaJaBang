package com.ssafy.pjt.model.service.user;

import com.ssafy.pjt.model.dto.user.UserDTO;

public interface MemberService {

	public UserDTO login(UserDTO memberDto) throws Exception;
	public UserDTO userInfo(String userid) throws Exception;
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleRefreshToken(String userid) throws Exception;
	public int regist(UserDTO memberDto) throws Exception;
	public int modify(UserDTO memberDto) throws Exception;
	
}
