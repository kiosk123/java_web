package repository;
 
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
 
import domain.MemberVO;
 
@Repository
public class MemberDAOImpl implements MemberDAO {
 
    //XML Mapper에서 mapper태그 namespace 애트리뷰트에 지정한 값
    private static final String namespace="mapper.MemberMapper";
    
    @Autowired
    private SqlSession sqlSession;
    
    @Override
    public String getTime() {
        //namespace + select태그 id애트리 뷰트값
        return sqlSession.selectOne(namespace+".getTime");
    }
 
    @Override
    public int insertMember(MemberVO vo) {
        //namespace + insert태그 id애트리 뷰트값
        return sqlSession.insert(namespace+".insertMember",vo);
    }
}
 