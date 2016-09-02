package com.icc.sixteenbitweb.service;

import java.util.List;

public interface AvailabilityService {

	public boolean checkAvailableRoom(List<Integer> reservedRoomsByType, int roomType);

	// public int noOfAvailableRooms(List<Integer> reservedRoomsByType, int
	// totalCountOfRoomType);

	public boolean checkValidNoOfRooms(List<Integer> reservedRoomsByType, int maxRoomsForType);

	public int getMaxByType(int roomType);

	public List<Integer> getRoomNums(int type);

}
