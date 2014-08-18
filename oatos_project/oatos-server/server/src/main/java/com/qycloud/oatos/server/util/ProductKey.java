package com.qycloud.oatos.server.util;

import java.io.FileWriter;
import java.io.IOException;

import com.qycloud.oatos.server.security.Security;

public class ProductKey {

	// private static String sql =
	// "INSERT INTO product_key (prod_key, sales, is_used, is_free, create_time) VALUES ('#{1}', '#{2}', 0, #{3}, now());";
	private static String sql = "insert into product_key(prod_key,sales,is_used,is_free,create_time) values('#{1}','#{2}',0,#{3},now());\n";

	/**
	 * 
	 * @param salesName
	 * @param number
	 * @param free
	 *            1:free; 0:not
	 * @throws IOException
	 */
	public static void generator(String salesName, int number, int free) throws IOException {

		FileWriter fw = new FileWriter("D:/temp/insert_prod_key.sql");

		try {
			String s = "use oatos_ent;\n";
			fw.write(s, 0, s.length());
			System.out.print(s);

			// 生成产品码
			for (int i = 0; i < number; i++) {

				StringBuffer key = new StringBuffer(30);

				key.append(Security.randomCharString(5));
				key.append("-");

				key.append(Security.randomCharString(5));
				key.append("-");

				key.append(Security.randomCharString(5));
				key.append("-");

				key.append(Security.randomCharString(5));
				key.append("-");

				key.append(Security.randomCharString(5));

				String instSQL = sql.replace("#{1}", key.toString().toUpperCase());
				instSQL = instSQL.replace("#{2}", salesName);
				instSQL = instSQL.replace("#{3}", String.valueOf(free));
				fw.write(instSQL, 0, instSQL.length());
				System.out.print(instSQL);
			}

			s = "commit;\n";
			System.out.print(s);
			fw.write(s, 0, s.length());

			fw.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			fw.close();
		}
	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		generator("conlect", 100, 0);
	}

}
