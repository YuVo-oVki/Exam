package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;

public class TestListStudentDao extends Dao{

	private String baseSql = "SELECT * FROM STUDENT";
	School school = new School();

	public List<Student> filter(Student student) throws Exception {
		List<Student> list = new ArrayList<>();
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

// 見たいの自分へ
//	ごめん僕には無理だった
}
