package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
<<<<<<< HEAD
		HttpSession session = req.getSession(true);// セッションを取得
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		Student stu = new Student();
		Subject subject = null;
=======
		//HttpSession session = req.getSession(true);// セッションを取得
>>>>>>> branch 'master' of https://github.com/YuVo-oVki/Exam.git
		SubjectDao sDao = new SubjectDao();
		//Subject subject = (Subject) session.getAttribute("user");// ログインユーザーを取得

		School school=new School();
		school.setCd("oom");
		school.setName("学校名");

<<<<<<< HEAD
        String cd = req.getParameter("cd");
        String name = req.getParameter("name");
		sDao.get(cd, school);
		school = stu.getSchool();
=======
        Subject subject= sDao.get(req.getParameter("cd"), school);
>>>>>>> branch 'master' of https://github.com/YuVo-oVki/Exam.git

		//JSPへフォワード
<<<<<<< HEAD
		req.setAttribute("cd_set", cd);
		req.setAttribute("name_set", name);
=======
		req.setAttribute("cd", subject.getCd());
		req.setAttribute("name",subject.getName());
>>>>>>> branch 'master' of https://github.com/YuVo-oVki/Exam.git
		req.getRequestDispatcher("SubjectUpdate.jsp").forward(req, res);
	}
}
