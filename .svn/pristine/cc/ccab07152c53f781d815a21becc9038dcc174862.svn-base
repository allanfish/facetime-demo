package com.qycloud.oatos.filecache;

import com.conlect.oatos.file.DiskUtil;
import com.conlect.oatos.utils.SysRuntime;


/**
 * 7zip
 * @author yang
 *
 */
public class SenvenZip {

	/**
	 * zip
	 * @param source
	 * @param target
	 * @throws Exception
	 */
	public static void zip(String source, String target) throws Exception {
		StringBuilder cmd = new StringBuilder("7z a ");
		cmd.append(target);
		cmd.append(" ");
		cmd.append(source);
		if (!source.endsWith(DiskUtil.FILE_SEPARATOR)) {
			cmd.append(DiskUtil.FILE_SEPARATOR);
		}
		cmd.append("*");
		SysRuntime.execute(cmd.toString());
	}

	/**
	 * unzip
	 * @param source
	 * @param target
	 * @throws Exception 
	 */
	public static void unzip(String source, String target) throws Exception {
		StringBuilder cmd = new StringBuilder("7z x ");
		cmd.append(source);
		cmd.append(" -o");
		cmd.append(target);
		SysRuntime.execute(cmd.toString());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			long c = System.currentTimeMillis();
			zip("d:\\temp\\tmp\\", "d:\\temp\\7ztest5.zip");
	           long a = System.currentTimeMillis() - c;
	           System.out.println(a);
//			unzip("d:\\temp\\7ztest1.zip", "d:\\temp\\tmp1\\");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
