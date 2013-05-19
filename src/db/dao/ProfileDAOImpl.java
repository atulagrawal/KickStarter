
package db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import db.dto.Profile;
import db.dto.Project;

public class ProfileDAOImpl extends GenericDAOImpl<Profile, Integer>{

	@Override
	public List<Profile> search(Profile criteria) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<String> getProfileURLList()
	{
		ArrayList<String> list = new ArrayList<String>();
		List<Profile> profileList= findAll();
		for (Profile profile : profileList) {
			list.add(profile.getUrl());
			System.out.println("profile url:"+profile.getUrl());
		}
		return list;
	}
	
	public List<Integer> getDisticntProfiles()
	{
		
	    	Criteria crit = getSession().createCriteria(getPersistentClass());
			
	    	crit.setProjection(Projections.distinct(Projections.property("ksId")));
	    	//crit.setMaxResults(10);
	        List<Integer> profileList = crit.list();
		
	        getSession().close();
	    	return profileList;
	}
	
	public Profile getProfileByKSId(Integer ksId)
	{
		
	    	Criteria crit = getSession().createCriteria(getPersistentClass());
	    	crit.add(Restrictions.like("ksId", ksId) );
	    
	        List<Profile> profileList = crit.list();
	        getSession().close();
	        if(profileList.size()>=1)
	        	return profileList.get(0);
	        else
	    	return null;
	}
    
}
