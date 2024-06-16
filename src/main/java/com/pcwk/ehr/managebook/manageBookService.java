package com.pcwk.ehr.managebook;

import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PLog;

public class manageBookService implements PLog {

	private manageBookDao dao;
	public	manageBookService() {
		dao = new manageBookDao();
	}
	
	// 목록 조회
	public List<manageBookDTO> doRetrieve(DTO search){
		return dao.doRetrieve(search);
	}
	
	// 저장
	public int doSave(manageBookDTO param) {
		return dao.doSave(param);
	}
	
	// 수정
	public int doUpdate(manageBookDTO param) {
		return dao.doUpdate(param);
	}
	
	// 삭제
	public int doDelete(manageBookDTO param) {
		return dao.doDelete(param);
	}	
} // class