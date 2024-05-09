package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class TestListStudentAction extends Action {

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
		ClassNumDao cNumDao = new ClassNumDao();
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();

		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		sub = req.getParameter("f3");
		noStr = req.getParameter("f4");

		List<String> list = cNumDao.filter(teacher.getSchool());

		if (entYearStr != null) {
			entYear = Integer.parseInt(entYearStr);
		}

		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}

		/*
		if (submit.equals("Subject")){
			req.setAttribute("f1", entYear);
			req.setAttribute("f2", classNum);
			req.setAttribute("class_num_set", list);
		}

		if (submit.equals("Student")){
			req.setAttribute("ent_year_set", entYearSet);
		}
		*/

		req.getRequestDispatcher("test_list.jsp").forward(req, res);

	}

	private void setTestListSubject(HttpServletRequest req, HttpServletResponse res){

	}

	private void setTestListStudent(HttpServletRequest req, HttpServletResponse res){

	}

}