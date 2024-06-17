package com.pcwk.ehr.managebook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pcwk.ehr.cmn.ConnectionMaker;
import com.pcwk.ehr.cmn.DBUtil;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.WorkDiv;

public class manageBookDao implements WorkDiv<manageBookDTO>, PLog {

	private ConnectionMaker connectionMaker;
	
	public manageBookDao() {
		connectionMaker = new ConnectionMaker();
	}

	@Override
	public List<manageBookDTO> doRetrieve(DTO search) {
		// 1. DriverManager
		// 2. Connection
		// 3. Statement/preparedStatement
		// 4. ResultSet
		// 5. 연결종료
		SearchDTO searchVO = (SearchDTO)search;

		StringBuilder sb = new StringBuilder(1000);
		StringBuilder sbWhere = new StringBuilder(1000);

		if (null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("10")) {
			sbWhere.append("WHERE BOOK_NAME LIKE ?||'%' \n");
		}else if(null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("20")) {
			sbWhere.append("WHERE GENRE_NAME LIKE ?||'%' \n");			
		}else if(null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("30")) {
			sbWhere.append("WHERE AUTHOR LIKE ?||'%' \n");			
		}else if(null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("40")) {
			sbWhere.append("WHERE PUBLISHER LIKE ?||'%' \n");		
		}else if(null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("50")) {
			sbWhere.append("WHERE RENTYN LIKE ?||'%' \n");		
		}
		
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null; // SQL+PARAM
		ResultSet	rs			= null; // SQL문의 결과
		
		List<manageBookDTO> list = new ArrayList<manageBookDTO>();

		sb.append("SELECT *																					\n");
		sb.append("FROM (                                                                                   \n");
		sb.append("    SELECT ROWNUM AS rnum, subquery.*                                                    \n");
		sb.append("    FROM (                                                                               \n");
		sb.append("        SELECT a.BOOK_NAME,                                                              \n");
		sb.append("               b.GENRE_NAME,                                                             \n");
		sb.append("               a.AUTHOR,                                                                 \n");
		sb.append("               a.PUBLISHER,                                                              \n");
		sb.append("               NVL(TO_CHAR(c.RENT_DATE, 'YY/MM/DD'), '없음') AS RENT_DATE,                \n");
		sb.append("               NVL(TO_CHAR(c.DUE_DATE, 'YY/MM/DD'), '없음') AS DUE_DATE,                  \n");
		sb.append("               NVL(TO_CHAR(c.RETURNED_DATE, 'YY/MM/DD'), '없음') AS RETURNED_DATE,        \n");
		sb.append("               CASE                                                                      \n");
		sb.append("                   WHEN c.RENT_DATE IS NOT NULL AND c.RETURNED_DATE IS NULL THEN '불가능' \n");
		sb.append("                   ELSE '가능'                                                            \n");
		sb.append("               END AS RENTYN,                                                            \n");
		sb.append("               NVL(c.NORETURN_COUNT, '대출여부없음') AS NORETURN_COUNT                       \n");
		sb.append("        FROM BOOK a                                                                      \n");
		sb.append("        LEFT JOIN RENT c ON a.BOOK_CODE = c.BOOK_CODE                                    \n");
		sb.append("        LEFT JOIN B_GENRE b ON a.BOOK_GENRE = b.BOOK_GENRE                               \n");
//		sb.append("        WHERE     GENRE_NAME LIKE '건강%'                                                 \n");
		sb.append(sbWhere.toString());				
		sb.append("        ORDER BY a.BOOK_CODE                                                             \n");
		sb.append("    ) subquery                                                                           \n");
//		sb.append("    WHERE ROWNUM <= 10   							 						            \n");
//		sb.append("WHERE ROWNUM <= ( :pageSize * (:pageNo -1)+:pageSize)									\n");
		sb.append("WHERE ROWNUM <= ( ? * (? -1)+?)								 							\n");		
		sb.append(")                                                                                        \n");
//		sb.append("WHERE rnum >= 1                                                                          \n");
//		sb.append("	WHERE rnum >= ( :pageSize * (:pageNo -1) + 1)										    \n");		
		sb.append("	WHERE rnum >= ( ? * (? -1) + 1)										 					\n");			
		
		log.debug("1.sql: {} \n", sb.toString());
		log.debug("2.conn: {} \n", conn);
		log.debug("3.param: {}", search);
		
		// Select
		try {
			// conn.setAutoCommit(false); // 자동 커밋 정지
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4. pstmt : {}", pstmt);
			
			// param 설정
			
			// paging
			if (null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("10")) {
				log.debug("4.1 searchDiv : {}", searchVO.getSearchDiv());
				// 검색어
				pstmt.setString(1, searchVO.getSearchWord());
				// ROWNUM
				pstmt.setInt(2, searchVO.getPageSize());
				pstmt.setInt(3, searchVO.getPageNo());
				pstmt.setInt(4, searchVO.getPageSize());
				
				// rnum
				pstmt.setInt(5, searchVO.getPageSize());
				pstmt.setInt(6, searchVO.getPageNo());
			}else if(null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("20")) {
				log.debug("4.1 searchDiv : {}", searchVO.getSearchDiv());
				// 검색어
				pstmt.setString(1, searchVO.getSearchWord());
				// ROWNUM
				pstmt.setInt(2, searchVO.getPageSize());
				pstmt.setInt(3, searchVO.getPageNo());
				pstmt.setInt(4, searchVO.getPageSize());
				
				// rnum
				pstmt.setInt(5, searchVO.getPageSize());
				pstmt.setInt(6, searchVO.getPageNo());
			}else if(null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("30")) {
				log.debug("4.1 searchDiv : {}", searchVO.getSearchDiv());
				// 검색어
				pstmt.setString(1, searchVO.getSearchWord());
				// ROWNUM
				pstmt.setInt(2, searchVO.getPageSize());
				pstmt.setInt(3, searchVO.getPageNo());
				pstmt.setInt(4, searchVO.getPageSize());
				
				// rnum
				pstmt.setInt(5, searchVO.getPageSize());
				pstmt.setInt(6, searchVO.getPageNo());
			}else if(null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("40")) {
				log.debug("4.1 searchDiv : {}", searchVO.getSearchDiv());
				// 검색어
				pstmt.setString(1, searchVO.getSearchWord());
				// ROWNUM
				pstmt.setInt(2, searchVO.getPageSize());
				pstmt.setInt(3, searchVO.getPageNo());
				pstmt.setInt(4, searchVO.getPageSize());
				
				// rnum
				pstmt.setInt(5, searchVO.getPageSize());
				pstmt.setInt(6, searchVO.getPageNo());
			}else if(null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("50")) {
				log.debug("4.1 searchDiv : {}", searchVO.getSearchDiv());
				// 검색어
				pstmt.setString(1, searchVO.getSearchWord());
				// ROWNUM
				pstmt.setInt(2, searchVO.getPageSize());
				pstmt.setInt(3, searchVO.getPageNo());
				pstmt.setInt(4, searchVO.getPageSize());
				
				// rnum
				pstmt.setInt(5, searchVO.getPageSize());
				pstmt.setInt(6, searchVO.getPageNo());
			}else {
				// ROWNUM
				pstmt.setInt(1, searchVO.getPageSize());
				pstmt.setInt(2, searchVO.getPageNo());
				pstmt.setInt(3, searchVO.getPageSize());
				
				// rnum
				pstmt.setInt(4, searchVO.getPageSize());
				pstmt.setInt(5, searchVO.getPageNo());
			}
			
			// SELECT실행
			rs = pstmt.executeQuery();
			log.debug("5.rs:\n" + rs);
			while (rs.next()) {
				// 건수 최대값만 정해짐
				manageBookDTO outVO = new manageBookDTO();
				
				outVO.setBookName(rs.getString("BOOK_NAME"));
				outVO.setGenre(rs.getString("GENRE_NAME"));
				outVO.setAuthor(rs.getString("AUTHOR"));
				outVO.setPublisher(rs.getString("PUBLISHER"));
				outVO.setRentDate(rs.getString("RENT_DATE"));
				outVO.setDueDate(rs.getString("DUE_DATE"));
				outVO.setRetunredDate(rs.getString("RETURNED_DATE"));
				outVO.setRentYn(rs.getString("RENTYN"));
				outVO.setNoreturnCount(rs.getString("NORETURN_COUNT"));
				
				list.add(outVO);
			} // while
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(conn, pstmt, rs); 
			log.debug("5. finally conn : {} pstmt : {} rs : {}",conn, pstmt, rs);
		}// try catch finally
		
		return list;
		
	} // doRetrieve 끝

