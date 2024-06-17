package com.pcwk.ehr.join;

import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PLog;

public class JoinService implements PLog {
	private JoinDao dao;
	
	public JoinService() {
		dao = new JoinDao();
	}
	
	/**
	 * 목록 조회
	 * 
	 * @param search
	 * @return List<BoardDTO>
	 */
	public List<JoinDTO> doRetrieve(DTO search) {
		return dao.doRetrieve(search);
	}
}
