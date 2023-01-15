package com.ssafy.pjt.model.dao.house.alpha;

import java.util.List;

import com.ssafy.pjt.model.dto.user.alpha.InterestDTO;

public interface InterestMapper {
	public int insert(InterestDTO interest);
	public int delete(int ino);
	public List<InterestDTO> selectAll(String userid);
	public int selectCount(long aptcode);
	public List<String> getId(long aptcode);

}
