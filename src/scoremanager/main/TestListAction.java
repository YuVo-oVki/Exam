package scoremanager.main;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
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
		int classNum;
		String sub="";
		String noStr = "";
		ClassNumDao cNumDao = new ClassNumDao();
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();

		entYearStr = req.getParameter("f1");
		// classNum = req.getParameter("f2");
		sub = req.getParameter("f3");
		noStr = req.getParameter("f4");


		req.getRequestDispatcher("test_list.jsp").forward(req, res);
	}

	private void setTestListSubject(HttpServletRequest req, HttpServletResponse res){
		//
	}

	private void setTestListStudent(HttpServletRequest req, HttpServletResponse res){
		//
	}

}