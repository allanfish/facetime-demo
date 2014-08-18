package martin.ajax.test;

import java.util.List;

import net.sf.json.JSONArray;

import martin.ajax.dao.EmpDao;
import martin.ajax.domain.Emp;

public class TestJson {

	public static void main(String[] args) {
		List<Emp> emps = (new EmpDao()).findEmps();
		JSONArray jsonArr = JSONArray.fromObject(emps);
		System.out.println(jsonArr.toString());
	}
}
