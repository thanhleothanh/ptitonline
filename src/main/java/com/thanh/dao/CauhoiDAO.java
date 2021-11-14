package com.thanh.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.thanh.model.Cauhoi;
import com.thanh.model.Cauhoithi;

public class CauhoiDAO extends DAO {
	public CauhoiDAO () {
		super();
	}

	public int themCauhoi(Cauhoi newQuestion) {
		String sql = "insert into tblcauhoi (`cauhoi`, `cautraloidung`, `cautraloisai1`, `cautraloisai2`, `cautraloisai3`, `hinhanh`, `tblmonhocid`) values (?, ?, ?, ?, ?, ?,?)";
		try {
			PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, newQuestion.getCauhoi());
			st.setString(2, newQuestion.getCautraloidung());
			st.setString(3, newQuestion.getCautraloisai1());
			st.setString(4, newQuestion.getCautraloisai2());
			st.setString(5, newQuestion.getCautraloisai3());
			st.setString(6, newQuestion.getHinhanh());
			st.setInt(7, newQuestion.getTblmonhocid());
			st.execute();

			ResultSet rs = st.getGeneratedKeys();
			int generatedKey = 0;
			if (rs.next()) {
				generatedKey = rs.getInt(1);
			}

			return generatedKey;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public Cauhoi layCauhoiTheoID(int id) {
		Cauhoithi question = new Cauhoithi();
		String sql = "select * from tblcauhoi where id=" + id;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				question.setID(rs.getInt("ID"));
				question.setCauhoi(rs.getString("cauhoi"));
				question.setCautraloidung(rs.getString("cautraloidung"));
				question.setCautraloisai1(rs.getString("cautraloisai1"));
				question.setCautraloisai2(rs.getString("cautraloisai2"));
				question.setCautraloisai3(rs.getString("cautraloisai3"));
				question.setHinhanh(rs.getString("hinhanh"));
				question.setTblmonhocid(rs.getInt("tblmonhocid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return question;
	}
	

	
	public boolean xoaCauhoi(int id) {
		String sql = "delete from tblcauhoi where id=" +id;
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.execute();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}



}
