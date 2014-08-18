package com.conlect.oatos.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Comparator;

import com.conlect.oatos.dto.autobean.IFileDTO;
import com.conlect.oatos.dto.client.FileDTO;
import com.conlect.oatos.dto.client.FileUploadDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;

/**
 * 网盘文件目录管理
 * 
 * @author yang
 * 
 */
public class DiskUtil {

	/**
	 * 路径分隔符
	 */
	public static final String FILE_SEPARATOR = "/";

	/**
	 * 临时目录名
	 */
	private static final String TEMP_DIR = "temp";
	/**
	 * pdf文件目录名
	 */
	private static final String PDF_DIR = "pdf";
	/**
	 * swf文件目录名
	 */
	private static final String SWF_DIR = "swf";
	/**
	 * html文件目录名
	 */
	private static final String HTML_DIR = "html";
	/**
	 * 文件封面目录名
	 */
	private static final String THUMB_DIR = "thumb";
	/**
	 * 用户头像目录名
	 */
	private static final String ICON_DIR = "icon";
	/**
	 * 企业logo目录名
	 */
	private static final String LOGO_DIR = "logo";
	/**
	 * 文件历史版本目录名
	 */
	private static final String HISTORY_DIR = "history";

	/**
	 * 网盘根目录名
	 */
	private static final String ONLINEDISK_DIR = "onlinedisk" + FILE_SEPARATOR;

	/**
	 * 留言目录
	 */
	public static final String VOICERECORD_DIR = ONLINEDISK_DIR + "voicerecord"
			+ FILE_SEPARATOR;
	/**
	 * 网盘临时目录
	 */
	public static final String DISK_TEMP_DIR = ONLINEDISK_DIR + TEMP_DIR
			+ FILE_SEPARATOR;

	/**
	 * 企业网盘目录
	 */
	private static final String SHARE_DISK_DIR = ONLINEDISK_DIR + "share"
			+ FILE_SEPARATOR;
	/**
	 * 个人网盘目录
	 */
	private static final String PERSONAL_DISK_DIR = ONLINEDISK_DIR + "personal"
			+ FILE_SEPARATOR;

	/**
	 * 网盘根路径，配置文件key
	 */
	public static final String NetworkDiskPath = "NetworkDiskPath";

	/**
	 * 网盘根路径
	 */
	public static String ONLINEDISK_PATH = "/var/udata/" + FILE_SEPARATOR;

	/**
	 * 取网盘临时目录
	 * 
	 * @return
	 */
	public static String getDiskTempPath() {
		makeDir(DISK_TEMP_DIR);
		return ONLINEDISK_PATH + DISK_TEMP_DIR;
	}

	/**
	 * 取用户个人网盘根目录
	 * 
	 * @param userId
	 * @return
	 */
	public static String getPersonalDiskDir(long userId) {
		String dir = PERSONAL_DISK_DIR + userId + FILE_SEPARATOR;
		makeDir(dir);
		return dir;
	}

	/**
	 * 取企业网盘根目录
	 * 
	 * @param entId
	 * @return
	 */
	public static String getShareDiskDir(long entId) {
		String dir = SHARE_DISK_DIR + entId + FILE_SEPARATOR;
		makeDir(dir);
		return dir;
	}

	/**
	 * 取会议文档目录
	 * 
	 * @param entId
	 * @param conferenceId
	 * @return
	 */
	public static String getConferenceDocDir(long entId, long conferenceId) {
		String dir = getShareDiskDir(entId) + conferenceId + FILE_SEPARATOR;
		makeDir(dir);
		return dir;
	}

	/**
	 * 取会议文档路径
	 * 
	 * @param entId
	 * @param conferenceId
	 * @param fileName
	 * @return
	 */
	public static String getConferenceDocPath(long entId, long conferenceId,
			String fileName) {
		return getConferenceDocDir(entId, conferenceId) + fileName;
	}

