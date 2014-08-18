package com.conlect.oatos.dto.status;

/**
 * 文档转换优先级<br>
 * 数值越小，优先级越高
 * 
 * @author yang
 * 
 */
public interface DocConvertPriority {

	/**
	 * 编辑html后保存，html转成源格式文件
	 */
	public static final int SAVE_HTML = 1;

	/**
	 * 视频会议文件转换
	 */
	public static final int CONFERENCE_DOC = 2;

	/**
	 * 文件在线编辑转换
	 */
	public static final int EDIT_HTML = 3;

	/**
	 * 文件预览
	 */
	public static final int FILE_VIEW = 4;

	/**
	 * 默认
	 */
	public static final int DEFAULT = 5;
}
