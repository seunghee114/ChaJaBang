package com.ssafy.pjt.model.dao.house;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.pjt.model.dto.house.HouseDeal;
import com.ssafy.pjt.model.dto.house.HouseInfo;
import com.ssafy.pjt.model.dto.house.dongCode;

@Repository
public interface HouseMapper {

	// HouseDeal
	public List<HouseDeal> selectAllByAptCode(long aptCode);

	// HouseInfo	
	public List<HouseInfo> selectHouseInfo(String code);
	
	public HouseInfo selectOneByAptCode(long aptCode);


	// dongCode
	public List<String> selectSido();

	public List<String> selectGugun(String sido);

	public List<String> selectDong(@RequestParam("sido") String sido, @RequestParam("gugun") String gugun);

	public String selectDongCodes(@RequestParam("sido") String sido, @RequestParam("gugun") String gugun,
			@RequestParam("dong") String dong);

	public dongCode selectDongCode(long aptcode);
	
	public String selectSidoGugunCode(@RequestParam("sido") String sido, @RequestParam("gugun") String gugun);
}
