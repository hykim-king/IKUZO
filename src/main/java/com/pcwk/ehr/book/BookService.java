package com.pcwk.ehr.book;

import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PLog;

public class BookService implements PLog {
	
	private BookDAO dao;
	
	public BookService() {
		dao = new BookDAO();
	}
	
	/**
	 * 목록 조회
	 * @param search
	 * @return
	 */
	public List<BookDTO> doRetrieve(DTO search){
		return dao.doRetrieve(search);
	}
	
	/**
	 * 저장
	 * @param param
	 * @return
	 */
	public int doSave(BookDTO param) {
		return dao.doSave(param);
	}
	
	/**
	 * 수정
	 * @param param
	 * @return
	 */
	public int doUpdate(BookDTO param) {
		return dao.doUpdate(param);
	}
	
	/**
	 * 삭제
	 * @param param
	 * @return
	 */
	public int doDelete(BookDTO param) {
		return dao.doDelete(param);
	}
	
	/**
	 * 단건 조회
	 * @param param
	 * @return
	 */
	public BookDTO doSelectOne(BookDTO param) {
		return dao.doSelectOne(param);
	}
}
