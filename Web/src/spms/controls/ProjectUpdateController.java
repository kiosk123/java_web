package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.bind.DataBinding;
import spms.dao.ProjectDao;
import spms.vo.Project;

@Component("/project/update.do")
public class ProjectUpdateController implements DataBinding, Controller {

	private ProjectDao projectDao;

	public void setProjectDao(ProjectDao projectDao) {
		this.projectDao = projectDao;
	}

	@Override
	public Object[] getDataBinders() {
		return new Object[]{"no",Integer.class,"project",Project.class};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Project project=(Project)model.get("project");
		Integer no=(Integer)model.get("no");
		if(project.getTitle()==null){
			project=projectDao.selectOne(no);
			model.put("project", project);
			return "/project/ProjectUpdateForm.jsp";
		}else{
			project=(Project)model.get("project");
			projectDao.update(project);
			return "redirect:list.do";
		}
	}

}
