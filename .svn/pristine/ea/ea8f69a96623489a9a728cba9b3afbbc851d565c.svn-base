package com.qycloud.oatos.convert;

import java.io.IOException;

import com.itextpdf.text.pdf.PdfReader;

/**
 * pdf工具类
 * 
 * @author yang
 * 
 */
public class PDFUtil {

	/**
	 * 取PDF文件页数
	 * 
	 * @param pdf
	 * @return
	 * @throws IOException
	 */
	public static int getPageCount(String pdf) throws IOException {
		PdfReader pdfReader = new PdfReader(pdf);
		return pdfReader.getNumberOfPages();
	}

}
