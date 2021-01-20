package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.io.FileUtils;

/**
 * <h1>Assessment Test</h1>
 * Html Report Utilities for the BDD framework
 * <p>
 * 
 *
 * @author  Ronald Nickson Pasteen Baskar
 * @version 1.0
 * @since   2021-01-18
 */

public class ReportUtils {

	public static void generateHtmlReport(String fileName,String title,Set<String> links) {
		try {
			File htmlTemplateFile = new File(System.getProperty("user.dir")+"/src/test/resources/reportTemplates/template.html");
			String htmlString = FileUtils.readFileToString(htmlTemplateFile);
			htmlString = htmlString.replace("$title", title);
			htmlString = htmlString.replace("$scanDate", CommonUtils.getCurrentDateTime());
			htmlString = htmlString.replace("$h1", title);

			String body="<table style=\"width:100%\">\r\n" + 
					"			  <tr>\r\n" + 
					"			    <th>S.No#</th>\r\n" + 
					"			    <th>Links</th>\r\n" + 
					"			    <th>Response Code</th>\r\n" + 
					"			  </tr>";
			Iterator<String> it=links.iterator();
			int cnt=0;
			while(it.hasNext()) {
				body=body+"<tr><td align=\"center\">"+(++cnt)+"</td><td align=\"left\">"+it.next()+"</td><td align=\"center\"></td></tr>";
			}
			body=body+"</table>";
			htmlString = htmlString.replace("$body", body);
			File newHtmlFile = new File("target/cucumberHtmlReports/reports/"+fileName+".html");
			FileUtils.writeStringToFile(newHtmlFile, htmlString);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void generateHtmlReport(String fileName, String title, Map<String, Integer> linkResponseMap) {
		try {
			File htmlTemplateFile = new File(System.getProperty("user.dir")+"/src/test/resources/reportTemplates/template.html");
			String htmlString = FileUtils.readFileToString(htmlTemplateFile);
			htmlString = htmlString.replace("$title", title);
			htmlString = htmlString.replace("$scanDate", CommonUtils.getCurrentDateTime());
			htmlString = htmlString.replace("$h1", title);

			String body="<table style=\"width:100%\">\r\n" + 
					"			  <tr>\r\n" + 
					"			    <th>S.No#</th>\r\n" + 
					"			    <th>Links</th>\r\n" + 
					"			    <th>Response Code</th>\r\n" + 
					"			  </tr>";

			int cnt=0;
			for(Entry<String, Integer> entry:linkResponseMap.entrySet()){
				body=body+"<tr><td align=\"center\">"+(++cnt)+"</td><td align=\"left\">"+entry.getKey()+"</td><td align=\"center\">"+entry.getValue()+"</td></tr>";
			}
			body=body+"</table>";
			htmlString = htmlString.replace("$body", body);
			File newHtmlFile = new File("target/cucumberHtmlReports/reports/"+fileName+".html");
			FileUtils.writeStringToFile(newHtmlFile, htmlString);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
