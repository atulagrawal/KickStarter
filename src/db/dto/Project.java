package db.dto;
// Generated Mar 4, 2013 12:27:59 AM by Hibernate Tools 3.2.1.GA


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Project generated by hbm2java
 */
public class Project  implements java.io.Serializable {


   //  private Integer id;
     private String url;
     private Double goal;
     private String currency;
     private String pledgedAmount;
     private String percentageRaised;
     private String postedDate;
     private String endedDate;
     private String category;
     private String proId;
     private String location;
     private Integer backers;
     private String daysToGo;
     private String duration;
     private Set<Profile> profiles = new HashSet<Profile>();
     
     public Set<Profile> getProfiles() {
		return profiles;
	}

	public void setProfiles(Set<Profile> profiles) {
		this.profiles = profiles;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	private Set rewards = new HashSet(0);

    public Project() {
    }

    public Project(String url, Double goal, String currency, String pledgedAmount, String percentageRaised, String postedDate, String endedDate, String category, Set rewards) {
       this.url = url;
       this.goal = goal;
       this.currency = currency;
       this.pledgedAmount = pledgedAmount;
       this.percentageRaised = percentageRaised;
       this.postedDate = postedDate;
       this.endedDate = endedDate;
       this.category = category;
       this.rewards = rewards;
    }

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    public Double getGoal() {
        return this.goal;
    }
    
    public void setGoal(Double goal) {
        this.goal = goal;
    }
    public String getCurrency() {
        return this.currency;
    }
    
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getPledgedAmount() {
        return this.pledgedAmount;
    }
    
    public void setPledgedAmount(String pledgedAmount) {
        this.pledgedAmount = pledgedAmount;
    }
    public String getPercentageRaised() {
        return this.percentageRaised;
    }
    
    public void setPercentageRaised(String percentageRaised) {
        this.percentageRaised = percentageRaised;
    }
    public String getPostedDate() {
        return this.postedDate;
    }
    
    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }
    public String getEndedDate() {
        return this.endedDate;
    }
    
    public void setEndedDate(String endedDate) {
        this.endedDate = endedDate;
    }
    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    public Set getRewards() {
        return this.rewards;
    }
    
    public void setRewards(Set rewards) {
        this.rewards = rewards;
    }

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
	}

	public Integer getBackers() {
		return backers;
	}

	public void setBackers(Integer backers) {
		this.backers = backers;
	
	}
	

	public String getDaysToGo() {
		return daysToGo;
	}

	public void setDaysToGo(String daysToGo) {
		this.daysToGo = daysToGo;
	}
	

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		for (Profile profile : profiles) {
			System.out.println(profile.toString());
		}
		return "Project [url=" + url + ", goal=" + goal
				+ ", currency=" + currency + ", pledgedAmount=" + pledgedAmount
				+ ", percentageRaised=" + percentageRaised + ", postedDate="
				+ postedDate + ", endedDate=" + endedDate + ", category="
				+ category + ",daysToGo=" + daysToGo + ",backers=" + backers + ",duration=" + duration + ", proId=" + proId + "]";
	}




}


