package com.hbbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class HBbsDAO {

	private Connection conn;

	public HBbsDAO(Connection conn) {
		this.conn = conn;
	}

	public int getMaxNum() {

		int result = 0;
		PreparedStatement pstmt;
		ResultSet rs;
		String sql;

		try {

			sql = "SELECT NVL(MAX(BBSID), 0) FROM HBBS";

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				result = rs.getInt(1);

			}

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

	public int insertHBbs(HBbsDTO dto) {

		int result = 0;
		PreparedStatement pstmt;
		String sql;

		try {

			sql = "INSERT INTO HBBS ";
			sql += "VALUES(?, ?, ?, ?, ?, SYSDATE, NULL, 0)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, getMaxNum() + 1);
			pstmt.setString(2, dto.getBbsCategory());
			pstmt.setString(3, dto.getUserId());
			pstmt.setString(4, dto.getBbsTitle());
			pstmt.setString(5, dto.getBbsContent());

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

	public int updateHBbs(HBbsDTO dto) {

		int result = 0;
		PreparedStatement pstmt;
		String sql;

		try {

			sql = "UPDATE HBBS SET BBSCATEGORY = ?, BBSTITLE = ?, BBSCONTENT = ?, UPDATEDDATE = SYSDATE ";
			sql += "WHERE BBSID = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getBbsCategory());
			pstmt.setString(2, dto.getBbsTitle());
			pstmt.setString(3, dto.getBbsContent());
			pstmt.setInt(4, dto.getBbsId());

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

	public int deleteHBbs(HBbsDTO dto) {

		int result = 0;
		PreparedStatement pstmt;
		String sql;

		try {

			sql = "DELETE HBBS WHERE BBSID = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getBbsId());

			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

	public List<HBbsDTO> getHBbsList(int start, int end, String searchKey, String searchValue) {

		List<HBbsDTO> list = new ArrayList<HBbsDTO>();
		PreparedStatement pstmt;
		ResultSet rs;
		String sql;

		try {
			
			searchValue = "%" + searchValue + "%";
			
			sql = "SELECT * FROM (";
			sql+= "SELECT ROWNUM AS RNUM, DATA.* FROM (";
			sql+= "SELECT BBSID, BBSCATEGORY, USERID, BBSTITLE, CREATEDDATE, UPDATEDDATE,  HITCOUNT ";
			sql+= "FROM HBBS WHERE " + searchKey + " LIKE ? ORDER BY BBSID DESC) DATA) ";
			sql+= "WHERE RNUM >= ? AND RNUM <= ?";		

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchValue);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				HBbsDTO dto = new HBbsDTO();

				dto.setBbsId(rs.getInt("BBSID"));
				dto.setBbsCategory(rs.getString("BBSCATEGORY"));
				dto.setUserId(rs.getString("USERID"));
				dto.setBbsTitle(rs.getString("BBSTITLE"));
				dto.setCreatedDate(rs.getString("CREATEDDATE"));
				dto.setUpdatedDate(rs.getString("UPDATEDDATE"));
				dto.setHitCount(rs.getInt("HITCOUNT"));

				list.add(dto);
			}
			
			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return list;

	}

	public HBbsDTO getHBbs(HBbsDTO dto) {

		PreparedStatement pstmt;
		ResultSet rs;
		String sql;

		try {

			sql = "SELECT * FROM HBBS WHERE BBSID = ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getBbsId());

			rs = pstmt.executeQuery();

			if (rs.next()) {

				dto.setBbsId(rs.getInt(1));
				dto.setBbsCategory(rs.getString(2));
				dto.setUserId(rs.getString(3));
				dto.setBbsTitle(rs.getString(4));
				dto.setBbsContent(rs.getString(5));
				dto.setCreatedDate(rs.getString(6));
				dto.setUpdatedDate(rs.getString(7));
				dto.setHitCount(rs.getInt(8));

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
	
	public int getDataCount(String searchKey, String searchValue) {
		
		int result = 0;
		PreparedStatement pstmt;
		ResultSet rs;
		String sql;
		
		try {
			
			searchValue = "%" + searchValue + "%";
			
			sql = "SELECT COUNT(*) FROM HBBS ";
			sql += "WHERE " + searchKey + " LIKE ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchValue);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = rs.getInt(1);
			}

			rs.close();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
		
	}

}
