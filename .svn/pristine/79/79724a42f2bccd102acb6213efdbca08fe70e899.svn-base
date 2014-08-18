package martin.ajax.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import martin.ajax.dao.EmpDao;
import martin.ajax.domain.Emp;
import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class JsonAction extends HttpServlet {

	private static Logger logger = Logger.getLogger(JsonAction.class);

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter out = response.getWriter();
		String jsonObjs = this.getJsonObject();
		out.println(jsonObjs);
	}

	/**
	 * 将集合包装成JSON形式的字符串
	 * 
	 * @return JSON
	 */
	private String getJsonObject() {
		List<Emp> emps = (new EmpDao()).findEmps();
		String empInfo = JSONArray.fromObject(emps).toString();
		logger.info(empInfo.length());
		return empInfo;
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
