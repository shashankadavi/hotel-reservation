package com.icc.sixteenbitweb.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Reservation {
	private int roomNum;
	private String date;
	private String name;
	private int type;

	@Id
	private int id;

	public Reservation() {
	}

	public Reservation(int roomNum, String date, String name, int type) {
		super();
		this.roomNum = roomNum;
		this.date = date;
		this.name = name;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public int getRoomNum() {
		return roomNum;
	}

	public void setRoomNum(int roomNum) {
		this.roomNum = roomNum;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Reservation [roomNum=" + roomNum + ", date=" + date + ", name=" + name + ", type=" + type + ", id=" + id
				+ "]";
	}

}
