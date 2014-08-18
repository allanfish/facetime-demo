package com.conlect.oatos.file;

import com.conlect.oatos.dto.client.doc.DocConvertDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.ErrorType;

/**
 * <p>
 * 文档转换REST服务请求地址
 * </p>
 * 
 * 主要功能：
 * <ul>
 * <li>文档转换
 * <li>检查文本编码
 * </ul>
 * 
 * @author yang
 * 
 */
public interface DocConvertUrl {

	/**
	 * 文档转换服务根地址 <b>参数：</b>
	 * <ul>
	 * <li>
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>
	 * </ul>
	 */
	String ConvertService = "convert";

	/**
	 * <p>
	 * 文档转换
	 * </p>
	 * <b>参数：</b>
	 * <ul>
	 * <li>{@link DocConvertDTO}的JSON
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>{@link CommConstants#QUEUED}：文档已加入等待队列，等待转换
	 * <li>{@link CommConstants#OK_MARK}：文档转换完成
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>{@link ErrorType#errorFileNotFound}：源文件不存在
	 * <li>{@link CommConstants#ERROR_MARK}：文档转换失败
	 * <li>{@link ErrorType#error500}：系统错误
	 * </ul>
	 */
	String docConvert = "docConvert";

	/**
	 * <p>
	 * 检查文本编码，文本编码转成UTF-8
	 * </p>
	 * <b>参数：</b>
	 * <ul>
	 * <li>文件地址
	 * </ul>
	 * <b>正常返回：</b>
	 * <ul>
	 * <li>
	 * </ul>
	 * <b>异常返回：</b>
	 * <ul>
	 * <li>
	 * </ul>
	 */
	String checkTextCharset = "checkTextCharset";

}
