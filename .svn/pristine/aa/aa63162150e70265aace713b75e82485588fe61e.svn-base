package com.qycloud.oatos.convert;

import com.conlect.oatos.dto.client.doc.DocConvertDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.file.DiskUtil;

/**
 * pdf转成swf线程
 * @author yang
 *
 */
public class Pdf2SwfThread extends Thread {

	private DocConvertDTO docDTO;

	public Pdf2SwfThread(DocConvertDTO docDTO) {
		this.docDTO = docDTO;
	}

	@Override
	public void run() {
		int start = docDTO.getStartPage();
		int pageCount = docDTO.getPageCount();
		String pdfPath = docDTO.getDiskRootPath() + docDTO.getPdfPath();
		while (start <= pageCount) {
			int end = start + CommConstants.PDF_SPLIT_PAGES - 1;
			String swfPath = docDTO.getDiskRootPath() + DiskUtil.getSplitSwfPath(docDTO.getTargetPath(), start);
			SwfTools.pdfToSwf(pdfPath, swfPath, start, end);
			start = start + CommConstants.PDF_SPLIT_PAGES;
		}
	}
}
