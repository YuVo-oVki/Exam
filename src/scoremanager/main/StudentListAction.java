package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;

public class StudentListAction extends Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		HttpServletRequest req = request;
		HttpServletResponse res = response;

		// HttpSession session = request.getSession();
		// Teacher teacher = (Teacher)session.getAttribute("user");


		School school = new School();
		school.setCd("tes");
		school.setName("テスト校");

		Teacher teacher = new Teacher();
		teacher.setId("admin1");
		teacher.setName("管理者1");
		teacher.setPassword("password");
		teacher.setSchool(school);


		String entYearStr="";
		String classNum="";
		String isAttendStr="";
		int entYear = 0;
		boolean isAttend = false;
		List<Student> students = null;
		LocalDate todaysDate = LocalDate.now();
		int year = todaysDate.getYear();
		StudentDao sDao = new StudentDao();
		ClassNumDao cNumDao = new ClassNumDao();
		Map<String, String> errors = new HashMap<>();

		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		isAttendStr = req.getParameter("f3");

		List<String> list = cNumDao.filter(teacher.getSchool());

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

		if (entYearStr != null) {
			entYear = Integer.parseInt(entYearStr);
		}
		List<Integer> entYearSet = new ArrayList<>();
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}

		req.setAttribute("f1", entYear);
		req.setAttribute("f2", classNum);
		if (isAttendStr != null) {
			isAttend = true;
			req.setAttribute("f3", isAttendStr);
		}
		req.setAttribute("students", students);
		req.setAttribute("class_num_set", list);
		req.setAttribute("ent_year_set", entYearSet);

		req.getRequestDispatcher("student_list.jsp").forward(req, res);
	}
}