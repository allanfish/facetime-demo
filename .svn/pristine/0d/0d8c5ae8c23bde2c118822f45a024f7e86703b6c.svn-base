package com.qycloud.oatos.convert.domain.logic;

import java.io.File;

import org.springframework.stereotype.Service;

import com.conlect.oatos.dto.client.doc.DocConvertDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.DocConvertType;
import com.conlect.oatos.dto.status.ErrorType;
import com.qycloud.oatos.convert.OfficeConverter;

/**
 * 文档转换队列服务
 * @author yang
 *
 */
@Service
public class ConvertLogic {

	/**
	 * office文件转换线程池
	 */
	private OfficeConvertPoolExecutor officeConvertExecutor = OfficeConvertPoolExecutor.getInstance();

	/**
	 * swf文件转换线程池
	 */
	private SwfConvertPoolExecutor swfConvertExecutor = SwfConvertPoolExecutor.getInstance();

	/**
	 * 文档转换
	 * @param doc
	 * @return
	 * @throws Exception
	 */
	public String convert(DocConvertDTO doc) {
		String result = CommConstants.QUEUED;
		// 取缓存的任务状态
		String status = ConvertPoolExecutor.getStatus(doc);
		if (status != null) {
			// 返回缓存的状态
			result = status;
		} else {
			File target = new File(doc.getTargetPath());
			boolean converted = false;
			if (target.exists()) {
				//  目标文件已经存在
				converted = true;
				if (DocConvertType.DOC_TO_SWF.equals(doc.getConvertType())) {
					if (doc.getPageCount() == null) {
						// 文件页数为空，需要获取文件页数
						converted = false;
					}
				}
			}
			if (converted) {
				result = CommConstants.OK_MARK;
			} else {
				// 开始新的转换
				result = doConvert(doc);
			}
		}
		return result;
	}

	/**
	 * 开始新的转换，将任务加入队列
	 * @param doc
	 * @return
	 */
	private String doConvert(DocConvertDTO doc) {
		String result = CommConstants.QUEUED;
		if (DocConvertType.DOC_TO_PDF.equals(doc.getConvertType())) {
			// 文档转成pdf
			if (CommonUtil.isPdf(doc.getSourcePath())) {
				result = CommConstants.OK_MARK;
			} else if (CommonUtil.isConvertToPdfSupported(doc.getSourcePath())) {
				officeConvertExecutor.convert(doc);
			} else {
				result = ErrorType.errorNotSupported.name();
			}
		} else if (DocConvertType.DOC_TO_HTML.equals(doc.getConvertType())) {
			// 文档转成html
			if (CommonUtil.isHtml(doc.getSourcePath())) {
				result = CommConstants.OK_MARK;
			} else if (CommonUtil.isConvertToHtmlSupported(doc.getSourcePath())) {
				officeConvertExecutor.convert(doc);
			} else {
				result = ErrorType.errorNotSupported.name();
			}
		} else if (DocConvertType.DOC_TO_SWF.equals(doc.getConvertType())) {
			// 文档转成swf
			if (CommonUtil.isPdf(doc.getSourcePath())) {
				// pdf文档直接转成swf
				doc.setPdfPath(doc.getSourcePath());
				swfConvertExecutor.convert(doc);
			} else if (CommonUtil.isConvertToPdfSupported(doc.getSourcePath())) {
				// 其他文档
				File pdfFile = new File(doc.getDiskRootPath(), doc.getPdfPath());
				if (pdfFile.exists()) {
					// pdf已经存在，将pdf转成swf
					swfConvertExecutor.convert(doc);
				} else {
					// pdf不存在，先转成pdf
					officeConvertExecutor.convert(doc);
				}
			} else {
				result = ErrorType.errorNotSupported.name();
			}
		} else if (DocConvertType.DOC_CONVERT.equals(doc.getConvertType())) {
			// office文档转换
			officeConvertExecutor.convert(doc);
		}
		return result;
	}

	/**
	 * 检查文本编码，如果不是utf-8，则转换成utf-8
	 * @param filePath
	 */
	public void checkTextCharset(String filePath) {
		OfficeConverter.checkTextCharset(filePath);
	}

}
