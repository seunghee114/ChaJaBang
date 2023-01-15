package com.ssafy.pjt.model.dto.board.alpha;

public class NoticeDTO {
	private int nno;
	private String ntitle;
	private String ncontent;
	private String nwriteDate;

	public NoticeDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoticeDTO(int nno, String ntitle, String ncontent, String nwriteDate) {
		super();
		this.nno = nno;
		this.ntitle = ntitle;
		this.ncontent = ncontent;
		this.nwriteDate = nwriteDate;
	}

	public int getNno() {
		return nno;
	}

	public void setNno(int nno) {
		this.nno = nno;
	}

	public String getNtitle() {
		return ntitle;
	}

	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}

	public String getNcontent() {
		return ncontent;
	}

	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}

	public String getNwriteDate() {
		return nwriteDate;
	}

	public void setNwriteDate(String nwriteDate) {
		this.nwriteDate = nwriteDate;
	}

	@Override
	public String toString() {
		return "NoticeDTO [nno=" + nno + ", ntitle=" + ntitle + ", ncontent=" + ncontent + ", nwriteDate=" + nwriteDate
				+ "]";
	}

}
