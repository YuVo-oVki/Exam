package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.TestListStudent;

public class TestListStudentDao extends Dao{

	private String baseSql = "SELECT * FROM STUDENT";
	School school = new School();

	private List<TestListStudent> postFilter(ResultSet rSet, School school) throws Exception {
		List<TestListStudent> list = new ArrayList<>();
		try {
			while (rSet.next()) {
				
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<TestListStudent> filter(Student student) throws Exception {
		List<TestListStudent> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		String classNum="";
		int entYear = 0;

		String condition = "JOIN TEST ON STUDENT.NO = TEST.STUDENT_NO";
		String conditionIsAttend = "WHERE STUDENT.IS_ATTEND = TRUE;";

		try {
			statement = connection.prepareStatement(baseSql + condition + conditionIsAttend);
			statement.setString(1, school.getCd());
			statement.setInt(2, entYear);
			statement.setString(3, classNum);
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
}
