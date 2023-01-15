package com.ssafy.pjt.model.dto.user.alpha;

public class InterestDTO {
	private int ino;
	private long aptcode;
	private String userid;

	public InterestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InterestDTO(int ino, long aptcode, String userid) {
		super();
		this.ino = ino;
		this.aptcode = aptcode;
		this.userid = userid;
	}

	public int getIno() {
		return ino;
	}

	public void setIno(int ino) {
		this.ino = ino;
	}

	public long getAptcode() {
		return aptcode;
	}

	public void setAptcode(long aptcode) {
		this.aptcode = aptcode;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "Interest [ino=" + ino + ", aptcode=" + aptcode + ", userid=" + userid + "]";
	}

}
