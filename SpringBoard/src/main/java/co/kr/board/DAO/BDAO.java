package co.kr.board.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

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

	public void write(final String bName, final String bTitle, final String bContent) {

		template.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT mvc_board(bName, bTitle, bContent, bHit, bGroup, bStep, bIndent, bDate) ");
				sql.append("SELECT ?, ?, ?, 0, COALESCE(MAX(bId),0) + 1, 0, 0, NOW() FROM mvc_board ");
				PreparedStatement pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, bName);
				pstmt.setString(2, bTitle);
				pstmt.setString(3, bContent);

				return pstmt;
			}
		});

	}

	public BDTO contentView(String bId) {
		BDTO dto = null;
		String sql = "SELECT * FROM mvc_board WHERE bId = " + bId;
		dto = template.queryForObject(sql, new BeanPropertyRowMapper<BDTO>(BDTO.class));

		upHit(bId);

		return dto;
	}

	public void upHit(String bId) {
		String sql = "UPDATE mvc_board SET bHit = bHit + 1 WHERE bId = " + bId;
		template.update(sql);

	}

}
