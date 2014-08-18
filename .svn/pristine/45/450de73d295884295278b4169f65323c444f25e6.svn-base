package com.qycloud.oatos.convert.domain.logic;

import com.conlect.oatos.dto.client.doc.DocConvertDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.DocConvertType;
import com.qycloud.oatos.convert.OfficeConverter;

/**
 * office文档转换任务
 * @author yang
 *
 */
public class OfficeConvertTask extends ConvertTask {

	public OfficeConvertTask(DocConvertDTO docDTO) {
		super(docDTO);
	}

	@Override
	protected void doConvert(DocConvertDTO docDTO) throws Exception {
		String sourcePath = docDTO.getDiskRootPath() + docDTO.getSourcePath();
		String targetPath = docDTO.getDiskRootPath() + docDTO.getTargetPath();
		if (DocConvertType.DOC_TO_PDF.equals(docDTO.getConvertType())) {
			// 文档转成pdf
			OfficeConverter.docToPdf(sourcePath, targetPath);
		} else if (DocConvertType.DOC_TO_HTML.equals(docDTO.getConvertType())) {
			// 文档转成html
			OfficeConverter.docToHtml(sourcePath, targetPath);
		} else if (DocConvertType.DOC_TO_SWF.equals(docDTO.getConvertType())) {
			// 文档转成swf
			if (CommonUtil.isPdf(docDTO.getSourcePath())) {
				// pdf文档直接转成swf
				docDTO.setPdfPath(docDTO.getSourcePath());
				SwfConvertPoolExecutor.getInstance().convert(docDTO);
			} else {
				// 其他文档，先转成pdf
				String pdfPath = docDTO.getDiskRootPath() + docDTO.getPdfPath();
				String result = OfficeConverter.docToPdf(sourcePath, pdfPath);
				if (CommConstants.OK_MARK.equals(result)) {
					// 再转成swf
					SwfConvertPoolExecutor.getInstance().convert(docDTO);
				}
			}
		} else if (DocConvertType.DOC_CONVERT.equals(docDTO.getConvertType())) {
			// office文档转换
			OfficeConverter.convert(sourcePath, targetPath);
		}
	}

}
