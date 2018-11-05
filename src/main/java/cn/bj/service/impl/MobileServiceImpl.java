package cn.bj.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bj.dao.MobileDao;
import cn.bj.pojo.Mobile;
import cn.bj.service.MobileService;
@Service
public class MobileServiceImpl implements MobileService {

	@Autowired
	private MobileDao mobileDao;
	
	@Override
	public Mobile getMobile(String mobilenumber) {
		return mobileDao.getMobile(mobilenumber);
	}

	

}
