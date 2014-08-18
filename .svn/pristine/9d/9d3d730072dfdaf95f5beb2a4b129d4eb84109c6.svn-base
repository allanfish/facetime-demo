package com.qycloud.oatos.convert;

import com.artofsolving.jodconverter.DefaultDocumentFormatRegistry;
import com.artofsolving.jodconverter.DocumentFamily;
import com.artofsolving.jodconverter.DocumentFormat;

/**
 * 文件格式注册
 * @author yang
 *
 */
public class MyDocumentFormatRegistry extends DefaultDocumentFormatRegistry {

	public MyDocumentFormatRegistry() {

		// docx
		final DocumentFormat docx = new DocumentFormat("Microsoft Word 2007 XML", DocumentFamily.TEXT, "application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx");
		docx.setExportFilter(DocumentFamily.TEXT, "MS Word 2007");
		addDocumentFormat(docx);
		
		// xlsx
		final DocumentFormat xlsx = new DocumentFormat("Microsoft Excel 2007 XML", DocumentFamily.SPREADSHEET, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "xlsx");
		xlsx.setExportFilter(DocumentFamily.SPREADSHEET, "MS Excel 2007");
		xlsx.setExportFilter(DocumentFamily.PRESENTATION, "impress_html_Export");
		xlsx.setExportFilter(DocumentFamily.TEXT, "HTML (StarWriter)");
		addDocumentFormat(xlsx);
		
		// pptx
		final DocumentFormat pptx = new DocumentFormat("Microsoft PowerPoint 2007 XML", DocumentFamily.PRESENTATION, "application/vnd.openxmlformats-officedocument.presentationml.presentation", "pptx");
		pptx.setExportFilter(DocumentFamily.PRESENTATION, "MS PowerPoint 2007");
		addDocumentFormat(pptx);
		
		// xls
		DocumentFormat xls = getFormatByFileExtension("xls");
		xls.setExportFilter(DocumentFamily.PRESENTATION, "impress_html_Export");
		xls.setExportFilter(DocumentFamily.TEXT, "HTML (StarWriter)");

		// log
		final DocumentFormat log = new DocumentFormat("Plain Text", DocumentFamily.TEXT, "text/plain", "log");
		log.setImportOption("FilterName", "Text");
		log.setExportFilter(DocumentFamily.TEXT, "Text");
		addDocumentFormat(log);

		// sql
		final DocumentFormat sql = new DocumentFormat("Plain Text", DocumentFamily.TEXT, "text/plain", "sql");
		sql.setImportOption("FilterName", "Text");
		sql.setExportFilter(DocumentFamily.TEXT, "Text");
		addDocumentFormat(sql);

		// xml
		final DocumentFormat xml = new DocumentFormat("Plain Text", DocumentFamily.TEXT, "text/plain", "xml");
		xml.setImportOption("FilterName", "Text");
		xml.setExportFilter(DocumentFamily.TEXT, "Text");
		addDocumentFormat(xml);

		// wps
		final DocumentFormat wps = new DocumentFormat("WPS Word", DocumentFamily.TEXT, "application/vnd.ms-works", "wps");
		wps.setExportFilter(DocumentFamily.TEXT, "MS Word 97");
		addDocumentFormat(wps);
	}

}
