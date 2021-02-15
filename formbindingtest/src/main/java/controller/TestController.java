package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vo.TESTVO;

@Controller("/test")
public class TestController {
	
	private final static Logger log=LoggerFactory.getLogger(TestController.class);
	
	@RequestMapping(method=RequestMethod.POST)
	public String test(TESTVO vo,RedirectAttributes attr){
		log.info("---------------------------------------------------------------------------------");
		log.info(" no : "+vo.getNo()+", content : "+vo.getContent()+", checkbox : "+vo.getCheckbox());
		log.info("---------------------------------------------------------------------------------");
		log.info(" no is null = "+((vo.getNo()==null)?"true":"false")+" , content is null = "+((vo.getContent()==null)?"true":"false")+" , checkbox is null = "+((vo.getCheckbox()==null)?"true":"false"));
		log.info("---------------------------------------------------------------------------------");
		log.info("content equal to \"\" "+vo.getContent().equals(""));
		log.info("check box value "+vo.getCheckbox());
		
		attr.addFlashAttribute("no", vo.getNo());
		attr.addFlashAttribute("content", vo.getContent());
		attr.addFlashAttribute("checkbox", vo.getCheckbox());
		
		return "redirect:/result";
	}
}
