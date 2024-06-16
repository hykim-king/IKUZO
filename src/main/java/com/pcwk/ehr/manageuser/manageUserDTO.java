package com.pcwk.ehr.manageuser;

import com.pcwk.ehr.cmn.DTO;

public class manageUserDTO extends DTO {
	private int rnum; // 번호
	private String  userId	 ;//유저 아이디
	private String  userName ;//유저 이름
	private String  isAdmin	 ;//관리자 여부
	private String rentBookYn; // 미납도서유무
	private String rentDate; // 대출날짜
	private String returnedDate; // 반납일
	private int extraSum; // 연체금액	
	private int rentCode; // 대출번호
	
	public manageUserDTO() {} // 빈통

	public manageUserDTO(int rnum, String userId, String userName, String isAdmin, String rentBookYn, String rentDate,
			String returnedDate, int extraSum, int rentCode) {
		super();
		this.rnum = rnum;
		this.userId = userId;
		this.userName = userName;
		this.isAdmin = isAdmin;
		this.rentBookYn = rentBookYn;
		this.rentDate = rentDate;
		this.returnedDate = returnedDate;
		this.extraSum = extraSum;
		this.rentCode = rentCode;
	}

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getRentBookYn() {
		return rentBookYn;
	}

	public void setRentBookYn(String rentBookYn) {
		this.rentBookYn = rentBookYn;
	}

	public String getRentDate() {
		return rentDate;
	}

	public void setRentDate(String rentDate) {
		this.rentDate = rentDate;
	}

	public String getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(String returnedDate) {
		this.returnedDate = returnedDate;
	}

	public int getExtraSum() {
		return extraSum;
	}

	public void setExtraSum(int extraSum) {
		this.extraSum = extraSum;
	}

	public int getRentCode() {
		return rentCode;
	}

	public void setRentCode(int rentCode) {
		this.rentCode = rentCode;
	}

	@Override
	public String toString() {
		return "BoardDTO [rnum=" + rnum + ", userId=" + userId + ", userName=" + userName + ", isAdmin=" + isAdmin
				+ ", rentBookYn=" + rentBookYn + ", rentDate=" + rentDate + ", returnedDate=" + returnedDate
				+ ", extraSum=" + extraSum + ", rentCode=" + rentCode + ", toString()=" + super.toString() + "]";
	}	
	
} // class