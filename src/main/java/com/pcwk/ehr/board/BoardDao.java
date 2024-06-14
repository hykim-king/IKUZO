package com.pcwk.ehr.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pcwk.ehr.cmn.ConnectionMarker;
import com.pcwk.ehr.cmn.DBUtil;
import com.pcwk.ehr.cmn.DTO;
import com.pcwk.ehr.cmn.PLog;
import com.pcwk.ehr.cmn.WorkDiv;

public class BoardDao implements WorkDiv<BoardDTO>, PLog {

	private ConnectionMarker connectionMaker;
	
	public BoardDao() {
		connectionMaker = new ConnectionMarker();
	}

	@Override
	public List<BoardDTO> doRetrieve(DTO search) {
		// 1. DriverManager
		// 2. Connection
		// 3. Statement/preparedStatement
		// 4. ResultSet
		// 5. 연결종료
		SearchDTO searchVO = (SearchDTO)search;

		StringBuilder sb = new StringBuilder(1000);
		StringBuilder sbWhere = new StringBuilder(1000);

		if (null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("10")) {
			sbWhere.append("WHERE title LIKE ?||'%' \n");
		}else if(null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("20")) {
			sbWhere.append("WHERE contents LIKE ?||'%' \n");			
		}else if(null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("30")) {
			sbWhere.append("WHERE mod_id = ? \n");			
		}else if(null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("40")) {
			sbWhere.append("WHERE title LIKE ?||'%' \n");		
			sbWhere.append("OR contents LIKE ?||'%' \n");		
		}else if(null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("50")) {
			sbWhere.append("WHERE seq = ? \n");			
		}
		
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null; // SQL+PARAM
		ResultSet	rs			= null; // SQL문의 결과
		
		List<BoardDTO> list = new ArrayList<BoardDTO>();

		sb.append("SELECT A.*,B.*									\n");
		sb.append("FROM(                                            \n");
		sb.append("	SELECT  TT1.rnum AS num,                        \n");
		sb.append("			TT1.seq,                             	\n");
		sb.append("			TT1.title,                              \n");
		sb.append("			TT1.contents,                           \n");
		sb.append("			TT1.read_cnt,                           \n");
		sb.append("			TT1.mod_id,                             \n");
		sb.append("			DECODE(TO_CHAR(TT1.mod_dt, 'YYYY/MM/DD')\n");
		sb.append("			, TO_CHAR(SYSDATE, 'YYYY/MM/DD')        \n");
		sb.append("			, TO_CHAR(tt1.mod_dt, 'HH24:MI')        \n");
		sb.append("			, TO_CHAR(tt1.mod_dt, 'YYYY/MM/DD')     \n");
		sb.append("			) AS mod_dt                           	\n");
		sb.append("	FROM(                                           \n");
		sb.append("		SELECT ROWNUM AS rnum, T1.*                 \n");
		sb.append("		FROM(                                       \n");
		sb.append("			SELECT          *                       \n");
		sb.append("			FROM            board                   \n");
		sb.append("			-- WHERE                                \n");
		//--WHERE-------------------------------------------------------
		//sb.append("		WHERE ROWNUM <= 10                      \n");
		//sb.append("WHERE ROWNUM <= ( :pageSize * (:pageNo -1)+:pageSize)\n");
		sb.append(sbWhere.toString());
		//--WHERE-------------------------------------------------------				
		sb.append("			ORDER BY        mod_dt DESC             \n");
		sb.append("		)T1                                         \n");
		sb.append("WHERE ROWNUM <= ( ? * (? - 1)+?)					\n");
		sb.append("	)TT1                                            \n");
		//sb.append("	WHERE rnum >= 1                              \n");
		//sb.append("	WHERE rnum >= ( :pageSize * (:pageNo -1) + 1)\n");
		sb.append("	WHERE rnum >= (? * (? -1) + 1)					\n");
		sb.append("	-- WHERE num BETWEEN 1 AND 10                   \n");
		sb.append(")A,(                                             \n");
		sb.append("	-- 1~10 : 1PAGE                                 \n");
		sb.append("	SELECT  COUNT(*) totalCnt                       \n");
		sb.append("	FROM    board                                   \n");
		sb.append("	-- WHERE 조건                                    \n");
		//--WHERE-------------------------------------------------------
		sb.append(sbWhere.toString());		
		//--WHERE-------------------------------------------------------
		sb.append(")B                                            	  ");
		
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
			}
			// 제목 + 내용
			else if(null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("40")) {
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
			}
			// seq
			else if(null != searchVO.getSearchDiv() && searchVO.getSearchDiv().equals("50")) {
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
				BoardDTO outVO = new BoardDTO();
				
				outVO.setNum(rs.getInt("num"));
				outVO.setSeq(rs.getInt("seq"));
				outVO.setTitle(rs.getString("title"));
				outVO.setContents(rs.getString("contents"));
				outVO.setReadCnt(rs.getInt("read_cnt"));
				outVO.setModId(rs.getString("mod_id"));
				outVO.setModDt(rs.getString("mod_dt"));
				outVO.setTotalCnt(rs.getInt("totalCnt"));
				
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
	public int doSave(BoardDTO param) {
		int flag = 0;
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null; // SQL + PARAM
		
		StringBuilder sb = new StringBuilder();
		// 아래 SQL코드에는 세미콜론(;) 금지
		sb.append("INSERT INTO board (\n");
		sb.append("    seq,           \n");
		sb.append("    title,         \n");
		sb.append("    contents,      \n");
		sb.append("    read_cnt,      \n");
		sb.append("    reg_id,        \n");
		sb.append("    reg_dt,        \n");
		sb.append("    mod_id,        \n");
		sb.append("    mod_dt         \n");
		sb.append(") VALUES (         \n");
		sb.append("    board_seq.NEXTVAL,\n");
		sb.append("    ?,             \n");
		sb.append("    ?,             \n");
		sb.append("    0,             \n");
		sb.append("    ?,             \n");
		sb.append("    SYSDATE,       \n");
		sb.append("    ?,             \n");
		sb.append("    SYSDATE        \n");
		sb.append(")                  \n");		
		
		log.debug("1. sql : {}", sb.toString());
		log.debug("2. conn : {}", conn);
		log.debug("3. param : {}", param);
		
		try {
			// conn.setAutoCommit(false); // 자동 커밋 정지
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4. pstmt : {}", pstmt);
			
			// param 설정
			// pstmt.setInt(1, param.getSeq()); 시퀀스 자동입력
			pstmt.setString(1, param.getTitle());
			pstmt.setString(2, param.getContents());
			pstmt.setString(3, param.getRegId()); // 등록자도 자동입력이지만 현재 로그인기능 구현 못해서 등록 240612
			pstmt.setString(4, param.getRegId()); // 수정자도 최초등록때는 등록자와 동일하여 ModId가 아닌 RegId로 등록
			
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
	public int doUpdate(BoardDTO param) {
		int flag = 0;
		Connection conn = connectionMaker.getConnection();
		
		PreparedStatement pstmt = null; // SQL + PARAM		
		StringBuilder sb = new StringBuilder(500);
		// 아래 SQL코드에는 세미콜론(;) 금지
		sb.append("UPDATE board			 \n");
		sb.append("SET                   \n");
		sb.append("    title 	=  ?,    \n");
		sb.append("    contents =  ?,	 \n");
		sb.append("    mod_id 	=  ?,    \n");
		sb.append("    read_cnt 	=  0,\n");
		sb.append("    mod_dt 	= SYSDATE\n");
		sb.append("WHERE                 \n");
		sb.append("    seq 		= ?		   ");
		
		log.debug("1. sql : {}", sb.toString());
		log.debug("2. conn : {}", conn);
		log.debug("3. param : {}", param);
		
		try {
			// conn.setAutoCommit(false); // 자동 커밋 정지
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4. pstmt : {}", pstmt);

			pstmt.setString(1, param.getTitle());
			pstmt.setString(2, param.getContents());
			pstmt.setString(3, param.getRegId());
			pstmt.setInt(4, param.getSeq());
			
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
	} // doUpdate() 끝

	@Override
	public int doDelete(BoardDTO param) {
		int flag = 0;
		Connection conn = connectionMaker.getConnection();
		
		PreparedStatement pstmt = null; // SQL + PARAM		
		StringBuilder sb = new StringBuilder();
		// 아래 SQL코드에는 세미콜론(;) 금지
		sb.append("DELETE 	FROM board\n");
		sb.append("WHERE 	seq = ?  \n");	
		
		log.debug("1. sql : {}", sb.toString());
		log.debug("2. conn : {}", conn);
		log.debug("3. param : {}", param);
		
		try {
			// conn.setAutoCommit(false); // 자동 커밋 정지
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4. pstmt : {}", pstmt);
			
			pstmt.setInt(1, param.getSeq());
			
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
	
	// 교수님이 만든 조회수 메서드
	public int doUpdateReadcnt(BoardDTO param) {
		int flag = 0;
		Connection conn = connectionMaker.getConnection();
		
		PreparedStatement pstmt = null; // SQL + PARAM		
		StringBuilder sb = new StringBuilder(200);
		// 조회수 증가 메서드 SQL 명령문
		sb.append("UPDATE	board							\n");
		sb.append("SET   	read_cnt = read_cnt + 1 		\n"); 
		sb.append("WHERE 	seq = ?                         \n");
		sb.append("AND NOT EXISTS(                          \n");
		sb.append("    SELECT 1 FROM board                  \n");
		sb.append("    WHERE seq = ?                        \n");
		sb.append("    AND reg_id = ?                       \n");
		sb.append(")                                          ");

		log.debug("1. sql : {}", sb.toString());
		log.debug("2. conn : {}", conn);
		log.debug("3. param : {}", param);
		try {
			// conn.setAutoCommit(false); // 자동 커밋 정지
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4. pstmt : {}", pstmt);
			
			pstmt.setInt(1, param.getSeq());
			pstmt.setInt(2, param.getSeq());
			pstmt.setString(3, param.getRegId());
			
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
	}// 교수님이 만든 조회수 메서드 끝
	
	// 내가 만든 조회수 메서드
	public int readCntPlus(BoardDTO param) {
		int flag = 0;
		Connection conn = connectionMaker.getConnection();
		
		PreparedStatement pstmt = null; // SQL + PARAM		
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE board               ");
		sb.append("SET read_cnt = read_cnt + 1");	
		sb.append("WHERE seq = ?           	  ");
		
		log.debug("1. sql : {}", sb.toString());
		log.debug("2. conn : {}", conn);
		log.debug("3. param : {}", param);
		
		try {
			// conn.setAutoCommit(false); // 자동 커밋 정지
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4. pstmt : {}", pstmt);
			
			pstmt.setInt(1, param.getSeq());
			
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
	}// 내가 만든 조회수 메서드 끝
	
	
	@Override
	public BoardDTO doSelectOne(BoardDTO param) {
		BoardDTO outVO = null; // 단건조회 결과
		Connection conn = connectionMaker.getConnection();
		PreparedStatement pstmt = null; // SQL+PARAM
		ResultSet	rs			= null; // SQL문의 결과
		
		StringBuilder sb = new StringBuilder(500);
		
		sb.append("SELECT                                              ");
		sb.append("    seq,                                            ");
		sb.append("    title,                                          ");
		sb.append("    contents,                                       ");
		sb.append("    read_cnt,                                       ");
		sb.append("    reg_id,                                         ");
		sb.append("    TO_CHAR(reg_dt, 'YYYY/MM/DD HH24:MI:SS') reg_dt,");
		sb.append("    mod_id,                                         ");
		sb.append("    TO_CHAR(mod_dt, 'YYYY/MM/DD HH24:MI:SS') mod_dt ");
		sb.append("FROM                                                ");
		sb.append("    board                                           ");
		sb.append("WHERE                                               ");
		sb.append("    seq = ?                                         ");
		
		log.debug("1.sql: {} \n", sb.toString());
		log.debug("2.conn: {} \n", conn);
		log.debug("3.param: {}", param);
		
		try {
			// conn.setAutoCommit(false); // 자동 커밋 정지
			pstmt = conn.prepareStatement(sb.toString());
			log.debug("4. pstmt : {}", pstmt);
			
			pstmt.setInt(1, param.getSeq());
			
			// SELECT실행
			rs = pstmt.executeQuery();
			log.debug("5.rs:\n" + rs);
			
			if(rs.next()) {
				outVO = new BoardDTO();
				
				outVO.setSeq(rs.getInt("seq"));
				outVO.setTitle(rs.getString("title"));
				outVO.setContents(rs.getString("contents"));
				outVO.setReadCnt(rs.getInt("read_cnt"));
				outVO.setRegId(rs.getString("reg_id"));
				outVO.setRegDt(rs.getString("reg_dt"));
				
				outVO.setModId(rs.getString("mod_id"));
				outVO.setModDt(rs.getString("mod_dt"));
				
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
