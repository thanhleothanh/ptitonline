package com.thanh.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.thanh.model.Cauhoiluyentap;

public class CauhoiluyentapDAO extends DAO{
	public CauhoiluyentapDAO() {
		super();
	}

	public void themCauhoiluyentap(int cauhoiId, int giaovienId) {
		String sql = "insert into tblcauhoiluyentap (`tblcauhoiid`, `tblgiaovienid`) values (?,?)";
		try {
			PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, cauhoiId);
			st.setInt(2, giaovienId);
			st.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Cauhoiluyentap> layCauhoiluyentapRandom(int monhocId) {
		ArrayList<Cauhoiluyentap> questions = new ArrayList<>();
		String sql = "select * from tblcauhoi join tblcauhoiluyentap on tblcauhoi.ID = tblcauhoiluyentap.tblCauhoiID where tblcauhoi.tblmonhocid=" + monhocId + " ORDER BY RAND() LIMIT 20";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Cauhoiluyentap temp = new Cauhoiluyentap();
				temp.setID(rs.getInt("ID"));
				temp.setCauhoi(rs.getString("cauhoi"));
				temp.setCautraloidung(rs.getString("cautraloidung"));
				temp.setCautraloisai1(rs.getString("cautraloisai1"));
				temp.setCautraloisai2(rs.getString("cautraloisai2"));
				temp.setCautraloisai3(rs.getString("cautraloisai3"));
				temp.setHinhanh(rs.getString("hinhanh"));
				temp.setTblmonhocid(rs.getInt("tblmonhocid"));
				temp.setTblcauhoiid(rs.getInt("tblcauhoiid"));
				temp.setTblgiaovienid(rs.getInt("tblgiaovienid"));

				questions.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return questions;
	}

	public Cauhoiluyentap layCauhoiluyentapTheoID(int cauhoiId) {
		Cauhoiluyentap question = new Cauhoiluyentap();
		String sql = "select * from tblcauhoiluyentap join tblcauhoi on tblcauhoiluyentap.tblcauhoiid = tblcauhoi.ID where tblcauhoiluyentap.tblcauhoiid=" + cauhoiId;
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
				question.setTblcauhoiid(rs.getInt("tblcauhoiid"));
				question.setTblgiaovienid(rs.getInt("tblgiaovienid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return question;
	}

}
