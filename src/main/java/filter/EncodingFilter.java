package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

// 어떠한 경로로 들어오든 해당 필터를 거치게 만드는 것
// 해당 어노테이션을 사용할 경우 web.xml에 등록을 안 해도 됨. (단, 실행 순서가 필요할 경우 직접 web.xml에 등록해야 함
// 해당 어노테이션 안에 url을 넣어줄 수도 있고, 다양한 속성 값을 변경할 수도 있음.
@WebFilter(filterName = "encodingFilter")
//@WebFilter("/*")
public class EncodingFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// chain -> tomcat과 servlet을 각각 호출하여 필터가 중간에서 연결해줌
		// chain이 없으면 이전 혹은 다음의 filter나 tomcat, servlet과 연결할 수 없음
		System.out.println("전처리");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// chain 기점으로 이전은 전처리, 이후는 후처리
		chain.doFilter(request, response);	// 다음 필터 또는 서블릿 호출
		System.out.println("후처리");
	}

}
