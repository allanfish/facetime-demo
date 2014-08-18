package com.qycloud.oatos.filecache.test.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.InputStreamBody;
import org.junit.Test;

import com.conlect.oatos.dto.client.FileUploadDTO;
import com.conlect.oatos.dto.client.NetworkFileDTO;
import com.conlect.oatos.dto.client.ShareFileUpdateDTO;
import com.conlect.oatos.dto.status.CommConstants;
import com.conlect.oatos.dto.status.CommonUtil;
import com.conlect.oatos.dto.status.RESTurl;
import com.conlect.oatos.file.FileUtil;
import com.conlect.oatos.http.PojoMapper;
import com.conlect.oatos.http.XhrProxy;

/**
 * 文件上传测试
 * @author yang
 *
 */
public class FileUploadServiceTest extends BaseServiceTest {

	/**
	 * 截图上传
	 * @throws IOException
	 */
	@Test
	public void screenshot() throws IOException {
		String userId = "23";
		headers.set(RESTurl.userId, userId);
		File f = new File("D:/oatos.jpg");
		String r = post(RESTurl.screenshot, f);
		System.out.println(r);
	}

	/**
	 * 文件上传
	 * @throws IOException
	 */
	@Test
	public void fileUpload() throws IOException {
		FileUploadDTO bean = new FileUploadDTO();
		bean.setUserId(23L);
		bean.setEntId(21L);
		bean.setFolderId(25L);
		bean.setFileName("test22.pdf");
		bean.setType(CommConstants.FILE_TYPE_ONLINEDISK);
		bean.setFileSize(39906065L);

		bean.setFileNameASCII(CommonUtil.string2ASCII(bean.getFileName()));

		// 检查网盘空间，同名文件，权限
		bean.setMethod(RESTurl.getStartPoint);
		String r = post(RESTurl.fileUpload, bean);
		System.out.println(r);
		if (CommConstants.OK_MARK.equals(r)) {
			// 开始上传
			File f = new File("d:/test/test.pdf");
			bean.setMethod(RESTurl.upload);
			r = upload(f, bean);
			System.out.println(r);
		}
	}

