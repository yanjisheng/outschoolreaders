package cn.edu.jju.outschoolreaders.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 金额格式化（保留两位小数）
 * @author yanjisheng
 *
 */
//@Component
public class AmountFormatter implements Converter<String, BigDecimal> {

	@Override
	public BigDecimal convert(String source) {
		return new BigDecimal(source).setScale(2, RoundingMode.DOWN);
	}

}
