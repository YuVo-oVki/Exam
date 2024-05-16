package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		//HttpSession session = req.getSession(true);// セッションを取得
		SubjectDao sDao = new SubjectDao();
		String cd = "";
		//Subject subject = (Subject) session.getAttribute("user");// ログインユーザーを取得

		School school=new School();
		school.setCd("oom");
		school.setName("学校名");

        cd = req.getParameter("cd");//科目コード

		Subject subject = sDao.get(cd, school);// 学生番号から学生インスタンスを取得
		//JSPへフォワード
		req.setAttribute("subject", subject);
		req.getRequestDispatcher("SubjectDelete.jsp").forward(req, res);
	}
}


