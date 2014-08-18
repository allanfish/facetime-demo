package martin.ajax.dao;

import java.util.List;

import martin.ajax.domain.Dept;

import org.apache.log4j.Logger;
import org.damo.j2ee.util.HbnUtil;
import org.hibernate.Session;

@SuppressWarnings("unchecked")
public class DeptDao {

	private static Logger logger = Logger.getLogger(DeptDao.class);

	/**
	 * 
	 * @return
	 */

	public  List<Dept> findDepts() {
		Session session = HbnUtil.getSession();
		List<Dept> depts = session.createQuery("from Dept").list();
		logger.debug("depts.size():" + depts.size());
		session.close();
		return depts;
	}
}
