package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		//HttpSession session = req.getSession(true);// セッションを取得
		SubjectDao sDao = new SubjectDao();
		Subject subject = new Subject();
		//Subject subject = (Subject) session.getAttribute("user");// ログインユーザーを取得

		String cd = req.getParameter("cd");

		School school=new School();
		school.setCd("oom");
		school.setName("学校名");
        cd = req.getParameter("cd");//科目コード

		subject = sDao.get(cd, school);// 学生番号から学生インスタンスを取得
		sDao.delete(subject);

		req.getRequestDispatcher("SubjectDeleteDone.jsp").forward(req, res);
	}
}