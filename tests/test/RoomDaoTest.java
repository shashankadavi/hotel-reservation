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

import com.icc.sixteenbitweb.dao.RoomDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {"classpath:beans/beans.xml"} )
@RunWith(SpringJUnit4ClassRunner.class)
public class RoomDaoTest {

	@Autowired
	private DataSource datasource;
	
	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(datasource);
		jdbc.execute("delete from rooms");
	}
	
//	@Test
//	public void testCreateRoom() {
//		ApplicationContext context = new ClassPathXmlApplicationContext("beans/beans.xml");
//		
//		RoomDao room = (RoomDao)context.getBean("roomDao");
//		
////		assertTrue("",!room.createRoom(1,"04/04/2004"));
//		assertTrue("The room was created!",room.createRoom(1));
//		assertTrue("The room was created!",room.createRoom(2));
//		assertTrue("The room was created!",room.createRoom(3));
//		assertTrue("The room was created!",room.createRoom(1));
//		assertTrue("The room was created!",room.createRoom(2));
//		assertTrue("The room was created!",room.createRoom(3));
//		assertTrue("The room was created!",room.createRoom(1));
//		
//		((ClassPathXmlApplicationContext)context).close();
//	}
	
//	@Test
//	public void testCreateMultipleRooms() {
//		ApplicationContext context = new ClassPathXmlApplicationContext("beans/beans.xml");
//		
//		RoomDao room = (RoomDao)context.getBean("roomDao");
//		List<Integer> numBedsList = new ArrayList<Integer>();
//		
//		numBedsList.add(1);
//		numBedsList.add(2);
//		numBedsList.add(3);
//		numBedsList.add(3);
//		numBedsList.add(2);
//		numBedsList.add(1);
//		numBedsList.add(2);
//		numBedsList.add(3);
//		numBedsList.add(1);
//		
//		assertTrue("The rooms were created!",room.createMultipleRooms(numBedsList));
//		
//		((ClassPathXmlApplicationContext)context).close();
//	}
	
//	@Test
//	public void testGetRoomsByBedNum() {
//		ApplicationContext context = new ClassPathXmlApplicationContext("beans/beans.xml");
//		
//		RoomDao room = (RoomDao)context.getBean("roomDao");
//		List<Integer> numBedsList = new ArrayList<Integer>();
//		
//		numBedsList.add(1);
//		numBedsList.add(2);
//		numBedsList.add(3);
//		numBedsList.add(3);
//		numBedsList.add(2);
//		numBedsList.add(1);
//		numBedsList.add(2);
//		numBedsList.add(3);
//		numBedsList.add(1);
//		
//		assertTrue("The rooms were created!",room.createMultipleRooms(numBedsList));
//		
//		assertTrue("There are no rooms with 4 beds!",room.getRooms(4).isEmpty());
//		assertTrue("The room numbers were retreived!",!room.getRooms(1).isEmpty());
//		
//		((ClassPathXmlApplicationContext)context).close();
//	}
	
	@Test
	public void testGetRooms() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans/beans.xml");
		
		RoomDao room = (RoomDao)context.getBean("roomDao");
		List<Integer> numBedsList = new ArrayList<Integer>();
		
		numBedsList.add(1);
		numBedsList.add(2);
		numBedsList.add(3);
		numBedsList.add(3);
		numBedsList.add(2);
		numBedsList.add(1);
		numBedsList.add(2);
		numBedsList.add(3);
		numBedsList.add(1);
		
		assertTrue("The rooms were created!",room.createMultipleRooms(numBedsList));
		
		assertTrue("The room numbers were retreived!",!room.getRooms().isEmpty());
		
		((ClassPathXmlApplicationContext)context).close();
	}
}
