package spms.servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import spms.bind.DataBinding;
import spms.context.ApplicationContext;
import spms.controls.Controller;
import spms.listener.ContextLoaderListener;
import spms.util.ServletRequestDataBinder;

@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");

		// 클라이언트가 요청한 서블릿 경로를 알아낸다
		String servletPath = request.getServletPath();

		try {
			ApplicationContext ctx = ContextLoaderListener.getApplicationContext();

			// 맵 객체 준비 : Dao,session,request,response 객체는 필요에 따라 미리 담아두자
			HashMap<String, Object> model = new HashMap<String, Object>();

			model.put("session", request.getSession());
			model.put("request", request);
			model.put("response", response);

			//페이지 컨트를러를 찾는다.
			Controller pageController = (Controller) ctx.getBean(servletPath);
             
			//페이지 컨트롤러가 없으면 오류발생
			if (pageController == null) {
				throw new Exception("요청한 서비스를 찾을 수 없습니다.");
			}

			if (pageController instanceof DataBinding) {
				prepareRequestData(request, model, (DataBinding) pageController);
			}

			// 서비스할 url 추출
			String viewUrl = pageController.execute(model);

			// 키와 객체를 매핑해서 request에 넣음
			for (String key : model.keySet()) {
				request.setAttribute(key, model.get(key));
			}

			if (viewUrl.startsWith("redirect:")) {
				response.sendRedirect(viewUrl.substring(9));
				return;
			} else {
				RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
				rd.include(request, response);
			}

			/*
			 * 서블릿을 만들 때 매번 오류처리를 했는데 이제 프런트 컨트롤러에서 오류처리를 담당하기 대문에 페이지 컨트롤러를
			 * 작성할때는 오류 처리 코드를 넣을 필요가 없다.
			 */
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e);
			RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
			rd.forward(request, response);
		}
	}

	private void prepareRequestData(HttpServletRequest request, HashMap<String, Object> model, DataBinding dataBinding)
			throws Exception {

		Object[] dataBinders = dataBinding.getDataBinders();
		String dataName = null;
		Class<?> dataType = null;
		Object dataObj = null;

		for (int i = 0; i < dataBinders.length; i += 2) {
			dataName = (String) dataBinders[i];
			dataType = (Class<?>) dataBinders[i + 1];
			// ServletReqeustDataBinder에서 bind 정적 메소드 호출
			dataObj = ServletRequestDataBinder.bind(request, dataType, dataName);
			model.put(dataName, dataObj);
		}
	}

}