	/**
	 * 文件分段上传
	 * @throws Exception
	 */
	@Test
	public void sectionFileUpload() throws Exception {
		FileUploadDTO bean = new FileUploadDTO();
		bean.setUserId(101287L);
		bean.setEntId(101285L);
		bean.setFolderId(101319L);
		bean.setFileName("网盘主界面.jpg");
		bean.setType(CommConstants.FILE_TYPE_ONLINEDISK);

		bean.setFileNameASCII(CommonUtil.string2ASCII(bean.getFileName()));

		File f = new File("D:/test/网盘主界面.jpg");
		bean.setMd5(FileUtil.getFileMD5(f));

		long fileSize = f.length();
		bean.setFileSize(fileSize);

		int blockSize = 1024 * 100; // 1M
		bean.setBlockSize((long) blockSize);

		int blockCount = (int) ((fileSize - 1) / blockSize + 1);
		bean.setBlockCount(blockCount);

		// 检查网盘空间，权限，同名文件，取上传开始位置
		bean.setMethod(RESTurl.getStartPoint);
		String r = post(RESTurl.sectionFileUpload, bean);
		System.out.println("get start: " + r);
		if (isUploadSuccess(r)) {
			// 所有分块都上传完成,或者快速复制，文件上传成功
			System.out.println("upload success: " + r);
		} else if (r != null && !r.startsWith(CommConstants.ERROR_MARK)) {
			try {
				int start = Integer.parseInt(r);
				// 开始上传
				bean.setMethod(RESTurl.upload);

				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
				long off = blockSize * start;
				bis.skip(off);

				for (int i = start; i < blockCount; i++) {
					int blockIndex = i + 1;
					if (blockIndex == blockCount) {
						// 最后一个分块大小
						blockSize = (int) (fileSize - blockSize * (blockCount - 1));
					}

					bean.setBlockIndex(blockIndex);
					bean.setBlockSize((long) blockSize);

					byte[] b = new byte[blockSize];
					bis.read(b, 0, blockSize);

					r = post(RESTurl.sectionFileUpload, b, bean);
					System.out.println(blockIndex + " upload : " + r);
					if (isBlockUploadSuccess(r, bean)) {
						// 分块上传成功，继续上传下一个分块
						continue;
					} else {
						// 分块上传失败，重新上传分块
						for (;;) {
							r = post(RESTurl.sectionFileUpload, b, bean);
							if (isBlockUploadSuccess(r, bean)) {
								break;
							}
						}
					}
				}
				if (isUploadSuccess(r)) {
					// 所有分块都上传完成，文件上传成功
					System.out.println("upload success: " + r);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}
	}

	private boolean isBlockUploadSuccess(String r, FileUploadDTO bean) {
		boolean s = CommConstants.OK_MARK.equals(r);
		if (!s && bean.getBlockIndex().equals(bean.getBlockCount())) {
			s = CommConstants.UPLOAD_SUCCESS.equals(r);
		}
		return s;
	}

	private boolean isUploadSuccess(String r) {
		return CommConstants.UPLOAD_SUCCESS.equals(r);
	}

	/**
	 * 网盘文件同步上传
	 * @throws Exception
	 */
	@Test
	public void syncFileUpload() throws Exception {
		FileUploadDTO bean = new FileUploadDTO();
		bean.setUserId(23L);
		bean.setEntId(21L);
		bean.setFolderId(29L);
		bean.setFileName("test11asdgas.pdf");
		bean.setType(CommConstants.FILE_TYPE_SHAREDISK);

		bean.setFileNameASCII(CommonUtil.string2ASCII(bean.getFileName()));

		File f = new File("D:/test/test.pdf");
		bean.setMd5(FileUtil.getFileMD5(f));

		long fileSize = f.length();
		bean.setFileSize(fileSize);

		int blockSize = 2 * 1024 * 1024; // 1M
		bean.setBlockSize((long) blockSize);

		int blockCount = (int) ((fileSize - 1) / blockSize + 1);
		bean.setBlockCount(blockCount);

		// 检查网盘空间，权限，同名文件，取上传开始位置
		bean.setMethod(RESTurl.getStartPoint);
		String r = post(RESTurl.syncFileUpload, bean);
		System.out.println("get start: " + r);
		if (isSyncUploadSuccess(r, bean)) {

		} else if (r != null && !r.startsWith(CommConstants.ERROR_MARK)) {
			try {
				int start = Integer.parseInt(r);
				// 开始上传
				bean.setMethod(RESTurl.upload);

				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
				int off = blockSize * start;
				bis.skip(off);

				for (int i = start; i < blockCount; i++) {
					int blockIndex = i + 1;
					if (blockIndex == blockCount) {
						// 最后一个分块大小
						blockSize = (int) (fileSize - blockSize * (blockCount - 1));
					}

					bean.setBlockIndex(blockIndex);
					bean.setBlockSize((long) blockSize);

					byte[] b = new byte[blockSize];
					bis.read(b, 0, blockSize);

					r = post(RESTurl.syncFileUpload, b, bean);
					System.out.println(blockIndex + " upload : " + r);
					if (isSyncBlockUploadSuccess(r, bean)) {
						// 分块上传成功，继续上传下一个分块
						continue;
					} else {
						// 分块上传失败，重新上传分块
						for (;;) {
							r = post(RESTurl.syncFileUpload, b, bean);
							if (isSyncBlockUploadSuccess(r, bean)) {
								break;
							}
						}
					}
				}
				if (isSyncUploadSuccess(r, bean)) {
					// 所有分块都上传完成，文件上传成功
					System.out.println("upload success: " + r);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	private boolean isSyncBlockUploadSuccess(String r, FileUploadDTO bean) {
		boolean s = CommConstants.OK_MARK.equals(r);
		if (!s && bean.getBlockIndex().equals(bean.getBlockCount())) {
			s = isSyncUploadSuccess(r, bean);
		}
		return s;
	}

	private boolean isSyncUploadSuccess(String r, FileUploadDTO bean) {
		boolean s = false;
		try {
			if (CommConstants.FILE_TYPE_SHAREDISK.equals(bean.getType())) {
				ShareFileUpdateDTO f = PojoMapper.fromJsonAsObject(r, ShareFileUpdateDTO.class);
				if (f != null) {
					s = true;
				}
			} else if (CommConstants.FILE_TYPE_ONLINEDISK.equals(bean.getType())) {
				NetworkFileDTO f = PojoMapper.fromJsonAsObject(r, NetworkFileDTO.class);
				if (f != null) {
					s = true;
				}
			}

		} catch (Exception ex) {
		}
		return s;
	}

	private String post(String url, FileUploadDTO bean) throws IOException {
		bean.setMethod(RESTurl.getStartPoint);

		String postUrl = appService + url;

		Map<String, String> headers = new HashMap<String, String>();
		headers.put(RESTurl.UserTokenkey, token);
		headers.put(RESTurl.postJsonData, PojoMapper.toJson(bean));

		HttpEntity postEntity = new StringEntity("", CommConstants.CHARSET_UTF_8);

		String r = XhrProxy.getInstance().post(postUrl, postEntity, headers);
		return r;
	}

	protected String upload(File f, FileUploadDTO bean) throws IOException {
		MultipartEntity multipartEntity = new MultipartEntity();
		ContentBody body = new InputStreamBody(new FileInputStream(f), CommonUtil.string2ASCII(bean.getFileName()));
		multipartEntity.addPart(bean.getFileName(), body);

		String postUrl = appService + RESTurl.fileUpload;

		Map<String, String> headers = new HashMap<String, String>();
		headers.put(RESTurl.UserTokenkey, token);
		headers.put(RESTurl.postJsonData, PojoMapper.toJson(bean));

		String r = XhrProxy.getInstance().post(postUrl, multipartEntity, headers);
		return r;
	}

}
