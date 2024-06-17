package com.pcwk.ehr.managebook;

import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PLog;

public class ManageBookService implements PLog {

	private ManageBookDao dao;
	public	ManageBookService() {
		dao = new ManageBookDao();
	}
	
	// 목록 조회
	public List<ManageBookDTO> doRetrieve(DTO search){
		return dao.doRetrieve(search);
	}
	
	// 저장
	public int doSave(ManageBookDTO param) {
		return dao.doSave(param);
	}
	
	// 수정
	public int doUpdate(ManageBookDTO param) {
		return dao.doUpdate(param);
	}
	
	// 삭제
	public int doDelete(ManageBookDTO param) {
		return dao.doDelete(param);
	}	
} // class