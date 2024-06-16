package com.pcwk.ehr.manageuser;

import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PLog;

public class manageUserService implements PLog {

	private manageUserDao dao;
	public	manageUserService() {
		dao = new manageUserDao();
	}
	
	// 목록 조회
	public List<manageUserDTO> doRetrieve(DTO search){
		return dao.doRetrieve(search);
	}
	
	// 저장
	public int doSave(manageUserDTO param) {
		return dao.doSave(param);
	}
	
	// 수정
	public int doUpdate(manageUserDTO param) {
		return dao.doUpdate(param);
	}
	
	// 삭제
	public int doDelete(manageUserDTO param) {
		return dao.doDelete(param);
	}	
} // class