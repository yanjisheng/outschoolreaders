package cn.edu.jju.outschoolreaders.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.jju.outschoolreaders.dao.ReaderDao;
import cn.edu.jju.outschoolreaders.model.Reader;
import cn.edu.jju.outschoolreaders.model.ReaderQuery;
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
	
	public void addReader(Reader reader) {
		log.info("新读者["+reader.getName()+"]注册");
		readerDao.addOne(reader);
	}
	
	public Reader getReaderById(Integer readerId) {
		log.debug("按id["+readerId+"]查询读者信息");
		return readerDao.selectById(readerId);
	}
	
	public Reader getReaderByCardNoAndName(String cardNo, String name) {
		log.debug("按借阅证卡号["+cardNo+"]和姓名["+name+"]查询读者信息");
		Reader reader = readerDao.selectByCardNo(cardNo);
		if(reader == null || !reader.getName().equalsIgnoreCase(name)) {
			return null;
		}
		return reader;
	}
	
	public PageResult<Reader> queryReaders(ReaderQuery query) {
		log.debug("按条件查询读者列表");
		int count = readerDao.count(query);
		List<Reader> list = readerDao.query(query);
		return new PageResult<>(count, list);
	}

	public void modifyReader(Reader reader) {
		log.info("修改读者["+reader.getId()+"]信息");
		readerDao.modify(reader);
	}
}
