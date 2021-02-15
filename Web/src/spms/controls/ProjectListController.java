package spms.controls;

import java.util.HashMap;
import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.ProjectDao;
@Component("/project/list.do")
public class ProjectListController implements Controller, DataBinding{

	private ProjectDao projectDao;
	
	public void setProjectDao(ProjectDao projectDao){
		this.projectDao=projectDao;
	}
	
	/*orderCond 변수 값을 처리하기 위한 바인딩 메소드 추가*/
	@Override
	public Object[] getDataBinders() {
		return new Object[]{"orderCond",String.class};
	}
	
	@Override
	public String execute(Map<String, Object> model) throws Exception {
		
		/*프로젝트 목록을 가져오는 매퍼의 파라미터 타입이 map 객체 이므로*/
		HashMap<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("orderCond", model.get("orderCond"));
		model.put("projects", projectDao.selectList(paramMap));
		return "/project/ProjectList.jsp";
	}	
}
