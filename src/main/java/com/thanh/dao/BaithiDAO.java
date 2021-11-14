package com.thanh.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.thanh.model.*;

public class BaithiDAO extends DAO {
	public BaithiDAO() {
		super();
	}

	public int themBaithi(Baithi newTest) {
		String sql = "INSERT INTO `tblbaithi`(`tenbaithi`, `mota`, `tblMonhocID`, `tblGiaovienID`) VALUES (?, ?, ?, ?)";
		try {
			PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, newTest.getTenbaithi());
			st.setString(2, newTest.getMota());
			st.setInt(3, newTest.getTblmonhocid());
			st.setInt(4, newTest.getTblgiaovienid());
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

public ArrayList<Baithi> layTatcaBaithi(int sinhvienId) {
		ArrayList<Baithi> allTests = new ArrayList<>();
//		String sql = "select distinct tblbaithi.ID as ID,tenbaithi,mota,trangthaiActive, tblmonhoc.tenmon as monhoc, tblnguoidung.hoten as giaovien from tblbaithi join tblmonhoc on tblbaithi.tblMonhocID = tblmonhoc.ID join tblgiaovien on tblgiaovien.tblNguoidungID = tblbaithi.tblGiaovienID join tblnguoidung on tblgiaovien.tblNguoidungID = tblnguoidung.ID where trangthaiActive=1";
		String sql = "select tblbaithi.ID as baithiID, tenbaithi, mota, tblbaithi.tblmonhocid as tblmonhocid, trangthaiActive, tblbaithicholop.tbllopid, tblsinhvienid, tblnguoidung.hoten as giaovien, tblbaithi.tblgiaovienid as tblgiaovienid , tblmonhoc.tenmon as monhoc from tblbaithi join tblbaithicholop on tblbaithi.ID = tblbaithicholop.tblbaithiid join tbllop on tbllop.ID = tblbaithicholop.tbllopid join tblsinhvientronglop on tblsinhvientronglop.tbllopid = tbllop.ID join tblmonhoc on tblmonhoc.ID = tblbaithi.tblmonhocid join tblnguoidung on tblnguoidung.ID = tblbaithi.tblgiaovienid where tblbaithi.trangthaiActive=true and tblsinhvientronglop.tblsinhvienid=" + sinhvienId;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Baithi temp = new Baithi();
				temp.setID(rs.getInt("baithiID"));
				temp.setTenbaithi(rs.getString("tenbaithi"));
				temp.setMota(rs.getString("mota"));
				temp.setTrangthaiActive(rs.getString("trangthaiActive"));

				Monhoc tempMonhoc = new Monhoc();
				tempMonhoc.setID(rs.getInt("tblmonhocid"));
				tempMonhoc.setTenmon(rs.getString("monhoc"));
				temp.setMonhoc(tempMonhoc);
				
				Giaovien tempGiaovien = new Giaovien();
				tempGiaovien.setID(rs.getInt("tblgiaovienid"));
				tempGiaovien.setHoten(rs.getString("giaovien"));
				temp.setGiaovien(tempGiaovien);

				allTests.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allTests;
	}

	public Baithi layBaithiTheoID(int id) {
		Baithi test = new Baithi();
		String sql = "select tblbaithi.ID as ID, tenbaithi, mota, trangthaiActive, tblmonhocid, tblmonhoc.tenmon as tenmon from tblbaithi join tblmonhoc on tblbaithi.tblmonhocid = tblmonhoc.ID where tblbaithi.ID=" + id;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				test.setID(rs.getInt("ID"));
				test.setTenbaithi(rs.getString("tenbaithi"));
				test.setMota(rs.getString("mota"));
				test.setTblmonhocid(rs.getInt("tblmonhocid"));
				test.setTrangthaiActive(rs.getString("trangthaiActive"));
				
				Monhoc tempMonhoc = new Monhoc();
				tempMonhoc.setTenmon(rs.getString("tenmon"));
				test.setMonhoc(tempMonhoc);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return test;
	}

	public void capnhatBaithi(int id, Baithi changedTest) {
		String setTenbaithi = changedTest.getTenbaithi() != null ? "tenbaithi=\"" + changedTest.getTenbaithi() + "\"," : "";

		String setMota = changedTest.getMota() != null ? "mota=\"" + changedTest.getMota() + "\"," : "";

		String preparedSql = "update tblbaithi set " + setTenbaithi + setMota;

		String sql = preparedSql.substring(0, preparedSql.length() - 1) + "where ID=" + id;
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void capnhatActive(int id, boolean active) {
		String sql = active ? "update tblbaithi set trangthaiActive=1 where id=" + id : "update tblbaithi set trangthaiActive=0 where id=" + id;
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean xoaBaithi(int id) {
		String sql = "delete from tblbaithi where id=" + id;
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.execute();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	public ArrayList<Baithi> layTatcaBaithiGiaovien(int id) {
		ArrayList<Baithi> allTests = new ArrayList<>();
		String sql = "select distinct tblbaithi.ID as ID, tenbaithi, mota, trangthaiActive, tenmon from tblbaithi join tblmonhoc on tblbaithi.tblmonhocid = tblmonhoc.ID where tblgiaovienid=" + id;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Baithi temp = new Baithi();
				temp.setID(rs.getInt("ID"));
				temp.setTenbaithi(rs.getString("tenbaithi"));
				temp.setMota(rs.getString("mota"));
				temp.setTrangthaiActive(rs.getString("trangthaiActive"));
				Monhoc tempMonhoc = new Monhoc();
				tempMonhoc.setTenmon(rs.getString("tenmon"));
				temp.setMonhoc(tempMonhoc);

				allTests.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allTests;
	}


}