	/**
	 * 取会议文档转换后的pdf路径
	 * 
	 * @param entId
	 * @param fileName
	 * @param type
	 * @return
	 */
	public static String getConferencePdfPath(long entId, long conferenceId,
			String fileName) {
		String prefix = CommonUtil.getFilePrefixName(fileName);
		String path = getConferenceDocDir(entId, conferenceId) + PDF_DIR
				+ FILE_SEPARATOR;
		makeDir(path);
		return path + prefix + "." + CommConstants.FILE_FORMAT_PDF;
	}

	/**
	 * 取会议文档转换后的swf目录
	 * 
	 * @param entId
	 * @param conferenceId
	 * @param fileName
	 * @return
	 */
	public static String getConferenceSwfDir(long entId, long conferenceId,
			String fileName) {
		String prefix = CommonUtil.getFilePrefixName(fileName);
		String dir = getConferenceDocDir(entId, conferenceId) + SWF_DIR
				+ FILE_SEPARATOR + prefix + FILE_SEPARATOR;
		makeDir(dir);
		return dir;
	}

	/**
	 * 取会议文档转换后的swf路径
	 * 
	 * @param entId
	 * @param conferenceId
	 * @param fileName
	 * @return
	 */
	public static String getConferenceSwfPath(long entId, long conferenceId,
			String fileName) {
		String prefix = CommonUtil.getFilePrefixName(fileName);
		return getConferenceSwfDir(entId, conferenceId, fileName) + prefix
				+ "." + CommConstants.FILE_FORMAT_SWF;
	}

	/**
	 * 取用户头像目录
	 * 
	 * @param userId
	 * @return
	 */
	public static String getUserIconDir(long userId) {
		String dir = getPersonalDiskDir(userId) + ICON_DIR + FILE_SEPARATOR;
		makeDir(dir);
		return dir;
	}

	/**
	 * 取企业logo目录
	 * 
	 * @param entId
	 * @return
	 */
	public static String getLogoDir(long entId) {
		String dir = getShareDiskDir(entId) + LOGO_DIR + FILE_SEPARATOR;
		makeDir(dir);
		return dir;
	}

	/**
	 * 删除文件
	 * 
	 * @param fileDTO
	 * @return
	 */
	public static boolean deleteFile(FileDTO fileDTO) {
		DeleteFileThread delete = new DeleteFileThread(fileDTO);
		delete.start();
		return true;
	}

	/**
	 * 创建网盘目录
	 * 
	 * @param dir
	 */
	private static void makeDir(String dir) {
		File f = new File(ONLINEDISK_PATH, dir);
		if (!f.mkdir()) {
			f.mkdirs();
		}
	}

	/**
	 * 取文件路径
	 * 
	 * @param fromId
	 * @param type
	 * @return
	 */
	private static String getFileDir(long fromId, String type) {
		String dir = DiskUtil.DISK_TEMP_DIR;
		if (CommConstants.FILE_TYPE_SHAREDISK.equals(type)) {
			dir = DiskUtil.getShareDiskDir(fromId);
		} else if (CommConstants.FILE_TYPE_ONLINEDISK.equals(type)) {
			dir = DiskUtil.getPersonalDiskDir(fromId);
		} else if (CommConstants.FILE_TYPE_ENTLOGO.equals(type)) {
			dir = DiskUtil.getLogoDir(fromId);
		} else if (CommConstants.FILE_TYPE_ICON.equals(type)) {
			dir = DiskUtil.getUserIconDir(fromId);
		} else if (CommConstants.FILE_TYPE_SHAREFILE_THUMB.equals(type)) {
			dir = DiskUtil.getShareDiskDir(fromId) + DiskUtil.THUMB_DIR
					+ DiskUtil.FILE_SEPARATOR;
		} else if (CommConstants.FILE_TYPE_PRIVATEFILE_THUMB.equals(type)) {
			dir = DiskUtil.getPersonalDiskDir(fromId) + DiskUtil.THUMB_DIR
					+ DiskUtil.FILE_SEPARATOR;
		}
		makeDir(dir);
		return dir;
	}

