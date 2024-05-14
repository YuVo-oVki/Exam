package scoremanager.main;

<<<<<<< HEAD
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

public class SubjectUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);// セッションを取得
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		String cd = "";
		String name = "";
		Student stu = new Student();
		Subject subject = null;
		SubjectDao sDao = new SubjectDao();
		Map<String, String> errors = new HashMap<>();
		//Subject subject = (Subject) session.getAttribute("user");// ログインユーザーを取得
		LocalDate todaysDate = LocalDate.now();// LocalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得

		School school=new School();
		school.setCd("oom");
		school.setName("学校名");


        cd = req.getParameter("cd");//科目コード
        name = req.getParameter("name");
		subject = sDao.get(cd, school);// 学生番号から学生インスタンスを取得


		subject = new Subject();
		subject.setCd(cd);
		subject.setSchool(school);
		subject.setName(name);
		sDao.save(subject);


		//JSPへフォワード
		req.setAttribute("cd_set", cd);
		req.setAttribute("name_set", name);

		if(!errors.isEmpty()){
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("cd", cd);
			req.setAttribute("name",name );
			req.getRequestDispatcher("SubjectUpdate.jsp").forward(req, res);
			return;
		}
=======

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		Subject subject = new Subject();
		String cd = "";
		String name = "";
		SubjectDao sDao = new SubjectDao();
		//Subject subject = (Subject) session.getAttribute("user");// ログインユーザーを取得

		School school=new School();
		school.setCd("oom");
		school.setName("学校名");

        cd = req.getParameter("cd");//番号
	    name = req.getParameter("name");//氏名

		subject = sDao.get(cd, school);// 番号から学生インスタンスを取得
		subject.setName(name);

		sDao.save(subject);
>>>>>>> branch 'master' of https://github.com/YuVo-oVki/Exam.git

		req.getRequestDispatcher("SubjectUpdateDone.jsp").forward(req, res);
	}
}


