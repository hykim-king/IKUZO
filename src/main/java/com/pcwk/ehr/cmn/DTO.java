package com.pcwk.ehr.cmn;

public class DTO {
	
	private int flag; // DML작성 상태값
	private int num; // 글번호
	private int totalCnt; // 총 글수
	
	public DTO() {
		
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "DTO [flag=" + flag + ", num=" + num + ", totalCnt=" + totalCnt + "]";
	}
	
	
} // class 끝
