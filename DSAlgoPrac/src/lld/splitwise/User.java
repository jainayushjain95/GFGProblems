package lld.splitwise;

import java.util.UUID;

public class User {

	private String id;
	private String name;
	private String email;
	private String contactNo;
	
	public User(String id, String name, String email, String contactNo) {
		this.contactNo = contactNo;
		this.name = name;
		this.email = email;
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	
	
}
