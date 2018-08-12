package com.afterpay.util.fraud;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public final class FraudDetector {

	public static final SimpleDateFormat yyyy_MM_ddTHHmmss = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	public static final SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * 
	 * @param list
	 *  Eg.   10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 10.00
	 *        10d7ce2f43e35fa57d1bbf8b1e2, 2014-05-31T13:16:14, 20.00
	 * @param day (Eg. 2014-04-29)
	 * @param T (Eg. 15.00))
	 * @return
	 * @throws Exception
	 */
    public static String[] detect(String[] list, String day, double T) throws Exception {

    	day = yyyy_MM_dd.format(yyyy_MM_dd.parse(day));

        LinkedHashMap<PanDate, Double> priceAggregation = new LinkedHashMap<PanDate, Double>();

        for (String line : list) {

        	if (line.trim().length() == 0) {
        		continue;
        	}

            Transaction txn = new Transaction(line);

            PanDate panDate = new PanDate(txn.getHPan(), txn.getDate());

            if (priceAggregation.get(panDate) == null) {
                priceAggregation.put(panDate, 0.0);
            }

            double priceSum = priceAggregation.get(panDate) + txn.getPrice();

            priceAggregation.put(panDate, priceSum);
        }

        ArrayList<String> fraudulentList = new ArrayList<String>();

        for (Map.Entry<PanDate, Double> e : priceAggregation.entrySet()) {

            if (e.getKey().getDate().equals(day) && e.getValue() > T) {
                fraudulentList.add(e.getKey().getPan());
            }
        }

        return fraudulentList.toArray(new String[0]);
    }    
}
