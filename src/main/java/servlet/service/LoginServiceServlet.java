package servlet.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;

import dao.UserDao;
import entity.User;

@WebServlet("/api/login")
public class LoginServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	// IOException으로 예외 던져줌
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		User user = UserDao.findUserByUsername(username);
		if(user == null) {
			responseLoginError(resp);
			// 이후 if문 다음으로 넘어가지 않도록 return을 걸어줌
			return;
		}
		
		if(!BCrypt.checkpw(password, user.getPassword())) {
			responseLoginError(resp);
			// 이후 if문 다음으로 넘어가지 않도록 return을 걸어줌
			return;
		}
		
		// session 객체를 받아옴
		HttpSession session = req.getSession();
		// session에 user객체를 넣어놓음 (로그인 성공)
		session.setAttribute("authentication", user);
		// 요청이 들어왔을 때, index로 요청을 강제로 날림
		resp.sendRedirect("/ssa/index");
	}
	
	private void responseLoginError(HttpServletResponse resp) throws IOException {
		StringBuilder responseBody = new StringBuilder();
		responseBody.append("<script>");
		responseBody.append("alert('사용자 정보를 확인해주세요.');");
		responseBody.append("history.back();");
		responseBody.append("</script>");
		
		// html 타입으로 응답해줌
		resp.setContentType("text/html");
		// IOException으로 예외 던져줌
		resp.getWriter().println(responseBody.toString());
	}
    

}
