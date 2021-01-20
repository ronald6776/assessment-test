package pageObjectRepository;

import java.io.File;
import java.util.Map;
import utilities.JsonUtils;
import utilities.LoggerUtils;

/**
 * <h1>Assessment Test</h1>
 * Page Object Repository to get the page elements from the Page Object Json file
 * <p>
 * 
 *
 * @author  Ronald Nickson Pasteen Baskar
 * @version 1.0
 * @since   2021-01-18
 */

public class PageObjectRepo {

	public static String getPageObjets(String elementName) {
		LoggerUtils.info("Entry getPageObjects method");
		Map<String, String> pageElementsMap=null;
		File f1=null;
		try {
			f1=new File(System.getProperty("user.dir")+"/src/test/resources/pageObjectRepository/objectRepository.json");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		pageElementsMap=JsonUtils.parseJsonToMap(f1);
		LoggerUtils.info("Identifier for the page element is: "+pageElementsMap.get(elementName));
		LoggerUtils.info("Exit getPageObjects method");
		return pageElementsMap.get(elementName);
	}

}
