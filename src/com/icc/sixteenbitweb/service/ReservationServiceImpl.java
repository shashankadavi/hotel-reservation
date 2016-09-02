package com.icc.sixteenbitweb.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icc.sixteenbitweb.dao.ReservationDao;

@Service("reservationService")
@Transactional
public class ReservationServiceImpl implements ReservationService{

	@Autowired
	private ReservationDao reservationDao = new ReservationDao();

//	@Autowired
//	public void setOffersDao(ReservationDao reservationDao) {
//		this.reservationDao = reservationDao;
//	}

	// Availability by room number
	public boolean available(int roomNum, String date) {
		if (!reservationDao.available(roomNum, date)) {
			return false;
		}
		return true;
	}

	public boolean available(int roomNum, List<String> dates) {
		for (String date : dates) {
			if (!reservationDao.available(roomNum, date)) {
				return false;
			}
		}
		return true;
	}

	public boolean available(int roomNum, String start, String end) {
		List<String> dates = getDates(start, end);
		return available(roomNum, dates);
	}

	// Availability by room type
	public int availableByType(int type, String date) {
		return reservationDao.typeAvailable(type, date);
	}

	public List<Integer> availableByType(int type, List<String> dates) {
		List<Integer> reserved = new ArrayList<Integer>();
		for (String date : dates) {
			reserved.add(reservationDao.typeAvailable(type, date));
		}
		return reserved;
	}

	public List<Integer> availableByType(int type, String start, String end) {
		List<String> dates = getDates(start, end);
		return availableByType(type, dates);
	}

	// Create reservation(s)
	public boolean create(int roomNum, String name, String date, int type) {

		if (!reservationDao.setReservation(roomNum, name, date, type)) {
			return false;
		}

		return true;
	}

	public boolean create(int roomNum, String name, List<String> dates, int type) {

		if (!reservationDao.setReservations(roomNum, name, dates, type)) {
			return false;
		}

		return true;
	}
	
	public boolean create(int roomNum, String name, String start, String end, int type) {
		List<String> dates = getDates(start, end);
		return create(roomNum, name, dates, type);
	}
	
	public boolean create(List<Integer> roomNums, String name, String start, String end, int type) {
		List<String> dates = getDates(start, end);
		
		for(int roomNum: roomNums){
			if(create(roomNum, name, dates, type)){
				return true;
			}
		}
		
		return false;
	}

	// Cancel reservation(s)
	public boolean cancel(int id) {
		if (!reservationDao.cancel(id)) {
			return false;
		}
		return true;
	}

	public boolean cancel(List<Integer> ids) {
		for (int id : ids) {
			if (!cancel(id)) {
				return false;
			}
		}
		return true;
	}

	// Get list of dates
	public List<String> getDates(String start, String end) {
		return reservationDao.getDates(start, end);
	}

}