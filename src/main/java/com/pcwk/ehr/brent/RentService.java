package com.pcwk.ehr.brent;

import com.pcwk.ehr.cmn.PLog;

public class RentService implements PLog {
	
	private RentDAO dao;
	
	public RentService() {
		dao = new RentDAO();
	}
	
	public int doSave(RentDTO param) {
		return dao.doSave(param);
	}
}
