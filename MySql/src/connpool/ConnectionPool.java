package connpool;

import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class ConnectionPool {
	public static Connection getConnection()throws NamingException,SQLException{
		
		
			Context initCtx=new InitialContext();
			
			//lookup("java:comp/env"); 큰타옴표 안에 기술된 "java:comp/env"에 해당하는 객체를 찾아서 envCtx변수에 넣는다.
			Context envCtx=(Context)initCtx.lookup("java:comp/env");
			
			//"java:comp/env"이름으로 찾아낸 객체 envCtx의 lookup("jdbc/home")메소드를 사용해 "jdbc/home"을 가지고 객체를
			//얻어내서 DataSource 객체 타입으로 형 변환한후 ds 변수에 저장한다.
			//"jdbc/home"은 Resource엘리먼트의 name에 적힌걸 쓰면된다.
			DataSource ds=(DataSource)envCtx.lookup("jdbc/board");
			
			//ds객체의 getConnection()메소드를 사용해 커넥션 풀로부터 커넥션 객체를 얻어내서 DB를 연동하기우해conn변수에 저장한다.
			Connection conn=ds.getConnection();
			return conn;	
	}
	public static void close(Connection conn){
		if(conn!=null){try{conn.close();}catch(SQLException e){}}		
	}
	public static void close(Connection conn,PreparedStatement pstmt){
	
		if(pstmt!=null){try{pstmt.close();}catch(SQLException e){}}
		if(conn!=null){try{conn.close();}catch(SQLException e){}}	
	}
	public static void close(Connection conn,PreparedStatement pstmt,ResultSet rs){
		
		if(rs!=null){try{rs.close();}catch(SQLException e){}}
		if(pstmt!=null){try{pstmt.close();}catch(SQLException e){}}
		if(conn!=null){try{conn.close();}catch(SQLException e){}}	
	}
}
