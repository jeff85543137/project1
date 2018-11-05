package cn.bj.test;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.bj.dao.StuDao;
import cn.bj.pojo.Stu;
import cn.bj.service.StuService;

public class TestService {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
		 StuService bean = context.getBean(StuService.class);
		List<Stu> list = bean.getAllStu();
		for (Stu stu : list) {
			System.out.println("姓名:"+stu.getName()+" 成绩:"+stu.getScore());
		}
	}
}
