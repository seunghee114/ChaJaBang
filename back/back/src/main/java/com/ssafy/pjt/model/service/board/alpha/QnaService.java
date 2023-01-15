package com.ssafy.pjt.model.service.board.alpha;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.model.dao.board.alpha.QnaMapper;
import com.ssafy.pjt.model.dto.board.alpha.NoticeDTO;
import com.ssafy.pjt.model.dto.board.alpha.QnaDTO;

@Service
public class QnaService {
	@Autowired
	private QnaMapper qdao;
	
	public boolean write(QnaDTO qna) {
		int result = qdao.insert(qna);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	public boolean delete(int qno) {
		int result = qdao.delete(qno);
		if(result == 1) {
			return true;
		}
		return false;
		
	}
	
	public boolean modifyUser(QnaDTO qna) {
		int result = qdao.updateUser(qna);
		if(result == 1) {
			return true;
		}
		return false;
	}
	public boolean modifyManager(QnaDTO qna) {
		int result = qdao.updateManager(qna);
		if(result == 1) {
			return true;
		}
		return false;
	}
	
	// user select all
		public List<QnaDTO> getUserQna(String userid){
			return qdao.selectUserAll(userid);
		}

		// USER SELECT 완료된 문의 중 확인 안한 문의
		public List<QnaDTO> getUserNotCheck(String userid){
			return qdao.selectUserNotCheck(userid);
		}

		// USER SELECT 완료된 문의
		public List<QnaDTO> getUserAnswered(String userid){
			return qdao.selectUserAnswer(userid);
		}

		// USER SELECT 미완료된 문의
		public List<QnaDTO> getUserNotAnswer(String userid){
			return qdao.selectUserNotAnswer(userid);
		}

		// manager select all
		public List<QnaDTO> getManagerQna(){
			return qdao.selectManagerAll();
		}

		//	MANAGER 미완료 문의
		public List<QnaDTO> getManagerNotAnswer(){
			return qdao.selectManagerNotAnswer();
		}

		// select one
		public QnaDTO getQna(int qno) {
			return qdao.selectOne(qno);
		}
		
		// update user check status
		public boolean updateChck(int qno) {
			if(qdao.updateChck(qno)==1) return true;
			else return false;
		}
}
