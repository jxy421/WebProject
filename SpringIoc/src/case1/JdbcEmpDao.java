package case1;

public class JdbcEmpDao implements EmpDao{

	public void save() {
		System.out.println("��������");
	}

	public void findById() {
		System.out.println("��Id���Ҽ�¼");
	}

	public void findAll() {
		System.out.println("����ȫ����¼");
	}
	
}
