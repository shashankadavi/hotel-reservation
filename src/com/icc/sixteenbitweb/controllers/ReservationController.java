package com.icc.sixteenbitweb.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.icc.sixteenbitweb.service.AvailabilityService;
import com.icc.sixteenbitweb.service.AvailabilityServiceImpl;
import com.icc.sixteenbitweb.service.ReservationService;
import com.icc.sixteenbitweb.service.ReservationServiceImpl;

@Controller
@RequestMapping("/reservation")
public class ReservationController {

	@Autowired
	private AvailabilityService availabilityService = new AvailabilityServiceImpl();

	@Autowired
	private ReservationService reservationService = new ReservationServiceImpl();

//	@Autowired
//	public void setService(AvailabilityService availabilityService, ReservationService reservationService) {
//		this.availabilityService = availabilityService;
//		this.reservationService = reservationService;
//	}

//	@RequestMapping(value = "/")
//	public String showLogin() {
//		return "reservation";
//	}
//	
//	@RequestMapping(value = "/availability")
//	public String availability() {
//		return "available";
//	}

	@RequestMapping(value = "/check", method = RequestMethod.GET)
	public String checkAvailability(int roomType, String startDate, String endDate) {

		// Get the number of reserved rooms by type. (over multiple dates.)
		List<Integer> reservedRoomsByType = reservationService.availableByType(roomType, startDate, endDate);

		// Pass the above values to check if there is at least one room of that
		// type available for each day.
		if(availabilityService.checkAvailableRoom(reservedRoomsByType, roomType)){
			return "redirect:http://localhost:8080/#reservations";
		}
		return "error";
	}

	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String createReservation(String name, String startDate, String endDate, int type){
		List<Integer> roomNums = availabilityService.getRoomNums(type);
		if(roomNums.isEmpty()){
			return "error";
		}
		try{
		reservationService.create(roomNums, name, startDate, endDate, type);
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			return "error";
		}
		
		return "redirect:http://localhost:8080/#reservations";
	}

	@RequestMapping(value = "/cancel", method = RequestMethod.POST)
	public String cancelReservation(int resNum) {

		if (reservationService.cancel(resNum)) {
			return "redirect:http://localhost:8080/#reservations";
		}

		return "error";
	}

}