// BoardDAO.java

package com.pcwk.ehr.board;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAO {

    private static final String URL = "jdbc:oracle:thin:@118.33.104.105:1522:xe"; // 데이터베이스 URL
    private static final String USERNAME = "ikuzo"; // 데이터베이스 사용자명
    private static final String PASSWORD = "pcwk12"; // 데이터베이스 비밀번호

    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver"); // Oracle JDBC 드라이버 로드
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("JDBC 드라이버를 로드할 수 없습니다.");
        }
    }

    public int insertBoard(BoardDTO board) {
        String sql = "INSERT INTO BOARD_BOOK (SEQ, TITLE, CONTENTS, REG_ID, REG_DT, MOD_ID, MOD_DT, IS_ADMIN) " +
                     "VALUES (IKUZO.BOARD_SEQ.NEXTVAL, ?, ?, ?, SYSDATE, ?, SYSDATE, ?)";
        
        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, board.getTitle());
            pstmt.setString(2, board.getContents());
            pstmt.setString(3, board.getRegId());
            pstmt.setString(4, board.getModId());
            pstmt.setString(5, board.getIsAdmin());
            
            return pstmt.executeUpdate();
            
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public List<BoardDTO> getBoardList() {
        List<BoardDTO> boardList = new ArrayList<>();

        String sql = "SELECT SEQ, TITLE, CONTENTS, REG_ID, REG_DT, MOD_ID, MOD_DT, IS_ADMIN FROM BOARD_BOOK";

        try (Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                BoardDTO board = new BoardDTO();
                board.setSeq(rs.getInt("SEQ"));
                board.setTitle(rs.getString("TITLE"));
                board.setContents(rs.getString("CONTENTS"));
                board.setRegId(rs.getString("REG_ID"));
                board.setRegDt(rs.getDate("REG_DT"));
                board.setModId(rs.getString("MOD_ID"));
                board.setModDt(rs.getDate("MOD_DT"));
                board.setIsAdmin(rs.getString("IS_ADMIN"));

                boardList.add(board);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return boardList;
    }
}
