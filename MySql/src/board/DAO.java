package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import connpool.ConnectionPool;

public class DAO {
	private static DAO instance = new DAO();

	public static DAO getInstance() {
		return instance;
	}

	private DAO() {
	}

	// 현재 입력될 글번호의 값을 가져오는 메소드
	public int getBoardNum() {
		int num = 1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			String sql = "select max(num) from board";
			conn = ConnectionPool.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				num = rs.getInt(1) + 1;

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
		} catch (NamingException ne) {
			ne.printStackTrace();
			System.out.println(ne.getMessage());
		} finally {
			ConnectionPool.close(conn, pstmt, rs);
		}
		return num;
	}

	// 게시판 글쓰기 처리
	public void insertArticle(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int num = board.getNum();
		int ref = board.getRef();
		int step = board.getStep();
		int indent = board.getIndent();
		int boardNumber = getBoardNum();
		String sql = null;

		try {

			conn = ConnectionPool.getConnection();

			// num이 0이면 제목글 0이 아니면 답변글
			// 답변글 처리
			if (num != 0) {
				// ref는 부모글의 ref를 사용하고 step과 level의 기본값은 부모의 값을 가지고 있다.
				// 이때 기존에 있던 step값중 현재 글의 step보다 큰값은 모두+1씩을 한다
				sql = "update board set step=step+1 where ref=? and step>?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, ref);
				pstmt.setInt(2, step);
				pstmt.executeUpdate();

				// 자신의 step과 level값도 모두 1씩 더한다.
				step = step + 1;
				indent = indent + 1;

			} else {
				// 제목글의 ref값은 글번호의 값을 그대로 사용한다.
				ref = boardNumber;
				step = 0; // step과 indent는 0이된다.
				indent = 0;
			}

			sql = "insert into board(writer,email,title," + "password,time,ref,step,indent,content,ip)"
					+ "values(?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getWriter());
			pstmt.setString(2, board.getEmail()==null?"":board.getEmail());
			pstmt.setString(3, board.getTitle());
			pstmt.setString(4, board.getPassword()==null?"":board.getPassword());
			pstmt.setTimestamp(5, board.getTime());
			pstmt.setInt(6, ref);
			pstmt.setInt(7, step);
			pstmt.setInt(8, indent);
			pstmt.setString(9, board.getContent());
			pstmt.setString(10, board.getIp());

			pstmt.executeUpdate();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
		} catch (NamingException ne) {
			ne.printStackTrace();
			System.out.println(ne.getMessage());
		} finally {
			ConnectionPool.close(conn, pstmt);
		}
	}

	// 전체글목록의 수를 얻어낸다
	public int getBoardCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;

		try {
			conn = ConnectionPool.getConnection();
			pstmt = conn.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();
			if (rs.next())
				count = rs.getInt(1);
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
		} catch (NamingException ne) {
			ne.printStackTrace();
			System.out.println(ne.getMessage());
		} finally {
			ConnectionPool.close(conn, pstmt, rs);
		}
		return count;
	}

	// 게시글의 글목록보기
	public List<Board> getBoards(int start, int end) {
		/*
		 * mysql start=start-1; end=end;
		 */
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board> boardList = null;

		try {

			String sql = "select * from board order by ref desc,step asc limit ?,?";
			conn = ConnectionPool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start - 1);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();

			boardList = new ArrayList<Board>();

			if (rs.next()) {

				do {
					Board board = new Board();
					board.setContent(rs.getString("content"));
					board.setEmail(rs.getString("email"));
					board.setIndent(rs.getInt("indent"));
					board.setIp(rs.getString("ip"));
					board.setNum(rs.getInt("num"));
					board.setPassword(rs.getString("password"));
					board.setReadCount(rs.getInt("readcount"));
					board.setTitle(rs.getString("title"));
					board.setTime(rs.getTimestamp("time"));
					board.setRef(rs.getInt("ref"));
					board.setStep(rs.getInt("step"));
					board.setWriter(rs.getString("writer"));

					boardList.add(board);

				} while (rs.next());
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
		} catch (NamingException ne) {
			ne.printStackTrace();
			System.out.println(ne.getMessage());
		} finally {
			ConnectionPool.close(conn, pstmt, rs);
		}

		return boardList;
	}

	// 게시물 가져오기 --update는 글수정시 사용할 변수
	public Board getBoard(int boardNum, boolean modify) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = null;

		try {
			conn = ConnectionPool.getConnection();
			
			// 해당 게시물의 조회수 증가
			if (modify != true) {//게시물을 수정하는 것이아니고 조회하는 용도라면
				//readcount증가시킨다.
				pstmt = conn.prepareStatement("update board set readcount=readcount+1 where num=?");
				pstmt.setInt(1, boardNum);
				pstmt.executeUpdate();
			}

			// 해당 게시물 조회
			pstmt = conn.prepareStatement("select * from board where num=?");
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				board = new Board();
				board.setContent(rs.getString("content"));
				board.setEmail(rs.getString("email"));
				board.setIndent(rs.getInt("indent"));
				board.setIp(rs.getString("ip"));
				board.setNum(rs.getInt("num"));
				board.setPassword(rs.getString("password"));
				board.setReadCount(rs.getInt("readcount"));
				board.setTitle(rs.getString("title"));
				board.setTime(rs.getTimestamp("time"));
				board.setRef(rs.getInt("ref"));
				board.setStep(rs.getInt("step"));
				board.setWriter(rs.getString("writer"));
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
		} catch (NamingException ne) {
			ne.printStackTrace();
			System.out.println(ne.getMessage());
		} catch (Exception e) {
			System.out.println("익셉션 : " + e.getMessage());
		} finally {
			ConnectionPool.close(conn, pstmt, rs);
		}
		return board;
	}

	// 글수정 처리
	public int updateBoard(Board board) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		String dbpass = "";
		String sql = "select password from board where num=?";

		try {
			conn = ConnectionPool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, board.getNum());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dbpass = rs.getString(1);
				
				//기존의 게시물 패스워드와 글수정시 입력한 패스워드를 비교한다.
				//넘어온 값이 null일경우를 처리하기 위한 첫번째 if다 사실 첫번째 if문같은 코드는 추가하지 않는 것이 좋다
				if(board.getPassword()==null)
					board.setPassword("");
								
				if (dbpass.equals(board.getPassword())) {//패스워드가 일치하면
					
					sql = "update board set writer=?,email=?,title=?,password=?" + ",content=? where num=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, board.getWriter());
					pstmt.setString(2, board.getEmail());
					pstmt.setString(3, board.getTitle());
					pstmt.setString(4, board.getPassword());
					pstmt.setString(5, board.getContent());
					pstmt.setInt(6, board.getNum());
					pstmt.executeUpdate();
					x = 1;
				} else {
					x = 0;
				}

			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
		} catch (NamingException ne) {
			ne.printStackTrace();
			System.out.println(ne.getMessage());
		} finally {
			ConnectionPool.close(conn, pstmt, rs);
		}
		return x;
	}

	// 글 삭제 처리
	public int deleteBoard(int boardNum, String password) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;

		String dbpass = "";
		String sql = "select password from board where num=?";

		try {
			conn = ConnectionPool.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNum);
			rs = pstmt.executeQuery();
			
			//게시물번호에 해당하는 패스워드가 존재하면 
			if (rs.next()) {
				
				//패스워드를 저장한다.
				dbpass = rs.getString(1);
				
				//넘어온 값이 null일경우를 처리하기 위한 첫번째 if다 사실 첫번째 if문같은 코드는 추가하지 않는 것이 좋다
				//왜냐하면 쓸데없이 복잡해진다 그러므로 추가하지말고 암호화 라이브러리를 이용해서 코딩하자
				if(password==null)
					password="";
				
				if(dbpass.equals(password)){
					sql = "delete from board where num=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, boardNum);
					pstmt.executeUpdate();
					x = 1;
				} else {
					x = 0;
				}

			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println(sqle.getMessage());
		} catch (NamingException ne) {
			ne.printStackTrace();
			System.out.println(ne.getMessage());
		} finally {
			ConnectionPool.close(conn, pstmt, rs);
		}
		return x;
	}
}
