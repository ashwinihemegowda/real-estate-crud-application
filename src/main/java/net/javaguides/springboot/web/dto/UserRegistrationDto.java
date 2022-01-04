package net.javaguides.springboot.web.dto;

public class UserRegistrationDto {
	private String Uname;
	private String email;
	private String password;
	
	public UserRegistrationDto(){
		
	}
	
	public UserRegistrationDto(String Uname, String email, String password) {
		super();
		this.Uname = Uname;
		this.email = email;
		this.password = password;
	}
	
	public String getUname() {
		return Uname;
	}
	public void setUname(String firstName) {
		this.Uname = Uname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
