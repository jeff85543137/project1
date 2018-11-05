package cn.bj.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.bj.dao.StuDao;
import cn.bj.pojo.Mobile;
import cn.bj.pojo.Stu;
import cn.bj.service.StuService;

@Service
public class StuServiceImpl implements StuService {

	@Autowired
	private StuDao stuDao;
	
	@Override
	public List<Stu> getAllStu() {
		return stuDao.getAllStu();
	}

	@Override
	public void save(Stu s) {
		stuDao.save(s);
	}

	@Override
	public void batchsave(List<Mobile> mobileList) {
		stuDao.batchsave(mobileList);
	}

}
