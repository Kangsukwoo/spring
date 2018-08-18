package co.kr.board.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import co.kr.board.jdbc.Constant;
import co.kr.board.service.BContentService;
import co.kr.board.service.BDeleteService;
import co.kr.board.service.BListService;
import co.kr.board.service.BModifyService;
import co.kr.board.service.BReplyService;
import co.kr.board.service.BReplyViewService;
import co.kr.board.service.BService;
import co.kr.board.service.BWriteService;

@Controller
public class BController {

	BService service;
	
	public JdbcTemplate template;
	
	@Autowired
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
		Constant.template = this.template;
	}

	@RequestMapping("/list")
	public String list(Model model) {
		System.out.println("----list()梓端 持失");

		service = new BListService();
		service.execute(model);

		return "list";
	}

	@RequestMapping("/write_view")
	public String write_view(Model model) {
		System.out.println("----write_view()梓端 持失");

		return "write_view";
	}

	@RequestMapping("/write")
	public String write(HttpServletRequest req, Model model) {
		System.out.println("----write()梓端 持失");

		model.addAttribute("request", req);
		service = new BWriteService();
		service.execute(model);

		return "redirect:list";
	}

	@RequestMapping("/content_view")
	public String contentView(HttpServletRequest req, Model model) {
		System.out.println("----content_view()梓端 持失");

		model.addAttribute("request", req);
		service = new BContentService();
		service.execute(model);

		return "content_view";

	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(HttpServletRequest req, Model model) {
		System.out.println("----modify()梓端 持失");

		model.addAttribute("request", req);
		service = new BModifyService();
		service.execute(model);

		return "redirect:list";

	}

	@RequestMapping("/reply_view")
	public String reply_view(HttpServletRequest req, Model model) {
		System.out.println("----reply_view()梓端 持失");

		model.addAttribute("request", req);
		service = new BReplyViewService();
		service.execute(model);

		return "reply_view";

	}
	
	@RequestMapping("/reply")
	public String reply(HttpServletRequest req, Model model) {
		System.out.println("----reply()梓端 持失");
		
		model.addAttribute("request", req);
		service = new BReplyService();
		service.execute(model);
		
		return "redirect:list";
	}
	
	@RequestMapping("/delete")
	public String delete(HttpServletRequest req, Model model) {
		System.out.println("----delete()梓端 持失");
		
		model.addAttribute("request", req);
		service = new BDeleteService();
		service.execute(model);
		
		return "redirect:list";		
	}
	
	

}
