package com.ssafy.pjt.model.service.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt.model.dao.board.BoardMapper;
import com.ssafy.pjt.model.dto.board.BoardDTO;


@Service
public class BoardService {

	private static final int COUNT_PER_PAGE = 10;

	@Autowired
	private BoardMapper dao;

	public Map<String, Object> makePage(int page) {
		Map<String, Object> result = new HashMap<String, Object>();

		int totalCount = dao.selectTotalCount(); // 총 게시글의 개수

		int totalPage = totalCount / COUNT_PER_PAGE;
		if (totalCount % COUNT_PER_PAGE > 0) {
			totalPage++;
		}

		int startPage = (page - 1) / 10 * 10 + 1; // 10*10하면 1의 자리가 떨어져 나가서 11, 12, 13, ...
		int endPage = startPage + 9; // 시작이 11이면 화면 하단 끝 페이지는 20으로 맞춰놨음.!!

		if (endPage > totalPage)
			endPage = totalPage;

		int startRow = (page - 1) * COUNT_PER_PAGE; // (1-1)*10 = 0, (2-1)*10 = 10
		List<BoardDTO> boardList = dao.selectList(startRow, COUNT_PER_PAGE);

		result.put("startPage", startPage);
		result.put("endPage", endPage);
		result.put("curPage", page);
		result.put("totalPage", totalPage);
		result.put("boardList", boardList);

		return result;
	}
	
	public List<BoardDTO> getBoards(){
		return dao.selectAll();
	}
	
	public List<BoardDTO> getRanking(){
		return dao.orderList();
	}

	public boolean write(BoardDTO board) {
		if (board == null || board.getTitle() == null || board.getTitle().length() == 0 || board.getWriter() == null
				|| board.getWriter().length() == 0)
			return false;

		if (dao.insert(board) == 1)
			return true;
		return false;

	}

	public BoardDTO read(int bno) {
		BoardDTO board = dao.selectOne(bno);
		// 현재 로그인 정보와 글 작성자가 다를 때에만 조회수 증가시키기
		dao.updateReadCount(bno); // 이따 로그인 붙이면 내가 쓴 글을 읽을 때는 조회수 증가 안한.ㅁ
		return board;
	}

	public boolean modify(BoardDTO board) {
		if (dao.modify(board) == 1)
			return true;
		return false;
	}

	public boolean delete(int bno) {
		if (dao.delete(bno) == 1)
			return true;
		return false;
	}


}
