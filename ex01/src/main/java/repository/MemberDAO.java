package repository;
 
import domain.MemberVO;
 
public interface MemberDAO {
    public String getTime();
    public int insertMember(MemberVO vo);
}
 
