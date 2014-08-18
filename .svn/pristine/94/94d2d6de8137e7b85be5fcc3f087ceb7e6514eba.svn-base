package com.conlect.oatos.file;

import com.conlect.oatos.dto.client.FileDTO;
import com.conlect.oatos.dto.status.CommonUtil;

public class DeleteFileThread extends Thread {

	private FileDTO fileDTO;

	public DeleteFileThread(FileDTO fileDTO) {
		this.fileDTO = fileDTO;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(60 * 1000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		String target = DiskUtil.getFilePath(fileDTO);
		FileUtil.delete(DiskUtil.ONLINEDISK_PATH + target, 0);

		String thumb = DiskUtil.ONLINEDISK_PATH
				+ DiskUtil.getThumbPath(fileDTO);
		FileUtil.delete(thumb, 0);

		if (CommonUtil.isConvertToPdfSupported(fileDTO.getGuid())) {
			String pdf = DiskUtil.ONLINEDISK_PATH
					+ DiskUtil.getPdfFilePath(fileDTO);
			String swfDir = DiskUtil.ONLINEDISK_PATH
					+ DiskUtil.getSwfDir(fileDTO);

			FileUtil.delete(pdf, 0);
			FileUtil.delete(swfDir, 0);
		}

		if (CommonUtil.isConvertToHtmlSupported(fileDTO.getGuid())) {
			String htmlDir = DiskUtil.ONLINEDISK_PATH
					+ DiskUtil.getHtmlFilePath(fileDTO);
			FileUtil.delete(htmlDir, 0);
		}
	}

}
