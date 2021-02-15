package spms.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter{
	FilterConfig config;
	
	@Override
	public void destroy() {}

	/*필터와 연결된 URL에 요청이 들어오면 항상 호출되는 메소드*/
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain nextFilter)
			throws IOException, ServletException {
		/*서블릿 실행되기 전 하는 작업*/
		request.setCharacterEncoding(config.getInitParameter("encoding"));
		nextFilter.doFilter(request, response);/*다음 필터 호출, 호출될 필터가 없으면 서블릿의 service()호출*/
		/*서블릿 실행후 클라이언트에게 응답하기 전에 해야할 작업*/
		}
	
	/*필터 객체 생성후 준비작업을 위해 딱 한번 호출*/
	@Override
	public void init(FilterConfig config) throws ServletException {
		/*필터 초기화 매개변수를 사용하기 위해  config 인스턴스 변수에 저장*/
		this.config=config;
		
	}
}
