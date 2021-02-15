package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

//Annotation 적용
@Component("/member/add.do")
public class MemberAddController implements Controller,DataBinding{

	MemberDao memberDao;
	public MemberAddController setMemberDao(MemberDao memberDao) {
		this.memberDao=memberDao;
		return this;
	}
	
	//DataBinding 인터페이스 메소드 오버라이드
	@Override
	public Object[] getDataBinders() {
		return new Object[]{
			"member",spms.vo.Member.class	
		};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member member=(Member)model.get("member");
		if(member.getEmail()==null){//입력폼을 요청할때
			return "/member/MemberForm.jsp";
		}else{//회원등록을 요청할 대
			memberDao.insert(member);
			return "redirect:list.do";
		}
	}
	
}
