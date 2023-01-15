package com.ssafy.pjt.model.service.house.alpha;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.model.dao.house.alpha.InterestMapper;
import com.ssafy.pjt.model.dto.user.alpha.InterestDTO;

@Service
public class InterestService {

	@Autowired
	private InterestMapper idao;

	public boolean interest(InterestDTO interest) {
		int result = idao.insert(interest);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean uninterest(int ino) {
		int result = idao.delete(ino);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public List<InterestDTO> getInterests(String userid){
		return idao.selectAll(userid);
	}
	
	public int getCount(long aptcode) {
		return idao.selectCount(aptcode);
	}
	
	public List<String> getInterestId(long aptcode) {
		return idao.getId(aptcode);
	}
}
