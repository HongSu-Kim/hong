package com.huser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HUserDAO {

	private Connection conn;

	public HUserDAO(Connection conn) {
		this.conn = conn;
	}

	public int insertHuser(HUserDTO dto) {

		int result = 0;
		PreparedStatement pstmt;
		String sql;

		try {

			sql = "INSERT INTO HUSER ";
			sql += "VALUES(?, ?, ?, ?, ?, ?, ?, SYSDATE)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getUserPassword());
			pstmt.setString(3, dto.getUserName());
			pstmt.setString(4, dto.getUserBirth());
			pstmt.setString(5, dto.getUserGender());
			pstmt.setString(6, dto.getUserTel());
			pstmt.setString(7, dto.getUserEmail());

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

	public int updateHuser(HUserDTO dto) {

		int result = 0;
		PreparedStatement pstmt;
		String sql;

		try {

			sql = "UPDATE HUSER SET USERPASWORD = ?, USERNAME = ?, USERBIRTH = ?, USERGENDER = ?, USERTEL = ?, USEREMAIL ";
			sql += "WHERE USERID = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserPassword());
			pstmt.setString(2, dto.getUserName());
			pstmt.setString(3, dto.getUserBirth());
			pstmt.setString(4, dto.getUserGender());
			pstmt.setString(5, dto.getUserTel());
			pstmt.setString(6, dto.getUserEmail());
			pstmt.setString(7, dto.getUserId());

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

	public int deleteHuser(HUserDTO dto) {

		int result = 0;
		PreparedStatement pstmt;
		String sql;

		try {

			sql = "DELETE HUSER WHERE USERID = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserId());

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

	public List<HUserDTO> getHUserList() {

		List<HUserDTO> list = new ArrayList<HUserDTO>();
		PreparedStatement pstmt;
		ResultSet rs;
		String sql;

		try {

			sql = "SELECT * FROM HUSER ORDER BY JOINDATE DECS";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				HUserDTO dto = new HUserDTO();

				dto.setUserId(rs.getString(1));
				dto.setUserPassword(rs.getString(2));
				dto.setUserName(rs.getString(3));
				dto.setUserBirth(rs.getString(4));
				dto.setUserGender(rs.getString(5));
				dto.setUserTel(rs.getString(6));
				dto.setUserEmail(rs.getString(7));
				dto.setJoinDate(rs.getString(8));

				list.add(dto);
			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return list;

	}

	public HUserDTO getHUser(String userId) {

		HUserDTO dto = null;
		PreparedStatement pstmt;
		ResultSet rs;
		String sql;

		try {

			sql = "SELECT * FROM HUSER WHERE USERID = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				dto = new HUserDTO();
				
				dto.setUserId(rs.getString(1));
				dto.setUserPassword(rs.getString(2));
				dto.setUserName(rs.getString(3));
				dto.setUserBirth(rs.getString(4));
				dto.setUserGender(rs.getString(5));
				dto.setUserTel(rs.getString(6));
				dto.setUserEmail(rs.getString(7));
				dto.setJoinDate(rs.getString(8));

			}
			
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return dto;

	}

}
