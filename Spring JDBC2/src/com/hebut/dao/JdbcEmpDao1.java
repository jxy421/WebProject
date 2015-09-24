package com.hebut.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hebut.entity.Emp;
import com.hebut.entity.EmpMapper;
//����һ��Bean
@Repository("jdbcEmpDao1")
public class JdbcEmpDao1 implements EmpDao{
	//ע��
	@Resource
	private JdbcTemplate template;
	
	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public void save(Emp emp) {
		String sql = "INSERT INTO emp_ VALUES(?,?,?,?,?,?,?,?)";
		Object[] params = {
				emp.getEmpno(),
				emp.getEname(),
				emp.getJob(),
				emp.getMgr(),
				emp.getHiredate(),
				emp.getSal(),
				emp.getComm(),
				emp.getDeptno(),
		};
		template.update(sql,params);
	}

	public Emp findById(int empno) {
		String sql = "SELECT * FROM emp_ WHERE empno=?";
		
		Object[] param = {empno};
		EmpMapper mapper = new EmpMapper();
		//����һ�н��ʹ��QueryForObject
		Emp emp = (Emp)template.queryForObject(sql, param,mapper);
		return emp;
	}

	public List<Emp> findAll() {
		//���ض��м�¼
		String sql = "SELECT * FROM emp_";
		EmpMapper mapper = new EmpMapper();
		List<Emp> listEmp = template.query(sql,mapper);
		return listEmp;
	}
}
