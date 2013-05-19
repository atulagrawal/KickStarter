
package db.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import db.dto.Profile;
import db.dto.Project;

public class ProjectDAOImpl extends GenericDAOImpl<Project, Integer>{

	@Override
	public List<Project> search(Project criteria) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
    
	public ArrayList<String> getProjectURL()
	{
		ArrayList<String> list = new ArrayList<String>();
		List<Project> projectList= findAll();
		for (Project project : projectList) {
			list.add(project.getUrl());
			System.out.println("project url:"+project.getUrl());
		}
		return list;
	}
	
	public ArrayList<String> getProjectURLWithoutHTTP()
	{
		ArrayList<String> list = new ArrayList<String>();
		List<Project> projectList= findAll();
		for (Project project : projectList) {
			if(project.getUrl()!=null && project.getUrl().contains("//"))
			{
				
				list.add(project.getUrl().split("//")[1]);
				System.out.println("project url:"+project.getUrl().split("//")[1]);
			}
		}
		return list;
	}
	
	public List<Project> getProjectsByPercentage(Double start, Double end)
	{
		
	    	Criteria crit = getSession().createCriteria(getPersistentClass());
	        crit.add(Restrictions.between("percentageRaised", start, end) );
	        List<Project> projectList = crit.list();
	        getSession().close();
	    	return projectList;
	}
	
	public List<Project> getProjectsByGoal(Double start, Double end)
	{
		
	    	Criteria crit = getSession().createCriteria(getPersistentClass());
	        crit.add(Restrictions.between("goal", start, end) );
	        List<Project> projectList = crit.list();
	        getSession().close();
	    	return projectList;
	}
	
	public Project getProjectsByURL(String url)
	{
		
	    	Criteria crit = getSession().createCriteria(getPersistentClass());
	        crit.add(Restrictions.like("url", url) );
	        List<Project> projectList = crit.list();
	        getSession().close();
	        if(projectList.size()==1)
	        	return projectList.get(0);
	        else return null;
	    	
	}
	
}
