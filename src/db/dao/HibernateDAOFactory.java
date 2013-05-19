package db.dao;



public class HibernateDAOFactory {

	private RewardDAOImpl rewardDAOImpl;
	private ProjectDAOImpl projectDAOImpl;
	private BreakUPDAOImpl breakupDAOImpl;
	private ProfileDAOImpl profileDAOImpl;

    public HibernateDAOFactory() {
    	rewardDAOImpl = new RewardDAOImpl();
    	projectDAOImpl =new ProjectDAOImpl();
    	breakupDAOImpl =new BreakUPDAOImpl();
    	profileDAOImpl =new ProfileDAOImpl();
    	

    }

	public RewardDAOImpl getRewardDAOImpl() {
		return rewardDAOImpl;
	}

	public void setRewardDAOImpl(RewardDAOImpl rewardDAOImpl) {
		this.rewardDAOImpl = rewardDAOImpl;
	}

	public ProjectDAOImpl getProjectDAOImpl() {
		return new ProjectDAOImpl();
	}

	public void setProjectDAOImpl(ProjectDAOImpl projectDAOImpl) {
		this.projectDAOImpl = projectDAOImpl;
	}

	public BreakUPDAOImpl getBreakupDAOImpl() {
		return breakupDAOImpl;
	}

	public void setBreakupDAOImpl(BreakUPDAOImpl breakupDAOImpl) {
		this.breakupDAOImpl = breakupDAOImpl;
	}

	public ProfileDAOImpl getProfileDAOImpl() {
		return new ProfileDAOImpl();
	}

	
	public void setProfileDAOImpl(ProfileDAOImpl profileDAOImpl) {
		this.profileDAOImpl = profileDAOImpl;
	}

       
}
