Daily Rate can be found [here][daily-yield]. These can change during any market day so I began building out a script in Java to pull this data out automatically and run some mathematical analysis on a daily basis. This project can be found on my [Github][project-link]. As of this date I am far from done but the framework is there at least.

When running `Main.java` it will pull the Year-To-Date treasury rates into a 2D ArrayList `totalTable`

``` java
totalTable = "[
[Date, 1 Mo, 2Mo, 3 Mo, 6 Mo, 1 Yr, 2 Yr, 3 Yr, 5 Yr, 7 Yr, 10 Yr, 20 Yr, 30 Yr]
[01/02/19, 2.40, 2.40, 2.42, 2.51, 2.60, 2.50, 2.47, 2.49, 2.56, 2.66, 2.83, 2.97]
[01/03/19, 2.42, 2.42, 2.41, 2.47, 2.50, 2.39, 2.35, 2.37, 2.44, 2.56, 2.75, 2.92]
[01/04/19, 2.40, 2.42, 2.42, 2.51, 2.57, 2.50, 2.47, 2.49, 2.56, 2.67, 2.83, 2.98]
[01/07/19, 2.42, 2.42, 2.45, 2.54, 2.58, 2.53, 2.51, 2.53, 2.60, 2.70, 2.86, 2.99]
[01/08/19, 2.40, 2.42, 2.46, 2.54, 2.60, 2.58, 2.57, 2.58, 2.63, 2.73, 2.88, 3.00]
[01/09/19, 2.40, 2.42, 2.45, 2.52, 2.59, 2.56, 2.54, 2.57, 2.64, 2.74, 2.90, 3.03]
[01/10/19, 2.42, 2.42, 2.43, 2.51, 2.59, 2.56, 2.54, 2.56, 2.63, 2.74, 2.92, 3.06]
[01/11/19, 2.41, 2.43, 2.43, 2.50, 2.58, 2.55, 2.51, 2.52, 2.60, 2.71, 2.90, 3.04]
[01/14/19, 2.42, 2.43, 2.45, 2.52, 2.57, 2.53, 2.51, 2.53, 2.60, 2.71, 2.91, 3.06]
[01/15/19, 2.41, 2.43, 2.45, 2.52, 2.57, 2.53, 2.51, 2.53, 2.61, 2.72, 2.92, 3.08]
[01/16/19, 2.41, 2.40, 2.43, 2.49, 2.57, 2.55, 2.53, 2.54, 2.62, 2.73, 2.92, 3.07]
[01/17/19, 2.41, 2.41, 2.42, 2.50, 2.57, 2.56, 2.55, 2.58, 2.66, 2.75, 2.93, 3.07]
]"
```

Under `Functions.java` I have some dirty methods quickly created to calculate Rolling Averages `getAverage(ArrayList<ArrayList<String>> totalTable, int days)`

```java
// Input
System.out.println(Functions.getAverage(totalTable, 2));
System.out.println(Functions.getAverage(totalTable, 3));

// Output
[2 Day Average, 2.405, 2.405, 2.415, 2.500, 2.585, 2.590, 2.575, 2.600, 2.680, 2.770, 2.940, 3.080]
[3 Day Average, 2.407, 2.403, 2.420, 2.497, 2.580, 2.577, 2.560, 2.580, 2.660, 2.757, 2.933, 3.077]
```

``` java
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
```

Same goes for Prices Changes during a certain date range. `getChange(ArrayList<ArrayList<String>> totalTable, int days)`

```java
// Input
System.out.println(Functions.getChange(totalTable, 2));
System.out.println(Functions.getChange(totalTable, 3));

// Output
[2 Day Change, -.01, .00, -.02, .01, .03, .07, .07, .08, .08, .06, .03, .02]
[3 Day Change, -.01, -.03, -.04, -.02, .03, .09, .09, .09, .09, .07, .03, .01]
```

``` java
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
    System.out.println(currentDay); // DEBUG
    System.out.println(earlierDay); // DEBUG
    return output;
}
```

Before I build out more complex functions I will need to start refractoring the repeative portions of my code. I'll thank myself later.

[cash-equivalents]:     https://www.investopedia.com/terms/c/cashequivalents.asp
[treasury-bill]:        https://www.investopedia.com/terms/t/treasurybill.asp
[flat-yield-curve]:     https://www.investopedia.com/terms/f/flatyieldcurve.asp
[inverted-yield-curve]: https://www.investopedia.com/terms/i/invertedyieldcurve.asp
[daily-yield]:          https://www.treasury.gov/resource-center/data-chart-center/interest-rates/Pages/TextView.aspx?data=yield
[project-link]:         https://github.com/AndyWongDev/TreasuryYield
