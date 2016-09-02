package com.icc.sixteenbitweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icc.sixteenbitweb.dao.Guest;
import com.icc.sixteenbitweb.service.GuestService;
import com.icc.sixteenbitweb.service.GuestServiceImpl;

@Controller
@CrossOrigin("http://localhost:8080")
@RequestMapping("/account")
public class LoginController {
	
	@Autowired
	private GuestService guestServiceImpl = new GuestServiceImpl();
	
//	public void setService(GuestServiceImpl guestServiceImpl) {
//		this.guestServiceImpl = guestServiceImpl;
//	}
//	
//	@RequestMapping(value = "/login" )
//	public String showLogin(){
//		return "login";
//	}
//	
//	@RequestMapping(value = "logout" )
//	public String showLogout(){
//		return "/logout";
//	}
//	
//	
//	@RequestMapping(value="/newaccount", method=RequestMethod.GET)
//	public String showCreateAccount(Model model) {
//		model.addAttribute("guest",new Guest());
//		return "redirect:http://localhost:8080/";
//	}
	
	@RequestMapping(value="/createaccount", method=RequestMethod.POST, consumes ="application/json")
	@ResponseBody
	public  String createAccount(@RequestBody Guest guest, BindingResult result) {
		
		System.out.println("yoyoyoyoyoy");
		if(result.hasErrors()) {
			return "redirect:http://localhost:8080/#account/createaccount";
		}
		
		guest.setAuthority("ROLE_USER");
		
		try {
			guestServiceImpl.createGuest(guest);
		} catch (DuplicateKeyException e) {
			result.rejectValue("username", "DuplicateKey.guest.username");
			return "redirect:http://localhost:8080/#createaccount";
		}
		
		return "redirect:http://localhost:8080/";
	}
	
	@RequestMapping(value="/updateaccount",method=RequestMethod.PUT)
	public String updateAccount( @RequestBody Guest guest){
		
		
		
		return "redirect:http://localhost:8080/";
		
	}
	
	@RequestMapping(value="/forgotpassword")
	public String updatePassword(){
		return "redirect:http://localhost:8080/";
	}
	
}
