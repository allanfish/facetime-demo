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
public class EmpList extends HttpServlet {
	private static Logger logger = Logger.getLogger(EmpList.class);

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");
		
		String idStr = request.getParameter("id");
		if (idStr == null || idStr == "") {
			logger.error("IllageArgumentsException:", new Exception("你未传参数Id"));
			return;
		}
		Integer deptno = 0;
		deptno = Integer.valueOf(idStr);
		if (deptno < 0) {
			logger.error("TypeCastException:", new Exception(
					"argument type cast exception"));
			throw new ServletException("");
		}
		
		String target = this.getJSON(deptno);
		PrintWriter out = response.getWriter();
		out.println(target);
		out.flush();
		out.close();
	}

	private String getJSON(Integer deptno) {
		List<Emp> emps = (new EmpDao()).findEmpsByDeptno(deptno);
		String target = JSONArray.fromObject(emps).toString();
		return target;
	}

	@Override
	protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		doGet(arg0, arg1);
	}
}