	@Override
	public int doDelete(manageBookDTO param) {
		int flag = 0;
		Connection conn = connectionMaker.getConnection();
		
		PreparedStatement pstmt = null; // SQL + PARAM		
		StringBuilder sb = new StringBuilder();
		// 아래 SQL코드에는 세미콜론(;) 금지
		sb.append("DELETE 	FROM BOOK	   \n");
		sb.append("WHERE 	BOOK_CODE = ?  \n");	
		
		log.debug("1. sql : {}", sb.toString());
		log.debug("2. conn : {}", conn);
		log.debug("3. param : {}", param);
		
		try {
			// conn.setAutoCommit(false); // 자동 커밋 정지
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4. pstmt : {}", pstmt);
			
			pstmt.setInt(1, param.getBookCode());
			
			// DML 수행
			flag = pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(conn, pstmt);
			
			log.debug("5. finally conn : {} pstmt : {}", conn, pstmt);
		}// try catch
		log.debug("6. flag: {}", flag);
		
		return flag;
	}
	
	@Override
	public manageBookDTO doSelectOne(manageBookDTO param) {
		manageBookDTO outVO = null; // 단건조회 결과
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null; // SQL+PARAM
		ResultSet	rs			= null; // SQL문의 결과
		
		StringBuilder sb = new StringBuilder(500);
		
		sb.append("SELECT a.BOOK_NAME,      														\n");                                                       
		sb.append("       b.GENRE_NAME,                                                             \n");
		sb.append("       a.AUTHOR,                                                                 \n");
		sb.append("       a.PUBLISHER,                                                              \n");
		sb.append("       CASE                                                                      \n");
		sb.append("           WHEN c.RENT_DATE IS NOT NULL AND c.RETURNED_DATE IS NULL THEN '불가능' \n");
		sb.append("           ELSE '가능'                                                            \n");
		sb.append("       END AS RENTYN                                                             \n");
		sb.append("FROM BOOK a                                                                      \n");
		sb.append("LEFT JOIN RENT c ON a.BOOK_CODE = c.BOOK_CODE                                    \n");
		sb.append("LEFT JOIN B_GENRE b ON a.BOOK_GENRE = b.BOOK_GENRE                               \n");
		sb.append("WHERE a.BOOK_NAME = ?                                                            \n");
		
		log.debug("1.sql: {} \n", sb.toString());
		log.debug("2.conn: {} \n", conn);
		log.debug("3.param: {}", param);
		
		try {
			// conn.setAutoCommit(false); // 자동 커밋 정지
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4. pstmt : {}", pstmt);
			
			pstmt.setString(1, param.getBookName());
			
			// SELECT실행
			rs = pstmt.executeQuery();
			log.debug("5.rs:\n" + rs);
			
			if(rs.next()) {
				outVO = new manageBookDTO();
				
				outVO.setBookName(rs.getString("BOOK_NAME"));
				outVO.setGenre(rs.getString("GENRE_NAME"));
				outVO.setAuthor(rs.getString("AUTHOR"));
				outVO.setPublisher(rs.getString("PUBLISHER"));
				outVO.setRentYn(rs.getString("RENTYN"));
				
				log.debug("6.outVO:" + outVO);
			} // if
			
		} catch (SQLException e) {
			log.debug("────────────────────────────");
			log.debug("SQLException : " + e.getMessage());
			log.debug("────────────────────────────");
		} finally{
			DBUtil.close(conn, pstmt, rs); 
		}// try catch finally		
		return outVO;
	} // doSelectOne 끝

