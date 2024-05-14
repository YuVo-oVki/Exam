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

public class SubjectDeleteExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);// セッションを取得
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		String cd = "";
		String name = "";
		Subject subject = null;
		Student stu = new Student();
		SubjectDao sDao = new SubjectDao();
		Map<String, String> errors = new HashMap<>();
		//Subject subject = (Subject) session.getAttribute("user");// ログインユーザーを取得
		LocalDate todaysDate = LocalDate.now();// LocalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得

		School school=new School();
		school.setCd("oom");
		school.setName("学校名");



        cd = req.getParameter("cd_set");//科目コード
        name = req.getParameter("name_set");
        System.out.println(cd+name);
		subject = sDao.get(cd, school);// 学生番号から学生インスタンスを取得
		sDao.delete(subject);


		if(!errors.isEmpty()){
			// リクエスト属性をセット
			req.setAttribute("errors", errors);
			req.setAttribute("cd", cd);
			req.setAttribute("school",school );
			req.getRequestDispatcher("SubjectDelete.jsp").forward(req, res);
			return;
		}

		req.getRequestDispatcher("SubjectDeleteDone.jsp").forward(req, res);
	}
}


