package com.ssafy.pjt.controller.house;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt.model.dto.house.HouseDeal;
import com.ssafy.pjt.model.dto.house.HouseInfo;
import com.ssafy.pjt.model.service.house.HouseService;

@RestController
@RequestMapping("/house")
@CrossOrigin(origins= {"*"}, maxAge=6000)

public class HouseController {
	@Autowired
	HouseService hservice;
	@GetMapping()
	public ResponseEntity<?> sidoHouse() {
		List<String> sidos = hservice.getSido();
		if (sidos == null || sidos.size() == 0) {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<String>>(sidos, HttpStatus.ACCEPTED);
		}
	}

	@GetMapping("/{sido}")
	public ResponseEntity<?> gugunHouse(@PathVariable("sido") String sido) {
		List<String> guguns = hservice.getGugun(sido);
		if (guguns == null || guguns.size() == 0) {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<String>>(guguns, HttpStatus.ACCEPTED);
		}
	}

	@GetMapping("/{sido}/{gugun}")
	public ResponseEntity<?> dongHouse(@PathVariable("sido") String sido, @PathVariable("gugun") String gugun) {
		List<String> dongs = hservice.getDong(sido, gugun);
		if (dongs == null || dongs.size() == 0) {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<String>>(dongs, HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping("/code/{sido}/{gugun}")
	public ResponseEntity<?> getSidoGugun(@PathVariable("sido") String sido, @PathVariable("gugun") String gugun) {
		String str = hservice.getSidoGugun(sido, gugun);
		if (str == null) {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<String>(str, HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping("/{sido}/{gugun}/{dong}")
	public ResponseEntity<?> dongHouse(@PathVariable("sido") String sido, @PathVariable("gugun") String gugun, @PathVariable("dong") String dong) {
		String code = hservice.getCode(sido, gugun, dong);
		List<HouseInfo> list = hservice.getApts(code);
		
		if (list == null) {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<HouseInfo>>(list, HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping("/detail/{APTcode}")
	public ResponseEntity<?> dealList(@PathVariable("APTcode") long APTcode) {
		List<HouseDeal> list = hservice.getDeals(APTcode);
		if (list == null || list.size() == 0) {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<List<HouseDeal>>(list, HttpStatus.ACCEPTED);
		}
	}
	
	@GetMapping("/detail/apt/{APTcode}")
	public ResponseEntity<?> APT(@PathVariable("APTcode") long APTcode) {
		HouseInfo info = hservice.getApt(APTcode);
		if (info == null) {
			return new ResponseEntity<String>("fail", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<HouseInfo>(info, HttpStatus.ACCEPTED);
		}
	}

}
