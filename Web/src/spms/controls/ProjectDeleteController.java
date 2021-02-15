package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.ProjectDao;

@Component("/project/delete.do")
public class ProjectDeleteController implements DataBinding, Controller {
	private ProjectDao projectDao;

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[]{"no",Integer.class};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Integer no=(Integer)model.get("no");
		projectDao.delete(no);
		return "redirect:list.do";
	}

}
