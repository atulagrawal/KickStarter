package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Constant {
	public static final int PROFILE_UPDATER_THREAD_COUNT = 20;
	public static final String KICKSTARTER_BASE_URL = "http://www.kickstarter.com";
	public static final String PROFILE_BASE_URL = KICKSTARTER_BASE_URL + "/profile";
	private static final String categories = "art|comics|dance|design|fashion|film & video|food|games|music|photography|publishing|technology|theater";
	
	public static List<String> getCategoryList()
	{
		List<String> categoryList = new ArrayList<String>();
		String catagoriesArray[] = categories.split("\\|");
		categoryList = Arrays.asList(catagoriesArray);
		return categoryList;
	}
	
	public static List<String> getCategoriesMostFundedURL()
	{
		List<String> urlList =new ArrayList<String>();
		List<String> categoryList = getCategoryList();
		for (String url : categoryList) {
			urlList.add( KICKSTARTER_BASE_URL + "/discover/categories/" + url + "/most-funded/?page=X");
		}
		return urlList;
	}
	
	/*
	 * String t = "categories/art/most-funded";
		String type = "recently-launched";
		String type2 = "ending-soon";
		String type3="categories/music/popular";
		String type4="categories/art/popular";
		String type5="categories/design/popular";
		String type6="cities/los-angeles-ca/recommended";
		String type7="cities/los-angeles-ca/funding";
		String type8 = "cities/los-angeles-ca/successful";
		String type9 = "categories/games/popular";
		String type10= "categories/food/popular";
		String type11= "cities/san-francisco-ca/recommended";
		String pageUrl ="";
		//http://www.kickstarter.com/discover/categories/art/popular?page=
		//String baseUrl = "http://www.kickstarter.com/discover/"+ type11 + "?page=";
		String baseUrl = Constant.KICKSTARTER_BASE_URL + "/projects/search?page=X&term=an";
		
	 */
}
