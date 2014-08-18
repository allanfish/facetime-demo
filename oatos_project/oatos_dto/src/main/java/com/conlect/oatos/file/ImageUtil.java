package com.conlect.oatos.file;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.imageio.ImageIO;

import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;

/**
 * image util
 * 
 * @author yang
 * 
 */
public class ImageUtil {

	public static final int IMAGE_SIZE_96 = 96;

	/**
	 * 创建缩略图
	 * 
	 * @param filePath
	 * @param thumbPath
	 * @return
	 * @throws Exception
	 */
	public static String createThumb(String filePath, String thumbPath) {
		File file = new File(DiskUtil.ONLINEDISK_PATH, filePath);
		return createThumb(file, thumbPath);
	}

	/**
	 * 创建缩略图
	 * 
	 * @param file
	 * @param thumbPath
	 * @return
	 */
	public static String createThumb(File file, String thumbPath) {
		String thumb = null;
		try {
			if (CommonUtil.isImage(file.getName())) {
				File thumbFile = new File(DiskUtil.ONLINEDISK_PATH, thumbPath);
				if (!thumbFile.exists()) {
					// 创建缩略图
					resizeImage(file, thumbFile, IMAGE_SIZE_96, IMAGE_SIZE_96);
				}
				thumb = thumbPath;
			}
		} catch (Exception ex) {
			thumb = null;
		}
		return thumb;
	}

	/**
	 * 制作文件缩略图
	 * 
	 * @param fromId
	 * @param guid
	 * @param type
	 * @return
	 */
	public static String createThumb(long fromId, String guid, String type) {
		String thumb = null;
		try {
			String filePath = DiskUtil.getFilePath(fromId, guid, type);
			String thumbPath = DiskUtil.getThumbPath(fromId, guid, type);

			if (CommonUtil.isImage(guid)) {
				thumb = createThumb(filePath, thumbPath);
			}
		} catch (Exception ex) {
			thumb = null;
		}
		return thumb;
	}

	/**
	 * resize image
	 * 
	 * @param src
	 * @param des
	 * @param width
	 * @param height
	 * @throws IOException
	 * @throws Exception
	 */
	public static void resizeImage(File src, File des, int width, int height)
			throws IOException {

		BufferedImage bis = ImageIO.read(src);
		int realWidth = bis.getWidth();
		int realHeight = bis.getHeight();

		// set resize ratio
		float resizeRatio = 1.0F;
		float rw = ((float) width) / realWidth;
		float rh = ((float) height) / realHeight;
		if (rw > rh) {
			resizeRatio = rh;
		} else {
			resizeRatio = rw;
		}

		float w = resizeRatio * realWidth;
		float h = resizeRatio * realHeight;
		int x = (int) ((width - w) / 2);
		int y = (int) ((height - h) / 2);

		BufferedImage resizeImage = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = resizeImage.createGraphics();

		// 透明背景
		resizeImage = g2.getDeviceConfiguration().createCompatibleImage(width,
				height, Transparency.TRANSLUCENT);
		g2.dispose();
		g2 = resizeImage.createGraphics();

		// 画质
		g2.setComposite(AlphaComposite.Src);
		g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g2.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.drawImage(bis, x, y, (int) w, (int) h, null);
		g2.dispose();

		ImageIO.write(resizeImage, CommConstants.FILE_FORMAT_PNG, des);
	}

	/**
	 * create enterprise logo
	 * 
	 * @param filePath
	 * @param entId
	 * @return
	 */
	public static String createEnterpriseLogo(String filePath, long entId)
			throws Exception {
		File srcFile = new File(DiskUtil.ONLINEDISK_PATH, filePath);
		if (!srcFile.exists()) {
			return null;
		}

		String d = DiskUtil.getLogoDir(entId);

		File dir = new File(DiskUtil.ONLINEDISK_PATH, d);
		if (!(dir.mkdir())) {
			dir.mkdirs();
		}

		String newName = UUID.randomUUID().toString().toLowerCase() + "."
				+ CommConstants.FILE_FORMAT_PNG;
		String thumbPath = d + DiskUtil.FILE_SEPARATOR + newName;

		File thumbFile = new File(DiskUtil.ONLINEDISK_PATH, thumbPath);
		if (thumbFile.exists()) {
			return thumbPath;
		}

		resizeImage(srcFile, thumbFile, 200, 55);

		String url = thumbPath.replace(DiskUtil.FILE_SEPARATOR, "/");
		return url;
	}
}
