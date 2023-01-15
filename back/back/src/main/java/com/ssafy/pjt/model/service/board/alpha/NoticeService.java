package com.ssafy.pjt.model.service.board.alpha;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.model.dao.board.alpha.NoticeMapper;
import com.ssafy.pjt.model.dto.board.alpha.NoticeDTO;

@Service
public class NoticeService {
	@Autowired
	private NoticeMapper ndao;
	
	public boolean write(NoticeDTO notice) {
		int result = ndao.insert(notice);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean delete(int nno) {
		int result = ndao.delete(nno);
		if(result == 1) {
			return true;
		}
		return false;
		
	}
	
	public boolean modify(NoticeDTO notice) {
		int result = ndao.update(notice);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public List<NoticeDTO> getNotices(){
		return ndao.selectAll();
	}
	
	public List<NoticeDTO> getNotice4(){
		return ndao.selectFour();
	}
	
	public NoticeDTO getNotice(int nno) {
		return ndao.selectOne(nno);
	}

}