	@Override
	public int doSave(manageBookDTO param) {
		int flag = 0;
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null; // SQL + PARAM
		
		StringBuilder sb = new StringBuilder();
		// 아래 SQL코드에는 세미콜론(;) 금지
		sb.append("INSERT INTO BOOK(					\n");
		sb.append("    BOOK_NAME,                       \n");
		sb.append("    BOOK_GENRE,                      \n");
		sb.append("    ISBN,                            \n");
		sb.append("    BOOK_PUB_DATE,                   \n");
		sb.append("    PUBLISHER,                       \n");
		sb.append("    AUTHOR,                          \n");
		sb.append("    BOOK_INFO,                       \n");
		sb.append("    REG_ID,                          \n");
		sb.append("    MOD_ID                           \n");
		sb.append(")                                    \n");
		sb.append("VALUES(                              \n");
		sb.append("    ?,                         		\n");
		sb.append("    ?,	                            \n");
		sb.append("    ?,                   			\n");
		sb.append("    ?, 								\n");
		sb.append("    ?,		                        \n");
		sb.append("    ?,           		            \n");
		sb.append("    ?,	                            \n");
		sb.append("    ?,                       		\n");
		sb.append("    ?                         		\n");
		sb.append(")                                    \n");	
		
		log.debug("1. sql : {}", sb.toString());
		log.debug("2. conn : {}", conn);
		log.debug("3. param : {}", param);
		
		try {
			// conn.setAutoCommit(false); // 자동 커밋 정지
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4. pstmt : {}", pstmt);
			
			// param 설정
			pstmt.setString(1, param.getBookName());
			pstmt.setString(2, param.getGenre());
			pstmt.setLong(3, param.getIsbn());
			pstmt.setString(4, param.getBookPubDate());
			pstmt.setString(5, param.getPublisher());
			pstmt.setString(6, param.getAuthor());
			pstmt.setString(7, param.getBookInfo());
			pstmt.setString(8, param.getRegId());
			pstmt.setString(9, param.getRegId());
			
			// DML
			flag = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(conn, pstmt);
			
			log.debug("5. finally conn : {} pstmt : {}", conn, pstmt);
		}// try catch
		log.debug("6. flag: {}", flag);
		
		return flag;
	}

