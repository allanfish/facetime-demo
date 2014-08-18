package martin.ajax.test;

import java.util.List;

import martin.ajax.dao.DeptDao;
import martin.ajax.dao.EmpDao;
import martin.ajax.domain.Emp;

import org.apache.log4j.Logger;

/**
 * 
 * @author soft02
 * 
 */
public class TestDao {

	private static Logger logger = Logger.getLogger(TestDao.class);

	/**
	 * 测试业务逻辑层的功能
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		//DeptDao dept = new DeptDao();
		EmpDao empDao = new EmpDao();
		//dept.findDepts();
		List<Emp> emps=empDao.findEmpsLikeName("M");
		//List<Emp> emps = empDao.getEmpsLikeName("M%");
		if (emps == null) {
           logger.info("The ResultSet is Empty!");
           return ;
		}
		for (Emp emp : emps) {
			System.out.println(emp.getName());
		}
	}
}
