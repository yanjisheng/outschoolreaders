package cn.edu.jju.outschoolreaders.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.jju.outschoolreaders.dao.ReaderDao;
import cn.edu.jju.outschoolreaders.model.Reader;
import cn.edu.jju.outschoolreaders.model.ReaderQuery;
import cn.edu.jju.outschoolreaders.util.ExcelExporter;
import cn.edu.jju.outschoolreaders.util.PageResult;

/**
 * 读者信息
 * @author yanjisheng
 *
 */
@Service
public class ReaderService {

	private static final Logger log = LoggerFactory.getLogger(ReaderService.class);
	
	@Autowired
	private ReaderDao readerDao;
	
	@Autowired
	private ExcelExporter excelExporter;
	
	public void addReader(Reader reader) {
		log.info("新读者["+reader.getName()+"]注册");
		readerDao.addOne(reader);
	}
	
	public Reader getReaderById(Integer readerId) {
		log.debug("按id["+readerId+"]查询读者信息");
		return readerDao.selectDetailById(readerId);
	}
	
	public Reader getReaderByCardNoAndName(String cardNo, String name) {
		log.debug("按借阅证卡号["+cardNo+"]和姓名["+name+"]查询读者信息");
		Reader reader = readerDao.selectByCardNo(cardNo);
		if(reader == null || !reader.getName().equalsIgnoreCase(name)) {
			return null;
		}
		return reader;
	}
	
	public Reader getReaderByIdentityNoAndName(Integer id, String identityNo, String name) {
		log.debug("按id["+id+"]、身份证号["+identityNo+"]和姓名["+name+"]查询读者信息");
		if(id != null) {
			Reader reader = readerDao.selectById(id);
			if(reader != null && reader.getName().equalsIgnoreCase(name)) {
				return reader;
			}
		}
		Reader reader = readerDao.selectByIdentityNoAndName(identityNo, name);
		return reader;
	}
	
	public PageResult<Reader> queryReaders(ReaderQuery query) {
		log.debug("按条件查询读者列表");
		int count = readerDao.count(query);
		List<Reader> list = readerDao.query(query);
		return new PageResult<>(count, list);
	}

	/**
	 * 管理员修改读者信息
	 */
	public void modifyReader(Reader reader) {
		log.info("修改读者["+reader.getId()+"]信息");
		readerDao.modify(reader);
	}
	
	/**
	 * 读者修改自己的信息
	 */
	public void readerModify(Reader readerTemp) {
		Reader reader = getReaderById(readerTemp.getId());
		if(reader == null || !reader.getName().equalsIgnoreCase(readerTemp.getName())) {
			return;
		}
		reader.setAddress(readerTemp.getAddress());
		reader.setPhone(readerTemp.getPhone());
		reader.setEmail(readerTemp.getEmail());
		modifyReader(reader);
	}

	public void exportReaders(ReaderQuery query, HttpServletResponse response) {
		log.debug("导出读者信息");
		query.setPage(null);
		List<Reader> list = readerDao.query(query);
		try {
			excelExporter.export(Reader.class, list, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询所有用户照片的文件名
	 */
	public List<String> getAllImages() {
		List<String> images = new ArrayList<>();
		PageResult<Reader> readerPage = queryReaders(null);
		for(Reader reader : readerPage.getList()) {
			if(reader.getImage() != null && !reader.getImage().equals("")) {
				images.add(reader.getImage());
			}
		}
		return images;
	}
}