	/**
	 * 取文件路径
	 * 
	 * @param fromId
	 * @param guid
	 * @param type
	 * @return
	 */
	public static String getFilePath(long fromId, String guid, String type) {
		return getFileDir(fromId, type) + guid;
	}

	/**
	 * 取历史记录目录
	 * 
	 * @param fromId
	 * @param fileId
	 * @param type
	 * @return
	 */
	private static String getHistoryDir(long fromId, long fileId, String type) {
		String dir = DiskUtil.DISK_TEMP_DIR;
		if (CommConstants.FILE_TYPE_SHAREDISK.equals(type)) {
			dir = DiskUtil.getShareDiskDir(fromId);
		} else if (CommConstants.FILE_TYPE_ONLINEDISK.equals(type)) {
			dir = DiskUtil.getPersonalDiskDir(fromId);
		}
		String hdir = dir + DiskUtil.HISTORY_DIR + DiskUtil.FILE_SEPARATOR
				+ fileId + DiskUtil.FILE_SEPARATOR;
		makeDir(hdir);
		return hdir;
	}

	/**
	 * 获取文件历史版本在网盘中的路径
	 * 
	 * @param fromId
	 * @param fileId
	 * @param guid
	 * @param type
	 * @return
	 */
	public static String getHistoryFilePath(long fromId, long fileId,
			String guid, String type) {
		return getHistoryDir(fromId, fileId, type) + guid;
	}

	/**
	 * 获取文件在网盘中的目录并且建立文件夹
	 * 
	 * @param fileDTO
	 * @return
	 */
	private static String getFileDir(IFileDTO fileDTO) {
		String dir = DiskUtil.DISK_TEMP_DIR;
		String fileType = fileDTO.getType();
		// 得到文件在网盘中的目录
		if (CommConstants.FILE_TYPE_SHAREDISK.equals(fileType)) {
			dir = DiskUtil.getShareDiskDir(fileDTO.getEnterpriseId());
		} else if (CommConstants.FILE_TYPE_ONLINEDISK.equals(fileType)) {
			dir = DiskUtil.getPersonalDiskDir(fileDTO.getUserId());
		} else if (CommConstants.FILE_TYPE_CONFERENCE_DOC.equals(fileType)) {
			dir = DiskUtil.getConferenceDocDir(fileDTO.getEnterpriseId(),
					fileDTO.getFolderId());
		} else if (CommConstants.FILE_TYPE_ENTLOGO.equals(fileType)) {
			dir = DiskUtil.getLogoDir(fileDTO.getEnterpriseId());
		} else if (CommConstants.FILE_TYPE_ICON.equals(fileType)) {
			dir = DiskUtil.getUserIconDir(fileDTO.getUserId());
		} else if (CommConstants.FILE_TYPE_SHAREFILE_THUMB.equals(fileType)) {
			dir = DiskUtil.getShareDiskDir(fileDTO.getEnterpriseId())
					+ DiskUtil.THUMB_DIR + DiskUtil.FILE_SEPARATOR;
		} else if (CommConstants.FILE_TYPE_PRIVATEFILE_THUMB.equals(fileDTO
				.getType())) {
			dir = DiskUtil.getPersonalDiskDir(fileDTO.getUserId())
					+ DiskUtil.THUMB_DIR + DiskUtil.FILE_SEPARATOR;
		}
		// 建立文件夹
		makeDir(dir);
		return dir;
	}

	/**
	 * 获取文件在网盘中的路径
	 * 
	 * @param fileDTO
	 * @return
	 */
	public static String getFilePath(IFileDTO fileDTO) {
		return getFileDir(fileDTO) + fileDTO.getGuid();
	}

