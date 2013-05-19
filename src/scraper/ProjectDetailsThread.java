package scraper;

import java.util.List;
import java.util.concurrent.Callable;

import util.JSoupUtil;

import db.dao.HibernateDAOFactory;
import db.dao.ProjectDAOImpl;
import db.dto.Profile;
import db.dto.Project;

public class ProjectDetailsThread implements Runnable {
	private final String url;
	ProjectDAOImpl projectDAO;
	ProjectDetailsThread (String url) {
		this.url = url;
		HibernateDAOFactory factory = new HibernateDAOFactory();

		projectDAO = factory.getProjectDAOImpl();
	}

	@Override
	public void run() {
	
		Project project = JSoupUtil.getProjectFromURL(url);
		String projectUrl = project.getUrl();
		projectUrl = projectUrl.split("\\?")[0];
		String backerUrl = projectUrl + "/backers";
		if (project.getBackers()!=null && project.getBackers() >= 10) {
			// get backers list when project have more than 10 backers.
			List<Profile> profileList = JSoupUtil.getProfileListForProject(backerUrl);
			project.getProfiles().addAll(profileList);
			projectDAO.create(project);
		}
		
		
		System.out.println(project.toString());
		
	}
}
