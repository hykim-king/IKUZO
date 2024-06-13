package com.pcwk.ehr.mypage_loanlist;

import com.pcwk.ehr.cmn.DTO;

public class LoanListDTO extends DTO{
	private int num              ;//목록번호
	private int bookCode         ;//시퀀스
	private int bookGenre        ;//장르코드
	private String genreName     ;//장르이름
	private String bookName      ;//도서제목
	private String author        ;//작가
	private String regId         ;//등록자
	private String regDt         ;//등록날짜
	private String modId         ;//수정자
	private String modDt         ;//수정날짜
	
	
	public LoanListDTO () {}


	public LoanListDTO(int num, int bookCode, int bookGenre, String genreName, String bookName, String author, String regId,
			String regDt, String modId, String modDt) {
		super();
		this.num = num;
		this.bookCode = bookCode;
		this.bookGenre = bookGenre;
		this.genreName = genreName;
		this.bookName = bookName;
		this.author = author;
		this.regId = regId;
		this.regDt = regDt;
		this.modId = modId;
		this.modDt = modDt;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public int getBookCode() {
		return bookCode;
	}


	public void setBookCode(int bookCode) {
		this.bookCode = bookCode;
	}


	public int getBookGenre() {
		return bookGenre;
	}


	public void setBookGenre(int bookGenre) {
		this.bookGenre = bookGenre;
	}


	public String getGenreName() {
		return genreName;
	}


	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}


	public String getBookName() {
		return bookName;
	}


	public void setBookName(String bookName) {
		this.bookName = bookName;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getRegId() {
		return regId;
	}


	public void setRegId(String regId) {
		this.regId = regId;
	}


	public String getRegDt() {
		return regDt;
	}


	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}


	public String getModId() {
		return modId;
	}


	public void setModId(String modId) {
		this.modId = modId;
	}


	public String getModDt() {
		return modDt;
	}


	public void setModDt(String modDt) {
		this.modDt = modDt;
	}


	@Override
	public String toString() {
		return "BookDTO [num=" + num + ", bookCode=" + bookCode + ", bookGenre=" + bookGenre + ", genreName="
				+ genreName + ", bookName=" + bookName + ", author=" + author + ", regId=" + regId + ", regDt=" + regDt
				+ ", modId=" + modId + ", modDt=" + modDt + ", toString()=" + super.toString() + "]";
	}


	
	

	

}
