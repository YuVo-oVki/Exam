package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		HttpSession session = req.getSession(true);// セッションを取得
		ClassNumDao cNumDao = new ClassNumDao();// クラス番号Daoを初期化
		Student stu = new Student();
		StudentDao sDao = new StudentDao();
//		Teacher teacher = (Teacher) session.getAttribute("user");// ログインユーザーを取得
		LocalDate todaysDate = LocalDate.now();// LocalDateインスタンスを取得
		int year = todaysDate.getYear();// 現在の年を取得
		List<Integer> entYearSet = new ArrayList<>();//入学年度のリストを初期化

		School school=new School();
		school.setCd("OOM");
		school.setName("学校名");

		Teacher teacher = new Teacher();
		teacher.setId("admin");
		teacher.setPassword("password");
		teacher.setName("大原花子");
		teacher.setSchool(school);

		List<String> list = cNumDao.filter(teacher.getSchool());

		System.out.println(list);

		//jspのnoをゲットして
		String no = req.getParameter("no");
		stu = sDao.get(no);
		int entYear = stu.getEntYear();
		String name = stu.getName();
		String oldCls = stu.getClassNum();

		//JSPへフォワード 7
		req.setAttribute("ent_year_set", entYear);
		req.setAttribute("no_set", no);
		req.setAttribute("old_name", name);
		req.setAttribute("class_num_set", list);
		req.setAttribute("old_class", oldCls);
		req.getRequestDispatcher("student_update.jsp").forward(req, res);
	}
}
