package co.kr.board.service;

import java.util.ArrayList;

import org.springframework.ui.Model;

import co.kr.board.DAO.BDAO;
import co.kr.board.DTO.BDTO;

public class BListService implements BService {

	@Override
	public void execute(Model model) {
		BDAO dao = new BDAO();
		ArrayList<BDTO> dto = dao.list();

		model.addAttribute("list", dto);

	}

}
