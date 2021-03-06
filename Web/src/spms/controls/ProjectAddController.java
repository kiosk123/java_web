package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.ProjectDao;
import spms.vo.Project;

@Component("/project/add.do")
public class ProjectAddController implements DataBinding, Controller {

	private ProjectDao projectDao;

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[] { "project", Project.class };
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Project project=(Project)model.get("project");
		if(project.getTitle()==null)
			return "/project/ProjectForm.jsp";
		else{
			projectDao.insert(project);
			return "redirect:list.do";
		}
	}
}
