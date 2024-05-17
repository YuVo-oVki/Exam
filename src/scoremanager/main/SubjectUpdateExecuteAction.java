package scoremanager.main;


import java.util.HashMap;
import java.util.Map;

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
		Map<String, String> errors = new HashMap<>();// エラーメッセージ
		//Subject subject = (Subject) session.getAttribute("user");// ログインユーザーを取得

		School school=new School();
		school.setCd("oom");
		school.setName("学校名");

		subject.setCd(cd);
		subject.setName(name);

        cd = req.getParameter("cd");//番号
	    name = req.getParameter("name");//氏名
		subject = sDao.get(cd, school);// 番号から学生インスタンスを取得

		if(subject == null){
			errors.put("cd","科目が存在してません");
		} else {
			subject.setName(name);
			sDao.save(subject);
		}

		req.setAttribute("cd_set", cd);
		req.setAttribute("name_set", name);

		if(!errors.isEmpty()){
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("cd", cd);
			req.setAttribute("name", name);
			req.getRequestDispatcher("SubjectUpdate.jsp").forward(req, res);
			return;
		}

		req.getRequestDispatcher("SubjectUpdateDone.jsp").forward(req, res);
	}
}


