package spms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DBConnectionPool {
	
	String url;
	String username;
	String password;
	
	//Connection 객체를 보관할 ArrayList
	ArrayList<Connection> connList=new ArrayList<Connection>();
	
	//생성자에는 DB커넥션 생성에 필요한 값을 매개변수로 받는다.
	public DBConnectionPool(String driver,String url,String username,String password)
	throws Exception{
		this.url=url;
		this.username=username;
		this.password=password;
		
		Class.forName(driver);
	}
	
	/*
		ArrayList에 들어있는 Connection 객체를 꺼내준다. 단 DB커넥션 객체도 일정시간이 지나면
		서버와의 연결이 끊어지기 때문에 유효성 체크를 한 다음에 반환한다.
	*/
	public Connection getConnection() throws Exception{
		if(connList.size()>0){
			Connection conn=connList.get(0);
			if(conn.isValid(10)){
				return conn;
			}
		}
		//ArrayList에 보관된 Connection 객체가 없다면 새로 만들어 반환한다.
		return DriverManager.getConnection(url,username,password);
	}
	
	//커넥션 객체를 쓰고 난뒤 커넥션 풀에 반환한다.
	public void returnConnection(Connection conn)throws Exception{
		connList.add(conn);
	}
	
	//웹 애플리케이션을 종료하기 전에 이 메서드를 호출하여 데이터베이스와 연결된 것을 모두 끊는다.
	public void closeAll()
	{
		for(Connection conn:connList){
			try{conn.close();}catch(Exception e){}
		}
	}
}
