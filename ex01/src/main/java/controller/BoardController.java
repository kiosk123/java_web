package controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import domain.BoardVO;
import service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	private static final Logger logger=LoggerFactory.getLogger(BoardController.class);
		
	@Autowired
	private BoardService service;
	
	
	@RequestMapping(value="/modify",method=RequestMethod.GET)
	public String modifyGET(int bno, Model model)throws Exception{
		model.addAttribute(service.read(bno));
		return "board/modify";
	}
	
	@RequestMapping(value="/modify",method=RequestMethod.POST)
	public String modifyPOST(BoardVO board,RedirectAttributes rttr)throws Exception{
		logger.info("mod post............");
		service.modify(board);
		rttr.addFlashAttribute("msg","SUCCESS");
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value="/remove",method=RequestMethod.POST)
	public String remove(@RequestParam("bno")int bno,RedirectAttributes rttr)throws Exception{
		service.remove(bno);
		rttr.addFlashAttribute("msg","SUCCESS");
		return "redirect:/board/listAll";
	}
	
	
	@RequestMapping(value="/read",method=RequestMethod.GET)
	public String read(@RequestParam("bno")int bno,Model model)throws Exception{
		model.addAttribute(service.read(bno));
		return "board/read";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String registerGET(BoardVO board, Model model)throws Exception{
		logger.info("register get.......");
		return "board/register";
	}
	
	@RequestMapping(value="/register",method=RequestMethod.POST)
	public String registerPOST(BoardVO board, RedirectAttributes attr)throws Exception{
		
		logger.info("regist post ...............");
		logger.info(board.toString());
		
		service.regist(board);
		
		attr.addFlashAttribute("msg","success");		
		
		//return "/board/success";
		return "redirect:/listAll";
	}
	//게시물 목록을 가져오는 부분
	@RequestMapping(value="/listAll",method=RequestMethod.GET)
	public String listAll(Model model)throws Exception{
		logger.info("show all list");
		//게시물 목록을 가져와 model에 등록
		model.addAttribute("list",service.listAll());
		return "board/listAll";
	}
}
