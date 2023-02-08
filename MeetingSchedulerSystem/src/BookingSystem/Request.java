package BookingSystem;

import java.util.Date;

public class Request {
	private Date submitDateTime;
	private String empId;
	
	public static final String reqFormat = "yyyy-MM-dd HH:mm:ss";
	
	public Request(Date submitDateTime, String empId){
		this.submitDateTime = submitDateTime;
		this.empId = empId;
	}
	
	public Date getSubmitDateTime() {
		return submitDateTime;
	}
	public void setSubmitDate(Date submitDateTime) {
		this.submitDateTime = submitDateTime;
	}
	
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	
}
