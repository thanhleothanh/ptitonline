package com.thanh.model;

public class Sinhvientronglop {
	private int tbllopid;
	private int tblsinhvienid;
	private Lop lop;
	private Sinhvien sinhvien;
	public int getTbllopid() {
		return tbllopid;
	}
	public void setTbllopid(int tbllopid) {
		this.tbllopid = tbllopid;
	}
	public int getTblsinhvienid() {
		return tblsinhvienid;
	}
	public void setTblsinhvienid(int tblsinhvienid) {
		this.tblsinhvienid = tblsinhvienid;
	}
	public Lop getLop() {
		return lop;
	}
	public void setLop(Lop lop) {
		this.lop = lop;
	}
	public Sinhvien getSinhvien() {
		return sinhvien;
	}
	public void setSinhvien(Sinhvien sinhvien) {
		this.sinhvien = sinhvien;
	}

}
