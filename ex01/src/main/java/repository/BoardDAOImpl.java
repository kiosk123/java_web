package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDao{
	
	@Autowired
	private SqlSession sqlSession;
	
	private final static String namespace="mapper.BoardMapper";

	@Override
	public int create(BoardVO vo) throws Exception {
		return sqlSession.insert(namespace+".create",vo);
	}

	@Override
	public BoardVO read(Integer bno) throws Exception {
		return sqlSession.selectOne(namespace+".read",bno);
	}

	@Override
	public int update(BoardVO vo) throws Exception {
		return sqlSession.update(namespace+".update",vo);
	}

	@Override
	public int delete(Integer bno) throws Exception {
		return sqlSession.delete(namespace+".delete",bno);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return sqlSession.selectList(namespace+".listAll");
	}	

}
