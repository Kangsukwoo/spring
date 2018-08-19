package co.kr.board.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import co.kr.board.DAO.BDAO;

public class BReplyService implements BService {

	@Override
	public void execute(Model model) {
		Map<String, Object>map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("request");
		
		int bId = Integer.parseInt(req.getParameter("bId"));
		String bName = req.getParameter("bName");
		String bTitle = req.getParameter("bTitle");
		String bContent = req.getParameter("bContent");
		int bGroup = Integer.parseInt(req.getParameter("bGroup"));
		int bStep = Integer.parseInt(req.getParameter("bStep"));
		int bIndent = Integer.parseInt(req.getParameter("bIndent"));
		
		BDAO dao = new BDAO();
		dao.reply(bId, bName, bTitle, bContent, bGroup, bStep, bIndent);

	}

}
