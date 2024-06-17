package com.pcwk.ehr.manageuser;

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
import com.pcwk.ehr.cmn.SearchDTO;
import com.pcwk.ehr.cmn.WorkDiv;

public class ManageUserDao implements WorkDiv<ManageUserDTO>, PLog {

	private ConnectionMaker connectionMaker;
	
	public ManageUserDao() {
		connectionMaker = new ConnectionMaker();
	}

	@Override
	public List<ManageUserDTO> doRetrieve(DTO search) {
		// 1. DriverManager
		// 2. Connection
		// 3. Statement/preparedStatement
		// 4. ResultSet
		// 5. 연결종료
		SearchDTO searchVO = (SearchDTO)search;

		StringBuilder sb = new StringBuilder(1000);
		StringBuilder sbWhere = new StringBuilder(1000);

		if (null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("10")) {
			sbWhere.append("AND USER_ID LIKE ?||'%' \n");
		}else if(null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("20")) {
			sbWhere.append("AND USER_NAME LIKE ?||'%' \n");			
		}
		
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null; // SQL+PARAM
		ResultSet	rs			= null; // SQL문의 결과
		
		List<ManageUserDTO> list = new ArrayList<ManageUserDTO>();

		sb.append("SELECT * FROM (																							\n");
		sb.append("    SELECT ROWNUM AS num, subquery.*                                                                    \n");
		sb.append("    FROM (                                                                                               \n");
		sb.append("        SELECT  a.USER_ID AS USER_ID,                                                                    \n");
		sb.append("                a.USER_NAME AS USER_NAME,                                                                \n");
		sb.append("                DECODE(a.IS_ADMIN, 'Y', '관리자', '회원') AS IS_ADMIN,                                     \n");
		sb.append("                NVL(b.EXTRA_SUM, 0) AS EXTRA_SUM,                                                        \n");
		sb.append("                CASE                                                                                     \n");
		sb.append("                    WHEN b.USER_ID IS NOT NULL AND b.returned_date IS NULL THEN '미납'                    \n");
		sb.append("                    ELSE '없음'                                                                           \n");
		sb.append("                END AS RENTYN                                                                            \n");
		sb.append("        FROM    LIB_USER a,                                                                              \n");
		sb.append("                (SELECT USER_ID, EXTRA_SUM, RETURNED_DATE                                                \n");
		sb.append("                        FROM (                                                                           \n");
		sb.append("                            SELECT USER_ID, EXTRA_SUM, RETURNED_DATE,                                    \n");
		sb.append("                                   ROW_NUMBER() OVER (PARTITION BY USER_ID ORDER BY DUE_DATE DESC) AS rn \n");
		sb.append("                            FROM RENT                                                                    \n");
		sb.append("                        )                                                                                \n");
		sb.append("                        WHERE rn = 1                                                                     \n");
		sb.append("                ) b                                                                                      \n");
		sb.append("        WHERE a.USER_ID = b.USER_ID(+)                                                                   \n");
//		sb.append("        AND USER_NAME LIKE '최진서%'                                                                       \n");
		sb.append("        ORDER BY a.USER_ID                                                                               \n");
		sb.append("    ) subquery                                                                                           \n");
//		sb.append("		WHERE ROWNUM <= 10                      										 					\n");
//		sb.append("WHERE ROWNUM <= ( :pageSize * (:pageNo -1)+:pageSize)								 					\n");
		sb.append("WHERE ROWNUM <= ( ? * (? -1)+?)								 											\n");
		sb.append(")                                                                                                        \n");
//		sb.append("	WHERE rnum >= 1                              															\n");
//		sb.append("	WHERE rnum >= ( :pageSize * (:pageNo -1) + 1)												 			\n");
		sb.append("	WHERE num >= ( ? * (? -1) + 1)										 									\n");
		
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
				// 검색어
				pstmt.setString(7, searchVO.getSearchWord());
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
				// 검색어
				pstmt.setString(7, searchVO.getSearchWord());
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
				// 검색어
				pstmt.setString(7, searchVO.getSearchWord());
			}else if(null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("40")) {
				log.debug("4.1 searchDiv : {}", searchVO.getSearchDiv());
				// 검색어
				pstmt.setString(1, searchVO.getSearchWord());
				pstmt.setString(2, searchVO.getSearchWord());
				// ROWNUM
				pstmt.setInt(3, searchVO.getPageSize());
				pstmt.setInt(4, searchVO.getPageNo());
				pstmt.setInt(5, searchVO.getPageSize());
				
				// rnum
				pstmt.setInt(6, searchVO.getPageSize());
				pstmt.setInt(7, searchVO.getPageNo());
				// 검색어
				pstmt.setString(8, searchVO.getSearchWord());
				pstmt.setString(9, searchVO.getSearchWord());
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
				ManageUserDTO outVO = new ManageUserDTO();
				
				outVO.setRnum(rs.getInt("num"));
				outVO.setUserId(rs.getString("USER_ID"));
				outVO.setUserName(rs.getString("USER_NAME"));
				outVO.setIsAdmin(rs.getString("IS_ADMIN"));
				outVO.setExtraSum(rs.getInt("EXTRA_SUM"));
				outVO.setRentBookYn(rs.getString("RENTYN"));
				
				list.add(outVO);
			} // while
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBUtil.close(conn, pstmt, rs); 
			log.debug("5. finally conn : {} pstmt : {} rs : {}",conn, pstmt, rs);
		}// try catch finally
		
		return list;
	}

	@Override
	public int doDelete(ManageUserDTO param) {
		int flag = 0;
		Connection conn = connectionMaker.getConnection();
		
		PreparedStatement pstmt = null; // SQL + PARAM		
		StringBuilder sb = new StringBuilder();
		// 아래 SQL코드에는 세미콜론(;) 금지
		sb.append("DELETE 	FROM LIB_USER\n");
		sb.append("WHERE 	USER_ID = ?  \n");	
		
		log.debug("1. sql : {}", sb.toString());
		log.debug("2. conn : {}", conn);
		log.debug("3. param : {}", param);
		
		try {
			// conn.setAutoCommit(false); // 자동 커밋 정지
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4. pstmt : {}", pstmt);
			
			pstmt.setString(1, param.getUserId());
			
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
	public ManageUserDTO doSelectOne(ManageUserDTO param) {
		ManageUserDTO outVO = null; // 단건조회 결과
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null; // SQL+PARAM
		ResultSet	rs			= null; // SQL문의 결과
		
		StringBuilder sb = new StringBuilder(500);
		
		sb.append("SELECT * FROM (																		 \n");
		sb.append("    SELECT  ROWNUM AS num, subquery.*                                                 \n");
		sb.append("    FROM (                                                                            \n");
		sb.append("        SELECT  a.USER_ID,                                                 \n");
		sb.append("                a.USER_NAME,                                             \n");
		sb.append("                DECODE(a.IS_ADMIN, 'Y', '관리자', '회원') AS IS_ADMIN,                \n");
		sb.append("                NVL(b.EXTRA_SUM, 0) AS extra_sum,                                     \n");
		sb.append("                CASE                                                                  \n");
		sb.append("                    WHEN b.USER_ID IS NOT NULL AND b.returned_date IS NULL THEN '미납'\n");
		sb.append("                    ELSE '없음'                                                       \n");
		sb.append("                END AS RENTYN                                                         \n");
		sb.append("        FROM    LIB_USER a, RENT b                                                    \n");
		sb.append("        WHERE   a.USER_ID = b.USER_ID(+)                                              \n");
		sb.append("        AND     a.USER_ID = ?		                                                 \n");
		sb.append("        ORDER BY a.USER_ID DESC                                                       \n");
		sb.append("    ) subquery                                                                        \n");	
		sb.append(")                                                                                     \n");
		
		log.debug("1.sql: {} \n", sb.toString());
		log.debug("2.conn: {} \n", conn);
		log.debug("3.param: {}", param);
		
		try {
			// conn.setAutoCommit(false); // 자동 커밋 정지
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4. pstmt : {}", pstmt);
			
			pstmt.setString(1, param.getUserId());
			
			// SELECT실행
			rs = pstmt.executeQuery();
			log.debug("5.rs:\n" + rs);
			
			if(rs.next()) {
				outVO = new ManageUserDTO();
				
				outVO.setUserId(rs.getString("USER_ID"));
				outVO.setUserName(rs.getString("USER_NAME"));
				outVO.setIsAdmin(rs.getString("IS_ADMIN"));
				outVO.setExtraSum(rs.getInt("EXTRA_SUM"));
				outVO.setRentBookYn(rs.getString("RENTYN"));
				
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
	public int doUpdate(ManageUserDTO param) {
		int flag = 0;
		Connection conn = connectionMaker.getConnection();
		
		PreparedStatement pstmt = null; // SQL + PARAM		
		StringBuilder sb = new StringBuilder(500);
		// 아래 SQL코드에는 세미콜론(;) 금지
		sb.append("UPDATE RENT					  	\n");	
		sb.append("SET    RETURNED_DATE = SYSDATE	\n");
		sb.append("WHERE  RENT_CODE = ?				\n");
		
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

	public int doUpdateBack(ManageUserDTO param) {
		int flag = 0;
		Connection conn = connectionMaker.getConnection();
		
		PreparedStatement pstmt = null; // SQL + PARAM		
		StringBuilder sb = new StringBuilder(500);
		// 아래 SQL코드에는 세미콜론(;) 금지
		sb.append("UPDATE RENT					  	\n");	
		sb.append("SET    RETURNED_DATE = null		\n");
		sb.append("WHERE  RENT_CODE = ?				\n");
		
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
	} // 반납취소
	
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

	@Override
	public int doSave(ManageUserDTO param) {
		// TODO Auto-generated method stub
		return 0;
	}

} // class
