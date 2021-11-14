package com.thanh.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.thanh.model.*;

public class KetquabaithiDAO extends DAO{
	public KetquabaithiDAO () {
		super();
	}
	public boolean themKetquabaithi(Ketquabaithi newTestResult) {
		boolean fulfilled = false;
		String sql = "INSERT INTO tblketquabaithi (`diem`, `thoigianlambai`, `tblbaithiid`, `tblsinhvienid`) VALUES (?, CURRENT_TIMESTAMP, ?, ?)";
		try {
			PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, newTestResult.getDiem());
			st.setInt(2, newTestResult.getTblbaithiid());
			st.setInt(3, newTestResult.getTblsinhvienid());
			st.execute();

			fulfilled = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fulfilled;
	}

	public ArrayList<Ketquabaithi> layKetquabaithiTheoBaithiID(int baithiId) {
		ArrayList<Ketquabaithi> allResults = new ArrayList<>();
		String sql = "select diem, thoigianlambai, username, hoten, email, dienthoai, tblbaithiid as baithiID, tblsinhvienid as sinhvienID from tblketquabaithi join tblbaithi on tblbaithi.ID = tblketquabaithi.tblBaithiID join tblnguoidung on tblnguoidung.ID = tblketquabaithi.tblSinhvienID where tblketquabaithi.tblbaithiid=" + baithiId;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Ketquabaithi temp = new Ketquabaithi();
				temp.setDiem(rs.getInt("diem"));
				temp.setThoigianlambai(rs.getString("thoigianlambai"));
				temp.setTblbaithiid(rs.getInt("baithiID"));
				temp.setTblsinhvienid(rs.getInt("sinhvienID"));

				Sinhvien tempSinhvien = new Sinhvien();
				tempSinhvien.setUsername(rs.getString("username"));
				tempSinhvien.setHoten(rs.getString("hoten"));
				tempSinhvien.setEmail(rs.getString("email"));
				tempSinhvien.setDienthoai(rs.getString("dienthoai"));
				temp.setSinhvien(tempSinhvien);
				
				allResults.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allResults;
	}
	
	public Ketquabaithi layKetquabaithi(int baithiId, int sinhvienId) {
		String sql = "select diem, thoigianlambai, username, hoten, email, dienthoai, tblbaithiid as baithiID, tblsinhvienid as sinhvienID from tblketquabaithi join tblbaithi on tblbaithi.ID = tblketquabaithi.tblBaithiID join tblnguoidung on tblnguoidung.ID = tblketquabaithi.tblSinhvienID where tblketquabaithi.tblbaithiid=" + baithiId + " and tblketquabaithi.tblsinhvienid=" + sinhvienId;
		Ketquabaithi result = new Ketquabaithi();
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				result.setDiem(rs.getInt("diem"));
				result.setThoigianlambai(rs.getString("thoigianlambai"));
				result.setTblbaithiid(rs.getInt("baithiID"));
				result.setTblsinhvienid(rs.getInt("sinhvienID"));
				
				Sinhvien tempSinhvien = new Sinhvien();
				tempSinhvien.setUsername(rs.getString("username"));
				tempSinhvien.setHoten(rs.getString("hoten"));
				tempSinhvien.setEmail(rs.getString("email"));
				tempSinhvien.setDienthoai(rs.getString("dienthoai"));
				result.setSinhvien(tempSinhvien);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<Ketquabaithi> layKetquabaithiTheoSinhvienID(int sinhvienId) {
		ArrayList<Ketquabaithi> allResults = new ArrayList<>();
		String sql = "select tenbaithi, mota, diem, thoigianlambai, hoten as giaovien, tblketquabaithi.tblbaithiid as baithiId, tblketquabaithi.tblsinhvienid as sinhvienId, tblmonhoc.tenmon from tblketquabaithi join tblbaithi on tblbaithi.ID = tblketquabaithi.tblBaithiID join tblnguoidung on tblnguoidung.ID = tblbaithi.tblgiaovienid join tblmonhoc on tblbaithi.tblmonhocid = tblmonhoc.ID where tblketquabaithi.tblsinhvienid=" + sinhvienId;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Ketquabaithi temp = new Ketquabaithi();
				temp.setDiem(rs.getInt("diem"));
				temp.setThoigianlambai(rs.getString("thoigianlambai"));
				temp.setTblbaithiid(rs.getInt("baithiID"));
				temp.setTblsinhvienid(rs.getInt("sinhvienID"));
				
				Baithi tempBaithi = new Baithi();
				tempBaithi.setTenbaithi(rs.getString("tenbaithi"));
				tempBaithi.setMota(rs.getString("mota"));
				Giaovien tempGiaovien = new Giaovien();
				tempGiaovien.setHoten(rs.getString("giaovien"));
				Monhoc tempMonhoc = new Monhoc();
				tempMonhoc.setTenmon(rs.getString("tenmon"));
				
				tempBaithi.setGiaovien(tempGiaovien);
				tempBaithi.setMonhoc(tempMonhoc);
				temp.setBaithi(tempBaithi);
				
				allResults.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allResults;
	}
}
