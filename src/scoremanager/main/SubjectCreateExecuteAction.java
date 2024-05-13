package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		//HttpSession session = req.getSession();//セッション
		SubjectDao sDao = new SubjectDao();//科目Dao
		String cd ="";		//科目コード
		String name = "";	//科目名
		Subject subject = null;//科目
		Map<String, String> errors = new HashMap<>();// エラーメッセージ

		School school=new School();
		school.setCd("oom");
		school.setName("学校名");

		Teacher teacher = new Teacher();
		teacher.setId("admin");
		teacher.setPassword("password");
		teacher.setName("大原花子");
		teacher.setSchool(school);

		//リクエストパラメータ―の取得 2
		cd = req.getParameter("cd");//科目コード
		name = req.getParameter("name");//科目名


		//DBからデータ取得 3
		subject = sDao.get(cd,school);//科目インスタンス



		//ビジネスロジック 4
		//DBへデータ保存 5
		if (subject == null) {// 科目が未登録だった場合
			// 科目インスタンスを初期化
			subject = new Subject();
			// インスタンスに値をセット
			subject.setCd(cd);
			subject.setName(name);
			// 科目を保存
			sDao.save(subject);
		} else {//入力された学番がDBに保存されていた場合
			errors.put("cd", "学生番号が重複しています");
		}

		//JSPへフォワード 7


		if(!errors.isEmpty()){
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("cd", cd);
			req.setAttribute("name", name);
			req.getRequestDispatcher("subject_create.jsp").forward(req, res);
			return;
		}
		req.getRequestDispatcher("subject_create_done.jsp").forward(req, res);
	}
}
