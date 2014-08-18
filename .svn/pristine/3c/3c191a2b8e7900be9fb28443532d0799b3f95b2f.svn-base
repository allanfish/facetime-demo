package com.conlect.oatos.dto.client;

import com.conlect.oatos.dto.autobean.IImageDTO;
import com.conlect.oatos.dto.status.CommConstants;

/**
 * 图片dto
 * 
 * @author yang
 * 
 */
public class ImageDTO implements IImageDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 用户id
	 */
	public long userId;

	/**
	 * 地址
	 */
	public String url;

	/**
	 * 文件名
	 */
	public String name;

	/**
	 * 剪切起始坐标X
	 */
	public int cropStartX;

	/**
	 * 剪切起始坐标Y
	 */
	public int cropStartY;

	/**
	 * 宽度px
	 */
	public int width;

	/**
	 * 高度px
	 */
	public int height;

	/**
	 * 缩放比例
	 */
	public float resizeRatio;

	public String type = CommConstants.FILE_TYPE_TEMP;

	/**
	 * @deprecated
	 */
	@Deprecated
	private String cloudDiskIp;

	public ImageDTO() {
	}

	public ImageDTO(String srcImageName, int cropStartX, int cropStartY,
			int rectWidth, int rectHeight, float resizeRatio) {
		this.name = srcImageName;
		this.cropStartX = cropStartX;
		this.cropStartY = cropStartY;
		this.width = rectWidth;
		this.height = rectHeight;
		this.resizeRatio = resizeRatio;
	}

	@Override
	public long getUserId() {
		return userId;
	}

	@Override
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getCropStartX() {
		return cropStartX;
	}

	@Override
	public void setCropStartX(int cropStartX) {
		this.cropStartX = cropStartX;
	}

	@Override
	public int getCropStartY() {
		return cropStartY;
	}

	@Override
	public void setCropStartY(int cropStartY) {
		this.cropStartY = cropStartY;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public float getResizeRatio() {
		return resizeRatio;
	}

	@Override
	public void setResizeRatio(float resizeRatio) {
		this.resizeRatio = resizeRatio;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public String getCloudDiskIp() {
		return cloudDiskIp;
	}

	/**
	 * @deprecated
	 */
	@Deprecated
	@Override
	public void setCloudDiskIp(String cloudDiskIp) {
		this.cloudDiskIp = cloudDiskIp;
	}
}
