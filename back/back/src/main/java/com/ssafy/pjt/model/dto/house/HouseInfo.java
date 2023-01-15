package com.ssafy.pjt.model.dto.house;

public class HouseInfo {
	private long aptCode;
	private int buildYear;
	private String roadName, dongCode, apartmentName, lng, lat;

	public HouseInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public HouseInfo(long aptCode, int buildYear, String roadName, String dongCode, String apartmentName, String lng,
			String lat) {
		super();
		this.aptCode = aptCode;
		this.buildYear = buildYear;
		this.roadName = roadName;
		this.dongCode = dongCode;
		this.apartmentName = apartmentName;
		this.lng = lng;
		this.lat = lat;
	}

	public long getAptCode() {
		return aptCode;
	}

	public void setAptCode(long aptCode) {
		this.aptCode = aptCode;
	}

	public int getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}

	public String getRoadName() {
		return roadName;
	}

	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	public String getapartmentName() {
		return apartmentName;
	}

	public void setApartmentName(String aprtmentName) {
		this.apartmentName = aprtmentName;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

}

