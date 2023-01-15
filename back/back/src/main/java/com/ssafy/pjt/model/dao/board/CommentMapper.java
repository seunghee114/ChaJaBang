package com.ssafy.pjt.model.dao.board;

import java.util.List;

import com.ssafy.pjt.model.dto.board.CommentDTO;


public interface CommentMapper {
	public int insert(CommentDTO comment);
	public List<CommentDTO> selectList(int bno);
	public int modify(CommentDTO comment);
	public int delete(int cno);
	public CommentDTO selectOne(int cno);
}
