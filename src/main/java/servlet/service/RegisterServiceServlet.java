package servlet.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import dao.UserDao;
import entity.User;

@WebServlet("/api/register")
public class RegisterServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		
		if(UserDao.findUserByUsername(username) != null) {
			StringBuilder responsebody = new StringBuilder();
			responsebody.append("<script>");
			responsebody.append("alert('이미 존재하는 아이디입니다.');");
			responsebody.append("history.back();");
			responsebody.append("</script>");
			
			// html 타입으로 응답해줌
			resp.setContentType("text/html");
			resp.getWriter().println(responsebody.toString());
			// 이후 if문 다음으로 넘어가지 않도록 return을 걸어줌
			return;
		}
		
		User user = User.builder()
				.username(username)
				// BCrypt를 통해 hashpw 암호화(입력 비밀번호, 비밀번호 생성 알고리즘)
				.password(BCrypt.hashpw(password,BCrypt.gensalt()))
				.name(name)
				.email(email)
				.build();
		UserDao.save(user);
		
		StringBuilder responsebody = new StringBuilder();
		responsebody.append("<script>");
		responsebody.append("alert('회원가입에 성공하였습니다. 로그인 후 이용바랍니다.');");
		// replace는 history가 남지 않음.
		responsebody.append("location.replace('/ssa/login');");
		responsebody.append("</script>");
		
		// html 타입으로 응답해줌
		resp.setContentType("text/html");
		resp.getWriter().println(responsebody.toString());
	}
}
