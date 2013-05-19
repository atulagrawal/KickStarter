package scraper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import util.Constant;
import util.JSoupUtil;

import db.dao.HibernateDAOFactory;
import db.dao.ProjectDAOImpl;
import db.dto.Profile;
import db.dto.Project;

public class ProjectDetailsScrapper {
	public static void main(String args[]) {
		scrap();
	}

	public static void scrap() {
		HibernateDAOFactory factory = new HibernateDAOFactory();
		ProjectDAOImpl projectDAO = factory.getProjectDAOImpl();
		Set<String> projectUrlSet = new HashSet<String>();
		List<String> urlList = Constant.getCategoriesMostFundedURL();
		ExecutorService projectExecutor = Executors.newFixedThreadPool(10);
		
		for (String baseUrl : urlList) {
			int paginationCount = JSoupUtil.getPorojectPages(baseUrl.replace("X", "" +1));
		//	System.out.println("-----------paginationCount"+paginationCount);
			for (int i = 1; i < paginationCount; i++) {
				Document doc = null;
				String pageUrl = baseUrl.replace("X", "" + i);
				try {
					doc = Jsoup.connect(pageUrl).get();
					Elements projectElements = doc.select("div.project-thumbnail > a");
					if (projectElements != null && projectElements.size() > 0) {
						for (Element element : projectElements) {
							String projectUrl = element.attr("href");
							projectUrl = Constant.KICKSTARTER_BASE_URL + projectUrl.split("\\?")[0];
							if(!projectUrlSet.contains(projectUrl))
							{
								Runnable task = new ProjectDetailsThread(projectUrl);
								projectExecutor.submit(task);
								
							}
						}
					}
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		// This will make the executor accept no new threads
	    // and finish all existing threads in the queue
		projectExecutor.shutdown();
	}

}
