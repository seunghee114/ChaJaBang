package com.ssafy.pjt.model.dao.board.alpha;

import java.util.List;

import com.ssafy.pjt.model.dto.board.alpha.QnaDTO;

public interface QnaMapper {
	public int insert(QnaDTO qna);

	public int delete(int qno);

	public int updateUser(QnaDTO qna);

	public int updateManager(QnaDTO qna);

	// user select all
	public List<QnaDTO> selectUserAll(String userid);

	// USER SELECT 완료된 문의 중 확인 안한 문의
	public List<QnaDTO> selectUserNotCheck(String userid);

	// USER SELECT 완료된 문의
	public List<QnaDTO> selectUserAnswer(String userid);

	// USER SELECT 미완료된 문의
	public List<QnaDTO> selectUserNotAnswer(String userid);

	// manager select all
	public List<QnaDTO> selectManagerAll();

	//	MANAGER 미완료 문의
	public List<QnaDTO> selectManagerNotAnswer();

	// select one
	public QnaDTO selectOne(int qno);

	// update user check status
	public int updateChck(int qno);
}
