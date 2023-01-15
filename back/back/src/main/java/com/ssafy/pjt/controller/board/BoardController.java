package com.ssafy.pjt.controller.board;

import java.io.IOException;
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

import com.ssafy.pjt.model.dto.board.BoardDTO;
import com.ssafy.pjt.model.service.board.BoardService;


@CrossOrigin(origins= {"*"}, maxAge=6000)
@RestController
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService bservice;
	
//	@GetMapping()
//	public ResponseEntity<Map<String, Object>>lisst(@RequestParam(value= "page", defaultValue = "1") int page){
//		return new ResponseEntity<Map<String, Object>>(bservice.makePage(page), HttpStatus.ACCEPTED);
//				
//	}
	@GetMapping()
	public ResponseEntity<?>list(){
		System.out.println("here!!!!!!!!!!");
		return new ResponseEntity<List<BoardDTO>>(bservice.getBoards(), HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/order")
	public ResponseEntity<?> orderList(){
		return new ResponseEntity<List<BoardDTO>>(bservice.getRanking(), HttpStatus.ACCEPTED);
	}
	
	@PostMapping()
	public ResponseEntity<String> write(@RequestBody BoardDTO board) throws IllegalStateException, IOException{
		boolean writeResult = bservice.write(board); // 일단 글이 DB에 들어가야 글번호를 파일업로드에 넣을 수 있음.
		
		if (writeResult) {
			return new ResponseEntity<String>("success", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/{bno}")
	public ResponseEntity<?> read(@PathVariable("bno") int bno){
		BoardDTO board = bservice.read(bno);
		if (board!= null) {
			return new ResponseEntity<BoardDTO>(board, HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping()
	public ResponseEntity<String> modify(@RequestBody BoardDTO board){
		boolean modifyResult = bservice.modify(board);
		if (modifyResult) {
			return new ResponseEntity<String>("success", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/{bno}")
	public ResponseEntity<String> delete(@PathVariable("bno") int bno){
		boolean deleteResult = bservice.delete(bno);
		if (deleteResult) {
			return new ResponseEntity<String>("success", HttpStatus.ACCEPTED);
		} else {
			return new ResponseEntity<String>("error", HttpStatus.BAD_REQUEST);
		}
	}
}
