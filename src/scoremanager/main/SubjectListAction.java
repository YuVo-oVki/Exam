package scoremanager.main;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.School;
import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

@WebServlet(urlPatterns={"/main/SubjectListAction"})
public class SubjectListAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

//		HttpSession session = req.getSession();
//		Teacher teacher = (Teacher)session.getAttribute("user");

		School school=new School();
		school.setCd("oom");
		school.setName("学校名");

		Teacher teacher = new Teacher();
		teacher.setId("admin");
		teacher.setPassword("password");
		teacher.setName("大原花子");
		teacher.setSchool(school);

		SubjectDao sDao = new SubjectDao();
		List<Subject> subjects = null;

		subjects = sDao.filter(school);


		req.setAttribute("subjects", subjects);

		req.getRequestDispatcher("subject_list.jsp").forward(req, res);
	}
}