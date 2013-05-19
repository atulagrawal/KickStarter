package scraper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import util.JSoupUtil;
import db.dao.HibernateDAOFactory;
import db.dao.ProjectDAOImpl;
import db.dto.Profile;
import db.dto.Project;

public class ProfileDetailsThread extends Thread {
	Profile profile;
	ProjectDAOImpl projectDAO;
	public List<String> profileURLList = new ArrayList<String>();
	public ProfileDetailsThread(Profile profile) {
		super();
		this.profile = profile;
		HibernateDAOFactory factory = new HibernateDAOFactory();
		projectDAO = factory.getProjectDAOImpl();
		
	}

	public void run() {
		visit();
	}

	/**
	 * This function is called when a page is fetched and ready to be processed
	 * by your program.
	 */

	public void visit() {	
		profile = JSoupUtil.getProfileFromURL(profile);
		Set<Project> projectList = JSoupUtil.getProjectListForProfile(profile);
		profile.getProjects().addAll(projectList);
		profile.setProjects(projectList);
		
	}
}
