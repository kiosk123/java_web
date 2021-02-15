package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import entity.Authority;
import entity.Member;
import service.MemberService;

@Controller
@RequestMapping("/")
public class HomeController {

	private final static Logger log = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private MemberService memberService;

	@RequestMapping(method = RequestMethod.GET)
	public String homeGET(Model model) {
		model.addAttribute("memberList", memberService.getAllMemberList());
		log.info("memberList size is "+memberService.getAllMemberList().size());
		return "home/home";
	}

	@RequestMapping(value = "/regMember", method = RequestMethod.POST)
	public String regMemberPOST(RedirectAttributes redirectAttributes,Member member) {
		
		member.setAuthority(new Authority("1","ADMIN"));
		boolean success= memberService.insertNewMember(member);
		log.info("success value is "+success);
		if(!success){
			redirectAttributes.addFlashAttribute("message","다른 아이디를 입력해 주세요");
		}
		
		return "redirect:/";
	}
}
