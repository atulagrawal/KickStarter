package util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import db.dao.HibernateDAOFactory;
import db.dao.ProfileDAOImpl;
import db.dao.ProjectDAOImpl;
import db.dto.Breakup;
import db.dto.Profile;
import db.dto.Project;
import db.dto.Reward;

public class JSoupUtil {
	public static Type breakupCollectionType = new TypeToken<Collection<Breakup>>() {
	}.getType();
	public static Gson gson = new Gson();

	public static List<Profile> getProfileListForProject(String url) {
		HibernateDAOFactory factory = new HibernateDAOFactory();
		ProfileDAOImpl profileDAO = factory.getProfileDAOImpl();
		List<Profile> profileList = new ArrayList<Profile>();
		String cursorUrl = url + "?cursor=";
		String ks_id = "";
		Elements profileDivElements;
		try {

			do {
				if (!ks_id.equals("")) {
					url = cursorUrl + ks_id;
				}
				Document doc = Jsoup.connect(url).get();

				profileDivElements = doc.select("div.NS_backers__backing_row");
				for (Element element : profileDivElements) {
					Profile profile = new Profile();
					ks_id = element.attr("data-cursor");
					try {
						profile.setKsId(Integer.valueOf(ks_id));
					} catch (Exception e) {
						e.printStackTrace();
					}
					String profileUrl = Constant.PROFILE_BASE_URL + "/"+ ks_id;
					profile.setUrl(profileUrl);
					profile = getProfileFromURL(profile);
					profileList.add(profile);
				}
			} while (profileDivElements != null
					&& profileDivElements.size() > 0);

		} catch (Exception e) {

			e.printStackTrace();
		}

		return profileList;
	}

