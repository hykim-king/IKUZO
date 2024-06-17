package com.pcwk.ehr.b_favorite;

import com.pcwk.ehr.cmn.PLog;

public class B_FavoriteMain implements PLog {

	B_FavoriteDAO dao;
	B_FavoriteDTO fav;
	
	public B_FavoriteMain() {
		dao = new B_FavoriteDAO();
		
		fav = new B_FavoriteDTO(10,"user2",322,"SYSDATE");
	}
	
	public void doSave() {
		log.debug("do save()");
		int flag = dao.doSave(fav);
		if(1 == flag) {
			log.debug("성공 : {}", flag);
		}else 
			log.debug("실패 : {} ", flag);
	}
	
	public void doDelete() {
		
		int flag = dao.doDelete(fav);
		
		if(1 == flag) {
			log.debug("삭제 성공 : {}", flag);
		}else
			log.debug("삭제 실패 : {}", flag);
	}
	
	public static void main(String[] args) {
		B_FavoriteMain f = new B_FavoriteMain();
		//f.doSave();
		//f.doDelete();
	}

}
