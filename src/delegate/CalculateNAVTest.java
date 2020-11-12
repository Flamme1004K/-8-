package delegate;

import java.math.BigDecimal;

public class CalculateNAVTest {

    public static void main(String[] args) {
        computeStockWorth();
    }

    public static void computeStockWorth() {
        final CalculateNAV calculateNAV = new CalculateNAV(ticker -> new BigDecimal("6.01"));

        final CalculateNAV calculateNAV2 = new CalculateNAV(YahooFinance::getPrice);
        BigDecimal expected = new BigDecimal("6010.00");
        System.out.println(String.format("100 shares of Google worth : $%.2f",  calculateNAV2.computeStockWorth("G00G", 100)));
        System.out.println(calculateNAV.computeStockWorth("G00G", 1000).compareTo(expected));
    }
}
