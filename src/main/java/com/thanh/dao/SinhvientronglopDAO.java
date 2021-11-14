package com.thanh.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.thanh.model.*;
public class SinhvientronglopDAO extends DAO {
	public SinhvientronglopDAO() {
		super();
	}
	
	public boolean themSinhvienvaolop(int lopId, int sinhvienId) {
		boolean fulfilled = false;
		String sql = "insert into tblsinhvientronglop (`tbllopid`, `tblsinhvienid`) values (?, ?)";
		try {
			PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, lopId);
			st.setInt(2, sinhvienId);
			st.execute();
			fulfilled = true;
		} catch (Exception e) {
			e.printStackTrace();
			fulfilled = false;
		}
		return fulfilled;
	}

	public ArrayList<Sinhvientronglop> laySinhvientronglop(int lopId) {
		ArrayList<Sinhvientronglop> studentsInClass = new ArrayList<>();
		String sql = "select tblsinhvientronglop.tblSinhvienID as sinhvienId, tblnguoidung.hoten as hoten, tblnguoidung.username as username, tblnguoidung.email as email, tblnguoidung.dienthoai as dienthoai, tbllop.ID as lopId, tbllop.tenlop as tenlop from tblsinhvientronglop join tblsinhvien on tblsinhvientronglop.tblSinhvienID = tblsinhvien.tblNguoidungID join tblnguoidung on tblsinhvien.tblNguoidungID = tblnguoidung.ID join tbllop on tblsinhvientronglop.tblLopID = tbllop.ID where tbllopid=" + lopId;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Sinhvientronglop temp = new Sinhvientronglop();
				temp.setTbllopid(rs.getInt("lopId"));
				temp.setTblsinhvienid(rs.getInt("sinhvienId"));

				Lop tempLop = new Lop();
				tempLop.setID(rs.getInt("lopId"));
				tempLop.setTenlop(rs.getString("tenlop"));
				temp.setLop(tempLop);
				
				Sinhvien tempSinhvien = new Sinhvien();
				tempSinhvien.setID(rs.getInt("sinhvienId"));
				tempSinhvien.setUsername(rs.getString("username"));
				tempSinhvien.setHoten(rs.getString("hoten"));
				tempSinhvien.setEmail(rs.getString("email"));
				tempSinhvien.setDienthoai(rs.getString("dienthoai"));
				temp.setSinhvien(tempSinhvien);
				studentsInClass.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return studentsInClass;
	}

	public boolean xoaSinhvienkhoilop(int id, int sinhvienId) {
		boolean fulfilled = false;
		String sql = "delete from tblsinhvientronglop where tbllopid=" + id + " and tblsinhvienid=" + sinhvienId;
		try {
			PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.execute();
			fulfilled = true;
		} catch (Exception e) {
			e.printStackTrace();
			fulfilled = false;
		}
		return fulfilled;
	}
	

}
