package com.pcwk.ehr.libuser;

import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PLog;

public class BoardService implements PLog {

	private BoardDao dao;
	public	BoardService() {
		dao = new BoardDao();
	}
	
	// 목록 조회
	public List<BoardDTO> doRetrieve(DTO search){
		return dao.doRetrieve(search);
	}
	
	// 저장
	public int doSave(BoardDTO param) {
		return dao.doSave(param);
	}
	
	// 수정
	public int doUpdate(BoardDTO param) {
		return dao.doUpdate(param);
	}
	
	// 삭제
	public int doDelete(BoardDTO param) {
		return dao.doDelete(param);
	}	
	
	// doSelectOne + doUpdateReadcnt
	public BoardDTO selectOneReadCnt(BoardDTO param) {
		BoardDTO outVO = new BoardDTO();
		
		// 조회 성공시 updateReadCnt 호출
		int flag = dao.doUpdateReadcnt();
		log.debug("flag : {}", flag);
		
		outVO.setFlag(flag);
		
		// doSelectOne
		outVO = dao.doSelectOne(param);
		
		return outVO;
	}
} // class