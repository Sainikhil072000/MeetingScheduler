package BookingSystem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MeetingScheduler {
	private static Office ofc;
	private static HashMap<Date, HashMap<Date, Date>> resultMap;
	
	public static void main(String[] args) throws ParseException
	{
		Scanner sc = new Scanner(System.in);
		String officeHours = sc.nextLine();
		String[] convertedOfficeHours = officeHours.split(" ");
		Date openTime = new SimpleDateFormat(Office.officeFormat).parse(convertedOfficeHours[0]);
		Date closeTime = new SimpleDateFormat(Office.officeFormat).parse(convertedOfficeHours[1]);
		
		ArrayList<BookingRequest> existingMeetings = new ArrayList<BookingRequest>();
		
		ofc = new Office(openTime, closeTime);
		
		String input = sc.nextLine();
		
		while(!input.equalsIgnoreCase("end")) {
			String[] requestSubmissionArray = input.split(" ");
			Date submitDateTime = new SimpleDateFormat(Request.reqFormat).parse(requestSubmissionArray[0]+" "+requestSubmissionArray[1]);
			String empId = requestSubmissionArray[2];
			Request reqObj = new Request(submitDateTime, empId);
			
			String booking = sc.nextLine();
			String[] bookingRequest = booking.split(" ");
			Date bookingDateTime = new SimpleDateFormat(BookingRequest.bookingFormat).parse(bookingRequest[0]+" "+bookingRequest[1]);
			int duration = Integer.parseInt(bookingRequest[2]);
			BookingRequest bookingObj = new BookingRequest(bookingDateTime, duration);
			
			if(isValidMeeting(bookingObj, existingMeetings))
			{
				existingMeetings.add(bookingObj);
				
				Date bookingStartTime = bookingObj.getBookingDateTime();
				Date bookingEndTime = new Date(bookingObj.getBookingDateTime().getTime() + (bookingObj.getDuration()*3600*1000));
				
				
				Date keyValue = bookingObj.getBookingDateTime();
				HashMap<Date, Date> timings = new HashMap<Date, Date>();
				timings.put(bookingStartTime, bookingEndTime);
				
				if(resultMap==null)
					resultMap = new HashMap<Date, HashMap<Date, Date>>();
				
				resultMap.put(keyValue, timings);
			}
			else
			{
				//System.out.println("Invalid - "+ bookingObj.getBookingDateTime());  TODO - store invalid schedules to another HashMap
			}
			
			input = sc.nextLine();
		}
		
		System.out.println("\nBelow is the calender for scheduled meetings \n");
		
		for(Map.Entry<Date, HashMap<Date,Date>> entry: resultMap.entrySet())
		{
			String meetingDate = entry.getKey().toString();
			System.out.println(meetingDate);
			
			HashMap<Date, Date> timings = entry.getValue();
			for(Map.Entry<Date, Date>  t: timings.entrySet())
			{
				
				String startTime = t.getKey().toString().split(" ")[3];
				String endTime = t.getValue().toString().split(" ")[3];
				System.out.println("  From "+startTime+" To "+endTime);
				
			}
		}
		//TODO sort the list chronological order and accept the requests based on the submission time. Right now ignored submission time.
	}
	
	public static boolean isValidMeeting(BookingRequest bookingObj, ArrayList<BookingRequest> existingMeetings)
	{
		Date bookingStartTime = bookingObj.getBookingDateTime();
		Date bookingEndTime = new Date(bookingObj.getBookingDateTime().getTime() + (bookingObj.getDuration()*3600*1000));
		
		Date ofcStartTime = ofc.getOpenTime();
		Date ofcEndTime = ofc.getCloseTime();
		
		ofcStartTime.setDate(bookingObj.getBookingDateTime().getDate());
		ofcEndTime.setDate(bookingObj.getBookingDateTime().getDate());
		
		ofcStartTime.setMonth(bookingObj.getBookingDateTime().getMonth());
		ofcEndTime.setMonth(bookingObj.getBookingDateTime().getMonth());
		
		ofcStartTime.setYear(bookingObj.getBookingDateTime().getYear());
		ofcEndTime.setYear(bookingObj.getBookingDateTime().getYear());
		
		if(bookingStartTime.before(ofcStartTime) || bookingEndTime.after(ofcEndTime))
			return false;
		
		for(BookingRequest meeting: existingMeetings)
		{
			Date meetingStartTime = meeting.getBookingDateTime();
			Date meetingEndTime = new Date(meeting.getBookingDateTime().getTime() + (meeting.getDuration()*3600*1000));
			
			if(bookingStartTime.after(meetingStartTime) && bookingEndTime.before(meetingEndTime))
				return false;
			
		}
		
		return true;
	}
}
