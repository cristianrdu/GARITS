package org.servlets.garits.Accounts;

public class User {
//Should we make this class abstract?
	private int staffNo;
	private String userName="";
	private String name="";
	private String address="";
	private String tel="";
	private String email="";
	private String postCode="";
        private String role="";

    public String getRole() {
        return role;
    }
/**
	 * 
	 * @param setRole
	 */
    public void setRole(String role) {
        this.role = role;
    }
   

	public int getStaffNo() {
		return this.staffNo;
	}

	/**
	 * 
	 * @param staffNo
	 */
	public void setStaffNo(int staffNo) {
		this.staffNo = staffNo;
	}

	public String getUserName() {
		return this.userName;
	}

	/**
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return this.name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return this.address;
	}

	/**
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return this.tel;
	}

	/**
	 * 
	 * @param tel
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return this.email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public String getPostCode() {
		return this.postCode;
	}

	/**
	 * 
	 * @param postCode
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * 
	 * @param sno
	 * @param un
	 * @param n
	 * @param ad
	 * @param t
	 * @param em
	 * @param pc
	 */
	public User(int sno, String un, String n, String ad, String t, String em, String pc) {
		// TODO - implement User.User
		
	}

	public User() {
		// TODO - implement User.User
	}

    @Override
    public String toString() {
        return "User{" + "staffNo=" + staffNo + ", userName=" + userName + ", name=" + name + ", address=" + address + ", tel=" + tel + ", email=" + email + ", postCode=" + postCode + '}';
    }
        

}