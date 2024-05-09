package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

    private String baseSql = "SELECT * FROM SUBJECT WHERE school_cd=? ";

    private List<TestListSubject> postFilter(ResultSet rSet) throws SQLException {
        List<TestListSubject> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                TestListSubject testListSubject = new TestListSubject();
                Subject subject = new Subject();
                subject.setCd(rSet.getString("cd"));
                subject.setName(rSet.getString("name"));

                list.add(testListSubject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
        }
        return list;
    }

    public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
        List<TestListSubject> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rSet = null;
        String condition = " AND ent_year=? AND class_num=? AND subject_cd=? ";
        String order = " ORDER BY CD ASC";
        try {
            connection = getConnection();
            statement = connection.prepareStatement(baseSql + condition + order);
            statement.setInt(1, entYear);
            statement.setString(2, classNum);
            statement.setString(3, subject.getCd());
            rSet = statement.executeQuery();
            list = postFilter(rSet);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (rSet != null) {
                try {
                    rSet.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    throw sqle;
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    throw sqle;
                }
            }
        }
        return list;
    }
}
