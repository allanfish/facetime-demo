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
public class EngineServlet extends HttpServlet {

	private static Logger logger = Logger.getLogger(EngineServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		String name = request.getParameter("name");
		if (name == null) {
			return;
		}
		String target = this.getJSON(name);
		out.println(target);
		out.flush();
		out.close();
	}

	private String getJSON(String name) {
		List<Emp> emps = (new EmpDao()).findEmpsLikeName(name);
		String target = JSONArray.fromObject(emps).toString();
		logger.info("target.length:" + target.length());
		return target;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
