package com.icc.sixteenbitweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icc.sixteenbitweb.dao.ReservationDao;
import com.icc.sixteenbitweb.dao.RoomDao;

@Service("availabilityService")
@Transactional
public class AvailabilityServiceImpl implements AvailabilityService {
	
	@Autowired
	private RoomDao roomDao = new RoomDao();
	
//	@Autowired
//	private ReservationDao reservationDao = new ReservationDao();
//	
//	@Autowired
//	private ReservationService reservationService = new ReservationServiceImpl();
	
	//RoomTypes defined in the DAO layer
//	int[] maxCount={100,50,20,10,5};
//	List<Integer> reservedRoomsByType;
//	int noOfAvailableRooms;
	
	@Override
	public boolean checkAvailableRoom(List<Integer> reservedByType, int roomType) {
		int max = getMaxByType(roomType);

		// TODO Auto-generated method stub
		return checkValidNoOfRooms(reservedByType, max);
	}

	
	public boolean checkValidNoOfRooms(List<Integer> reservedByType,int max){
		for(Integer reservedForDate : reservedByType){
			if(reservedForDate >= max){
				return false;
			}
		}
		return true;
	}

	@Override
	public int getMaxByType(int roomType) {
		
		return roomDao.maxCount(roomType);
	}


	@Override
	public List<Integer> getRoomNums(int type) {
		return roomDao.getRoomNums(type);
	}
	

}
