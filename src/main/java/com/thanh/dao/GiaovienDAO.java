package com.thanh.dao;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import com.thanh.model.*;

public class GiaovienDAO extends DAO {
	public GiaovienDAO() {
		super();
	}
	
	public ArrayList<Nguoidung> layTatcaGiaovien() {
		ArrayList<Nguoidung> allTeachers = new ArrayList<>();
		String sql = "select * from tblnguoidung where vaitro=\"teacher\"";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Nguoidung temp = new Nguoidung();
				temp.setID(rs.getInt(1));
				temp.setUsername(rs.getString(2));
				temp.setPassword(rs.getString(3));
				temp.setHoten(rs.getString(4));
				temp.setDiachi(rs.getString(5));
				temp.setNgaysinh(rs.getString(6));
				temp.setEmail(rs.getString(7));
				temp.setDienthoai(rs.getString(8));
				temp.setVaitro(rs.getString(9));
				temp.setDiemluyentap(rs.getInt(10));

				allTeachers.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allTeachers;
	}


}
