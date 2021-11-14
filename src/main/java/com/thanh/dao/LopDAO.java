package com.thanh.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.thanh.model.Lop;
import com.thanh.model.Giaovien;
import com.thanh.model.Monhoc;


public class LopDAO extends DAO{
	public LopDAO() {
		super();
	}
	
	public int themLop(Lop newClass) {
		String sql = "INSERT INTO tbllop(`tenlop`, `tblMonhocID`, `tblGiaovienID`) VALUES (?,  ?, ?)";
		try {
			PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, newClass.getTenlop());
			st.setInt(2, newClass.getTblmonhocid());
			st.setInt(3, newClass.getTblgiaovienid());
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

	public ArrayList<Lop> layTatcaLop() {
		ArrayList<Lop> allClasses = new ArrayList<>();
		String sql = "select tbllop.ID as ID, tbllop.tenlop as tenlop, tbllop.tblgiaovienid as giaovienid, tblnguoidung.hoten as giaovien, tblmonhoc.tenmon as monhoc from tbllop join tblmonhoc on tblmonhoc.ID = tbllop.tblmonhocid join tblgiaovien on tblgiaovien.tblNguoidungID = tbllop.tblgiaovienid join tblnguoidung on tblgiaovien.tblnguoidungid = tblnguoidung.ID";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Lop temp = new Lop();
				temp.setID(rs.getInt("ID"));
				temp.setTenlop(rs.getString("tenlop"));

				Monhoc tempMonhoc = new Monhoc();
				tempMonhoc.setTenmon(rs.getString("monhoc"));
				temp.setMonhoc(tempMonhoc);
				
				Giaovien tempGiaovien = new Giaovien();
				tempGiaovien.setID(rs.getInt("giaovienid"));
				tempGiaovien.setHoten(rs.getString("giaovien"));
				temp.setGiaovien(tempGiaovien);

				allClasses.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return allClasses;
	}

	public Lop layLopTheoID(int id) {
		Lop temp = new Lop();
		String sql = "select tbllop.ID as ID, tbllop.tenlop as tenlop, tbllop.tblgiaovienid as giaovienid, tblnguoidung.hoten as giaovien, tblmonhoc.tenmon as monhoc from tbllop join tblmonhoc on tblmonhoc.ID = tbllop.tblmonhocid join tblgiaovien on tblgiaovien.tblNguoidungID = tbllop.tblgiaovienid join tblnguoidung on tblgiaovien.tblnguoidungid = tblnguoidung.ID where tbllop.ID=" + id;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				temp.setID(rs.getInt("ID"));
				temp.setTenlop(rs.getString("tenlop"));

				Monhoc tempMonhoc = new Monhoc();
				tempMonhoc.setTenmon(rs.getString("monhoc"));
				temp.setMonhoc(tempMonhoc);
				
				Giaovien tempGiaovien = new Giaovien();
				tempGiaovien.setID(rs.getInt("giaovienid"));
				tempGiaovien.setHoten(rs.getString("giaovien"));
				temp.setGiaovien(tempGiaovien);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}

	public void capnhatLop(int id, Lop changedClass) {
		String setTenlop = changedClass.getTenlop() != null ? "tenlop=\"" + changedClass.getTenlop() + "\"," : "";
		
		String preparedSql = "update tbllop set " + setTenlop;

		String sql = preparedSql.substring(0, preparedSql.length() - 1) + "where ID=" + id;
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void capnhatGiaovien(int id, int newTeacherId) {
		String sql = "update tbllop set tblgiaovienid=" + newTeacherId + " where id=" + id; 
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean xoaLop(int id) {
		String sql = "delete from tbllop where id=" + id;
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
