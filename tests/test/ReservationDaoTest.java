package test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icc.sixteenbitweb.dao.ReservationDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {"classpath:com/icc/sixteenbitweb/beans/beans.xml"} )
@RunWith(SpringJUnit4ClassRunner.class)
public class ReservationDaoTest {

	@Autowired
	private DataSource datasource;
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(datasource);
		jdbc.execute("delete from reservations");
		jdbc.execute("insert into reservations(roomNum,guestName,date,type) value (1,'Artem','04/04/2004',1)");
	}
	
//	@Test
//	public void testAvailability() {
//		ApplicationContext context = new ClassPathXmlApplicationContext("com/icc/sixteenbitweb/beans/beans.xml");
//		
//		ReservationDao reservation = (ReservationDao)context.getBean("reservationDao");
//		
//		assertTrue("There should not be anything available ",!reservation.getReservations(1,"04/04/2004", 1).isEmpty());
//		assertTrue("There should something available ",reservation.getReservations(1,"04/04/2005", 1).isEmpty());
////		assertTrue("There should something available ",reservation.available(2,"04/04/2004"));
////		assertTrue("There should something available ",reservation.available(2,"04/04/2005"));
//		
//		((ClassPathXmlApplicationContext)context).close();
//	}
	
	@Test
	public void testAvailabileByType() {
		ApplicationContext context = new ClassPathXmlApplicationContext("com/icc/sixteenbitweb/beans/beans.xml");
		
		ReservationDao reservation = (ReservationDao)context.getBean("reservationDao");
		
		assertTrue("There should not be anything available ",!reservation.available(1,"04/04/2004", 1));
		assertTrue("There should something available ",reservation.available(1,"04/04/2005", 1));
//		assertTrue("There should something available ",reservation.available(2,"04/04/2004"));
//		assertTrue("There should something available ",reservation.available(2,"04/04/2005"));
		
		((ClassPathXmlApplicationContext)context).close();
	}
	
//	@Test
//	public void testAvailability() {
//		ApplicationContext context = new ClassPathXmlApplicationContext("beans/beans.xml");
//		
//		ReservationDao reservation = (ReservationDao)context.getBean("reservationDao");
//		
//		assertTrue("There should not be anything available ",!reservation.available(1,"04/04/2004"));
//		assertTrue("There should something available ",reservation.available(1,"04/04/2005"));
//		assertTrue("There should something available ",reservation.available(2,"04/04/2004"));
//		assertTrue("There should something available ",reservation.available(2,"04/04/2005"));
//		
//		((ClassPathXmlApplicationContext)context).close();
//	}
	
//	@Test
//	public void testSetReservation() {
//		ApplicationContext context = new ClassPathXmlApplicationContext("beans/beans.xml");
//		
//		ReservationDao reservation = (ReservationDao)context.getBean("reservationDao");
//		
//		assertTrue("This should not work ",!reservation.setReservation(1,"Artem","04/04/2004"));
//		assertTrue("This should create a reservation ",reservation.setReservation(1,"Shashank","04/04/2005"));
//
//		
//		((ClassPathXmlApplicationContext)context).close();
//	}
//	
//	@Test
//	public void testSetMultipleReservation() {
//		ApplicationContext context = new ClassPathXmlApplicationContext("beans/beans.xml");
//		
//		ReservationDao reservation = (ReservationDao)context.getBean("reservationDao");
//		
//		List<String> goodDates = new ArrayList<String>();
//		List<String> badDates = new ArrayList<String>();
//		
//		goodDates.add("04/04/2006");
//		goodDates.add("04/05/2006");
//		goodDates.add("04/06/2006");
//		
//		badDates.add("04/04/2004");
//		badDates.add("04/05/2004");
//		badDates.add("04/06/2004");
//		
//		assertTrue("This should not work ",!reservation.setMultipleReservations(1,"Artem",badDates));
//		assertTrue("This should create a reservation ",reservation.setMultipleReservations(1,"Shashank",goodDates));
//
//		
//		((ClassPathXmlApplicationContext)context).close();
//	}
	
//	@Test
//	public void testSetRoomReservation() {
//		ApplicationContext context = new ClassPathXmlApplicationContext("beans/beans.xml");
//		
//		ReservationDao reservation = (ReservationDao)context.getBean("reservationDao");
//		
//		assertTrue("This should not work ",!reservation.setReservation(1,"Artem","04/04/2004"));
//		assertTrue("This should create a reservation ",reservation.setReservation(2,"Shashank","04/04/2005"));
//		assertTrue("This should create a reservation ",reservation.setReservation(1,"Scott","04/04/2005"));
//
//		
//		((ClassPathXmlApplicationContext)context).close();
//	}
	
//	@Test
//	public void testSetMultipleReservation() {
//		ApplicationContext context = new ClassPathXmlApplicationContext("beans/beans.xml");
//		
//		ReservationDao reservation = (ReservationDao)context.getBean("reservationDao");
//		
//		List<String> goodDates = new ArrayList<String>();
//		List<String> goodDates2 = new ArrayList<String>();
//		List<String> badDates = new ArrayList<String>();
//		
//		goodDates.add("04/04/2006");
//		goodDates.add("04/05/2006");
//		goodDates.add("04/06/2006");
//		
//		goodDates2.add("04/04/2007");
//		goodDates2.add("04/05/2007");
//		goodDates2.add("04/06/2007");
//		
//		badDates.add("04/04/2004");
//		badDates.add("04/05/2004");
//		badDates.add("04/06/2004");
//		
//		assertTrue("This should not work ",!reservation.setReservations(1,"Artem",badDates,1));
//		assertTrue("This should create a reservation ",reservation.setReservations(1,"aaron",goodDates,1));
//		assertTrue("This should create a reservation ",reservation.setReservations(2,"Geoff",badDates,1));
//		assertTrue("This should create a reservation ",reservation.setReservations(2,"Shashank",goodDates2,1));
//
//		
//		((ClassPathXmlApplicationContext)context).close();
//	}
}
