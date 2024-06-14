package com.pcwk.ehr.libuser;

import com.pcwk.ehr.cmn.DTO;

public class BoardDTO extends DTO {
	private String  userId	 ;//유저 아이디
	private String  userName ;//유저 이름
	private String  isAdmin	 ;//관리자 여부
	private String rentBookYn; // 미납도서유무
	private int extraSum; // 연체금액	
	
	public BoardDTO() {} // 빈통

	public BoardDTO(String userId, String userName, String isAdmin, String rentBookYn, int extraSum) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.isAdmin = isAdmin;
		this.rentBookYn = rentBookYn;
		this.extraSum = extraSum;
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

	public int getExtraSum() {
		return extraSum;
	}

	public void setExtraSum(int extraSum) {
		this.extraSum = extraSum;
	}

	@Override
	public String toString() {
		return "libUserRentDTO [userId=" + userId + ", userName=" + userName + ", isAdmin=" + isAdmin + ", rentBookYn="
				+ rentBookYn + ", extraSum=" + extraSum + ", toString()=" + super.toString() + "]";
	}
	
} // class