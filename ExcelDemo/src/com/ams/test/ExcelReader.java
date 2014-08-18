package com.ams.test;

import java.io.File;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

/**
 * 2011/09/29王四维转过来的邮件: <br>
 * 为09/21日深圳老板通的可疑交易(见EXCEL表)生产电子POS签购单.
 * 
 * @author yufei
 * 
 */
public class ExcelReader {

	private static String SQL = "select a.MARKET_NAME, a.SHOP_NAME, a.SHOP_NO from POS_SHOPINFO a where a.TERMIANL_NO = ? ";

	/**
	 * POS签购单－深圳 <br>
	 * ＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝ <br>
	 * 商户名称： 电子设备商店姚大奎 <br>
	 * 商户编号： 410440357329825 <br>
	 * 终端编号： 95500697 <br>
	 * 卡 号：6221881100026139905 <br>
	 * 发卡行名称：邮政储蓄异地卡 <br>
	 * 收单行名称：深圳平安银行 <br>
	 * 交易类型：收款 <br>
	 * 凭 证 号： 004926 <br>
	 * 参 考号： 132232004926<br>
	 * 交易时间： 2009-2-22 13:22:32 <br>
	 * 交易金额： 1500.00 <br>
	 * 备注： <br>
	 * 签名：<br>
	 * 
	 * 本人确认以上交易 <br>
	 * 同意将其计入本卡帐户<br>
	 */
	public static void main(String[] args) throws Exception {
		File fileA = new File(
				"D:\\workspace\\projects\\excel_demo\\src\\9.21日老板通可疑交易.xls");
		Workbook wbA = Workbook.getWorkbook(fileA);

		Sheet sheet = wbA.getSheet(0);
		int rowsA = sheet.getRows();
		List<BossBean> bossList = new ArrayList<BossBean>();
		for (int i = 1; i < rowsA; i++) {
			String id = sheet.getCell(0, i).getContents();
			String pan = sheet.getCell(1, i).getContents().substring(1);
			String aBId = sheet.getCell(2, i).getContents().substring(1);
			String termId = sheet.getCell(3, i).getContents().substring(1);
			String srvStan = sheet.getCell(4, i).getContents().substring(1);
			String tranType = sheet.getCell(5, i).getContents();
			String tranAmt = sheet.getCell(6, i).getContents();
			String tranDate = sheet.getCell(7, i).getContents();
			tranDate = convertDate(tranDate);
			String tranFee = sheet.getCell(10, i).getContents();
			String consumeAmt = sheet.getCell(11, i).getContents();
			consumeAmt = convertNumber(consumeAmt);
			String vouncherType = sheet.getCell(12, i).getContents();
			BossBean boss = new BossBean(id, pan, aBId, termId, srvStan,
					tranType, tranAmt, tranDate, tranFee, consumeAmt,
					vouncherType);
			Connection conn = JdbcUtil.getConnection();
			setShopInfo(boss, conn);
			JdbcUtil.close(conn);
			System.out.println(boss);
			bossList.add(boss);
		}

		StringBuilder sb = new StringBuilder();
		for (BossBean boss : bossList) {
			printBossTrade(sb, boss);
		}
		System.out.println(sb.toString());
	}

	private static void printBossTrade(StringBuilder sb, BossBean boss) {
		sb.append("POS签购单－深圳 \r\n");
		sb.append("＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝＝ \r\n");
		sb.append("商户名称: ").append(boss.getShopName()).append("\r\n");
		sb.append("商户编号: ").append(boss.getShopNo()).append("\r\n");
		sb.append("终端编号: ").append(boss.getTermId()).append("\r\n");
		sb.append("卡号: ").append(boss.getPan()).append("\r\n");
		sb.append("发卡行名称: \r\n");
		sb.append("收单行名称: 深圳平安银行\r\n");
		sb.append("交易类型: 收款\r\n");
		sb.append("凭证号: ").append(boss.getSrvStan()).append("\r\n");
		sb.append("参考号: \r\n");
		sb.append("交易时间: ").append(boss.getTranDate()).append("\r\n");
		sb.append("交易金额: ").append(boss.getConsumeAmt()).append("\r\n");
		sb.append("备注: \r\n");
		sb.append("签名: \r\n\n");
		sb.append("    本人确认以上交易    \r\n");
		sb.append("   同意将其计入本卡帐户\r\n");
		sb.append("\n\n\n\n");
	}

	private static void setShopInfo(BossBean boss, Connection conn) {
		if (boss.getTermId() == null || boss.getTermId().isEmpty()) {
			throw new AssertionError("ternimal no is empty.");
		}
		System.out.println("TERM ID:" + boss.getTermId());
		List<Object[]> result = JdbcUtil.execute(conn, SQL,
				new String[] { boss.getTermId() });
		if (result.size() == 0) {
			throw new AssertionError("result size is 0");
		}
		if (result.size() > 1) {
			throw new AssertionError("result size great than 1.");
		}
		Object[] shopInfo = result.get(0);
		boss.setShopNo(shopInfo[2].toString());
		boss.setShopName(shopInfo[0].toString() + shopInfo[1].toString());
	}

	private static String convertDate(String tranDate) {
		if (tranDate == null || tranDate.isEmpty())
			return "";
		SimpleDateFormat format = new SimpleDateFormat("M/dd/yy hh:mm");
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss");
		try {
			Date date = format.parse(tranDate);
			return dateFormat.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String convertNumber(String consumeAmt) {
		if (consumeAmt == null)
			return "";
		NumberFormat format = new DecimalFormat("#0.00");
		return format.format(Double.parseDouble(consumeAmt));
	}
}
