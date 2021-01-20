package unitTestScripts;

import java.io.File;
import java.util.Map;
import org.testng.annotations.Test;
import utilities.JsonUtils;
import utilities.LoggerUtils;

/**
 * <h1>Assessment Test</h1>
 * Unit test scripts to validate the Utility Classes of the framework
 * <p>
 * 
 *
 * @author  Ronald Nickson Pasteen Baskar
 * @version 1.0
 * @since   2021-01-18
 */

public class UtilsTest {

	@Test(enabled=false)
	public void jsonStringTraverseTest() {
		File f1=null;
		try {
			f1=new File(System.getProperty("user.dir")+"/src/test/resources/pageObjectRepository/objectRepository.json");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		Map<String, String> l1=JsonUtils.parseJsonToMap(f1);
		LoggerUtils.info("map:"+l1);
		LoggerUtils.info("value:"+l1.get("pictureSources"));
		LoggerUtils.info("Json parsing is Done!!!");
	}

}
