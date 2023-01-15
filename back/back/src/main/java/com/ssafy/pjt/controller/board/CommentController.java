package com.ssafy.pjt.controller.board;

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

import com.ssafy.pjt.model.dto.board.CommentDTO;
import com.ssafy.pjt.model.service.board.CommentService;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = { "*" }, maxAge = 6000) // 천사
public class CommentController {
	@Autowired
	private CommentService service; 

	@GetMapping("/{bno}")
	public List<CommentDTO> commentList(@PathVariable("bno") int bno) {
		return service.getComments(bno); 
	}
	@GetMapping("/one/{cno}")
	public CommentDTO comment(@PathVariable("cno") int cno) {
		return service.getComment(cno); 
	}

	@PostMapping()
	public ResponseEntity<String> write(@RequestBody CommentDTO comment){
		boolean writeResult = service.writeComment(comment);
		if (writeResult) {
			return new ResponseEntity<String>("success", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping()
	public ResponseEntity<String> modify(@RequestBody CommentDTO comment){ 
		boolean modifyResult = service.modify(comment);
		if (modifyResult) {
			return new ResponseEntity<String>("success", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{cno}")
	public ResponseEntity<String> delete(@PathVariable("cno") int cno){
		boolean deleteResult = service.delete(cno);
		if (deleteResult) {
			return new ResponseEntity<String>("success", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}
	}
}
