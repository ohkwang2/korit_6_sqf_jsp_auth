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
			responsebody.append("alert('�̹� �����ϴ� ���̵��Դϴ�.');");
			responsebody.append("history.back();");
			responsebody.append("</script>");
			
			// html Ÿ������ ��������
			resp.setContentType("text/html");
			resp.getWriter().println(responsebody.toString());
			// ���� if�� �������� �Ѿ�� �ʵ��� return�� �ɾ���
			return;
		}
		
		User user = User.builder()
				.username(username)
				// BCrypt�� ���� hashpw ��ȣȭ(�Է� ��й�ȣ, ��й�ȣ ���� �˰���)
				.password(BCrypt.hashpw(password,BCrypt.gensalt()))
				.name(name)
				.email(email)
				.build();
		UserDao.save(user);
		
		StringBuilder responsebody = new StringBuilder();
		responsebody.append("<script>");
		responsebody.append("alert('ȸ�����Կ� �����Ͽ����ϴ�. �α��� �� �̿�ٶ��ϴ�.');");
		// replace�� history�� ���� ����.
		responsebody.append("location.replace('/ssa/login');");
		responsebody.append("</script>");
		
		// html Ÿ������ ��������
		resp.setContentType("text/html");
		resp.getWriter().println(responsebody.toString());
	}
}
