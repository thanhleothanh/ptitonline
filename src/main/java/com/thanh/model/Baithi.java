package com.thanh.model;

public class Baithi {
	private int ID;
	private String tenbaithi;
	private String mota;
	private String trangthaiActive;
	private int tblmonhocid;
	private int tblgiaovienid;
	private Monhoc monhoc;
	private Giaovien giaovien;

	public Giaovien getGiaovien() {
		return giaovien;
	}
	public void setGiaovien(Giaovien giaovien) {
		this.giaovien = giaovien;
	}
	public Monhoc getMonhoc() {
		return monhoc;
	}
	public void setMonhoc(Monhoc monhoc) {
		this.monhoc = monhoc;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getTenbaithi() {
		return tenbaithi;
	}
	public void setTenbaithi(String tenbaithi) {
		this.tenbaithi = tenbaithi;
	}
	public String getMota() {
		return mota;
	}
	public void setMota(String mota) {
		this.mota = mota;
	}
	public String getTrangthaiActive() {
		return trangthaiActive;
	}
	public void setTrangthaiActive(String trangthaiActive) {
		this.trangthaiActive = trangthaiActive;
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

}
