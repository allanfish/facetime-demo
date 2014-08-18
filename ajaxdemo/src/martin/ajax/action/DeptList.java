package martin.ajax.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import martin.ajax.dao.DeptDao;
import martin.ajax.domain.Dept;
import net.sf.json.JSONArray;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class DeptList extends HttpServlet {

	private static Logger logger = Logger.getLogger(DeptList.class);

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String target = this.getJSON();
		out.println(target);
		logger.info("Success!");
		out.flush();
		out.close();
	}

	private String getJSON() {
		List<Dept> depts = (new DeptDao()).findDepts();
		JSONArray json = JSONArray.fromObject(depts);
		logger.info("json object info:" + json);
		return json.toString();
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
