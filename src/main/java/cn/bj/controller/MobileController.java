package cn.bj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bj.pojo.Mobile;
import cn.bj.service.MobileService;

@Controller
public class MobileController {

	@Autowired
	private MobileService mobileService;
	
	@RequestMapping("getMobile")
	@ResponseBody
	public Mobile getMobile(String mobilenumber){
		
		Mobile mobile = mobileService.getMobile(mobilenumber);
		return mobile;
	}
}