	@Override
	public int doUpdate(manageBookDTO param) {
		int flag = 0;
		Connection conn = connectionMaker.getConnection();
		
		PreparedStatement pstmt = null; // SQL + PARAM		
		StringBuilder sb = new StringBuilder(500);
		// 아래 SQL코드에는 세미콜론(;) 금지
		sb.append("UPDATE  BOOK				 \n");
		sb.append("SET     BOOK_NAME = ?,    \n");
		sb.append("        BOOK_GENRE = ?,   \n");
		sb.append("        AUTHOR = ?,       \n");
		sb.append("        PUBLISHER = ?,    \n");
		sb.append("        ISBN = ?,         \n");
		sb.append("        BOOK_PUB_DATE = ?,\n");
		sb.append("        BOOK_INFO = ?     \n");
		sb.append("WHERE   BOOK_CODE = ?     \n");
		
		log.debug("1. sql : {}", sb.toString());
		log.debug("2. conn : {}", conn);
		log.debug("3. param : {}", param);
		
		try {
			// conn.setAutoCommit(false); // 자동 커밋 정지
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4. pstmt : {}", pstmt);

			pstmt.setString(1, param.getBookName());
			pstmt.setInt(2, param.getBookGenre());
			pstmt.setString(3, param.getAuthor());
			pstmt.setString(4, param.getPublisher());
			pstmt.setLong(5, param.getIsbn());
			pstmt.setString(6, param.getBookPubDate());
			pstmt.setString(7, param.getBookInfo());
			pstmt.setInt(8, param.getBookCode());
			
			// DML 수행
			flag = pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(conn, pstmt);
			
			log.debug("5. finally conn : {} pstmt : {}", conn, pstmt);
		}// try catch
		log.debug("6. flag: {}", flag);
		
		return flag;
	}
	
	public int doDueDateUpdate(manageBookDTO param) {
		int flag = 0;
		Connection conn = connectionMaker.getConnection();
		
		PreparedStatement pstmt = null; // SQL + PARAM		
		StringBuilder sb = new StringBuilder(500);
		// 아래 SQL코드에는 세미콜론(;) 금지
		sb.append("UPDATE RENT					\n");
		sb.append("SET DUE_DATE = DUE_DATE + 7, \n");
		sb.append("    NORETURN_COUNT = 'N'     \n");
		sb.append("WHERE NORETURN_COUNT = 'Y'   \n");
		sb.append("AND   RENT_CODE = ? 	        \n");
		
		log.debug("1. sql : {}", sb.toString());
		log.debug("2. conn : {}", conn);
		log.debug("3. param : {}", param);
		
		try {
			// conn.setAutoCommit(false); // 자동 커밋 정지
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4. pstmt : {}", pstmt);

			pstmt.setInt(1, param.getRentCode());
			
			// DML 수행
			flag = pstmt.executeUpdate();			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(conn, pstmt);
			
			log.debug("5. finally conn : {} pstmt : {}", conn, pstmt);
		}// try catch
		log.debug("6. flag: {}", flag);
		
		return flag;
	}
	
	@Override
	public int doSaveFile() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int doReadFile() {
		// TODO Auto-generated method stub
		return 0;
	}

} // class
