package martin.ajax.dao;

import java.util.List;

import martin.ajax.domain.Emp;

import org.apache.log4j.Logger;
import org.damo.j2ee.util.HbnUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 * 封装Emp类的业务逻辑，即对Emp对应数据库表的基本操作
 * 
 * @author soft02
 * 
 */
@SuppressWarnings("unchecked")
public class EmpDao {

	private Logger logger = Logger.getLogger(EmpDao.class);

	/**
	 * 地毯式查找
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Emp> findEmps() {// assure
		Session session = HbnUtil.getSession();
		List<Emp> emps = session.createQuery("from Emp").list();
		logger.debug("emps size:" + emps.size());
		session.close();
		return emps;
	}

	/**
	 * 根据外键查找
	 * @param deptno
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Emp> findEmpsByDeptno(Integer deptno) {// ensure
		Session session = HbnUtil.getSession();
		List<Emp> emps = session
				.createQuery("from Emp emp  where emp.deptno=?").setInteger(0,
						deptno).list();
		logger.debug("emps.size" + emps.size());
		return emps;
	}

	/**
	 * 使用标量查询
	 * 
	 * @param name
	 * @return
	 */

	public List<Emp> findEmpsLikeName(String name) {// Assure
		if (name == null) {
			return null;
		}
		Session session = HbnUtil.getSession();
		Criteria crit = session.createCriteria(Emp.class);
		crit.add(Restrictions.like("name", name + "%"));
		return crit.list();
	}

	/**
	 * 是对上面标量查询的补充，使用的是HQL模糊查找
	 * 
	 * @param name
	 * @return
	 */
	public List<Emp> getEmpsLikeName(String name) {// Ensure
		if (name == null) {
			return null;
		}
		Session session = HbnUtil.getSession();
		String hql = "from Emp emp where name like :ename";
		Query query = session.createQuery(hql).setParameter("ename", name);
		return query.list();
	}
}
