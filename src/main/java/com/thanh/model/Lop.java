package com.thanh.model;

public class Lop {
	private int ID;
	private String tenlop;
	private int tblmonhocid;
	private int tblgiaovienid;
	private Monhoc monhoc;
	private Giaovien giaovien;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getTenlop() {
		return tenlop;
	}
	public void setTenlop(String tenlop) {
		this.tenlop = tenlop;
	}
	public int getTblmonhocid() {
		return tblmonhocid;
	}
	public void setTblmonhocid(int tblmonhocid) {
		this.tblmonhocid = tblmonhocid;
	}
	public int getTblgiaovienid() {
		return tblgiaovienid;
	}
	public void setTblgiaovienid(int tblgiaovienid) {
		this.tblgiaovienid = tblgiaovienid;
	}
	public Monhoc getMonhoc() {
		return monhoc;
	}
	public void setMonhoc(Monhoc monhoc) {
		this.monhoc = monhoc;
	}
	public Giaovien getGiaovien() {
		return giaovien;
	}
	public void setGiaovien(Giaovien giaovien) {
		this.giaovien = giaovien;
	}
}
