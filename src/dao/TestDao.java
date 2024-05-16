package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
			statement = connection.prepareStatement(baseSql + " and ent_year=? and class_num=? and cd=?");
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
		List<Test> list = new ArrayList<>();
		Connection connection = getConnection();
		return list;
	}

    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
    	List<Test> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet rSet = null;

		if (/*科目管理の検索ボタンが押されたら*/) {
			String subCondition = baseSql + " inner join student on subject.school_cd = student.school_cd and subject.cd";
			statement.setString(1, school.getCd());
			statement.setString(2, classNum);
		} else if () {
			String stuCondition = baseSql + "inner join student on ";
		}
		try {
			statement = connection.prepareStatement(baseSql + condition + conditionIsAttend + order);
			statement.setString(1, school.getCd());
			statement.setInt(2, entYear);
			statement.setString(3, classNum);
			rSet = statement.executeQuery();
			list = postFilter(rSet, school);
		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}

		return list;
	}

    public boolean save(List<Test> list) throws Exception {
    	Connection connection = getConnection();
		PreparedStatement statement = null;
		int count = 0;
		School school = new School();
		return false;
    }

    public boolean save(Test test, Connection connection) throws Exception {
    	Connection connection2 = getConnection();
		PreparedStatement statement = null;
		int count = 0;
		School school = new School();
		return false;
    }

    public boolean delete(List<Test> list) throws Exception {
    	Connection connection = getConnection();
		PreparedStatement statement = null;
		int count = 0;
		School school = new School();
		return false;
    }

    public boolean delete(Test test, Connection connection) throws Exception {
    	Connection connection3 = getConnection();
		PreparedStatement statement = null;
		int count = 0;
		School school = new School();
		return false;
    }
}