package co.kr.board.DAO;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import co.kr.board.DTO.BDTO;
import co.kr.board.jdbc.Constant;

public class BDAO {

	JdbcTemplate template = null;

	public BDAO() {
		template = Constant.template;
	}

	public ArrayList<BDTO> list() {

		ArrayList<BDTO> dto = null;
		String sql = "SELECT bId ,bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, bDate FROM mvc_board ORDER BY bGroup DESC, bStep ASC";
		dto = (ArrayList<BDTO>) template.query(sql, new BeanPropertyRowMapper<BDTO>(BDTO.class));
		
		return dto;
	}

}
