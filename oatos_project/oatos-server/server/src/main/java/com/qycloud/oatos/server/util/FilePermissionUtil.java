package com.qycloud.oatos.server.util;

import java.util.List;

import com.conlect.oatos.dto.status.FilePermission;
import com.qycloud.oatos.server.domain.entity.RolePermission;

public class FilePermissionUtil {

	/**
	 * 权限判断
	 * @param folderId
	 * @param operation
	 * @param permissions
	 * @return
	 */
	public static boolean checkPermission(long folderId, FilePermission operation, List<RolePermission> permissions) {
		boolean check = false;
		for (RolePermission rp : permissions) {
			if (folderId == rp.getFolderId()) {
		        switch (operation)
	            {
	            case Read:
		            check = rp.isRead() || rp.isWrite();
		            break;
	            case Write:
		            check = rp.isWrite();
		            break;
	            case Download:
		            check = rp.isDownload();
		            break;
	            case Upload:
		            check = rp.isUpload();
		            break;
	            case Delete:
		            check = rp.isDelete();
		            break;
	            case Share:
		            check = rp.isShare();
		            break;
	            case Local:
		            check = rp.isLocal();
		            break;
	            case List:
		            check = rp.isList() || rp.isRead() || rp.isWrite() || rp.isDownload() || rp.isUpload() || rp.isDelete() || rp.isShare() || rp.isLocal();
		            break;

	            default:
		            break;
	            }
		        if (check) {
		            break;
	            } 
            }
        }
	    return check;
	}
}
