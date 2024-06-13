package com.pcwk.ehr.mypage_loanlist;

import java.util.List;

import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PLog;

public class BookService implements PLog {

	private LoanList dao;

	
	public BookService() {
		dao = new LoanList();

	}
	
	/**
	 * 마이페이지 대출목록 조회
	 * 
	 * @param search
	 * @return List<BoardDTO>
	 */
	public List<LoanListDTO> doRetrieve(DTO search) {
		return dao.doRetrieve(search);
	}
	
	
	
//	/**
//	 * 저장
//	 * @param param
//	 * @return
//	 */
//	public int doSave(BookDTO param) {
//		return dao.doSave(param);
//	}
//	
//	/**
//	 * 수정
//	 * @param param
//	 * @return
//	 */
//	public int doUpdate(BookDTO param) {
//		return dao.doUpdate(param);
//	}
//	
//	/**
//	 * 삭제
//	 * @param param
//	 * @return
//	 */
//	public int doDelete(BookDTO param) {
//		return dao.doDelete(param);
//	}
	 
	
//	//BookDao(doRetrieve) + BookGenreDao(doRetrieve)
//	public BoardDTO selectOneReadCnt(BoardDTO param) {
//		BoardDTO outVO = null;
//		
//		//doSelectOne
//		outVO = dao.doSelectOne(param);
//		
//		//조회 성공시 updateReadCnt 호출
//		if(null != outVO) {
//			int flag = dao.doUpdateReadCnt(param);
//			log.debug("flag:{}",flag);
//			
//			outVO.setFlag(flag);
//			
//		}
//		
//		return outVO;
//	}
}