	/**
	 * 获取文件历史版本在网盘中的目录
	 * 
	 * @param fileDTO
	 * @return
	 */
	public static String getHistoryDir(FileDTO fileDTO) {
		String dir = DiskUtil.DISK_TEMP_DIR;
		if (CommConstants.FILE_TYPE_SHAREDISK.equals(fileDTO.getType())) {
			dir = DiskUtil.getShareDiskDir(fileDTO.getEnterpriseId());
		} else if (CommConstants.FILE_TYPE_ONLINEDISK.equals(fileDTO.getType())) {
			dir = DiskUtil.getPersonalDiskDir(fileDTO.getUserId());
		}
		String hdir = dir + DiskUtil.HISTORY_DIR + DiskUtil.FILE_SEPARATOR
				+ fileDTO.getFileId() + DiskUtil.FILE_SEPARATOR;
		makeDir(hdir);
		return hdir;
	}

	/**
	 * 获取文件历史版本在网盘中的路径
	 * 
	 * @param fileDTO
	 * @return
	 */
	public static String getHistoryFilePath(FileDTO fileDTO) {
		return getHistoryDir(fileDTO) + fileDTO.getGuid();
	}

	/**
	 * 获取转换后的pdf文件地址
	 * 
	 * @param fileDTO
	 * @return
	 */
	public static String getPdfFilePath(FileDTO fileDTO) {
		String prefix = CommonUtil.getFilePrefixName(fileDTO.getGuid());
		String dir = DISK_TEMP_DIR;
		if (CommConstants.FILE_TYPE_SHAREDISK.equals(fileDTO.getType())) {
			dir = getShareDiskDir(fileDTO.getEnterpriseId()) + PDF_DIR
					+ FILE_SEPARATOR;
		} else if (CommConstants.FILE_TYPE_ONLINEDISK.equals(fileDTO.getType())) {
			dir = getPersonalDiskDir(fileDTO.getUserId()) + PDF_DIR
					+ FILE_SEPARATOR;
		}
		makeDir(dir);
		return dir + prefix + "." + CommConstants.FILE_FORMAT_PDF;
	}

	/**
	 * 获取转换后的swf文件存放目录
	 * 
	 * @param fileDTO
	 * @return
	 */
	public static String getSwfDir(FileDTO fileDTO) {
		String prefix = CommonUtil.getFilePrefixName(fileDTO.getGuid());
		String dir = DISK_TEMP_DIR;
		if (CommConstants.FILE_TYPE_SHAREDISK.equals(fileDTO.getType())) {
			dir = getShareDiskDir(fileDTO.getEnterpriseId());
		} else if (CommConstants.FILE_TYPE_ONLINEDISK.equals(fileDTO.getType())) {
			dir = getPersonalDiskDir(fileDTO.getUserId());
		}
		dir = dir + SWF_DIR + FILE_SEPARATOR + prefix + FILE_SEPARATOR;
		makeDir(dir);
		return dir;
	}

	/**
	 * 获取转换后的swf文件路径
	 * 
	 * @param fileDTO
	 * @return
	 */
	public static String getSwfFilePath(FileDTO fileDTO) {
		String prefix = CommonUtil.getFilePrefixName(fileDTO.getGuid());
		return getSwfDir(fileDTO) + prefix + "."
				+ CommConstants.FILE_FORMAT_SWF;
	}

	/**
	 * get split swf path
	 * 
	 * @param userId
	 * @param fileName
	 * @param page
	 * @return
	 */
	public static String getSplitSwfPath(String fileName, int page) {
		int start = ((page - 1) / CommConstants.PDF_SPLIT_PAGES)
				* CommConstants.PDF_SPLIT_PAGES + 1;
		int end = start + CommConstants.PDF_SPLIT_PAGES - 1;
		String prefix = CommonUtil.getFilePrefixName(fileName);
		return prefix + "_" + start + "-" + end + "."
				+ CommConstants.FILE_FORMAT_SWF;
	}

