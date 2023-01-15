package com.ssafy.pjt.model.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.model.dao.board.CommentMapper;
import com.ssafy.pjt.model.dto.board.CommentDTO;


@Service
public class CommentService {

	@Autowired
	private CommentMapper cdao;

	public boolean writeComment(CommentDTO comment) {
		if(cdao.insert(comment)==1) return true;
		else return false;
	}

	public List<CommentDTO> getComments(int bno) {
		return cdao.selectList(bno);
	}

	public boolean delete(int cno) {
		if(cdao.delete(cno)==1) {
			return true;
		}else {
			return false;
		}
	}

	public boolean modify(CommentDTO comment) {
		if(cdao.modify(comment)==1) {
			return true;
		}else {
			return false;
		}
	}
	
	public CommentDTO getComment(int cno) {
		return cdao.selectOne(cno);
	}

}
