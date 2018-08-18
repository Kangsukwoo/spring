package co.kr.board.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import co.kr.board.DAO.BDAO;
import co.kr.board.DTO.BDTO;

public class BContentService implements BService {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("request");
		String bId = req.getParameter("bId");
		
		BDAO dao = new BDAO();
		BDTO dto = dao.contentView(bId);
		
		model.addAttribute("content_view" ,dto);

	}

}
