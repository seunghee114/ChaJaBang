package com.ssafy.pjt.model.dao.user;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.pjt.model.dto.user.UserDTO;

@Mapper
public interface MemberMapper {

	public UserDTO login(UserDTO memberDto) throws SQLException;
	public UserDTO userInfo(String userid) throws SQLException;
	public void saveRefreshToken(Map<String, String> map) throws SQLException;
	public Object getRefreshToken(String userid) throws SQLException;
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
	public int regist(UserDTO memberDto) throws SQLException;
	public int modify(UserDTO memberDto) throws SQLException;
}
