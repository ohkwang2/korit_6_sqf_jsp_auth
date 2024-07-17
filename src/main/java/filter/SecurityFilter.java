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
		// 로그인이 안 되어 있을 경우 서블릿 호출 이전 전처리 해주어야 함
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpSession session = httpRequest.getSession();
		
		System.out.println("시큐리티 필터");
		
		User user = (User) session.getAttribute("authentication");
		if(user == null) {
			StringBuilder responseBody = new StringBuilder();
			responseBody.append("<script>");
			// \ 하나만 사용하면 java에서 줄바꿈 되고, \\ 두 개를 쓰면 javascript에서 실행
			responseBody.append("alert('잘못된 접근입니다.\\n로그인 후에 이용바랍니다.');");
			responseBody.append("location.href='/ssa/login';");
			responseBody.append("</script>");
			response.setContentType("text/html");
			response.getWriter().println(responseBody.toString());
			return;
		}
		
		
		chain.doFilter(request, response);	//서블릿 호출
	}


}
