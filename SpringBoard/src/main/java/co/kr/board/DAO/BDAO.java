package co.kr.board.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

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

	public void modify(int bId, String bName, String bTitle, String bContent) {
		template.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql = "UPDATE mvc_board SET bName = ?, bTitle = ?, bContent = ? WHERE bId = ? ";
				PreparedStatement pstmt = con.prepareStatement(sql);
				pstmt.setString(1, bName);
				pstmt.setString(2, bTitle);
				pstmt.setString(3, bContent);
				pstmt.setInt(4, bId);

				return pstmt;
			}
		});

	}

	public void delete(int bId) {
		String sql = "DELETE FROM mvc_board WHERE bId = ?";
		template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, bId);

			}
		});
	}

	public BDTO reply_view(int bId) {
		BDTO dto = null;
		String sql = "SELECT * FROM mvc_board WHERE bId = " + bId;
		dto = template.queryForObject(sql, new BeanPropertyRowMapper<BDTO>(BDTO.class));

		return dto;
	}

	public void reply(int bId, String bName, String bTitle, String bContent, int bGroup, int bStep, int bIndent) {

		replyShape(bGroup, bStep);
		template.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT mvc_board(bName, bTitle, bContent, bGroup, bStep, bIndent, bDate) ");
				sql.append("VALUES (?, ?, ?, ?, ?, ?, NOW()) ");
				PreparedStatement pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, bName);
				pstmt.setString(2, bTitle);
				pstmt.setString(3, bContent);
				pstmt.setInt(4, bGroup);
				pstmt.setInt(5, bStep + 1);
				pstmt.setInt(6, bIndent + 1);

				return pstmt;
			}
		});

	}

	public void replyShape(int bGroup, int bStep) {
		String sql = "UPDATE mvc_board SET bStep = bStep + 1 WHERE bGroup = ? AND bStep > ? ";
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				pstmt.setInt(1, bGroup);
				pstmt.setInt(2, bStep);

			}
		});
	}

}
