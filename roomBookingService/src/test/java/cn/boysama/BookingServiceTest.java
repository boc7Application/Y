package cn.boysama;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
//import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.boysama.dto.ReserveInfo;
import cn.boysama.dto.User;
import cn.boysama.service.impl.BookingServiceImpl;
import cn.boysama.service.impl.UserServiceImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BookingServiceTest {

	@Autowired
	private BookingServiceImpl bookingServiceImpl;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
//	@Test
//	public void rateSubmitTest() {
//		RateResult rateInfo = new RateResult();
//		Boolean result = false;
//		for(int i=0;i<10;i++){
//			rateInfo.setUserName("userTest" + i);
//			Random r1 = new Random();
//			rateInfo.setValue1(r1.nextInt(11) + 10);
//			rateInfo.setValue2(r1.nextInt(21) + 10);
//			rateInfo.setValue3(r1.nextInt(41) + 10);
//			rateInfo.setRateid("hackathon");
//			result = rateService.rateSubmit(rateInfo);
//		}
//		Assert.assertEquals(true, result);
//	}
	
	@Test
	public void timeConvertTest() {
		String time1="11:10";
		String time2="18:20";
		String time3="18:20";
		Date date1=null, date2 = null, date3 = null; 
		SimpleDateFormat formatter=new SimpleDateFormat("HH:mm"); 
		try {
			date1=formatter.parse(time1);
			date2=formatter.parse(time2);
			date3=formatter.parse(time3);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
//		System.out.println(Integer.valueOf("123")); 
		String[] xx = "http://xxx.xx".split("//");
		List<String> xxx = Arrays.asList(xx);
		System.out.println(xx[1].toString()+"--------");
		Assert.assertEquals(true, date1.before(date2));
		Assert.assertEquals(true, date2.equals(date3));
		Assert.assertEquals(true, date1.getTime()-date2.getTime()<0);
		
		System.out.println(date1.getTime() + " / " + date2.getTime()); 
		
	}
	
	@Test
	public void timeCheckWithListTest() {
		ReserveInfo reserveInfo1 = new ReserveInfo();
		ReserveInfo reserveInfo2 = new ReserveInfo();
		ReserveInfo reserveInfo3 = new ReserveInfo();
		ReserveInfo reserveInfo4 = new ReserveInfo();
		ReserveInfo reserveInfo5 = new ReserveInfo();
		ReserveInfo reserveInfo6 = new ReserveInfo();
		ReserveInfo reserveInfo7 = new ReserveInfo();
		ReserveInfo reserveInfo8 = new ReserveInfo();
		ReserveInfo reserveInfo9 = new ReserveInfo();
		
		reserveInfo1.setStartTime("08:00");
		reserveInfo1.setEndTime("09:30");
		reserveInfo2.setStartTime("10:00");
		reserveInfo2.setEndTime("13:30");
		reserveInfo3.setStartTime("14:00");
		reserveInfo3.setEndTime("14:30");
		//待比较时间 6种情况
		reserveInfo4.setStartTime("05:00");
		reserveInfo4.setEndTime("08:00");
		
		reserveInfo5.setStartTime("06:00");
		reserveInfo5.setEndTime("08:30");
		
		reserveInfo6.setStartTime("06:00");
		reserveInfo6.setEndTime("09:40");
		
		reserveInfo7.setStartTime("08:30");
		reserveInfo7.setEndTime("09:00");
		
		reserveInfo8.setStartTime("08:30");
		reserveInfo8.setEndTime("10:30");
		
		reserveInfo9.setStartTime("09:40");
		reserveInfo9.setEndTime("09:50");
		
		List<ReserveInfo> resultList = new ArrayList<>();
		resultList.add(reserveInfo2);
		resultList.add(reserveInfo1);
		resultList.add(reserveInfo3);
		
		Assert.assertEquals(true, bookingServiceImpl.timeCheckWithList(reserveInfo4, resultList));
		Assert.assertEquals(false, bookingServiceImpl.timeCheckWithList(reserveInfo5, resultList));
		Assert.assertEquals(false, bookingServiceImpl.timeCheckWithList(reserveInfo6, resultList));
		Assert.assertEquals(false, bookingServiceImpl.timeCheckWithList(reserveInfo7, resultList));
		Assert.assertEquals(false, bookingServiceImpl.timeCheckWithList(reserveInfo8, resultList));
		Assert.assertEquals(true, bookingServiceImpl.timeCheckWithList(reserveInfo9, resultList));
	}
	
//	@Test
//	public void testExcelAnalsysText(){
//		String path = "/users/Huan/Downloads/info1.xls";
//		try {
//			List<String> listS= ExcelAnalysis.exportListFromExcel(new File(path), 0);
//			for(int i=0;i<listS.size();i++){
//				System.out.println(listS.get(i));
//				String[] userListString = listS.get(i).split("\\|");
//				List<String> userList = Arrays.asList(userListString);
//				User userTmp = new User();
//				userTmp.setUserNo(userList.get(1));
//				userTmp.setUserName(userList.get(2));
//				userTmp.setUserDepartment("开发七部");
//				userTmp.setUserGroup(userList.get(3));
//				userTmp.setUserGender(userList.get(4));
//				userTmp.setUserPhonenum(userList.get(5));
////				userServiceImpl.insertUser(userTmp);
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	

}
