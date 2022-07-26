package com.huserInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HUserInfoDAO {

	private Connection conn;

	public HUserInfoDAO(Connection conn) {
		this.conn = conn;
	}

	public int insertData(String userId) {

		int result = 0;
		PreparedStatement pstmt;
		String sql;

		try {

			sql = "INSERT INTO HUSERINFO ";
			sql += "VALUES(?, 1, SYSDATE, 0, 0)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

	public int updateUserGrant(HUserInfoDTO dto) {

		int result = 0;
		PreparedStatement pstmt;
		String sql;

		try {

			sql = "UPDATE HUSERINFO SET USERGRANT = ? ";
			sql += "WHERE USERID = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserGrant());
			pstmt.setString(2, dto.getUserId());

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

	public int updateLastDate(String userId) {

		int result = 0;
		PreparedStatement pstmt;
		String sql;

		try {

			sql = "UPDATE HUSERINFO SET LASTDATE = SYSDATE ";
			sql += "WHERE USERID = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

	public int updateCreatedCount(String userId) {

		int result = 0;
		PreparedStatement pstmt;
		String sql;

		try {

			sql = "UPDATE HUSERINFO SET CREATEDCOUNT = CREATEDCOUNT + 1 ";
			sql += "WHERE USERID = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

	public int updateCommentCount(String userId) {

		int result = 0;
		PreparedStatement pstmt;
		String sql;

		try {

			sql = "UPDATE HUSERINFO SET COMMENTCOUNT = COMMENTCOUNT + 1 ";
			sql += "WHERE USERID = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

	public int deleteData(HUserInfoDTO dto) {

		int result = 0;
		PreparedStatement pstmt;
		String sql;

		try {

			sql = "DELETE HUSERINFO WHERE USERID = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserId());

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}
	
	public List<HUserInfoDTO> getHUserInfoList() {
		
		List<HUserInfoDTO> list = new ArrayList<HUserInfoDTO>();
		PreparedStatement pstmt;
		ResultSet rs;
		String sql;
		
		try {
			
			sql = "SELECT * FROM HUSERINFO";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				HUserInfoDTO dto = new HUserInfoDTO();

				dto.setUserId(rs.getString(1));
				dto.setUserGrant(rs.getString(2));
				dto.setLastDate(rs.getString(3));
				dto.setCreatedCount(rs.getInt(4));
				dto.setCommentCount(rs.getInt(5));
				
				list.add(dto);
				
			}
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return list;
		
	}
	
	public HUserInfoDTO getHUser(HUserInfoDTO dto) {
		
		PreparedStatement pstmt;
		ResultSet rs;
		String sql;
		
		try {
			
			sql = "SELECT * FROM HUSERINFO WHERE = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserId());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				dto.setUserId(rs.getString(1));
				dto.setUserGrant(rs.getString(2));
				dto.setLastDate(rs.getString(3));
				dto.setCreatedCount(rs.getInt(4));
				dto.setCommentCount(rs.getInt(5));
				
			} else {
				dto = null;
			}
			
			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;
		
	}
	
	
	
	
	
	
	

}
