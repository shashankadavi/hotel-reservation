package com.icc.sixteenbitweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.icc.sixteenbitweb.dao.Guest;
import com.icc.sixteenbitweb.dao.GuestDao;

@Service("guestService")
@Transactional
public class GuestServiceImpl implements GuestService {
	
	
	@Autowired
	private GuestDao guestDao = new GuestDao();
	
	public boolean createGuest(Guest guest) {
		
		return guestDao.createGuest(guest);
		
	}

	public Guest getGuest(Guest guest) {
		
		return guestDao.getGuest(guest);
	}
	
	
}
