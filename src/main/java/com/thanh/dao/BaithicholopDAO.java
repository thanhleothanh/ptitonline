package com.thanh.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.thanh.model.Baithi;
import com.thanh.model.Baithicholop;

public class BaithicholopDAO extends DAO {
	public BaithicholopDAO () {
		super();
	}

	public boolean themBaithicholop(Baithicholop newTestForClass) {
		String sql = "INSERT INTO tblbaithicholop (`tblbaithiid`, `tbllopid`) VALUES (?, ?)";
		try {
			PreparedStatement st = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, newTestForClass.getTblbaithiid());
			st.setInt(2, newTestForClass.getTbllopid());
			st.execute();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean xoaBaithicholop(int baithiId, int lopId) {
		String sql = "delete from tblbaithicholop where tblbaithiid=" + baithiId + " and tbllopid=" + lopId;
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.execute();
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

	public ArrayList<Baithicholop> layBaithicholopTheoBaithiID(int baithiId) {
		ArrayList<Baithicholop> allTestsForClass = new ArrayList<>();
		String sql = "select tblbaithiID as baithiID, tblbaithi.tblMonhocID as monhocID, tbllopid as lopID, tblbaithi.tblgiaovienid as giaovienID from tblbaithicholop join tblbaithi on tblbaithi.ID = tblbaithicholop.tblbaithiid join tbllop on tbllop.ID = tblbaithicholop.tbllopid where tblbaithiid=" + baithiId;
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Baithicholop temp = new Baithicholop();
				temp.setTblbaithiid(rs.getInt("baithiID"));
				temp.setTbllopid(rs.getInt("lopID"));

				Baithi tempBaithi = new Baithi();
				tempBaithi.setID(rs.getInt("baithiID"));
				tempBaithi.setTblgiaovienid(rs.getInt("giaovienID"));
				temp.setBaithi(tempBaithi);
				
				allTestsForClass.add(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return allTestsForClass;
	}

	
}
