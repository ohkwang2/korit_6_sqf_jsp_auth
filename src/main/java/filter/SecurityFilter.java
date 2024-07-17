package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import entity.User;

@WebFilter(filterName = "securityFilter")
//@WebFilter({"/mypage", "/mypage/password"})
public class SecurityFilter extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// �α����� �� �Ǿ� ���� ��� ���� ȣ�� ���� ��ó�� ���־�� ��
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession session = httpRequest.getSession();
		
		System.out.println("��ť��Ƽ ����");
		
		User user = (User) session.getAttribute("authentication");
		if(user == null) {
			StringBuilder responseBody = new StringBuilder();
			responseBody.append("<script>");
			// \ �ϳ��� ����ϸ� java���� �ٹٲ� �ǰ�, \\ �� ���� ���� javascript���� ����
			responseBody.append("alert('�߸��� �����Դϴ�.\\n�α��� �Ŀ� �̿�ٶ��ϴ�.');");
			responseBody.append("location.href='/ssa/login';");
			responseBody.append("</script>");
			response.setContentType("text/html");
			response.getWriter().println(responseBody.toString());
			return;
		}
		
		
		chain.doFilter(request, response);	//���� ȣ��
	}


}
