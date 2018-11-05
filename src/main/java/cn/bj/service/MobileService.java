package cn.bj.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import cn.bj.pojo.Mobile;

public interface MobileService {


	@POST
	@Path("/get/{mobilenumber}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Mobile getMobile(@PathParam("mobilenumber")String mobilenumber);
}
