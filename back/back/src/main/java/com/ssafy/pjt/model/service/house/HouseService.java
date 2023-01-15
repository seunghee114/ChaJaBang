package com.ssafy.pjt.model.service.house;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.model.dao.house.HouseMapper;
import com.ssafy.pjt.model.dto.house.HouseDeal;
import com.ssafy.pjt.model.dto.house.HouseInfo;
import com.ssafy.pjt.model.dto.house.dongCode;

@Service
public class HouseService {

	@Autowired
	private HouseMapper dao;

	// 시도 얻기
	public List<String> getSido() {
		return dao.selectSido();
	}

	// 구군 얻기
	public List<String> getGugun(String sido) {
		return dao.selectGugun(sido);
	}

	// 동 얻기
	public List<String> getDong(String sido, String gugun) {
		return dao.selectDong(sido, gugun);
	}

	public String getCode(String sido, String gugun, String dong) {
		return dao.selectDongCodes(sido, gugun, dong);
	}

	// 동코드 객체 얻기
	public dongCode getDongCode(long code) {
		return dao.selectDongCode(code);
	}
	// 동에 있는 아파트 리스트 얻기
	public List<HouseInfo> getApts(String code){
		return dao.selectHouseInfo(code);
	}
	
	// 해당 아파트의 거래내역
	public List<HouseDeal> getDeals(long aptCode) {
		return dao.selectAllByAptCode(aptCode);
	}
	
	// 아파트 정보
	public HouseInfo getApt(long aptCode) {
		return dao.selectOneByAptCode(aptCode);
	}
	
	public String getSidoGugun(String sido, String gugun) {
		return dao.selectSidoGugunCode(sido, gugun);
	}


}