package BookingSystem;

import java.util.Date;

public class Office {
	private Date openTime;
	private Date closeTime;
	
	public static final String officeFormat = "HHmm";
	
	public Office(Date openTime, Date closeTime){
		this.openTime = openTime;
		this.closeTime = closeTime;
	}
	
	public Date getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}
	public Date getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(Date closeTime) {
		this.closeTime = closeTime;
	}
	
}
