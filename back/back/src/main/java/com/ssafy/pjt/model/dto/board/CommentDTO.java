package com.ssafy.pjt.model.dto.board;

public class CommentDTO {
	private int cno;
	private int bno;
	private String ccontent;
	private String cwriter;
	private String cwrite_date;
	
	public CommentDTO() {
		
	}

	public CommentDTO(int cno, int bno, String ccontent, String cwriter, String cwrite_date) {
		super();
		this.cno = cno;
		this.bno = bno;
		this.ccontent = ccontent;
		this.cwriter = cwriter;
		this.cwrite_date = cwrite_date;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getCcontent() {
		return ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}

	public String getCwriter() {
		return cwriter;
	}

	public void setCwriter(String cwriter) {
		this.cwriter = cwriter;
	}

	public String getCwrite_date() {
		return cwrite_date;
	}

	public void setCwrite_date(String cwrite_date) {
		this.cwrite_date = cwrite_date;
	}

	@Override
	public String toString() {
		return "CommentDTO [cno=" + cno + ", bno=" + bno + ", ccontent=" + ccontent + ", cwriter=" + cwriter
				+ ", cwrite_date=" + cwrite_date + "]";
	}
	
	
	
	
}
