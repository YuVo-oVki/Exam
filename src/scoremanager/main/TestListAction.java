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
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class TestListAction extends Action {

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
		String noStr = "";
		int entYear = 0;
		boolean isAttend = false;
		List<Student> students = null;
		StudentDao sDao = new StudentDao();
		ClassNumDao cNumDao = new ClassNumDao();
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		Map<String, String> errors = new HashMap<>();

		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		sub = req.getParameter("f3");
		noStr = req.getParameter("f4");

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

		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}

		// req.setAttribute("year", list);

		req.getRequestDispatcher("test_list.jsp").forward(req, res);

	}

	private void setTestListSubject(HttpServletRequest req, HttpServletResponse res){

	}

	private void setTestListStudent(HttpServletRequest req, HttpServletResponse res){

	}

}