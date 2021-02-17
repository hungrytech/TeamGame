package GameDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import GameBeans.GameBean;

public class CountDao {
	// Dao 인스턴스
	private static CountDao dao= new CountDao();
	
	//db와 연결해서 가져올 Game객체	
	private GameBean gameBean;
	
	//Connection 클래스
	//PreparedStatmet 클래스
	//ResultSet 클래스
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	//JDBC 연결
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String id = "C##ORANGE";
	private String pw = "1234";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	
	//생성자
	private CountDao() {
		
	}
	// Dao 인스턴스 반환
	public static CountDao getDao() {
		return dao;
	}
	
	
	//게임 객체 주입
	public void setGameBean(GameBean gameBean) {
		this.gameBean = gameBean;
	}
	
	
	
	//JDBC 작업
	public void connectDB() {
		try {
			//JDBC 드라이버 로딩
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, id, pw);
			
			System.out.println("오라클 db 연결성공~");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//insert 
	public void insertDB() {
		int gameNumber = this.gameBean.getGameNumber();
		String gameName = this.gameBean.getGameName();
		int gameCount = this.gameBean.getGameCount();
		
		// db에 insert
		String sql = "insert into gamecount(GAME_NUM, GNAME, GCOUNT) values(?, ?, ?)";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gameNumber);
			pstmt.setString(2, gameName);
			pstmt.setInt(3, gameCount);
			// 쿼리날리기
			int rowCount = pstmt.executeUpdate();
			
			System.out.println("insert 완료.");
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally { 
			//자원반환.
			
			try {
				if(pstmt!=null) {pstmt.close();}
				}catch(Exception e) {
					e.printStackTrace();
				}
			try {
				if(conn!=null) {conn.close();}
				
				}catch(Exception e) {
					e.printStackTrace();
			
				}
	}
		
		
		
	}
	
}
