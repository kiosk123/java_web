package repository;


import java.util.List;

import domain.BoardVO;

public interface BoardDao {
	public int create(BoardVO vo)throws Exception;
	public BoardVO read(Integer bno)throws Exception;
	public int update(BoardVO vo)throws Exception;
	public int delete(Integer bno)throws Exception;
	public List<BoardVO> listAll()throws Exception;
}
