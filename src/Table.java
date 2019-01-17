import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

public class Table {
	public static String getHeader_xPath(String table_xPath) {
		String row_xPath = String.format("%s/th", table_xPath);
		return row_xPath;
	}
	public static String getRow_xPath(String table_xPath, int row) {
		String row_xPath = String.format("%s[%d]/td", table_xPath, row);
		return row_xPath;
	}
	public static ArrayList<String> getText(List<WebElement> webElementList) {
	    ArrayList<String> stringList = new ArrayList<String>();
	    for (int x = 0; x < webElementList.size(); x++)
	    	stringList.add(webElementList.get(x).getText());
		return stringList;
	}
}