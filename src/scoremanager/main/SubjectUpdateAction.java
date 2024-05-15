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
		//HttpSession session = req.getSession(true);// セッションを取得
		SubjectDao sDao = new SubjectDao();
		//Subject subject = (Subject) session.getAttribute("user");// ログインユーザーを取得

		School school=new School();
		school.setCd("oom");
		school.setName("学校名");

        Subject subject= sDao.get(req.getParameter("cd"), school);

		//JSPへフォワード
		req.setAttribute("cd", subject.getCd());
		req.setAttribute("name",subject.getName());
		req.getRequestDispatcher("SubjectUpdate.jsp").forward(req, res);
	}
}
