package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;

public class ClassNumDao extends Dao{

	private String baseSql ="select class_num from class_num where school_cd=?";

	public List<String>filter(School school) throws Exception{

		//リストを初期化
		List<String> list = new ArrayList<>();
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet rSet = null;
		//SQL文のソート
		String order = " order by class_num asc";


		try {
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + order);
			//プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());
			//プリペアードステートメントにを実行
			rSet = statement.executeQuery();

			//リザルトセットを全件走査
			while(rSet.next()){
				list.add(rSet.getString("class_num"));
			}

		}catch (Exception e){
			throw e;
		}finally {
			//プリペアードステートメントを閉じる
			if (statement != null){
				try {
					statement.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
			//コネクションを閉じる
			if(connection != null){
				try {
					connection.close();
				}catch(SQLException sqle){
					throw sqle;
				}
			}
		}
		return list;
	}
}
