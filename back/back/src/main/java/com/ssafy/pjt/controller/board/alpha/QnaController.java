package com.ssafy.pjt.controller.board.alpha;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.pjt.model.dto.board.alpha.QnaDTO;
import com.ssafy.pjt.model.service.board.alpha.QnaService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/qna")
public class QnaController {
	@Autowired
	private QnaService service;

	@PostMapping()
	public ResponseEntity<?> write(@RequestBody QnaDTO qna) {
		System.out.println("!!!!!!!!!!!!!!!!!!"+qna);
		if (service.write(qna)) {
			return new ResponseEntity<String>("qna write success!!", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("qna write fail", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{qno}")
	public ResponseEntity<?> delete(@PathVariable("qno") int qno) {
		if (service.delete(qno)) {
			return new ResponseEntity<String>("qna delete success!!", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("qna delete fail", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/user")
	public ResponseEntity<?> modifyUser(@RequestBody QnaDTO qna) {
		if (service.modifyUser(qna)) {
			return new ResponseEntity<String>("qna modify user success!!", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("qna modify user fail", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/manager")
	public ResponseEntity<?> modifyManager(@RequestBody QnaDTO qna) {
		if (service.modifyManager(qna)) {
			return new ResponseEntity<String>("qna modify manager success!!", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("qna modify manager fail", HttpStatus.BAD_REQUEST);
		}
	}

	// user select all
	@GetMapping("/{userid}")
	public ResponseEntity<?> getUserQna(@PathVariable("userid") String userid) {
		return new ResponseEntity<List<QnaDTO>>(service.getUserQna(userid), HttpStatus.ACCEPTED);

	}

	// USER SELECT 완료된 문의 중 확인 안한 문의
	@GetMapping("/nchk/{userid}")
	public ResponseEntity<?> getUserNotCheck(@PathVariable("userid") String userid) {
		return new ResponseEntity<List<QnaDTO>>(service.getUserNotCheck(userid), HttpStatus.ACCEPTED);

	}

	// USER SELECT 완료된 문의
	@GetMapping("/all/{userid}")
	public ResponseEntity<?> getUserAnswered(@PathVariable("userid") String userid) {
		return new ResponseEntity<List<QnaDTO>>(service.getUserAnswered(userid), HttpStatus.ACCEPTED);

	}

	// USER SELECT 미완료된 문의
	@GetMapping("/no/{userid}")
	public ResponseEntity<?> getUserNotAnswer(@PathVariable("userid") String userid) {
		return new ResponseEntity<List<QnaDTO>>(service.getUserNotAnswer(userid), HttpStatus.ACCEPTED);

	}

	// manager select all
	@GetMapping()
	public ResponseEntity<?> getManagerQna() {
		return new ResponseEntity<List<QnaDTO>>(service.getManagerQna(), HttpStatus.ACCEPTED);

	}

	// MANAGER 미완료 문의
	@GetMapping("/mno")
	public ResponseEntity<?> getManagerNotAnswer() {
		return new ResponseEntity<List<QnaDTO>>(service.getManagerNotAnswer(), HttpStatus.ACCEPTED);

	}

	// select one
	@GetMapping("/one/{qno}")
	public ResponseEntity<?> getQna(@PathVariable("qno") int qno) {
		QnaDTO cur = service.getQna(qno);
		if(cur!=null) {
			if(!cur.isAnswered()) {
				return new ResponseEntity<>(cur, HttpStatus.ACCEPTED);
			}else {
				service.updateChck(qno);
				return new ResponseEntity<>(cur, HttpStatus.ACCEPTED);
			}
		}
		
		return new ResponseEntity<>(cur, HttpStatus.ACCEPTED);

	}
	
	
	
}
