package com.pcwk.ehr.brent;

import com.pcwk.ehr.cmn.PLog;

public class RentMain implements PLog {

	RentDAO dao;
	RentDTO rent;
	
	public RentMain() {
		dao = new RentDAO();
		
		rent = new RentDTO(100,"user1",322,"24/06/17","24/06/20","24/06/20","Y",
				0,"SYSDATE","admin1","admin1","SYSDATE");
	}
	
	public void doSave() {
		log.debug("do save()");
		int flag = dao.doSave(rent);
		if(1 == flag) {
			log.debug("성공 : {}", flag);
		}else 
			log.debug("실패 : {} ", flag);
	}
	
	public static void main(String[] args) {
		RentMain r = new RentMain();
		//r.doSave();
	}

}
