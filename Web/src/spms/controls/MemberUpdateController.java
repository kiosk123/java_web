package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.MemberDao;
import spms.vo.Member;
//Annotation 적용
@Component("/member/update.do")
public class MemberUpdateController implements Controller,DataBinding {

	MemberDao memberDao;
	public MemberUpdateController setMemberDao(MemberDao memberDao) {
		this.memberDao=memberDao;
		return this;
	}
	
	
	
	@Override
	public Object[] getDataBinders() {
		return new Object[]{
			"no",Integer.class,"member",spms.vo.Member.class
		};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member member=(Member)model.get("member");
		Integer no=(Integer)model.get("no");			
		
		if(member.getEmail()==null)
		{
			member=memberDao.selectOne(no);
			model.put("member", member);
			return "/member/MemberUpdateForm.jsp";
			
		}else{
			memberDao.update(member);
			return "redirect:list.do";
		}				
	}
}
