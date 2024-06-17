package com.pcwk.ehr.cmn;

public class SearchDTO extends DTO {

	private int pageNo;   // page 번호 : 1,2,3...
	private int pageSize; // page size : 10,20,50,100,200...

    private String searchDiv;  //검색구분
    private String searchWord; //검색어
    
	public SearchDTO() {
	}

	public String getSearchDiv() {
		return searchDiv;
	}

	public void setSearchDiv(String searchDiv) {
		this.searchDiv = searchDiv;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "SearchDTO [pageNo=" + pageNo + ", pageSize=" + pageSize + ", searchDiv=" + searchDiv + ", searchWord="
				+ searchWord + ", toString()=" + super.toString() + "]";
	}

	

}