	public static int getPorojectPages(String url)
	{
		Document doc;
		Integer count =0;
		try {
			doc = Jsoup.connect(url).get();
			Elements backersElements = doc.select("div.pagination > a");
			Element lastPaginationElement = backersElements.get(backersElements.size()-2);
			count = Integer.valueOf(lastPaginationElement.text());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	public static Set<Project> getProjectListForProfile(Profile profile) {

		Set<Project> projectList = new HashSet<Project>();

		try {
			int pageCount = (profile.getTotalProjects() / 32) + 1;
			String baseUrl = profile.getUrl() + "?page=";
			String url = "";
			for (int i = 1; i <= pageCount; i++) {
				url = baseUrl + i;
				try {
					Document doc = Jsoup.connect(url).timeout(1000).get();
					Elements projectURLS = doc.select("a.project_item");

					for (Element element : projectURLS) {
						String projectUrl = element.attr("href");
						try {
							Project project = JSoupUtil
									.getProjectFromURL("http://www.kickstarter.com"
											+ projectUrl);

							projectList.add(project);
						} catch (Exception e) {
							e.printStackTrace();
						}

					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return projectList;
	}

	public static Project getProjectFromURL(String url) {
		Project project = new Project();
		project.setUrl(url);
		System.out.println("------" + url);
		Document doc;
		try {
			doc = Jsoup.connect(url).get();
			Elements backersElements = doc.select("div#backers_count");
			String totalBackers = backersElements.get(0).attr(
					"data-backers-count");
			Integer totalBackersCount = new Integer(-1);
			try {
				totalBackersCount = Integer.parseInt(totalBackers);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			// <span data-duration="29.958333333333332"
			// data-end_time="Wed, 03 Apr 2013 10:35:41 -0000"
			// data-hours-remaining="546.9010091555781"
			// id="project_duration_data"></span>
			Elements durations = doc.select("span[data-duration]");
			if (durations != null & durations.size() > 0) {
				Element duration = durations.get(0);
				project.setDuration(duration.attr("data-duration"));
				project.setDaysToGo(duration.attr("data-hours-remaining"));
			}
			Elements elements = doc.select("div[data-goal]");

			if (elements != null && elements.size() > 0) {
				Element element = elements.get(0);
				try {
					project.setGoal(Double.valueOf(element.attr("data-goal")));
				} catch (Exception e) {
					e.printStackTrace();
				}
				Elements dataEs = element.select("data[data-currency]");
				project.setBackers(totalBackersCount);
				project.setCurrency(dataEs.get(0).attr("data-currency"));
				project.setPledgedAmount(element.attr("data-pledged"));
				project.setPercentageRaised(element.attr("data-percent-raised"));
				project.setProId(element.attr("id"));

				Element innerEle = element.child(0);
				if (innerEle != null) {
					Elements projectRewards = doc
							.select("div.NS-projects-reward");

					Elements pledges = projectRewards.select("h5");
					Elements backers = projectRewards
							.select("span.num-backers");
					Set<Reward> rewardSet = new HashSet<Reward>();
					for (Element backer : backers) {
						Reward rwd = new Reward(pledges.get(0).text(), backers
								.get(0).text());

						rewardSet.add(rwd);

					}
					project.setRewards(rewardSet);
				}

				Elements locations = doc.select("li.location");
				project.setLocation(locations.get(0).text());
			//	System.out.println("location : " + project.getLocation());
				/*
				 * Elements posted = doc.select("li.posted");
				project.setPostedDate(posted.get(0).text());

				Elements ended = doc.select("li.ends");
				 */
				Elements posted = doc.select("p.tiny_type");
				System.out.println("date issue" + posted.text());
			//	project.setPostedDate(posted.text());

			//	Elements ended = doc.select("li.ends");
			//	project.setEndedDate(ended.get(0).text());
				Elements category = doc.select("li.category");

				project.setCategory(category.text());

			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return project;
	}

	public static Profile getProfileFromURL(Profile profile) {

		List<Breakup> breakups = new ArrayList<Breakup>();
		try {
			Document doc = Jsoup.connect(profile.getUrl()).timeout(10 * 1000)
					.get();
			Elements joineds = doc.select("span.joined");
			String joinedStr = "";
			if (joineds != null && joineds.size() > 0) {
				joinedStr = joineds.get(0).text();
				profile.setJoinedDate(joinedStr);
			}
			Elements locations = doc.select("span.location");
			if (locations != null && locations.size() > 0)
				profile.setLocation(locations.get(0).text());

			Elements backeds = doc.select("span.backed");
			String backed = backeds.get(0).text();
			Integer totalProjects = Integer.valueOf(backed
					.replace("Backed", "").replace("projects", "").trim());
			profile.setTotalProjects(totalProjects);
			Elements nameElements = doc
					.select("div#profile_bio > h2:first-child");
			String name = "";
			if (nameElements != null && nameElements.size() > 0) {
				name = nameElements.get(0).text().replace("Creator!", "");
				profile.setName(name);
			}

			Elements createdProjectsUL = doc.select("ul#project_nav > li");

			Integer createdCount = new Integer(0);
			if (createdProjectsUL != null && createdProjectsUL.size() == 3) {
				Element createdProjectsLI = createdProjectsUL.get(1);
				if (createdProjectsLI != null) {
					String countStr = createdProjectsLI.select("span.count")
							.get(0).text();
					
					createdCount = getCount(countStr);

				}
			}
			profile.setCreated(createdCount);
			Elements elements = doc.select("script");
			for (Element element : elements) {
				String eleStr = element.toString();
				
				if (eleStr.contains("circle_data")) {

					String dataStr = eleStr.split("circle_data = ")[1];
					String endDataStr = dataStr.split(";")[0];
					breakups = gson.fromJson(endDataStr, breakupCollectionType);
					for (Breakup breakup : breakups) {
						Integer projects = (Integer.valueOf(breakup
								.getProjects_backed()));
						if (breakup.getCategory_id().equals("1")) {
							profile.setArt(projects);
						} else if (breakup.getCategory_id().equals("3")) {
							profile.setComics(projects);
						} else if (breakup.getCategory_id().equals("6")) {
							profile.setDance(projects);
						} else if (breakup.getCategory_id().equals("7")) {
							profile.setDesign(projects);
						} else if (breakup.getCategory_id().equals("9")) {
							profile.setFashion(projects);
						} else if (breakup.getCategory_id().equals("11")) {
							profile.setFilmandvideo(projects);
						} else if (breakup.getCategory_id().equals("10")) {
							profile.setFood(projects);
						} else if (breakup.getCategory_id().equals("12")) {
							profile.setGames(projects);
						} else if (breakup.getCategory_id().equals("14")) {
							profile.setMusic(projects);
						} else if (breakup.getCategory_id().equals("15")) {
							profile.setPhotography(projects);
						} else if (breakup.getCategory_id().equals("18")) {
							profile.setPublishing(projects);
						} else if (breakup.getCategory_id().equals("16")) {
							profile.setTechnology(projects);
						} else if (breakup.getCategory_id().equals("17")) {
							profile.setTheator(projects);
						}

					}

					
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return profile;
	}

	private static Integer getCount(String countStr) {
		Integer count = new Integer(0);
		if (countStr != null && countStr.contains("(")
				&& countStr.contains(")")) {
			countStr = countStr.replace("(", "");
			countStr = countStr.replace(")", "");

			try {
				count = Integer.parseInt(countStr);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return count;

	}
}
