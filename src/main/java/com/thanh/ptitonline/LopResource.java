package com.thanh.ptitonline;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import com.thanh.model.*;

import com.thanh.dao.*;

@Path("classes")
public class LopResource {
	LopDAO dao = new LopDAO();
	SinhvientronglopDAO sinhvienlopDAO = new SinhvientronglopDAO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response layTatcaLop() {
		return Response.status(Status.ACCEPTED).entity(dao.layTatcaLop()).build();
	}

	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response layLopTheoID(@PathParam("id") int id) {
		Lop lop = dao.layLopTheoID(id);
		if (lop.getID() == 0)
			return Response.status(Status.BAD_REQUEST).entity("Không có lớp nào có ID này").build();
		return Response.status(Status.ACCEPTED).entity(lop).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response themLop(Lop newClass) {
		int newID = dao.themLop(newClass);
		if (newID == 0)
			return Response.status(Status.BAD_REQUEST).entity("Có lỗi xảy ra, kiểm tra lại thông tin lớp").build();
		return Response.status(Status.CREATED).entity(dao.layLopTheoID(newID)).build();
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response capnhatLop(@PathParam("id") int id, Lop changedClass) {
		if(changedClass.getTblgiaovienid() != 0) {
			dao.capnhatGiaovien(id, changedClass.getTblgiaovienid());
		}
		dao.capnhatLop(id, changedClass);
		return Response.status(Status.ACCEPTED).entity(dao.layLopTheoID(id)).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response xoaLop(@PathParam("id") int id) {
		Lop lop = dao.layLopTheoID(id);
		if (lop.getID() == 0)
			return Response.status(Status.BAD_REQUEST).entity("Không có lớp nào có ID này").build();
		return Response.status(Status.ACCEPTED).entity(dao.xoaLop(id)).build();
	}
	
	///sinhvientronglop
	///sinhvientronglop
	///sinhvientronglop
	///sinhvientronglop

	@POST
	@Path("/students")
	@Produces(MediaType.APPLICATION_JSON)
	public Response themSinhvienvaolop(Sinhvientronglop newStudentInClass) {
		boolean fulfilled = sinhvienlopDAO.themSinhvienvaolop(newStudentInClass.getTbllopid(), newStudentInClass.getTblsinhvienid());
		if (!fulfilled)
			return Response.status(Status.BAD_REQUEST).entity("Có lỗi xảy ra, vui lòng kiểm tra lại thông").build();
		return Response.status(Status.CREATED).entity(fulfilled).build();
	}
	
	@GET
	@Path("/{id}/students")
	@Produces(MediaType.APPLICATION_JSON)
	public Response laySinhvientronlop(@PathParam("id") int id) {
		Lop lop = dao.layLopTheoID(id);
		if (lop.getID() == 0)
			return Response.status(Status.BAD_REQUEST).entity("Không có lớp nào có ID này").build();
		return Response.status(Status.ACCEPTED).entity(sinhvienlopDAO.laySinhvientronglop(id)).build();
	}
	
	@DELETE
	@Path("/{id}/students/{sinhvienId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response xoaSinhvienkhoilop(@PathParam("id") int id, @PathParam("sinhvienId")int sinhvienId) {
		boolean fulfilled = sinhvienlopDAO.xoaSinhvienkhoilop(id, sinhvienId);
		if (!fulfilled)
			return Response.status(Status.BAD_REQUEST).entity("Có lỗi xảy ra, vui lòng kiểm tra lại thông").build();
		return Response.status(Status.CREATED).entity(fulfilled).build();
	}

}
