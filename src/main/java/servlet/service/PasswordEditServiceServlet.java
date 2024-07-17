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

@WebServlet("/api/mypage/password/edit")
public class PasswordEditServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("authentication");
		String password = req.getParameter("password");
		String checkPassword = req.getParameter("checkPassword");
		
		if(!password.equals(checkPassword)) {
			responsePasswordEditError(resp, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.\\n�ٽ� �Է��ϼ���.");
			return;
		}
		
		if(BCrypt.checkpw(password, user.getPassword())) {
			responsePasswordEditError(resp, "���� ��й�ȣ�� ������ ��й�ȣ�� ����� �� �����ϴ�.\\n�ٽ� �Է��ϼ���.");
			return;
		}
		
		User modifiyUser = User.builder()
				.userId(user.getUserId())
				.password(BCrypt.hashpw(password, BCrypt.gensalt()))
				.build();
		UserDao.updatePassword(modifiyUser);
		// �α��� ������Ű�� ������ ���ǿ� �ٽ� �ٲ� �н����� ������ �־���.
		user.setPassword(modifiyUser.getPassword());	// ���� �α׾ƿ� ���� ���� ��
		
		StringBuilder responseBody = new StringBuilder();
		responseBody.append("<script>");
		responseBody.append("alert('��й�ȣ ���� �Ϸ�!\\n�ٽ� �α����ϼ���.');");
		responseBody.append("location.href='/ssa/logout';");
		responseBody.append("</script>");
		resp.setContentType("text/html");
		resp.getWriter().println(responseBody.toString());
	}
	
	private void responsePasswordEditError(HttpServletResponse resp, String msg) throws IOException {
		StringBuilder responsebody = new StringBuilder();
		responsebody.append("<script>");
		responsebody.append("alert('");
		responsebody.append(msg);
		responsebody.append("');");
		responsebody.append("history.back();");
		responsebody.append("</script>");
		
		// html Ÿ������ ��������
		resp.setContentType("text/html");
		// IOException���� ���� ������
		resp.getWriter().println(responsebody.toString());
	}

}
