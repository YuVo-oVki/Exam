package scoremanager.main;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);// セッションを取得
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		Student stu = new Student();
		SubjectDao sDao = new SubjectDao();
		//Subject subject = (Subject) session.getAttribute("user");// ログインユーザーを取得
		LocalDate todaysDate = LocalDate.now();// LocalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得

		School school=new School();
		school.setCd("oom");
		school.setName("学校名");

        Subject subject= new Subject();
        subject.setCd("A02");
        subject.setName("国語");

        String cd = req.getParameter("cd");
		sDao.get(cd, school);
		school = stu.getSchool();

		//JSPへフォワード
		req.setAttribute("cd_set", cd);
		req.setAttribute("school_set", school);
		req.getRequestDispatcher("SubjectUpdate.jsp").forward(req, res);
	}
}
