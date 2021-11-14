package com.thanh.ptitonline;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import com.thanh.model.*;

import com.thanh.dao.*;

@Path("testresults")
public class KetquabaithiResource {
	KetquabaithiDAO dao = new KetquabaithiDAO();
	BaithiDAO baithiDao = new BaithiDAO();
	NguoidungDAO nguoidungDao = new NguoidungDAO();

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response themKetquabaithi(Ketquabaithi newResult) {
		boolean fulfilled = dao.themKetquabaithi(newResult);
		if (!fulfilled)
			return Response.status(Status.BAD_REQUEST).entity("Có lỗi xảy ra, kiểm tra lại thông tin").build();
		else {
			return Response.status(Status.ACCEPTED).entity(fulfilled).build();
		}
	}
	
	
}