	/**
	 * 取转换后的html存放目录
	 * 
	 * @param fileDTO
	 * @return
	 */
	public static String getHtmlFilePath(FileDTO fileDTO) {
		String prefix = CommonUtil.getFilePrefixName(fileDTO.getGuid());
		String dir = DISK_TEMP_DIR;
		if (CommConstants.FILE_TYPE_SHAREDISK.equals(fileDTO.getType())) {
			dir = getShareDiskDir(fileDTO.getEnterpriseId());
		} else if (CommConstants.FILE_TYPE_ONLINEDISK.equals(fileDTO.getType())) {
			dir = getPersonalDiskDir(fileDTO.getUserId());
		}
		dir = dir + HTML_DIR + FILE_SEPARATOR + prefix + FILE_SEPARATOR;
		makeDir(dir);
		return dir + prefix + "." + CommConstants.FILE_FORMAT_HTML;
	}

	/**
	 * 取转换后的html存放目录
	 * 
	 * @param fromId
	 * @param guid
	 * @param type
	 * @return
	 */
	public static String getHtmlFilePath(long fromId, String guid, String type) {
		String prefix = CommonUtil.getFilePrefixName(guid);
		String dir = DISK_TEMP_DIR;
		if (CommConstants.FILE_TYPE_SHAREDISK.equals(type)) {
			dir = getShareDiskDir(fromId);
		} else if (CommConstants.FILE_TYPE_ONLINEDISK.equals(type)) {
			dir = getPersonalDiskDir(fromId);
		}
		dir = dir + HTML_DIR + FILE_SEPARATOR + prefix + FILE_SEPARATOR;
		makeDir(dir);
		return dir + prefix + "." + CommConstants.FILE_FORMAT_HTML;
	}

	/**
	 * 取文件封面存放地址
	 * 
	 * @param fileDTO
	 * @return
	 */
	public static String getThumbPath(FileDTO fileDTO) {
		String prefix = CommonUtil.getFilePrefixName(fileDTO.getGuid());
		String dir = DISK_TEMP_DIR;
		if (CommConstants.FILE_TYPE_ONLINEDISK.equals(fileDTO.getType())
				|| CommConstants.FILE_TYPE_PRIVATEFILE_THUMB.equals(fileDTO
						.getType())) {
			dir = getPersonalDiskDir(fileDTO.getUserId());
		} else if (CommConstants.FILE_TYPE_SHAREDISK.equals(fileDTO.getType())
				|| CommConstants.FILE_TYPE_SHAREFILE_THUMB.equals(fileDTO
						.getType())) {
			dir = getShareDiskDir(fileDTO.getEnterpriseId());
		}
		dir = dir + THUMB_DIR + FILE_SEPARATOR;
		makeDir(dir);
		return dir + prefix + "." + CommConstants.FILE_FORMAT_PNG;
	}

	/**
	 * 取文件封面存放地址
	 * 
	 * @param fromId
	 * @param guid
	 * @param type
	 * @return
	 */
	public static String getThumbPath(long fromId, String guid, String type) {
		String prefix = CommonUtil.getFilePrefixName(guid);
		String dir = DISK_TEMP_DIR;
		if (CommConstants.FILE_TYPE_ONLINEDISK.equals(type)
				|| CommConstants.FILE_TYPE_PRIVATEFILE_THUMB.equals(type)) {
			dir = getPersonalDiskDir(fromId);
		} else if (CommConstants.FILE_TYPE_SHAREDISK.equals(type)
				|| CommConstants.FILE_TYPE_SHAREFILE_THUMB.equals(type)) {
			dir = getShareDiskDir(fromId);
		}
		dir = dir + THUMB_DIR + FILE_SEPARATOR;
		makeDir(dir);
		return dir + prefix + "." + CommConstants.FILE_FORMAT_PNG;
	}

