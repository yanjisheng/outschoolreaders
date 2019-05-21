package cn.edu.jju.outschoolreaders.util;

/**
 * 导出CSV文件中产生的错误
 * @author yanjisheng
 *
 */
public class CSVException extends Exception {

	private static final long serialVersionUID = -8787996422202201779L;

	public CSVException() {
		super();
	}

	public CSVException(String message, Throwable cause) {
		super(message, cause);
	}

	public CSVException(String message) {
		super(message);
	}

	
}
