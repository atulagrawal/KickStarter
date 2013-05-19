package updater;

import java.util.List;
import util.JSoupUtil;
import db.dao.HibernateDAOFactory;
import db.dao.ProjectDAOImpl;
import db.dto.Profile;
import db.dto.Project;


public class ProjectUpdaterThread extends Thread {
	
	ProjectDAOImpl projectDAOImpl;
	Project project;
	public ProjectUpdaterThread(Project project){
		super();
		this.project = project;
		HibernateDAOFactory factory = new HibernateDAOFactory();
		projectDAOImpl = factory.getProjectDAOImpl();
	}
	public void run(){
		visit();
	}
	
	 /**
     * This function is called when a page is fetched and ready to be processed
     * by your program.
     */
    
    public void visit ()
    {
    		System.out.println("------------------VISIT-----------");   
        	   try{	 
            	   Project pro = JSoupUtil.getProjectFromURL(project.getUrl());
            	   List<Profile> profiles = JSoupUtil.getProfileListForProject(pro.getUrl());
            	   pro.getProfiles().addAll(profiles);
            	   project.setCategory(pro.getCategory());
            	   project.setBackers(pro.getBackers());
            	   project.setCurrency(pro.getCurrency());
            	   project.setEndedDate(pro.getEndedDate());
            	   project.setPostedDate(pro.getPostedDate());
            	   project.setPledgedAmount(pro.getPledgedAmount());
            	   project.setPercentageRaised(pro.getPercentageRaised());
            	   project.setGoal(pro.getGoal());
            	   project.setLocation(pro.getLocation());
            	   project.setProId(pro.getProId());
            	   project.setDaysToGo(pro.getDaysToGo());
            	   project.setDuration(pro.getDuration());
            	   projectDAOImpl.update(project);
                   }catch(Exception e)
                   {
                	   e.printStackTrace();
                   }
               }
    
}
