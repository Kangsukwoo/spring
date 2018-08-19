package co.kr.board.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import co.kr.board.DAO.BDAO;

public class BDeleteService implements BService {

	@Override
	public void execute(Model model) {		
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("request");
		int bId = Integer.parseInt(req.getParameter("bId"));
		
		BDAO dao = new BDAO();
		dao.delete(bId);		

	}

}
