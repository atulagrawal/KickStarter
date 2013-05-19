package updater;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import db.dao.HibernateDAOFactory;
import db.dao.ProfileDAOImpl;
import db.dao.ProjectDAOImpl;
import db.dto.Project;


public class ExistingProjectUpdater {
	public static void main(String args[])
	{
		HibernateDAOFactory factory = new HibernateDAOFactory();
		ProjectDAOImpl projectDAO = factory.getProjectDAOImpl();
		ExecutorService projectExecutor = Executors.newFixedThreadPool(10);
		List<Project> projectList = projectDAO.findAll();
		for (Project project : projectList) {
			ProjectUpdaterThread thread = new ProjectUpdaterThread(project);
			projectExecutor.submit(thread);
		}
		// This will make the executor accept no new threads
	    // and finish all existing threads in the queue
		projectExecutor.shutdown();
	}
}
