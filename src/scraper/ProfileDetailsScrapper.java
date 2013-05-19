package scraper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import util.JSoupUtil;

import db.dao.HibernateDAOFactory;
import db.dao.ProfileDAOImpl;
import db.dao.ProjectDAOImpl;
import db.dto.Profile;
import db.dto.Project;


public class ProfileDetailsScrapper {
	public static void main(String args[])
	{
		HibernateDAOFactory factory = new HibernateDAOFactory();
		ProfileDAOImpl profileDAO = factory.getProfileDAOImpl();
		List<Integer> profileURLList = profileDAO.getDisticntProfiles();
		ProjectDAOImpl projectDAO = factory.getProjectDAOImpl();
		ExecutorService profileExecutor = Executors.newFixedThreadPool(10);
		System.out.println("total profile size" + profileURLList.size());
		  for(Integer ksId : profileURLList)
		  {
			Profile profile = profileDAO.getProfileByKSId(ksId);
			if(profile==null) continue;
			ProfileDetailsThread thread = new ProfileDetailsThread(profile);
			profileExecutor.submit(thread);
			
		  }
		  // This will make the executor accept no new threads
		  // and finish all existing threads in the queue
		  profileExecutor.shutdown();
	}
}
