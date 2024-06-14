package com.pcwk.ehr.board;

import java.util.Iterator;
import java.util.List;

import com.pcwk.ehr.cmn.PLog;

public class BoardDaoMain implements PLog{

	BoardDao dao;
	BoardDTO board01;
	
	public BoardDaoMain() {
		dao = new BoardDao();
		
		board01 = new BoardDTO(70, "제목_70", "내용_70", 0, "admin_70", "사용안함", "admin_70", "사용안함");
		// board01 = new BoardDTO(10, "제목_10", "내용_10", 0, "admin_10", "사용안함", "admin_10", "사용안함");
	}
	
	public void doSave(){
		log.debug("doSave()");
		int flag = dao.doSave(board01);
		if (1==flag) {
			log.debug("성공 : {}", flag);
		}else {
			log.debug("실패 : {}", flag);			
		}
	} // doSave끝 
	
	public void doDelete(){
		log.debug("doDelete()");
		int flag = dao.doDelete(board01);
		if (1==flag) {
			log.debug("삭제 성공 : {}", flag);
		}else {
			log.debug("삭제 실패 : {}", flag);			
		}
	} // doDelete끝 	
	
	// 내가 만든 조회수 메서드 시작
	public void readCntPlus(){
		log.debug("readCntPlus()");
		int flag = dao.readCntPlus(board01);
		if (1==flag) {
			log.debug("조회수 증가 : {}", flag);
		}else {
			log.debug("조회수 실패 : {}", flag);			
		}
	} // readCntPlus끝 		
	
	// 교수님이 만든 조회수 메서드 시작
	public void doUpdateReadcnt(){
		log.debug("doUpdateReadcnt()");
		int flag = dao.doUpdateReadcnt(board01);
		if (1==flag) {
			log.debug("조회수 증가(교수님) : {}", flag);
		}else {
			log.debug("조회수 실패(교수님) : {}", flag);			
		}
	} // doUpdateReadcnt끝 
	
	public void doSelectOne(){
		log.debug("doSelectOne()");
		BoardDTO outVO = dao.doSelectOne(board01);
		if (null != outVO) {
			log.debug("검색 성공 : {}", outVO);
		}else {
			log.debug("검색 실패 : {}", outVO);			
		}
	} // doSelectOne끝 	
	
	public void doUpdate(){
		log.debug("doUpdate()");
		// title,content,modid 뒤에 _u추가
			String updateStr = "_U";		
			board01.setTitle(board01.getTitle()+updateStr);
			board01.setContents(board01.getContents()+updateStr);
			board01.setModId(board01.getModId()+updateStr);
		// title,content,modid 뒤에 _u추가 끝
		int flag = dao.doUpdate(board01);
		if (1==flag) {
			log.debug("업데이트 성공 : {}", flag);
		}else {
			log.debug("업데이트 실패 : {}", flag);			
		}
	} // doUpdate끝 	
	
	public void doRetrieve() {
		log.debug("doRetrieve()");
		SearchDTO searchVO = new SearchDTO();
		searchVO.setPageNo(1);
		searchVO.setPageSize(10);
		
//		--WHERE title    LIKE :searchWord||'%'     "10" (제목)
//		--WHERE contents LIKE :searchWord||'%'     "20" (내용)
//		--WHERE mod_id      = :searchWord||'%'     "30" (아이디)
//		--WHERE title  LIKE   :searchWord||'%'     "40" (제목+내용)
//		   --OR contents LIKE :searchWord||'%'   
//		--WHERE seq         = :searchWord||'%'     "50" (시퀀스)
		
		// 검색구분
		//searchVO.setSearchDiv("50");
		//searchVO.setSearchWord("80");
		
		List<BoardDTO> list = dao.doRetrieve(searchVO);
		
		int i =0;
		
		for (BoardDTO vo :list) {
			log.debug("i: {}, vo: {}", ++i, vo);
		}
	} // doRetrieve 끝

	public void addAndGet() {
		// 1. 삭제 : 성공 실패 여부를 따지지 않는다
		// 2. 등록 : 
		// 3. 조회 : 
		log.debug("doRetrieve()");
		
		// 1.
		dao.doDelete(board01);
		
		// 2.
		int flag = dao.doSave(board01);
		if (1 == flag) {
			log.debug("등록 성공 : {}", flag);
		}else {
			log.debug("등록 실패 : {}", flag);
			return;
		}
		
		// 3.
		BoardDTO outVO = dao.doSelectOne(board01);		

		boolean isSame = isSameData(outVO, board01);
		
		if (false == isSame) {
			log.debug("─────────────────");
			log.debug("───────나가───────");
			log.debug("─────────────────");			
		}else {		
			log.debug("─────────────────");
			log.debug("───────성공───────");
			log.debug("─────────────────");
		}
		//m.doSelectOne();
		//m.doSave();
		//m.doDelete();	
	}
	
	public boolean isSameData(BoardDTO outVO, BoardDTO board01) {
		// board01, outVO 비교
				if (outVO.getSeq() != board01.getSeq()) {
					log.debug("실패 seq : {},{}", outVO.getSeq(),board01.getSeq());
					return false;
				}
				
				// 제목
				if (!outVO.getTitle().equals(board01.getTitle())) {
					log.debug("실패 title : {},{}", outVO.getTitle(),board01.getTitle());
					return false;
				}

				// 내용
				if (!outVO.getContents().equals(board01.getContents())) {
					log.debug("실패 contents : {},{}", outVO.getContents(),board01.getContents());
					return false;
				}
				
				// 등록자 ID
				if (!outVO.getRegId().equals(board01.getRegId())) {
					log.debug("실패 regId : {},{}", outVO.getRegId(),board01.getRegId());
					return false;
				}
				return true;
	}
	
	public static void main(String[] args) {
		BoardDaoMain m = new BoardDaoMain();
		m.doRetrieve();
		//m.readCntPlus();
		//m.doUpdateReadcnt();
		//m.doUpdate();
		//m.addAndGet();
		//m.doSave();
	} // main

} // class