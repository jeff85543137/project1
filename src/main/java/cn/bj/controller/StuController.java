package cn.bj.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.bj.pojo.Stu;
import cn.bj.pojo.pie;
import cn.bj.service.StuService;

@Controller
public class StuController {

	@Autowired
	private StuService stuService;
	
	@RequestMapping("/getAllStu")
	@ResponseBody
	public List<Stu> getAllStu(){
		return stuService.getAllStu();
	}
	@RequestMapping("/getallStuPie")
	@ResponseBody
	public List<pie> getallStuPie(){
		List<pie> listpie = new ArrayList<pie>();
		List<Stu> liststu = stuService.getAllStu();
		for (Stu stu : liststu) {
			pie p = new pie();
			p.setName(stu.getName());
			p.setValue(stu.getScore());
			
			listpie.add(p);
		}
		return listpie;
	}
	
	@RequestMapping("upload")
	public String upload(@RequestParam("java0625file")MultipartFile file,HttpServletRequest request,Model model ){
		String path = request.getServletContext().getRealPath("upload");
		File file1 = new File(path);
		if(!file1.exists()){
			file1.mkdir();
		}
		String filename = file.getOriginalFilename();
		File file2 = new File(path+"\\"+filename);
		try {
			file.transferTo(file2);
			System.out.println("上传成功:"+filename);
		} catch (IllegalStateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Workbook workbook = WorkbookFactory.create(file2);
			int numberOfSheets = workbook.getNumberOfSheets();
			for(int i = 0;i<numberOfSheets;i++){
				Sheet sheet = workbook.getSheetAt(i);
				System.out.println(sheet.getSheetName());
				int numberOfRows = sheet.getPhysicalNumberOfRows();
				for(int j = 0 ;j<numberOfRows;j++){
					Row row = sheet.getRow(j);
					int numberOfCells = row.getPhysicalNumberOfCells();
					StringBuffer b=new StringBuffer();
					for(int k=0;k<numberOfCells;k++){
						Cell cell = row.getCell(k);
						if(cell.getCellTypeEnum()==CellType.STRING){
							b.append(cell.getStringCellValue()+"~");
						}else if(cell.getCellTypeEnum()==CellType.NUMERIC){
							//创建数字格式化工具
							DecimalFormat dc = new DecimalFormat("####");
							b.append(dc.format(cell.getNumericCellValue())+"~");
						}else if(cell.getCellTypeEnum()==CellType.BOOLEAN){
							b.append(cell.getBooleanCellValue()+"~");
						}else if(cell.getCellTypeEnum()==CellType.BLANK){
							b.append(null+"~");
						}else{
							b.append(cell.getDateCellValue()+"~");
						}
					}
					String[] ds = b.toString().split("~");
					Stu s = new Stu();
					s.setName(ds[1]);
					s.setScore(Integer.parseInt(ds[2]));
					s.setPhone(ds[3]);
					stuService.save(s);
				}
			}
			workbook.close();
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("filename", filename);
		return "jsp/upload-success";
	}
	
	/**
	 * @param request
	 * @param response
	 */
	@RequestMapping("/downloadexcel")
	public void downloadExcel(HttpServletRequest request,HttpServletResponse response){
		List<Stu> list = stuService.getAllStu();
		String path = request.getServletContext().getRealPath("down");
		//创建file
		File file1 = new File(path);
		//判断下载目录是否存在
		if(!file1.exists()){
			file1.mkdir();
		}
		String downFile="java0625.xlsx";
		File file2 = new File(path+"\\"+downFile);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet1 = workbook.createSheet("学员成绩表");
		int rownum=0;
		//遍历数据库读取到数据集合
		for(Stu stu:list){
			XSSFRow row = sheet1.createRow(rownum);
			//设置行的内容
			row.createCell(0).setCellValue(stu.getId());
			row.createCell(1).setCellValue(stu.getName());
			row.createCell(2).setCellValue(stu.getScore());
			row.createCell(3).setCellValue(stu.getPhone());
			rownum++;
		}
		try {
			workbook.write(new FileOutputStream(file2));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setContentType("application/x-xls;charset=GBK");
		response.setHeader("Content-Disposition", "attachment; filename=\""+downFile+"\"");
		response.setContentLength((int) file2.length());
		int len =-1;
		byte[] b =new byte[4096];
		try {
			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(file2));
			while ((len=in.read(b))!=-1) {
				out.write(b,0,len);
			}
			out.close();
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
}
