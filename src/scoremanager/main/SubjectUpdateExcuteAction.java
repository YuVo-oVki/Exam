package scoremanager.main;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExcuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);// セッションを取得
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		String cd = "";
		Student stu = new Student();
		SubjectDao sDao = new SubjectDao();
		Map<String, String> errors = new HashMap<>();
		//Subject subject = (Subject) session.getAttribute("user");// ログインユーザーを取得
		LocalDate todaysDate = LocalDate.now();// LocalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得

		School school=new School();
		school.setCd("oom");
		school.setName("学校名");

        Subject subject= new Subject();
        subject.setCd("A02");
        subject.setName("国語");

        cd = req.getParameter("cd");//学生番号
		school = req.getParameter("school");//氏名

		subject = sDao.get(cd, school);// 学生番号から学生インスタンスを取得
		List<String> list = cNumDao.filter(subject.getSchool());// ログインユーザーの学校コードをもとにクラス番号の一覧を取得


		if (school == 0) {// 科目名が入力されていない場合
			errors.put("school", "入学年度を選択してください");
		}else{
			if (school == null) {// 科目名が未登録だった場合
				// 学生インスタンスを初期化
				school = new School();
				// インスタンスに値をセット
				subject.setCd(cd);
				subject.setSchool(school);
//				student.setSchool(((Teacher)session.getAttribute("user")).getSchool());
				// 学生を保存
				sDao.save(subject);
			} else {//入力された学番がDBに保存されていた場合
				errors.put("no", "学生番号が重複しています");
			}
		}



		//JSPへフォワード
		req.setAttribute("cd_set", cd);
		req.setAttribute("school_set", school);

		if(!errors.isEmpty()){
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("cd", cd);
			req.setAttribute("school",school );
			req.getRequestDispatcher("SubjectUpdate.jsp").forward(req, res);
			return;
		}

		req.getRequestDispatcher("SubjectUpdateDone.jsp").forward(req, res);
	}
}


