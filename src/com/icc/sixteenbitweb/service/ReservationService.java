package com.icc.sixteenbitweb.service;

import java.util.List;

import com.icc.sixteenbitweb.dao.ReservationDao;

public interface ReservationService {
//	public void setOffersDao(ReservationDao reservationDao);

	// Availability by room number
	public boolean available(int roomNum, String date);

	public boolean available(int roomNum, List<String> dates);

	public boolean available(int roomNum, String start, String end);

	// Availability by room type
	public int availableByType(int type, String date);

	public List<Integer> availableByType(int type, List<String> dates);

	public List<Integer> availableByType(int type, String start, String end);

	// Create reservation(s)
	public boolean create(int roomNum, String name, String date, int type);

	public boolean create(int roomNum, String name, List<String> dates, int type);

	public boolean create(int roomNum, String name, String start, String end, int type);
	
	public boolean create(List<Integer> roomNums, String name, String start, String end, int type);

	// Cancel reservation(s)
	public boolean cancel(int id);

	public boolean cancel(List<Integer> ids);

	// Get list of dates
	public List<String> getDates(String start, String end);

}
