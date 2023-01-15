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

import com.ssafy.pjt.model.dto.board.alpha.NoticeDTO;
import com.ssafy.pjt.model.service.board.alpha.NoticeService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/notice")
public class NoticeController {
	@Autowired
	private NoticeService service;

	@PostMapping()
	public ResponseEntity<?> write(@RequestBody NoticeDTO notice) {
		if (service.write(notice)) {
			return new ResponseEntity<String>("notice write success!!", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("notice write fail", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/{nno}")
	public ResponseEntity<?> delete(@PathVariable("nno")int nno) {
		if (service.delete(nno)) {
			return new ResponseEntity<String>("notice delete success!!", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("notice delete fail", HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping()
	public ResponseEntity<?> modify( @RequestBody NoticeDTO notice) {
		if (service.modify(notice)) {
			return new ResponseEntity<String>("notice modify success!!", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("notice modify fail", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping()
	public ResponseEntity<?> getNotices() {
		return new ResponseEntity<List<NoticeDTO>>(service.getNotices(), HttpStatus.ACCEPTED);
	}

	@GetMapping("/n4")
	public ResponseEntity<?> getNotice4() {
		return new ResponseEntity<List<NoticeDTO>>(service.getNotice4(), HttpStatus.ACCEPTED);
	}

	@GetMapping("/{nno}")
	public ResponseEntity<?> getNotice(@PathVariable("nno")int nno) {
		return new ResponseEntity<NoticeDTO>(service.getNotice(nno), HttpStatus.ACCEPTED);
	}

}
