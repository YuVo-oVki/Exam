package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();
//		Teacher teacher = (Teacher)session.getAttribute("user");

		School school=new School();
		school.setCd("oom");
		school.setName("学校名");

		Teacher teacher = new Teacher();
		teacher.setId("admin");
		teacher.setPassword("password");
		teacher.setName("大原花子");
		teacher.setSchool(school);

		String entYearStr = "";
		String classNum = "";
		String sub="";
		String no = "";
		int entYear = 0;
		boolean isAttend = false;
		Subject subject = new Subject();
		List<Student> students = null;
		List<Subject> subjects = null;
		StudentDao sDao = new StudentDao();
		SubjectDao subDao = new SubjectDao();
		ClassNumDao cNumDao = new ClassNumDao();
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		Map<String, String> errors = new HashMap<>();

		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		sub = req.getParameter("f3");
		no = req.getParameter("f4");

		List<String> list = cNumDao.filter(teacher.getSchool());

		if (entYearStr != null) {
			entYear = Integer.parseInt(entYearStr);
		}

		if (entYear != 0 && !classNum.equals("0")) {
			students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);
		} else if (entYear != 0 && classNum.equals("0")) {
			students = sDao.filter(teacher.getSchool(), entYear, isAttend);
		} else if (entYear == 0 && classNum == null || entYear == 0 && classNum.equals("0")) {
			students = sDao.filter(teacher.getSchool(), isAttend);
		} else {
			errors.put("f1", "クラスを指定する場合は入学年度も指定してください");
			req.setAttribute("errors", errors);
			// 全学年情報を取得
			students = sDao.filter(teacher.getSchool(), isAttend);
		}
		subjects = subDao.filter(school);

		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}

		/*
		req.setAttribute("f1", entYear);
		req.setAttribute("f2", classNum);
		req.setAttribute("f3", sub);
		req.setAttribute("f4", no);
		*/

		req.setAttribute("ent_year_set", entYearSet);
		req.setAttribute("num", list);
		req.setAttribute("sub", subject.getName()); // ここで科目の名前を収集したい

		req.getRequestDispatcher("test_list.jsp").forward(req, res);

	}

	private void setTestListSubject(HttpServletRequest req, HttpServletResponse res){
		// req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
	}

	private void setTestListStudent(HttpServletRequest req, HttpServletResponse res){
		// req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
	}

}
