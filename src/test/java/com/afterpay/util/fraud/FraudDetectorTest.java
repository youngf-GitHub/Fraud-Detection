package com.afterpay.util.fraud;

import junit.framework.TestCase;

/**
 * Unit test for simple FraudDetector.
 */
public class FraudDetectorTest extends TestCase {

    public void test_No_Fraud() throws Exception {
        
        String[] list = {
                 "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 10.00"
                ,"10d7ce2f43e35fa57d1bbf8b1e2, 2014-05-31T13:16:14, 10.00"
        };

        String day = "2014-04-29";
        Double T = 15.0;
        
        String[] fList = FraudDetector.detect(list, day, T);

        assertTrue(fList.length == 0);
    }

    public void test_One_Fraud() throws Exception {

        String[] list = {
                 "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 10.00"
                ,"10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:16:14, 10.00"
        };

        String day = "2014-04-29";
        double T = 15.0;
        
        String[] fList = FraudDetector.detect(list, day, T);

        assertTrue(fList.length == 1 && fList[0].equals("10d7ce2f43e35fa57d1bbf8b1e2"));
    }

    public void test_One_Fraud_With_MultiPan() throws Exception {

        String[] list = {
                 "90c4ac2f61e49fa57d1bbf8b1e2, 2014-04-29T13:15:54, 10.00"
                ,"10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T16:20:54, 15.01"
        };

        String day = "2014-04-29";
        double T = 15.0;

        String[] fList = FraudDetector.detect(list, day, T);

        assertTrue(fList.length == 1 && fList[0].equals("10d7ce2f43e35fa57d1bbf8b1e2"));
    }

    public void test_Two_Fraud_With_MultiPan() throws Exception {

        String[] list = {
                 "90c4ac2f61e49fa57d1bbf8b1e2, 2014-04-29T13:15:54, 10.00"
                ,"10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T14:16:14, 1.00"
                ,"10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T15:19:14, 14.99"
                ,"90c4ac2f61e49fa57d1bbf8b1e2, 2014-04-29T21:16:14, 4.99"
                ,"34a1ba1f52c30de55a2efc9a2a0, 2014-04-29T23:16:14, 10.10"
                ,"90c4ac2f61e49fa57d1bbf8b1e2, 2014-04-29T23:50:14, 0.02"
                ,"10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-30T23:16:14, 33.01"
        };

        String day = "2014-04-29";
        double T = 15.0;

        String[] fList = FraudDetector.detect(list, day, T);

        assertTrue(fList.length == 2
                && fList[0].equals("90c4ac2f61e49fa57d1bbf8b1e2")
                && fList[1].equals("10d7ce2f43e35fa57d1bbf8b1e2"));
    }
}
