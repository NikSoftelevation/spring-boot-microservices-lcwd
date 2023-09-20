package com.spring.lcwd.hotel.service.dto;

public class HotelDto {
	private String id;
	private String name;
	private String about;
	private String location;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public HotelDto(String id, String name, String about, String location) {
		super();
		this.id = id;
		this.name = name;
		this.about = about;
		this.location = location;
	}

	public HotelDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}