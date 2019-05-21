package cn.edu.jju.outschoolreaders.util;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.edu.jju.outschoolreaders.model.Manager;
import cn.edu.jju.outschoolreaders.model.Reader;
import cn.edu.jju.outschoolreaders.model.Transaction;

/**
 * 导出csv文件（可用Excel打开）
 * @author yanjisheng
 *
 */
@Component
public class CSVExporter {

	@Autowired
	private DateTimeFormatter dateFormatter;
	
	@SuppressWarnings("unchecked")
	public <T> void export(Class<T> dataType, List<T> data, OutputStream os) throws CSVException{
		if(data == null) {
			throw new CSVException("Data is null.", new NullPointerException());
		}
		StringBuilder out = new StringBuilder();
		if(dataType.equals(String.class)) {
			for(String item : (List<String>) data) {
				out.append(item + "\n");
			}
		} else if(dataType.equals(Transaction.class)) {
			out.append("序号,缴费日期,金额,缴费人姓名,性别,借阅证号,身份证号,住址,联系电话,电子邮件,读者类型,缴费类型,有效期至,经办人\n");
			for(Transaction transaction : (List<Transaction>) data) {
				out.append(transaction.getId() + ",");
				out.append(dateFormatter.toDate(transaction.getDate()) + ",");
				out.append(transaction.getAmount() + ",");
				Reader reader = transaction.getReader();
				if(reader != null) {
					out.append(reader.getName() + ",");
					out.append(reader.getGender() + ",");
					out.append(reader.getCardNo() + ",");
					out.append(reader.getIdentityNo() + ",");
					out.append(reader.getAddress() + ",");
					out.append(reader.getPhone() + ",");
					out.append(reader.getEmail() + ",");
					out.append(reader.getCategoryName() + ",");
				} else {
					out.append(",,,,,,,,");
				}
				out.append(transaction.getTypeName() + ",");
				out.append(dateFormatter.toDate(transaction.getValidThru()) + ",");
				Manager manager = transaction.getManager();
				if(manager != null) {
					out.append(manager.getName());
				}
			}
		} else if(dataType.equals(Reader.class)) {
			out.append("序号,读者姓名,性别,借阅证号,身份证号,住址,联系电话,电子邮件,有效期至,读者类型,注册时间,备注\n");
			//TODO
		} else if(dataType.equals(Manager.class)) {
			out.append("序号,登录名,姓名,权限\n");
			//TODO
		} else {
			throw new CSVException("Data type is unknown.");
		}
		try {
			Writer writer = new OutputStreamWriter(os, "GBK");
			writer.write(out.toString());
			writer.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new CSVException("GBK encoding is not supported", e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new CSVException("I/O exception", e);
		}
	}
	
}
