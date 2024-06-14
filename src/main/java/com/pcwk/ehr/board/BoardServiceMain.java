package com.pcwk.ehr.board;

import com.pcwk.ehr.cmn.PLog;

public class BoardServiceMain implements PLog{
	
	BoardService service;
	BoardDTO board01;
	
	public BoardServiceMain() {
		service = new BoardService();
		
		board01 = new BoardDTO(70, "제목_70", "내용_70", 0, "admin_70", "사용안함", "admin_70", "사용안함");
	}
	public void selectOneReadCnt() {
		log.debug("selectOneReadCnt()");
	
		BoardDTO dto = service.selectOneReadCnt(board01);
		if(null !=dto && dto.getFlag()==1) {
			log.debug("조회 및 readCount 처리 성공");
		}else {
			log.debug("조회 및 readCount 처리 실패");			
		}
	}
	
	public static void main(String[] args) {
		BoardServiceMain mService = new BoardServiceMain();
		mService.selectOneReadCnt();
	} // main

} // class
