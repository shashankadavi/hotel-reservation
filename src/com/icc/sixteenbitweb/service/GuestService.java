package com.icc.sixteenbitweb.service;

import com.icc.sixteenbitweb.dao.Guest;
public interface GuestService {
	public boolean createGuest(Guest guest);
	
	public Guest getGuest(Guest guest);
}
