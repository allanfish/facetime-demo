package com.qycloud.oatos.convert.test.logic;

import com.conlect.oatos.dto.client.doc.DocConvertDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.file.DiskUtil;
import com.qycloud.oatos.convert.PDFUtil;
import com.qycloud.oatos.convert.Pdf2SwfThread;
import com.qycloud.oatos.convert.SwfTools;

public class SwfConvertTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			String root = "D:\\test\\pdf\\";
			String pdf = "spring2.0-reference_final_zh_cn.pdf";
			String swf = "swf\\spring2.0-reference_final_zh_cn.swf";
			int pageCount = PDFUtil.getPageCount(root + pdf);
			System.out.println(pageCount);
			
			DocConvertDTO docDTO = new DocConvertDTO(pdf, swf);
			docDTO.setPdfPath(pdf);
			docDTO.setDiskRootPath(root);
			docDTO.setPageCount(pageCount);
			doConvert(docDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void doConvert(DocConvertDTO docDTO) throws Exception {
		int start = docDTO.getStartPage();
		if (start <= 0) {
			start = 1;
		}
		// 开始页数
		start = ((start - 1) / CommConstants.PDF_SPLIT_PAGES) * CommConstants.PDF_SPLIT_PAGES + 1;
		// 结束页数
		int end = start + CommConstants.PDF_SPLIT_PAGES - 1;

		String pdfPath = docDTO.getDiskRootPath() + docDTO.getPdfPath();
		Integer pageCount = docDTO.getPageCount();
		if (pageCount == null || pageCount < 1) {
			// 获取pdf总页数
			pageCount = PDFUtil.getPageCount(pdfPath);
			// 更新总页数至数据库
			docDTO.setPageCount(pageCount);

			if (start > pageCount) {
				return ;
			}
		}
		// swf文件地址
		String swfPath = docDTO.getDiskRootPath() + DiskUtil.getSplitSwfPath(docDTO.getTargetPath(), start);
		// 转换pdf为swf
		SwfTools.pdfToSwf(pdfPath, swfPath, start, end);
		
		if (pageCount > 0 && start + CommConstants.PDF_SPLIT_PAGES <= pageCount) {
			// 开启一个线程，转换所有页数
			docDTO.setStartPage(start);
			Pdf2SwfThread thread = new Pdf2SwfThread(docDTO);
			thread.start();
		}
	}

}
