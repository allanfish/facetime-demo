package com.ams.test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class Change400ToFCR {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		File fileA = new File("aaa.xls");
		File fileB = new File("bbb.xls");
		File fileC = new File("ccc.xls");
		File fileD = new File("ddd.xls");
		Workbook wbA = Workbook.getWorkbook(fileA);
		Workbook wbB = Workbook.getWorkbook(fileB);
		Workbook wbC = Workbook.getWorkbook(fileC);
		Workbook wbD = Workbook.getWorkbook(fileD);

		File outFileA = new File("3update_bank_subject.sql");
		File outFileB = new File("4update_msg_relation.sql");

		File outFileF = new File("6update_org_info.sql");
		File outFileG = new File("update_account.sql");

		if (!outFileA.exists())
			outFileA.createNewFile();

		if (!outFileB.exists())
			outFileB.createNewFile();

		if (!outFileF.exists())
			outFileF.createNewFile();

		if (!outFileG.exists())
			outFileG.createNewFile();

		FileOutputStream fosA = new FileOutputStream(outFileA);
		FileOutputStream fosB = new FileOutputStream(outFileB);
		FileOutputStream fosF = new FileOutputStream(outFileF);

		// 机构号映射
		Map<String, String> orgMap = new HashMap<String, String>();
		// 科目映射
		Map<String, String> subjectMap = new HashMap<String, String>();
		// 账号映射，key:400账号,value：FCR账号
		Map<String, String> accMap = new HashMap<String, String>();

		Sheet sheetA = wbA.getSheet(0);
		int rowsA = sheetA.getRows();
		for (int i = 0; i < rowsA; i++) {
			String oldCode = sheetA.getCell(0, i).getContents();
			String newCode = sheetA.getCell(2, i).getContents();
			if (oldCode.trim().length() > 0)
				orgMap.put(newCode, oldCode);
		}

		Sheet sheetB = wbB.getSheet(0);
		int rowsB = sheetB.getRows();
		StringBuffer buf = new StringBuffer();

		buf.append("update bank_subject set C_SUBJECT='1';\r\n");
		for (int i = 3; i < rowsB; i++) {
			String subject400 = sheetB.getCell(0, i).getContents(); // 400科目
			String subjectFcr = sheetB.getCell(7, i).getContents(); // FCR科目
			String orgFcr = sheetB.getCell(4, i).getContents(); // FCR机构号
			String acc400 = sheetB.getCell(5, i).getContents(); // 400账号

			if (subject400.trim().length() > 0) {
				// 保存科目关系
				subjectMap.put(subject400, subjectFcr);
				accMap.put(acc400, orgFcr + subjectFcr);

				buf.append("update bank_subject set C_SUBJECT='0',C_ACCOUNTNUM='");

				buf.append(orgFcr);
				buf.append(subjectFcr);
				buf.append("' where C_ACCOUNTNUM='");
				buf.append(acc400);
				buf.append("';\r\n");

			}
		}
		buf.append("\r\n");
		subjectMap.put("110", "110");

		System.out.println(subjectMap);
		System.out.println(accMap);

		StringBuffer bufC = new StringBuffer();

		// 调贷公式
		Sheet sheetC = wbC.getSheet(0);
		int rowsC = sheetC.getRows();
		for (int i = 1; i < rowsC; i++) {
			String cId = sheetC.getCell(0, i).getContents();

			String formSubject = sheetC.getCell(5, i).getContents();
			String formAccount = sheetC.getCell(6, i).getContents();
			String toSubject = sheetC.getCell(8, i).getContents();
			String toAccount = sheetC.getCell(9, i).getContents();

			System.out.println(formSubject);
			System.out.println(formAccount);
			System.out.println(toSubject);
			System.out.println(toAccount);

			bufC.append("update MSG_RELATION set ");

			bufC.append("C_FROMACCOUNT='");
			if (formSubject.trim().length() > 0) {
				String[] array = formAccount.trim().split(",");
				String newSubject = "";
				for (int j = 0; j < array.length; j++) {
					if (array[j].length() > 0) {
						newSubject += accMap.get(array[j]);
						newSubject += ",";
					}

				}
				newSubject = newSubject.substring(0, newSubject.length() - 1);
				bufC.append(newSubject);
			}

			bufC.append("',C_TOACCOUNT='");
			if (toSubject.trim().length() > 0) {
				String[] array = toAccount.trim().split(",");
				String newSubject = "";
				for (int j = 0; j < array.length; j++) {
					if (array[j].length() > 0) {
						newSubject += accMap.get(array[j]);
						newSubject += ",";
					}
				}
				newSubject = newSubject.substring(0, newSubject.length() - 1);
				bufC.append(newSubject);
			}
			bufC.append("' where C_ID='");

			bufC.append(cId);
			bufC.append("';\r\n");
		}
		bufC.append("\r\n");
		fosA.write(buf.toString().getBytes());
		fosA.close();

		fosB.write(bufC.toString().getBytes());
		fosB.close();

		StringBuffer bufD = new StringBuffer();
		for (String key : subjectMap.keySet()) {
			bufD.append("update SUB_RELATION set C_BANKSUBID='");
			bufD.append(subjectMap.get(key));
			bufD.append("' where C_BANKSUBID='");
			bufD.append(key);
			bufD.append("';\r\n");
		}
		bufD.append("\r\n");

		Sheet sheetD = wbD.getSheet(0);
		int rowsD = sheetD.getRows();
		StringBuffer bufE = new StringBuffer();
		for (int i = 1; i < rowsD; i++) {
			String org = sheetD.getCell(2, i).getContents(); // ORG ID
			String express = sheetD.getCell(4, i).getContents(); // 科目

			String[] expresses = express.split(",");
			StringBuffer tmp = new StringBuffer();
			for (String subject : expresses) {
				if (subjectMap.get(subject) == null) {
					System.out.println("科目" + subject + "无对应FCR科目\n");
					continue;
				}
				if (tmp.indexOf(subjectMap.get(subject)) < 0) {
					tmp.append(subjectMap.get(subject));
					tmp.append(",");
				}
			}
			tmp.deleteCharAt(tmp.length() - 1);

			bufE.append("update SUBPAN_RELATION set C_PAN='9101101000186',C_EXPRESSION='");
			bufE.append(tmp.toString());
			bufE.append("' where C_ORGID='");
			bufE.append(org);
			bufE.append("';\r\n");
		}
		bufE.append("\r\n");

		StringBuffer bufG = new StringBuffer();
		for (String key : orgMap.keySet()) {
			bufG.append("update ORG_INFO set C_FCR_ORG_ID='");
			bufG.append(orgMap.get(key));
			bufG.append("' where C_ORGID='");
			bufG.append(key);
			bufG.append("';\r\n");
		}
		bufG.append("\r\n");
		fosF.write(bufG.toString().getBytes());
		fosF.close();

		for (String key : accMap.keySet()) {
			System.out.print("update TXN_CLEARACCOUNT set C_PAN='");
			System.out.print(accMap.get(key));
			System.out.print("' where C_PAN='");
			System.out.print(key);
			System.out.println("';");
		}
	}

}
