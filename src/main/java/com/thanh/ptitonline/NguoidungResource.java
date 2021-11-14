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
import jakarta.servlet.ServletException;
import com.thanh.model.*;

import java.util.ArrayList;

import com.thanh.dao.*;

@Path("users")
public class NguoidungResource {
	NguoidungDAO dao = new NguoidungDAO();
	KetquabaithiDAO ketquabaithiDao = new KetquabaithiDAO();
	SinhvienDAO sinhvienDao = new SinhvienDAO();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response layTatcaNguoidung() {
		return Response.status(Status.ACCEPTED).entity(dao.layTatcaNguoidung()).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response layNguoidungTheoID(@PathParam("id") int id) throws ServletException {
		Nguoidung user = dao.layNguoidungTheoID(id);
		if (user.getID() == 0)
			return Response.status(Status.BAD_REQUEST).entity("Không có người dùng nào có ID này").build();
		return Response.status(Status.ACCEPTED).entity(user).build();
	}

	@GET
	@Path("/{id}/testresults")
	@Produces(MediaType.APPLICATION_JSON)
	public Response layKetquabaithiTheoSinhvienID(@PathParam("id") int id) {
		Nguoidung user = dao.layNguoidungTheoID(id);
		if (user.getID() == 0)
			return Response.status(Status.BAD_REQUEST).entity("Không có người dùng nào có ID này").build();
		return Response.status(Status.ACCEPTED).entity(ketquabaithiDao.layKetquabaithiTheoSinhvienID(id)).build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response themNguoidung(Nguoidung newUser) {
		int newID = dao.themNguoidung(newUser);
		if (newID == 0)
			return Response.status(Status.BAD_REQUEST).entity("Có lỗi gì đó xảy ra, kiểm tra lại thông tin").build();
		return Response.status(Status.ACCEPTED).entity(dao.layNguoidungTheoID(newID)).build();

	}

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response dangnhap(Nguoidung user) throws ServletException {
		int userID = dao.kiemtraDangnhap(user);
		if (userID == 0) {
			return Response.status(Status.BAD_REQUEST).entity("Tài khoản hoặc mật khẩu không đúng").build();
		}
		return Response.status(Status.ACCEPTED).entity(dao.layNguoidungTheoID(userID)).build();
	}

	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response capnhatNguoidung(@PathParam("id") int id, Nguoidung changedUser) throws ServletException {
		if (changedUser.getDiemluyentap() != 0) {
			Nguoidung user = dao.layNguoidungTheoID(id);
			int diemluyentapmoi = user.getDiemluyentap() + changedUser.getDiemluyentap();
			dao.capnhatDiemluyentap(id, diemluyentapmoi);
		}
		dao.capnhatNguoidung(id, changedUser);
		return Response.status(Status.ACCEPTED).entity(dao.layNguoidungTheoID(id)).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response xoaNguoidung(@PathParam("id") int id) throws ServletException {
		Nguoidung user = dao.layNguoidungTheoID(id);
		if (user.getID() == 0)
			return Response.status(Status.BAD_REQUEST).entity("Không tồn tại người dùng có id này").build();
		return Response.status(Status.ACCEPTED).entity(dao.xoaNguoidung(id)).build();

	}

	@GET
	@Path("/students")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Nguoidung> layTatcaSinhvien() {
		return sinhvienDao.layTatcaSinhvien();
	}
}
