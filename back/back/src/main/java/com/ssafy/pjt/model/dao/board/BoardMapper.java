package com.ssafy.pjt.model.dao.board;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.pjt.model.dto.board.BoardDTO;

@Repository
public interface BoardMapper {
	public int insert(BoardDTO board);
	public List<BoardDTO> selectList(@Param("start")int startRow, @Param("cnt")int count);
	public List<BoardDTO> selectAll();
	public int selectTotalCount();
	public BoardDTO selectOne(int bno);
	public int updateReadCount(int bno);
	public int modify(BoardDTO board);
	public int delete(int bno);
	public List<BoardDTO> orderList();
}
