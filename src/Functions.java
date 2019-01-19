import java.text.DecimalFormat;
import java.util.ArrayList;

public class Functions {
	// Get Rolling Average of a selected number of days
	public static ArrayList<String> getAverage(ArrayList<ArrayList<String>> totalTable, int days) {
		int entryDates = totalTable.size();
		int entryTypes = totalTable.get(0).size();
		ArrayList<String> output = new ArrayList<String>();
		DecimalFormat df = new DecimalFormat("#.000");
		
		output.add(days+" Day Average");
		
		Double tempSum;
		// Column=1 skips the [Date] column
		for (int column=1; column<entryTypes; column++) {
			tempSum = 0.0;
			System.out.println(totalTable.get(column));	// DEBUG
			for (int row=0; row<days; row++) {
				tempSum += Double.parseDouble(totalTable.get(entryDates-row-1).get(column));
			}
			output.add(String.valueOf(df.format(tempSum/days)));
		}
		return output;
	}
	public static ArrayList<String> getChange(ArrayList<ArrayList<String>> totalTable, int days) {
		int entryDates = totalTable.size();
		int entryTypes = totalTable.get(0).size();
		ArrayList<String> output = new ArrayList<String>();
		DecimalFormat df = new DecimalFormat("#.00");
		
		output.add(days+" Day Change");
		
		Double tempDelta;
		ArrayList<String> currentDay = totalTable.get(entryDates-1);
		ArrayList<String> earlierDay = totalTable.get(entryDates-1-days);
		for (int column=1; column<entryTypes; column++) {
			tempDelta = 
					Double.parseDouble(currentDay.get(column)) -
					Double.parseDouble(earlierDay.get(column));
			output.add(df.format(tempDelta));
		}
		System.out.println(currentDay);	// DEBUG
		System.out.println(earlierDay);	// DEBUG
		return output;
	}

}