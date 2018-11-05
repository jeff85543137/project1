package cn.bj.test;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.bj.dao.StuDao;
import cn.bj.pojo.Stu;

public class TestDao {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml");
		StuDao bean = context.getBean(StuDao.class);
		List<Stu> list = bean.getAllStu();
		for (Stu stu : list) {
			System.out.println("姓名:"+stu.getName()+" 成绩:"+stu.getScore());
		}
	}
}
