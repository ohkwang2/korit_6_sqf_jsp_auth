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

// ��� ��η� ������ �ش� ���͸� ��ġ�� ����� ��
// �ش� ������̼��� ����� ��� web.xml�� ����� �� �ص� ��. (��, ���� ������ �ʿ��� ��� ���� web.xml�� ����ؾ� ��
// �ش� ������̼� �ȿ� url�� �־��� ���� �ְ�, �پ��� �Ӽ� ���� ������ ���� ����.
@WebFilter(filterName = "encodingFilter")
//@WebFilter("/*")
public class EncodingFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// chain -> tomcat�� servlet�� ���� ȣ���Ͽ� ���Ͱ� �߰����� ��������
		// chain�� ������ ���� Ȥ�� ������ filter�� tomcat, servlet�� ������ �� ����
		System.out.println("��ó��");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// chain �������� ������ ��ó��, ���Ĵ� ��ó��
		chain.doFilter(request, response);	// ���� ���� �Ǵ� ���� ȣ��
		System.out.println("��ó��");
	}

}
