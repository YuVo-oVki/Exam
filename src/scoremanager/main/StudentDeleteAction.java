package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import dao.ClassNumDeleteDao;
import tool.Action;

public class StudentDeleteAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();
//		Teacher teacher = (Teacher)session.getAttribute("user");
//
		School school=new School();
		school.setCd("oom");
		school.setName("学校名");
//
		String classNum = req.getParameter("no");
//
		ClassNumDeleteDao cNumDelDao = new ClassNumDeleteDao();
		cNumDelDao.filter(school, classNum);

		req.getRequestDispatcher("student_delete.jsp").forward(req, res);
	}
}