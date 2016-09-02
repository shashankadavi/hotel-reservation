package com.icc.sixteenbitweb.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Room {
	
	private int roomNum;
	private int roomType;
	private int numBeds;
	
	@Id
	private int id;
	
	public Room () {
		
	}
	
	public Room (int roomNum, int numBeds, int roomType) {
		this.roomNum = roomNum;
		this.numBeds = numBeds;
		this.roomType = roomType;
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

	public int getRoomType() {
		return roomType;
	}

	public void setRoomType(int roomType) {
		this.roomType = roomType;
	}

	public int getNumBeds() {
		return numBeds;
	}

	public void setNumBeds(int numBeds) {
		this.numBeds = numBeds;
	}

	@Override
	public String toString() {
		return "Room [roomNum=" + roomNum + ", roomType=" + roomType + ", numBeds=" + numBeds + ", id=" + id + "]";
	}
		
}