	/**
	 * 获取已经成功上传的分块数目
	 * 
	 * @param tempdir
	 * @param bean
	 * @return
	 */
	public static int getUploadedBlockCount(File tempdir, FileUploadDTO bean) {
		int receiveCount = 0;
		File[] files = getSectionFiles(tempdir);
		if (files != null) {
			long ts = 0;
			for (int i = 0; i < files.length; i++) {
				File fi = files[i];
				ts = ts + fi.length();
				if (i < bean.getBlockCount() - 1) {
					if (bean.getBlockSize().equals(fi.length())) {
						receiveCount = i + 1;
					} else {
						break;
					}
				} else if (i == bean.getBlockCount() - 1) {
					if (bean.getFileSize().equals(ts)) {
						receiveCount = i + 1;
					} else {
						break;
					}
				}
			}
			for (int j = receiveCount; j < files.length; j++) {
				File fi = files[j];
				FileUtil.delete(fi, 0);
			}
		}
		return receiveCount;
	}

	/**
	 * 合并分块
	 * 
	 * @param tempdir
	 * @param tempFile
	 * @return
	 * @throws Exception
	 */
	public static long mergeFile(File tempdir, File tempFile) throws Exception {
		File[] files = getSectionFiles(tempdir);

		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(tempFile));
		int receiveSize = 0;
		for (File f : files) {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(f));
			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = bis.read(bytes)) != -1) {
				bos.write(bytes, 0, read);
				receiveSize = receiveSize + read;
				bytes = new byte[1024];
			}
			bis.close();
		}
		bos.flush();
		bos.close();
		FileUtil.delete(tempdir, 1000);
		return receiveSize;
	}

	/**
	 * 获取分块文件，并按分块编号排序
	 * 
	 * @param tempdir
	 * @return
	 */
	private static File[] getSectionFiles(File tempdir) {
		File[] files = tempdir.listFiles();
		if (files != null) {
			Arrays.sort(files, new Comparator<File>() {

				@Override
				public int compare(File o1, File o2) {
					return Integer.parseInt(o1.getName())
							- Integer.parseInt(o2.getName());
				}
			});
		}
		return files;
	}

	private static String getTargetDir(FileUploadDTO bean) {
		String dir = DiskUtil.DISK_TEMP_DIR;
		// make target dir
		if (CommConstants.FILE_TYPE_ONLINEDISK.equals(bean.getType())) {
			dir = DiskUtil.getPersonalDiskDir(bean.getUserId());
		} else if (CommConstants.FILE_TYPE_SHAREDISK.equals(bean.getType())) {
			dir = DiskUtil.getShareDiskDir(bean.getEntId());
		} else if (CommConstants.FILE_TYPE_CONFERENCE_DOC
				.equals(bean.getType())) {
			dir = DiskUtil.getConferenceDocDir(bean.getUserId(),
					bean.getFolderId());
		}
		return dir;
	}

	public static File getTargetFile(FileUploadDTO bean) {
		File dir = createTargetDir(bean);
		File f = new File(dir, bean.getGuidName());
		return f;
	}

	public static String getTargetFilePath(FileUploadDTO bean) {
		return getTargetDir(bean) + bean.getGuidName();
	}

	public static File getTempFile(String guid) {
		return new File(DiskUtil.ONLINEDISK_PATH, getTempFilePath(guid));
	}

	public static String getTempFilePath(String guid) {
		String dir = DISK_TEMP_DIR;
		makeDir(dir);
		return dir + guid;
	}

	private static File createTargetDir(FileUploadDTO bean) {
		File dir = new File(DiskUtil.ONLINEDISK_PATH, getTargetDir(bean));
		if (!dir.mkdir()) {
			dir.mkdirs();
		}
		return dir;
	}

	public static File getSectionTempDir(FileUploadDTO bean) {
		File dir = new File(DiskUtil.getDiskTempPath()
				+ String.valueOf(bean.getUserId()), bean.getMd5());
		if (!dir.mkdir()) {
			dir.mkdirs();
		}
		return dir;
	}
}
