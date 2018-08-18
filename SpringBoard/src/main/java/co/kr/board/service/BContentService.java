package co.kr.board.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public class BContentService implements BService {

	@Override
	public void execute(Model model) {
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("request");
		String bId = req.getParameter("bId");

	}

}
