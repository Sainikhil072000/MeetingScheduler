package BookingSystem;

import java.util.Date;

public class BookingRequest {
	private Date bookingDateTime;
	private int duration;
	
	public static final String bookingFormat = "yyyy-MM-dd HH:mm";
	
	public BookingRequest(Date bookingDateTime, int duration) {
		this.bookingDateTime = bookingDateTime;
		this.duration = duration;
	}
	
	public Date getBookingDateTime() {
		return bookingDateTime;
	}
	public void setBookingDate(Date bookingDateTime) {
		this.bookingDateTime = bookingDateTime;
	}
	
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
}
