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
	// IOException���� ���� ������
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		User user = UserDao.findUserByUsername(username);
		if(user == null) {
			responseLoginError(resp);
			// ���� if�� �������� �Ѿ�� �ʵ��� return�� �ɾ���
			return;
		}
		
		if(!BCrypt.checkpw(password, user.getPassword())) {
			responseLoginError(resp);
			// ���� if�� �������� �Ѿ�� �ʵ��� return�� �ɾ���
			return;
		}
		
		// session ��ü�� �޾ƿ�
		HttpSession session = req.getSession();
		// session�� user��ü�� �־���� (�α��� ����)
		session.setAttribute("authentication", user);
		// ��û�� ������ ��, index�� ��û�� ������ ����
		resp.sendRedirect("/ssa/index");
	}
	
	private void responseLoginError(HttpServletResponse resp) throws IOException {
		StringBuilder responseBody = new StringBuilder();
		responseBody.append("<script>");
		responseBody.append("alert('����� ������ Ȯ�����ּ���.');");
		responseBody.append("history.back();");
		responseBody.append("</script>");
		
		// html Ÿ������ ��������
		resp.setContentType("text/html");
		// IOException���� ���� ������
		resp.getWriter().println(responseBody.toString());
	}
    

}
