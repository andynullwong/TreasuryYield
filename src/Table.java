import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;



public class Table {
	public static String getRow_xPath(String table_xPath, int row) {
		String row_xPath = String.format("%s[%d]/th", table_xPath, row);
		return row_xPath;
	}

	public static List<String> getText(List<WebElement> webElementList) {
	    List<String> stringList = new ArrayList<String>();
	    for (int x = 0; x < webElementList.size(); x++)
	    	stringList.add(webElementList.get(x).getText());
		return stringList;
	}
}