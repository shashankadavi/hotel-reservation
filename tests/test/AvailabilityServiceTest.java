package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.icc.sixteenbitweb.service.AvailabilityService;
import com.icc.sixteenbitweb.service.AvailabilityServiceImpl;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {"classpath:beans/beans.xml"} )
@RunWith(SpringJUnit4ClassRunner.class)
public class AvailabilityServiceTest {
	
	@Autowired
	private static AvailabilityService as = new AvailabilityServiceImpl();
	
	@Test
	public void testGetMaxByType(){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans/beans.xml");
		
		assertEquals("Did not return correct amount.", 100, as.getMaxByType(1));
		assertEquals("Did not return correct amount.", 50, as.getMaxByType(2));
		assertEquals("Did not return correct amount.", 20, as.getMaxByType(3));
		assertEquals("Did not return correct amount.", 10, as.getMaxByType(4));
		assertEquals("Did not return correct amount.", 5, as.getMaxByType(5));
		
		((ClassPathXmlApplicationContext)context).close();
		
	}
	
	@Test
	public void testCheckValidNoOfRooms(){
		ApplicationContext context = new ClassPathXmlApplicationContext("beans/beans.xml");
		
		List<Integer> res1 = new ArrayList<Integer>();
		res1.add(99);
		res1.add(80);
		res1.add(0);
		
		List<Integer> res2 = new ArrayList<Integer>();
		res2.add(99);
		res2.add(100);
		res2.add(100);
		
		List<Integer> res3 = new ArrayList<Integer>();
		res3.add(100);
		res3.add(100);
		res3.add(100);
		
		assertTrue("Did not return correct amount.", as.checkValidNoOfRooms(res1,100));
		assertTrue("Did not return correct amount.", !as.checkValidNoOfRooms(res2,100));
		assertTrue("Did not return correct amount.", !as.checkValidNoOfRooms(res3,100));
	
		((ClassPathXmlApplicationContext)context).close();
		
	}

	
	
}

