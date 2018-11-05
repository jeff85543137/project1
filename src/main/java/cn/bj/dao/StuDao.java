package cn.bj.dao;

import java.util.List;

import cn.bj.pojo.Mobile;
import cn.bj.pojo.Stu;


public interface StuDao {

	public List<Stu> getAllStu();

	public void save(Stu s);

	public void batchsave(List<Mobile> list);
}
