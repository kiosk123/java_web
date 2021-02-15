package spms.listener;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import spms.context.ApplicationContext;

public class ContextLoaderListener implements ServletContextListener {

	static ApplicationContext applicationContext;

	/*
	 * ContextLoaderListener에서 만든 ApplicationContext 객체를 얻을 때 사용 프런트 컨트롤러에서
	 * 사용한다.
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	// 웹 애플리케이션이 시작될때 호출
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		try {

			/* web.xml 파일에서 프로퍼티 파일의 이름과 경로 정보를 읽어온다. */
			String propertiesPath = sc.getRealPath(sc.getInitParameter("contextConfigLocation"));
			
			/* mybatis 설정 파일 클래스 패스를 가르키는 변수 */
			String resource = "spms/dao/mybatis-config.xml";

			/* mybatis 설정 파일에서 리소를 읽어들인다.*/
			InputStream inputStream = Resources.getResourceAsStream(resource);

			SqlSessionFactory sqlSessionFactory = 
					new SqlSessionFactoryBuilder().build(inputStream);
			
			applicationContext = new ApplicationContext();
			
			/*sqlSessionFactory 객체를 applicationContext에 추가*/
			applicationContext.addBean("sqlSessionFactory", sqlSessionFactory);
			applicationContext.prepareObjectByProperties(propertiesPath);
			applicationContext.prepareObjectByAnnotion("");
			applicationContext.injectDependency();

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	// 웹 애플리케이션이 종료되기 전에 호출
	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}

}
