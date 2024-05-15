package scoremanager.main;


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

		req.getRequestDispatcher("SubjectUpdateDone.jsp").forward(req, res);
	}
}


