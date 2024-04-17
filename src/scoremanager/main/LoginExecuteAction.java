package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginExecuteAction {
	void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		req.getRequestDispatcher("").forward(req, res);
	}
}