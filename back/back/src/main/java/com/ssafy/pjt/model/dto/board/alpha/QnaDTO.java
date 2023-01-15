package com.ssafy.pjt.model.dto.board.alpha;

public class QnaDTO {
	private int qno;
	private String qtitle;
	private String qcontent;
	private String qwriter;
	private String qwriteDate;
	private String acontent;
	private String awriteDate;
	private boolean isAnswered;
	private boolean isChecked;

	public QnaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public QnaDTO(int qno, String qtitle, String qcontent, String qwriter, String qwriteDate, String acontent,
			String awriteDate, boolean isAnswered, boolean isChecked) {
		super();
		this.qno = qno;
		this.qtitle = qtitle;
		this.qcontent = qcontent;
		this.qwriter = qwriter;
		this.qwriteDate = qwriteDate;
		this.acontent = acontent;
		this.awriteDate = awriteDate;
		this.isAnswered = isAnswered;
		this.isChecked = isChecked;
	}

	public int getQno() {
		return qno;
	}

	public void setQno(int qno) {
		this.qno = qno;
	}

	public String getQtitle() {
		return qtitle;
	}

	public void setQtitle(String qtitle) {
		this.qtitle = qtitle;
	}

	public String getQcontent() {
		return qcontent;
	}

	public void setQcontent(String qcontent) {
		this.qcontent = qcontent;
	}

	public String getQwriter() {
		return qwriter;
	}

	public void setQwriter(String qwriter) {
		this.qwriter = qwriter;
	}

	public String getQwriteDate() {
		return qwriteDate;
	}

	public void setQwriteDate(String qwriteDate) {
		this.qwriteDate = qwriteDate;
	}

	public String getAcontent() {
		return acontent;
	}

	public void setAcontent(String acontent) {
		this.acontent = acontent;
	}

	public String getAwriteDate() {
		return awriteDate;
	}

	public void setAwriteDate(String awriteDate) {
		this.awriteDate = awriteDate;
	}

	public boolean isAnswered() {
		return isAnswered;
	}

	public void setAnswered(boolean isAnswered) {
		this.isAnswered = isAnswered;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

	@Override
	public String toString() {
		return "QnaDTO [qno=" + qno + ", qtitle=" + qtitle + ", qcontent=" + qcontent + ", qwriter=" + qwriter
				+ ", qwriteDate=" + qwriteDate + ", acontent=" + acontent + ", awriteDate=" + awriteDate
				+ ", isAnswered=" + isAnswered + ", isChecked=" + isChecked + "]";
	}

}
