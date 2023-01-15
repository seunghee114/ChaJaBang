package com.ssafy.pjt.model.dao.board.alpha;

import java.util.List;

import com.ssafy.pjt.model.dto.board.alpha.NoticeDTO;

public interface NoticeMapper {
	public int insert(NoticeDTO notice);
	public int delete(int nno);
	public int update(NoticeDTO notice);
	
	// select all
	public List<NoticeDTO> selectAll();
	
	// select top4
	public List<NoticeDTO> selectFour();
	
	public NoticeDTO selectOne(int nno);
	

}
