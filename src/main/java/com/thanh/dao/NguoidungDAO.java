package com.thanh.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.thanh.model.*;

public class NguoidungDAO extends DAO {
	public NguoidungDAO() {
		super();
	}

	public int themNguoidung(Nguoidung newUser) {
		String sql = "insert into tblnguoidung (`username`, `password`, `hoten`, `diachi`, `ngaysinh`, `email`, `dienthoai`, `vaitro`, `diemluyentap`) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, newUser.getUsername());
			st.setString(2, newUser.getPassword());
			st.setString(3, newUser.getHoten());
			st.setString(4, newUser.getDiachi());
			st.setString(5, newUser.getNgaysinh());
			st.setString(6, newUser.getEmail());
			st.setString(7, newUser.getDienthoai());
			st.setString(8, newUser.getVaitro());
			st.setInt(9, newUser.getDiemluyentap());
			st.execute();

			ResultSet rs = st.getGeneratedKeys();
			int generatedKey = 0;
			if (rs.next()) {
				generatedKey = rs.getInt(1);
			}

			phanVaitroNguoidung(newUser, generatedKey);

			return generatedKey;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public void phanVaitroNguoidung(Nguoidung newUser, int newID) {
		String vaitro = newUser.getVaitro().equals("student") ? "sinhvien"
				: newUser.getVaitro().equals("teacher") ? "giaovien" : "admin";
		String sql = "INSERT INTO tbl" + vaitro + " (`tblNguoidungID`) VALUES (?)";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, newID);
			st.execute();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Nguoidung> layTatcaNguoidung() {
		ArrayList<Nguoidung> allUsers = new ArrayList<>();
		String sql = "select * from tblnguoidung";
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

				allUsers.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allUsers;
	}

	public Nguoidung layNguoidungTheoID(int id) {
		Nguoidung user = new Nguoidung();
		String sql = "select * from tblnguoidung where id=" + id;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				user.setID(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setHoten(rs.getString(4));
				user.setDiachi(rs.getString(5));
				user.setNgaysinh(rs.getString(6));
				user.setEmail(rs.getString(7));
				user.setDienthoai(rs.getString(8));
				user.setVaitro(rs.getString(9));
				user.setDiemluyentap(rs.getInt(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public int kiemtraDangnhap(Nguoidung user) {
		int userID = 0;
		String sql = "select * from tblnguoidung where username=\"" + user.getUsername() + "\"";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
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
				if (temp.getPassword().equals(user.getPassword()))
					userID = temp.getID();
				return userID;
			} else
				return 0;
		} catch (Exception e) {
			e.printStackTrace();
			return userID;
		}
	}

	public void capnhatNguoidung(int id, Nguoidung changedUser) {
		String setPassword = changedUser.getPassword() != null ? "password=\"" + changedUser.getPassword() + "\","
				: "";
		String setHoten = changedUser.getHoten() != null ? "hoten=\"" + changedUser.getHoten() + "\"," : "";
		String setDiachi = changedUser.getDiachi() != null ? "diachi=\"" + changedUser.getDiachi() + "\"," : "";
		String setEmail = changedUser.getEmail() != null ? "email=\"" + changedUser.getEmail() + "\"," : "";
		String setDienthoai = changedUser.getDienthoai() != null ? "dienthoai=\"" + changedUser.getDienthoai() + "\","
				: "";

		String preparedSql = "update tblnguoidung set " + setPassword + setHoten + setDiachi + setEmail + setDienthoai; 
		
		String sql = preparedSql.substring(0, preparedSql.length() - 1) + "where id=" + id;
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean xoaNguoidung(int id) {
		String sql = "delete from tblnguoidung where id=" +id;
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.execute();
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void capnhatDiemluyentap(int id, int diem) {
		String sql ="update tblnguoidung set diemluyentap=" + diem + " where id=" + id; 
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
}
