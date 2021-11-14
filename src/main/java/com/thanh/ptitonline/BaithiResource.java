package com.thanh.ptitonline;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import com.thanh.model.*;
import com.thanh.dao.*;

@Path("tests")
public class BaithiResource {
	BaithiDAO dao = new BaithiDAO();
	CauhoithiDAO cauhoithiDao = new CauhoithiDAO();
	NguoidungDAO nguoidungDao = new NguoidungDAO();
	KetquabaithiDAO ketquabaithiDao = new KetquabaithiDAO();
	BaithicholopDAO baithicholopDao = new BaithicholopDAO();
	LopDAO lopDao = new LopDAO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response layTatcaBaithi(@HeaderParam("userID") String userID) {
		return Response.status(Status.ACCEPTED).entity(dao.layTatcaBaithi(Integer.parseInt(userID))).build();
	}

	@GET
	@Path("/teacher/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response layTatcaBaithiGiaovien(@PathParam("id") int id) {
		return Response.status(Status.ACCEPTED).entity(dao.layTatcaBaithiGiaovien(id)).build();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response layBaithiTheoID(@PathParam("id") int id) {
		Baithi test = dao.layBaithiTheoID(id);
		if (test.getID() == 0)
			return Response.status(Status.BAD_REQUEST).entity("Không có bài thi nào có ID này").build();
		return Response.status(Status.ACCEPTED).entity(test).build();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response xoaBaithi(@PathParam("id") int id) {
		Baithi test = dao.layBaithiTheoID(id);
		if (test.getID() == 0)
			return Response.status(Status.BAD_REQUEST).entity("Không có bài thi nào có ID này").build();
		return Response.status(Status.ACCEPTED).entity(dao.xoaBaithi(id)).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response themBaithi(Baithi newTest) {
		int newID = dao.themBaithi(newTest);
		if (newID == 0)
			return Response.status(Status.BAD_REQUEST).entity("Có lỗi xảy ra, kiểm tra lại thông tin bài thi").build();
		return Response.status(Status.ACCEPTED).entity(dao.layBaithiTheoID(newID)).build();
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response capnhatBaithi(@PathParam("id") int id, Baithi changedTest) {
		dao.capnhatBaithi(id, changedTest);
		if (changedTest.getTrangthaiActive() != null && changedTest.getTrangthaiActive().equals("true"))
			dao.capnhatActive(id, true);
		else if (changedTest.getTrangthaiActive() != null && changedTest.getTrangthaiActive().equals("false"))
			dao.capnhatActive(id, false);
		return Response.status(Status.ACCEPTED).entity(dao.layBaithiTheoID(id)).build();
	}
	


	@GET
	@Path("/{id}/questions")
	@Produces(MediaType.APPLICATION_JSON)
	public Response layCauhoithiTheoBaithiID(@PathParam("id") int id) {
		Baithi test = dao.layBaithiTheoID(id);
		if (test.getID() == 0)
			return Response.status(Status.BAD_REQUEST).entity("Không có bài thi nào có ID này").build();
		return Response.status(Status.ACCEPTED).entity(cauhoithiDao.layCauhoithiTheoBaithiID(id)).build();
	}
	
	@GET
	@Path("/{id}/testresults")
	@Produces(MediaType.APPLICATION_JSON)
	public Response layKetquabaithiTheoBaithiID(@PathParam("id") int id) {
		Baithi test = dao.layBaithiTheoID(id);
		if (test.getID() == 0)
			return Response.status(Status.BAD_REQUEST).entity("Không có bài thi nào có ID này").build();
		else  {
			return Response.status(Status.ACCEPTED).entity(ketquabaithiDao.layKetquabaithiTheoBaithiID(id)).build();
		}
	}
	
	@GET
	@Path("/{baithiId}/testresults/{sinhvienId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response layKetquabaithi(@PathParam("baithiId") int baithiId, @PathParam("sinhvienId") int sinhvienId) {
		Baithi test = dao.layBaithiTheoID(baithiId);
		Nguoidung user = nguoidungDao.layNguoidungTheoID(sinhvienId);
		if (test.getID() == 0 || user.getID()==0)
			return Response.status(Status.BAD_REQUEST).entity("Không tìm thấy bài thi hoặc người dùng trong hệ thống").build();
		else  {
			Ketquabaithi result = ketquabaithiDao.layKetquabaithi(baithiId, sinhvienId);
			if(result.getTblbaithiid() == 0 && result.getTblsinhvienid() == 0)
				return Response.status(Status.BAD_REQUEST).entity("Sinh viên chưa làm bài thi này").build();
			else
			return Response.status(Status.ACCEPTED).entity(result).build();
		}
	}
	
	@GET
	@Path("/{id}/classes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response layBaithicholop(@PathParam("id") int id) {
		Baithi test = dao.layBaithiTheoID(id);
		if(test.getID() == 0)
			return Response.status(Status.BAD_REQUEST).entity("Không có bài thi nào có ID này").build();
		else 
			return Response.status(Status.ACCEPTED).entity(baithicholopDao.layBaithicholopTheoBaithiID(id)).build();
	}

	@POST
	@Path("/{id}/classes")
	@Produces(MediaType.APPLICATION_JSON)
	public Response themBaithicholop(@PathParam("id") int id, Baithicholop testForClass) {
		Baithi test = dao.layBaithiTheoID(id);
		Lop lop = lopDao.layLopTheoID(testForClass.getTbllopid());
		if(test.getID() == 0 || lop.getID() == 0)
			return Response.status(Status.BAD_REQUEST).entity("Có gì đấy không đúng ở đây").build();
		
		boolean fulfilled = baithicholopDao.themBaithicholop(testForClass);
		if(!fulfilled) 
			return Response.status(Status.BAD_REQUEST).entity("Có gì đấy không đúng ở đây").build();
		else 
			return Response.status(Status.ACCEPTED).entity(true).build();
	}
	
	@DELETE
	@Path("/{baithiId}/classes/{lopId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response xoaBaithicholop(@PathParam("baithiId") int baithiId, @PathParam("lopId") int lopId) {
		Baithi test = dao.layBaithiTheoID(baithiId);
		Lop lop = lopDao.layLopTheoID(lopId);
		if(test.getID() == 0 || lop.getID() == 0)
			return Response.status(Status.BAD_REQUEST).entity("Có gì đấy không đúng ở đây").build();

		boolean fulfilled = baithicholopDao.xoaBaithicholop(baithiId, lopId);
		if(!fulfilled) 
			return Response.status(Status.BAD_REQUEST).entity("Có gì đấy không đúng ở đây").build();
		else 
			return Response.status(Status.ACCEPTED).entity(true).build();
	}
}
