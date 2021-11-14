package com.thanh.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.thanh.model.Cauhoithi;

public class CauhoithiDAO extends DAO {
	public CauhoithiDAO () {
		super();
	}

	public void themCauhoithi(int cauhoiId, int baithiId) {
		String sql = "insert into tblcauhoithi (`tblcauhoiid`, `tblbaithiid`) values (?,?)";
		try {
			PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, cauhoiId);
			st.setInt(2, baithiId);
			st.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Cauhoithi> layCauhoithiTheoBaithiID(int baithiId) {
		ArrayList<Cauhoithi> questions = new ArrayList<>();
		String sql = "select * from tblcauhoithi join tblcauhoi on tblcauhoithi.tblcauhoiid = tblcauhoi.ID where tblcauhoithi.tblbaithiid=" + baithiId;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Cauhoithi temp = new Cauhoithi();
				temp.setID(rs.getInt("tblcauhoiid"));
				temp.setCauhoi(rs.getString("cauhoi"));
				temp.setCautraloidung(rs.getString("cautraloidung"));
				temp.setCautraloisai1(rs.getString("cautraloisai1"));
				temp.setCautraloisai2(rs.getString("cautraloisai2"));
				temp.setCautraloisai3(rs.getString("cautraloisai3"));
				temp.setHinhanh(rs.getString("hinhanh"));
				temp.setTblbaithiid(rs.getInt("tblbaithiid"));
				temp.setTblmonhocid(rs.getInt("tblmonhocid"));
				temp.setTblcauhoiid(rs.getInt("tblcauhoiid"));

				questions.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questions;
	}

	public Cauhoithi layCauhoithiTheoID(int cauhoithiId) {
		Cauhoithi question = new Cauhoithi();
		String sql = "select * from tblcauhoithi join tblcauhoi on tblcauhoithi.tblcauhoiid = tblcauhoi.ID where tblcauhoithi.tblcauhoiid=" + cauhoithiId;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				question.setID(rs.getInt("tblcauhoiid"));
				question.setCauhoi(rs.getString("cauhoi"));
				question.setCautraloidung(rs.getString("cautraloidung"));
				question.setCautraloisai1(rs.getString("cautraloisai1"));
				question.setCautraloisai2(rs.getString("cautraloisai2"));
				question.setCautraloisai3(rs.getString("cautraloisai3"));
				question.setHinhanh(rs.getString("hinhanh"));
				question.setTblbaithiid(rs.getInt("tblbaithiid"));
				question.setTblmonhocid(rs.getInt("tblmonhocid"));
				question.setTblcauhoiid(rs.getInt("ID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return question;
	}
	

}
