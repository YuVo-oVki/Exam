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

public class SubjectDao extends Dao {

	private String baseSql = "select * from subject where school_cd=? ";

	// 科目コードと学校から科目を取得する
	public Subject get(String cd, School school) throws Exception {
		Subject subject = new Subject();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try {
			statement = connection.prepareStatement("select * from subject where cd=? and school_cd=?");
			statement.setString(1, cd);
			ResultSet rSet = statement.executeQuery();
			SchoolDao schoolDao = new SchoolDao();

			if (rSet.next()) {
				subject.setSchool(schoolDao.get(rSet.getString("school_cd")));
				subject.setCd(rSet.getString("cd"));
				subject.setName(rSet.getString("name"));
			} else {
				subject = null;
			}
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

		return subject;
	}

    // 学校に関連付けられたすべての科目をフィルタリングする
	public List<Subject> filter(School school) throws Exception {
		List<Student> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet rSet = null;
		String order = " order by no asc";

		try {
			statement = connection.prepareStatement(baseSql + order);
			statement.setString(1, school.getCd());
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

    // 科目を保存する
	public boolean save(Subject subject) throws Exception {
		Connection connection = getConnection();
		PreparedStatement statement = null;
		int count = 0;

		try {
			Subject old = get(subject.get());
			if (old == null) {
				statement = connection.prepareStatement(
						"insert into subject() values (?, ?, ?, ?, ?, ?)");
				statement.setString(1, subject.getNo());
				statement.setString(2, subject.getName());
				statement.setInt(3, student.getEntYear());
				statement.setString(4, student.getClassNum());
				statement.setBoolean(5, student.isAttend());
				statement.setString(6, student.getSchool().getCd());
			} else {
				statement = connection
						.prepareStatement("update student set name=?, ent_year=?, class_num=?, is_attend=?, where no=?");
				statement.setString(1, student.getName());
				statement.setInt(2, student.getEntYear());
				statement.setString(3, student.getClassNum());
				statement.setBoolean(4, student.isAttend());
				statement.setString(5, student.getNo());
			}

			count = statement.executeUpdate();
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

		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}


    // 科目を削除する
    boolean delete(Subject subject) {

    };
}
