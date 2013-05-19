package updater;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import util.Constant;

import db.dao.HibernateDAOFactory;
import db.dao.ProfileDAOImpl;
import db.dao.ProjectDAOImpl;
import db.dto.Profile;
import db.dto.Project;


public class ExistingProfileUpdater {
	public static void main(String args[])
	{
		HibernateDAOFactory factory = new HibernateDAOFactory();
		ProfileDAOImpl profileDAO = factory.getProfileDAOImpl();
		List<Profile> profileList = profileDAO.findAll();
		ExecutorService profileExecutor = Executors.newFixedThreadPool(Constant.PROFILE_UPDATER_THREAD_COUNT);
		for (Profile profile : profileList) {
			ProfileUpdaterThread thread = new ProfileUpdaterThread(profile);
			profileExecutor.submit(thread);
		}
		// This will make the executor accept no new threads
	    // and finish all existing threads in the queue
		profileExecutor.shutdown();
		
	}
}
