package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

	private String baseSql = "select * from test where school_cd=? ";

	public Test get(Student student, Subject subject, School school, int no) throws Exception {
		Student stu = new Student();
		Subject sub = new Subject();
		Test tes = new Test();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement(baseSql + ", ent_year=?, class_num=?, cd=?");
			statement.setString(1, school.getCd());
	        statement.setInt(2, student.getEntYear());
	        statement.setString(3, student.getClassNum());
	        statement.setInt(4, no);
			ResultSet rSet = statement.executeQuery();
			SchoolDao schoolDao = new SchoolDao();
		}catch (SQLException e) {
	        throw new Exception(e.getMessage());
	    } finally {
	    }
	    return tes;
	}

	private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
		List<Test> list = new
	}

    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
    }

    public boolean save(List<Test> list) throws Exception {
    }

    public boolean save(Test test, Connection connection) throws Exception {
    }

    public boolean delete(List<Test> list) throws Exception {
    }

    public boolean delete(Test test, Connection connection) throws Exception {
    }
}

//これを何か色々すると完成する
//あ、駄目だわっかんね