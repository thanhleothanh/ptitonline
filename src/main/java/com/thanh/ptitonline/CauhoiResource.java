package com.thanh.ptitonline;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import com.thanh.model.*;

import com.thanh.dao.*;

@Path("questions")
public class CauhoiResource {
	CauhoiDAO dao = new CauhoiDAO();
	CauhoithiDAO cauhoithiDao = new CauhoithiDAO();
	CauhoiluyentapDAO cauhoiluyentapDao = new CauhoiluyentapDAO();


	@POST
	@Path("/thi")
	@Produces(MediaType.APPLICATION_JSON)
	public Response themCauhoithi(Cauhoithi newQuestion) {
		int newID = dao.themCauhoi(newQuestion);
		if (newID == 0 || newQuestion.getTblbaithiid() == 0)
			return Response.status(Status.BAD_REQUEST).entity("Có lỗi xảy ra, kiểm tra lại thông tin câu hỏi thi").build();
		else {
			cauhoithiDao.themCauhoithi(newID, newQuestion.getTblbaithiid());
			return Response.status(Status.ACCEPTED).entity(cauhoithiDao.layCauhoithiTheoID(newID)).build();
		}
	}
	
	@POST
	@Path("/luyentap")
	@Produces(MediaType.APPLICATION_JSON)
	public Response themCauhoiluyentap(Cauhoiluyentap newQuestion) {
		int newID = dao.themCauhoi(newQuestion);
		if (newID == 0 || newQuestion.getTblgiaovienid() == 0)
			return Response.status(Status.BAD_REQUEST).entity("Có lỗi xảy ra, kiểm tra lại thông tin câu hỏi").build();
		else {
			cauhoiluyentapDao.themCauhoiluyentap(newID, newQuestion.getTblgiaovienid());
			return Response.status(Status.ACCEPTED).entity(cauhoiluyentapDao.layCauhoiluyentapTheoID(newID)).build();
		}
	}

	@GET
	@Path("/luyentap/monhoc/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response layCauhoiluyentapTheoMonhocID(@PathParam("id") int monhocId) {
		return Response.status(Status.ACCEPTED).entity(cauhoiluyentapDao.layCauhoiluyentapRandom(monhocId)).build();

	}
	

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response xoaCauhoi(@PathParam("id") int id) {
		Cauhoi question = dao.layCauhoiTheoID(id);
		if (question.getID() == 0)
			return Response.status(Status.BAD_REQUEST).entity("Không có câu hỏi thi nào có ID này").build();
		return Response.status(Status.ACCEPTED).entity(dao.xoaCauhoi(id)).build();

	}
	
}
