package com.ssafy.pjt.controller.house.alpha;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt.model.dto.user.alpha.InterestDTO;
import com.ssafy.pjt.model.service.house.alpha.InterestService;

@RestController
@RequestMapping("/interest")
@CrossOrigin(origins = { "*" }, maxAge = 6000) // 천사
public class InterestController {
	@Autowired
	private InterestService service;

	@PostMapping()
	public ResponseEntity<?> interest(@RequestBody InterestDTO interest) {
		if (service.interest(interest)) {
			return new ResponseEntity<String>("interest success!!", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("interest fail", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("{ino}")
	public ResponseEntity<?> uninterest(@PathVariable("ino") int ino) {
		if (service.uninterest(ino)) {
			return new ResponseEntity<String>("uninterest success!!", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("uninterest fail", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/{userid}")
	public ResponseEntity<?> getInterests(@PathVariable("userid") String userid) {
		return new ResponseEntity<List<InterestDTO>>(service.getInterests(userid), HttpStatus.ACCEPTED);
	}

	@GetMapping("/{aptcode}")
	public ResponseEntity<?> getCount(@PathVariable("aptcode") long aptcode) {
		return new ResponseEntity<Integer>(service.getCount(aptcode), HttpStatus.ACCEPTED);
	}

}