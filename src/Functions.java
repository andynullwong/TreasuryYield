import java.text.DecimalFormat;
import java.util.ArrayList;

public class Functions {
	// Get Rolling Average of a selected number of days
	public static ArrayList<String> getRollingAvg(ArrayList<ArrayList<String>> totalTable, int days) {
		int entryDates = totalTable.size();
		int entryTypes = totalTable.get(0).size();
		ArrayList<String> output = new ArrayList<String>();
		DecimalFormat df = new DecimalFormat("#.000");
		
		output.add(days+" Day Avg:");
		
		Double tempSum;
		for (int column=1; column<entryTypes; column++) {
			tempSum = 0.0;
			for (int row=0; row<days; row++) {
				tempSum += Double.parseDouble(totalTable.get(entryDates-row-1).get(column));
			}
			output.add(String.valueOf(df.format(tempSum/days)));
		}
		return output;
	}
	public static Double getAverage(ArrayList<ArrayList<String>> totalTable, String startDate, String endDate) {
		return null;
	}
	public static Double getSlope(ArrayList<ArrayList<String>> totalTable, Double point1, Double point2) {
		return null;
	}
}