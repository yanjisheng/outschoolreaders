package cn.edu.jju.outschoolreaders.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传和下载图片
 * @author yanjisheng
 *
 */
@RestController
public class FileController {

	private static final Logger log = LoggerFactory.getLogger(FileController.class);
	
	@Value("${upload-file-path}")
	private String filePath;
	
	private Integer serialNo = new Integer(0);
	
	/**
	 * 上传图片
	 */
	@PostMapping("/upload")
	public String upload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
		String generatedFileName = getFileName();
		String originalFileName = multipartFile.getOriginalFilename();
		String extension = originalFileName.substring(originalFileName.lastIndexOf(".") == -1 ? 0 
				: (originalFileName.lastIndexOf(".") + 1));
		if(!(extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg") || extension.equalsIgnoreCase("gif") 
				|| extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("bmp"))) {
			return "File format not supported.";
		}
		File path = new File(filePath);
		File file = new File(filePath, generatedFileName + "." + extension);
		path.mkdirs();
		multipartFile.transferTo(file);
		log.info("上传图片" + generatedFileName + "." + extension);
		return generatedFileName + "." + extension;
	}
	
	/**
	 * 下载图片
	 */
	@GetMapping("/upload/{fileName}")
	public void getUploadFile(HttpServletResponse response, @PathVariable String fileName) throws IOException {
		File file = new File(filePath, fileName);
		InputStream is = new FileInputStream(file);
		OutputStream os = response.getOutputStream();
		byte[] buf = new byte[1024];
		int numOfBytes = 0;
		while(numOfBytes >= 0) {
			numOfBytes = is.read(buf);
			os.write(buf, 0, numOfBytes);
		}
		is.close();
		os.close();
	}
	
	private String getFileName() {
		DecimalFormat format = new DecimalFormat("0000");
		synchronized(serialNo) {
			serialNo ++;
		}
		return String.valueOf(System.currentTimeMillis()) + format.format(serialNo % 10000);
	}
}
