package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession();//セッション
		SubjectDao subDao = new SubjectDao();//科目Dao
		String subCd ="";		//科目コード
		String subName = "";	//科目名
		Subject subjects = null;//科目
		Map<String, String> errors = new HashMap<>();// エラーメッセージ

		School school=new School();
		school.setCd("oom");
		school.setName("学校名");

		Subject subject = new Subject();
		subject.setSchool(school);
		subject.setCd("oom");
		subject.setName("国語");

		//リクエストパラメータ―の取得 2
		subCd = req.getParameter("subCd");//科目コード
		subName = req.getParameter("subName");//科目名


		//DBからデータ取得 3
		subjects = subDao.get(subCd, school);//科目インスタンス



		//ビジネスロジック 4
		//DBへデータ保存 5
		//条件で手順4~5の内容が分岐

		if (subCd == null) {// 科目コードが未登録だった場合
			// 学生インスタンスを初期化
			 subjects= new Subject();
			// インスタンスに値をセット

			subjects.setSchool(school);
			subjects.setCd(subCd);
			subjects.setName(subName);

			// 学生を保存
			subDao.save(subjects);
		} else {//入力された科目コードがDBに保存されていた場合
			errors.put("subCd", "科目コードが重複しています");
		}


		//エラーがあったかどうかで手順6~7の内容が分岐
		//レスポンス値をセット 6
		//JSPへフォワード 7


		if(!errors.isEmpty()){
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("sub_cd", subCd);
			req.setAttribute("sub_name", subName);
			req.getRequestDispatcher("subject_create.jsp").forward(req, res);
			return;
		}
		req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
	}
}
