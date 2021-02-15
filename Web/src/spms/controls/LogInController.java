package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;

//Annotation 적용
@Component("/auth/login.do")
public class LogInController implements Controller,DataBinding {

	MemberDao memberDao;
	
	public LogInController setMemberDao(MemberDao memberDao) {
		this.memberDao=memberDao;
		return this;
	}
	
	
	@Override
	public Object[] getDataBinders() {
		return new Object[]{
			"member",spms.vo.Member.class
		};
	}


	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member member=(Member)model.get("member");
		
		if(member.getEmail()==null||member.getPassword()==null)
		{
			return "/auth/LogInForm.jsp";
		}else{
			member=memberDao.exist(member.getEmail(), member.getPassword());
			
			if(member!=null)
			{
				HttpSession session=(HttpSession)model.get("session");
				session.setAttribute("member", member);
				return "redirect:../member/list.do";
				
			}else{
				
				return "/auth/LogInFail.jsp";
			}
		}
	}

}
