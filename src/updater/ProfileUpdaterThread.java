package updater;

import util.JSoupUtil;
import db.dao.HibernateDAOFactory;
import db.dao.ProfileDAOImpl;
import db.dto.Profile;

public class ProfileUpdaterThread implements Runnable {
	Profile profile;
	ProfileDAOImpl profileDAOImpl;
	
	public ProfileUpdaterThread(Profile profile){
		super();
		this.profile = profile;
		HibernateDAOFactory factory = new HibernateDAOFactory();
		profileDAOImpl = factory.getProfileDAOImpl();
	}
	
	@Override
	public void run() { 
 	   try{
 		   	Profile updated_profile = JSoupUtil.getProfileFromURL(profile);
 		   	profileDAOImpl.update(updated_profile);
        }catch(Exception e)
        {
     	   e.printStackTrace();   
        }
	}
	
}

