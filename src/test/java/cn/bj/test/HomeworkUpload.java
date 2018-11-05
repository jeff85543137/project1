package cn.bj.test;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.bj.pojo.Mobile;
import cn.bj.service.StuService;

public class HomeworkUpload {

	public static void main(String[] args) {
		
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-dao.xml","spring-service.xml");
			 StuService bean = context.getBean(StuService.class);
			long startTime = System.currentTimeMillis();
			List<Mobile> mobileList = new ArrayList<Mobile>(); 
			Workbook workbook = WorkbookFactory.create(new File("D:\\Mobile.xls"));
			int numberOfSheets = workbook.getNumberOfSheets();
			long readTime = System.currentTimeMillis();
			//第一层循环
			for(int i = 0;i<numberOfSheets;i++){
				Sheet sheet = workbook.getSheetAt(i);
				System.out.println(sheet.getSheetName());
				int numberOfRows = sheet.getPhysicalNumberOfRows();
				
				//第二层循环
				for(int j = 1 ;j<numberOfRows;j++){
					Row row = sheet.getRow(j);
					int numberOfCells = row.getPhysicalNumberOfCells();
					StringBuffer b=new StringBuffer();
					
					//第三层循环
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
					
					//封装对象
					String[] ds = b.toString().split("~");
					Mobile mobile = new Mobile();
					mobile.setId(Integer.parseInt(ds[0]));
					mobile.setMobilenumber(ds[1]);
					mobile.setMobilearea(ds[2]);
					mobile.setMobiletype(ds[3]);
					mobile.setAreacode(Integer.parseInt(ds[4]));
					mobile.setPostcode(Integer.parseInt(ds[5]));
					
					 mobileList.add(mobile);
					 //当大于1000时保存一次，并清空
					 if(mobileList.size()>1000){
						 bean.batchsave(mobileList);
						 mobileList.clear();
					 }
				}
				//当一次循环结束时，不足1000，保存一次，并清空
				 bean.batchsave(mobileList);
				
				 mobileList.clear();
				
			}

			long endTime = System.currentTimeMillis();
			System.out.println("程序读取时间：" + (readTime - startTime)/1000 + "s");
			System.out.println("程序运行时间：" + (endTime - startTime)/1000 + "s");
			workbook.close();
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
