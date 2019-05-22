package cn.edu.jju.outschoolreaders.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import cn.edu.jju.outschoolreaders.model.Manager;
import cn.edu.jju.outschoolreaders.model.Reader;
import cn.edu.jju.outschoolreaders.model.Transaction;

/**
 * 导出Excel
 * @author yanjisheng
 *
 */
@Component
public class ExcelExporter {

	/**
	 * 输出Excel表格
	 */
	public Workbook export(List<Map<String, Object>> dataList) {
		Set<String> titles = dataList.get(0).keySet();
		Workbook workbook = new HSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		CellStyle dateStyle = workbook.createCellStyle();
		dateStyle.setDataFormat((short) 0xe);
		int rowNum = 0;
		int cellNum = 0;
		Row titleRow = sheet.createRow(rowNum);		
		//标题行
		for(String title : titles) {
			Cell cell = titleRow.createCell(cellNum);
			cell.setCellValue(title);
			cellNum ++;
		}
		rowNum ++;
		//内容行
		for(Map<String, Object> data : dataList) {
			Row row = sheet.createRow(rowNum);
			cellNum = 0;
			for(String title : titles) {
				Cell cell = row.createCell(cellNum);
				Object dataItem = data.get(title);
				if(dataItem instanceof String) {
					cell.setCellValue((String) dataItem);
				} else if(dataItem instanceof Number) {
					cell.setCellValue(((Number) dataItem).doubleValue()); 
				} else if(dataItem instanceof Date) {
					cell.setCellValue((Date) dataItem);
					cell.setCellStyle(dateStyle);
				}
				cellNum ++;
			}
			rowNum ++;
		}
		return workbook;
	}
	
	private Map<String, Object> getMap(Transaction transaction) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("序号", transaction.getId());
		map.put("缴费日期", transaction.getDate());
		map.put("金额", transaction.getAmount());
		map.put("缴费人姓名", transaction.getReader() == null ? null : transaction.getReader().getName());
		map.put("性别", transaction.getReader() == null ? null : transaction.getReader().getGender());
		map.put("借阅证号", transaction.getReader() == null ? null : transaction.getReader().getCardNo());
		map.put("身份证号", transaction.getReader() == null ? null : transaction.getReader().getIdentityNo());
		map.put("住址", transaction.getReader() == null ? null : transaction.getReader().getAddress());
		map.put("联系电话", transaction.getReader() == null ? null : transaction.getReader().getPhone());
		map.put("电子邮件", transaction.getReader() == null ? null : transaction.getReader().getEmail());
		map.put("读者类型", transaction.getReader() == null ? null : transaction.getReader().getCategoryName());
		map.put("缴费类型", transaction.getTypeName());
		map.put("有效期至", transaction.getValidThru());
		map.put("经办人", transaction.getManager() == null ? null : transaction.getManager().getName());
		return map;
	}
	
	private Map<String, Object> getMap(Reader reader) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("序号", reader.getId());
		map.put("读者姓名", reader.getName());
		map.put("性别", reader.getGender());
		map.put("借阅证号", reader.getCardNo());
		map.put("身份证号", reader.getIdentityNo());
		map.put("住址", reader.getAddress());
		map.put("联系电话", reader.getPhone());
		map.put("电子邮件", reader.getEmail());
		map.put("有效期至", reader.getValidThru());
		map.put("读者类型", reader.getCategoryName());
		map.put("注册时间", reader.getCreatedAt());
		map.put("备注", reader.getRemark());
		return map;
	}
	
	private Map<String, Object> getMap(Manager manager) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("序号", manager.getId());
		map.put("登录名", manager.getLoginName());
		map.put("姓名", manager.getName());
		map.put("权限", manager.getSuperAdmin() == 1 ? "超级管理员" : "管理员");
		return map;
	}
	
	private <T> List<Map<String, Object>> getMapList(Class<T> dataType, List<T> data) {
		List<Map<String, Object>> list = new ArrayList<>();
		if(dataType.equals(Transaction.class)) {
			for(T item : data) {
				list.add(getMap((Transaction) item));
			}
		} else if(dataType.equals(Reader.class)) {
			for(T item : data) {
				list.add(getMap((Reader) item));
			}
		} else if(dataType.equals(Manager.class)) {
			for(T item : data) {
				list.add(getMap((Manager) item));
			}
		}
		return list;
	}
	
	/**
	 * 向输出流导出Excel表格
	 */
	public <T> void export(Class<T> dataType, List<T> data, OutputStream os) throws IOException {
		List<Map<String, Object>> dataMapList = getMapList(dataType, data);
		Workbook workbook = export(dataMapList);
		workbook.write(os);
		workbook.close();
	}
	
	/**
	 * 向HttpServletResponse输出Excel表格
	 */
	public <T> void export(Class<T> dataType, List<T> data, HttpServletResponse response) throws IOException {
		response.setContentType("application/x-xls");
		OutputStream os = response.getOutputStream();
		export(dataType, data, os);
		os.close();
	}
	
}
