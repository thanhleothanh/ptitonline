package com.thanh.model;

public class Ketquabaithi {
	private int diem;
	private String thoigianlambai;
	private int tblbaithiid;
	private int tblsinhvienid;
	private Sinhvien sinhvien;
	private Baithi baithi;
	public int getTblbaithiid() {
		return tblbaithiid;
	}
	public int getDiem() {
		return diem;
	}
	public void setDiem(int diem) {
		this.diem = diem;
	}
	public String getThoigianlambai() {
		return thoigianlambai;
	}
	public void setThoigianlambai(String thoigianlambai) {
		this.thoigianlambai = thoigianlambai;
	}
	public void setTblbaithiid(int tblbaithiid) {
		this.tblbaithiid = tblbaithiid;
	}
	public int getTblsinhvienid() {
		return tblsinhvienid;
	}
	public void setTblsinhvienid(int tblsinhvienid) {
		this.tblsinhvienid = tblsinhvienid;
	}
	public Sinhvien getSinhvien() {
		return sinhvien;
	}
	public void setSinhvien(Sinhvien sinhvien) {
		this.sinhvien = sinhvien;
	}
	public Baithi getBaithi() {
		return baithi;
	}
	public void setBaithi(Baithi baithi) {
		this.baithi = baithi;
	}
	
}
