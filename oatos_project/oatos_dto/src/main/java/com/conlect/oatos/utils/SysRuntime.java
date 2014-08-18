package com.conlect.oatos.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SysRuntime {

	public static int execute(String command) throws Exception {
		Process pro = Runtime.getRuntime().exec(command);

		BufferedReader buffer = new BufferedReader(new InputStreamReader(
				pro.getInputStream()));
		while (buffer.readLine() != null) {
			// Logs.getLogger().debug(line);
		}

		BufferedReader errorbuffer = new BufferedReader(new InputStreamReader(
				pro.getErrorStream()));
		String error = null;
		while ((error = errorbuffer.readLine()) != null) {
			SysLogger.getOsLogger().error(error);
		}

		pro.waitFor();
		return pro.exitValue();
	}
}
