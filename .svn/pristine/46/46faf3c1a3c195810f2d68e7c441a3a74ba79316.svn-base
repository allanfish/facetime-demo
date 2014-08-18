package com.conlect.oatos.dto.client;

import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * Base64 编码 图片信息<br>
 * <br>
 * Base64 coding image's information
 * 
 * @author jinkerjiang
 * 
 */

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class Base64ImageDTO implements BaseDTO {
	private static final long serialVersionUID = 3644275587128928205L;

	/**
	 * 图片类型定义<br>
	 * <br>
	 * Image Type
	 * 
	 * @author jinkerjiang
	 * 
	 */
	public static enum ImageType {
		JPG, PNG, GIF, BPM
	}

	/**
	 * 图片base64编码串<br>
	 * <br>
	 * Image's base64 string
	 */
	private String base64;
	/**
	 * 图片宽度<br>
	 * <br>
	 * Image's width
	 */
	private int width;
	/**
	 * 图片长度<br>
	 * <br>
	 * Image's height
	 */
	private int height;
	private ImageType imageType;

	public Base64ImageDTO(String base64, int width, int height,
			ImageType imageType) {
		this.base64 = base64;
		this.width = width;
		this.height = height;
		this.imageType = imageType;
	}

	public Base64ImageDTO() {
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public ImageType getImageType() {
		return imageType;
	}

	public void setImageType(ImageType imageType) {
		this.imageType = imageType;
	}

}
