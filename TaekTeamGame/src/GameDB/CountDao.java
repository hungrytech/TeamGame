package GameDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import GameBeans.GameBean;

public class CountDao {
	// Dao �ν��Ͻ�
	private static CountDao dao= new CountDao();
	
	//db�� �����ؼ� ������ Game��ü	
	private GameBean gameBean;
	
	//Connection Ŭ����
	//PreparedStatmet Ŭ����
	//ResultSet Ŭ����
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	//JDBC ����
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String id = "C##ORANGE";
	private String pw = "1234";
	private String driver = "oracle.jdbc.driver.OracleDriver";
	
	//������
	private CountDao() {
		
	}
	// Dao �ν��Ͻ� ��ȯ
	public static CountDao getDao() {
		return dao;
	}
	
	
	//���� ��ü ����
	public void setGameBean(GameBean gameBean) {
		this.gameBean = gameBean;
	}
	
	
	
	//JDBC �۾�
	public void connectDB() {
		try {
			//JDBC ����̹� �ε�
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, id, pw);
			
			System.out.println("����Ŭ db ���Ἲ��~");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//insert 
	public void insertDB() {
		int gameNumber = this.gameBean.getGameNumber();
		String gameName = this.gameBean.getGameName();
		int gameCount = this.gameBean.getGameCount();
		
		// db�� insert
		String sql = "insert into gamecount(GAME_NUM, GNAME, GCOUNT) values(?, ?, ?)";
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, gameNumber);
			pstmt.setString(2, gameName);
			pstmt.setInt(3, gameCount);
			// ����������
			int rowCount = pstmt.executeUpdate();
			
			System.out.println("insert �Ϸ�.");
			
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally { 
			//�ڿ���ȯ.
			
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
