package scoremanager.main;

import java.time.LocalDate;
import java.util.HashMap;
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

public class SubjectDeleteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);// セッションを取得
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		Student stu = new Student();
		SubjectDao sDao = new SubjectDao();
		Subject subject = null;
		Map<String, String> errors = new HashMap<>();
		//Subject subject = (Subject) session.getAttribute("user");// ログインユーザーを取得
		LocalDate todaysDate = LocalDate.now();// LocalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得

		School school=new School();
		school.setCd("oom");
		school.setName("学校名");


		String cd = req.getParameter("cd");
		subject = sDao.get(cd, school);
		String name = subject.getName();
		school= stu.getSchool();


			//JSPへフォワード
			req.setAttribute("cd_set", cd);
			req.setAttribute("name_set", name);
			req.getRequestDispatcher("SubjectDelete.jsp").forward(req, res);
	}
}


