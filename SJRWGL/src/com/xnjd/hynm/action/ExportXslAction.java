/**
 * 
 */
package com.xnjd.hynm.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.CellStyle;

import com.xnjd.hynm.model.Account;
import com.xnjd.hynm.model.Event;
import com.xnjd.hynm.service.EventManageService;
import com.xnjd.hynm.service.UsersManageService;

import com.opensymphony.xwork2.ActionContext;
/**
 * @author Administrator
 *
 */
import com.opensymphony.xwork2.ActionSupport;

public class ExportXslAction extends ActionSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	
private UsersManageService usersManageService;//为实现下拉列表添加使用者
	
	/**
	 * @param usersManageService the usersManageService to set
	 */
	public void setUsersManageService(UsersManageService usersManageService) {
		this.usersManageService = usersManageService;
	}
	
	private EventManageService eventManageService;
	public void setEventManageService(EventManageService eventManageService) {
		this.eventManageService = eventManageService;
	}
	private List<Account> userlist;
	private InputStream excelFile;
	private int currentIndex=1;
	private String search_eventEngineer;
	private String search_startDate;
	private String search_endDate;
	
	/**
	 * @return the search_eventEngineer
	 */
	public String getSearch_eventEngineer() {
		return search_eventEngineer;
	}

	/**
	 * @param search_eventEngineer the search_eventEngineer to set
	 */
	public void setSearch_eventEngineer(String search_eventEngineer) {
		this.search_eventEngineer = search_eventEngineer;
	}

	/**
	 * @return the search_startDate
	 */
	public String getSearch_startDate() {
		return search_startDate;
	}

	/**
	 * @param search_startDate the search_startDate to set
	 */
	public void setSearch_startDate(String search_startDate) {
		this.search_startDate = search_startDate;
	}

	
	/**
	 * @return the search_endDate
	 */
	public String getSearch_endDate() {
		return search_endDate;
	}

	/**
	 * @param search_endDate the search_endDate to set
	 */
	public void setSearch_endDate(String search_endDate) {
		this.search_endDate = search_endDate;
	}

	// ++++++++++++++++++++++++++
	/*
	 * 数据库
	 * 查询后绑定到对象后 将对象赋值给list
	 * 然后转成对象进行列操作
	 * */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void  ExcelFile()  {   
		
		List<Event> list = new ArrayList<Event>();
		List<?> tempevent;
		Account tempaccAccount=new Account();
		tempaccAccount.setId(-1);
		tempaccAccount.setName("--全部--");
		userlist=new ArrayList<Account>();
		userlist.add(tempaccAccount);
		userlist.addAll(usersManageService.loadUser());
		//userlist = usersManageService.loadUser(); //新添加的用户列表中用于下拉列表显示
		 setCurrentIndex(2);
		Account account=(Account)ActionContext.getContext().getSession().get("admin");
		if(account.getSjsh())
		   tempevent= eventManageService.LoadAllCompletedExport(search_eventEngineer.equals("-1")?null:search_eventEngineer,search_startDate.length()==0?null:search_startDate,search_endDate.length()==0?null:search_endDate);
		else  
			tempevent=eventManageService.LoadCompletedExport(account.getId(),null,search_startDate.length()==0?null:search_startDate,search_endDate.length()==0?null:search_endDate);
		
		list.addAll((Collection<? extends Event>) tempevent);
		
		
		 HSSFWorkbook workbook = new HSSFWorkbook();
		 HSSFSheet sheet = workbook.createSheet();
		 workbook.setSheetName(0, "事件统计表");
		 HSSFRow row1= sheet.createRow(0);
		 sheet.addMergedRegion(new Region(0,(short)0,2,(short)7));
		 
		 HSSFCellStyle cellStyle = workbook.createCellStyle();
		 cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		 cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		 cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		 cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		 cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		 cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		 HSSFFont font = workbook.createFont();
		 font.setFontHeightInPoints((short)20);
		 font.setFontName("宋体");
		 font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//字体加粗
		 cellStyle.setFont(font);
		 HSSFCell titleCell = row1.createCell(0);
		 titleCell.setCellValue("事件汇总");
		 titleCell.setCellStyle(cellStyle);
		 
		
		HSSFRow row2 = sheet.createRow(3);
		   row2.setHeightInPoints(30);
		   CellStyle cellStyle1 = workbook.createCellStyle();
		   cellStyle1.setAlignment(CellStyle.ALIGN_CENTER);
		   cellStyle1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		   cellStyle1.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
		   cellStyle1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		   cellStyle1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		   cellStyle1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		   cellStyle1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		   HSSFFont font1 = workbook.createFont();
		   font1.setFontHeightInPoints((short)10);
		   font1.setFontName("宋体");
		   font1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//字体加粗
		   cellStyle1.setFont(font1);
		   cellStyle1.setWrapText(true);
		   
		   
		   CellStyle cellStyle2 = workbook.createCellStyle();
		   cellStyle2.setAlignment(CellStyle.ALIGN_CENTER);
		   cellStyle2.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		   cellStyle2.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
		   cellStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		   cellStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		   cellStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		   cellStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		   HSSFFont font2 = workbook.createFont();  //设置字体
		   font2.setFontHeightInPoints((short)10);
		   font2.setFontName("宋体");
		   cellStyle2.setFont(font2);
		   cellStyle2.setWrapText(true);//自动换行
		   
		   
		   HSSFCell eventEengineer = row2.createCell(0);
		   eventEengineer.setCellValue("工程师姓名");
		   eventEengineer.setCellStyle(cellStyle1);
		   
		   HSSFCell eventDate = row2.createCell(1);
		   eventDate.setCellValue("事件发生日期");
		   eventDate.setCellStyle(cellStyle1);
		   
		   HSSFCell comusterName = row2.createCell(2);
		   comusterName.setCellValue("客户名称");
		   comusterName.setCellStyle(cellStyle1);
		   
		   HSSFCell eventType = row2.createCell(3);
		   eventType.setCellValue("事件类型");
		   eventType.setCellStyle(cellStyle1);
		   
		   HSSFCell eventInfo = row2.createCell(4);
		   eventInfo.setCellValue("事件描述");
		   eventInfo.setCellStyle(cellStyle1);
		   
		   HSSFCell plantime1 = row2.createCell(5);
		   plantime1.setCellValue("预计完成时间");
		   plantime1.setCellStyle(cellStyle1);
		   
		   HSSFCell plantime2 = row2.createCell(6);
		   plantime2.setCellValue("预计第二完成时间");
		   plantime2.setCellStyle(cellStyle1);
		   
		  HSSFCell completetime = row2.createCell(7);
		   completetime.setCellValue("实际完成时间");
		   completetime.setCellStyle(cellStyle1);
		   	 
		   	 
	   /*输出数据**/
		for (int i = 1; i <= list.size(); i++) {
			Event att = list.get(i - 1);
			
			row2 = sheet.createRow(i+3);
			
			
			HSSFCell eventEngineer = row2.createCell(0);
			eventEngineer.setCellValue(att.getEventEngineer());
			eventEngineer.setCellStyle(cellStyle2);
			
			HSSFCell dateEvent = row2.createCell(1);
			dateEvent.setCellValue(att.getEventDate());
			dateEvent.setCellStyle(cellStyle2);
			
			HSSFCell eventCustomer = row2.createCell(2);
			eventCustomer.setCellValue(att.getEventCustomer());
			eventCustomer.setCellStyle(cellStyle2);
			
			HSSFCell typeEvent = row2.createCell(3);
			typeEvent.setCellValue(att.getEventType());
			typeEvent.setCellStyle(cellStyle2);
			
			HSSFCell infoEvent = row2.createCell(4);
			infoEvent.setCellValue(att.getEventInfo());
			infoEvent.setCellStyle(cellStyle2);
								
			HSSFCell time1Plan = row2.createCell(5);
			time1Plan.setCellValue(att.getPlanTime1());
			time1Plan.setCellStyle(cellStyle2);
			
			HSSFCell time2Plan = row2.createCell(6);
			time2Plan.setCellValue(att.getPlanTime2());
			time2Plan.setCellStyle(cellStyle2);
			
			HSSFCell timeComplete = row2.createCell(7);
			timeComplete.setCellValue(att.getCompleteTime());
			timeComplete.setCellStyle(cellStyle2);
			
			
			
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			
			workbook.write(baos);
 
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] aa = baos.toByteArray();
		excelFile = new ByteArrayInputStream(aa,0,aa.length); 
		try {
			baos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String execute() throws Exception {
	
		
		ExcelFile();
		return SUCCESS;
	}

	public InputStream getExcelFile() {
		return excelFile;
	}

	/**
	 * @return the currentIndex
	 */
	public int getCurrentIndex() {
		return currentIndex;
	}

	/**
	 * @param currentIndex the currentIndex to set
	 */
	public void setCurrentIndex(int currentIndex) {
		this.currentIndex = currentIndex;
	}
}
