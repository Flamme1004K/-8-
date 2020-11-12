package delegate;

import java.math.BigDecimal;
import java.util.function.Function;

//Delegate(대리자) 예제
public class CalculateNAV {

    public CalculateNAV(final Function<String, BigDecimal> aPriceFinder) {
        priceFinder = aPriceFinder;
    }

    //java.util.function.Function<T,R> 함수형 인터페이스
    // 이 추상 메서드는 값을 얻어와서 다른 타입의 다른 값을 리턴한다.
    private Function<String, BigDecimal> priceFinder;

    public BigDecimal computeStockWorth(final String ticker, final int shares) {
        return priceFinder.apply(ticker).multiply(BigDecimal.valueOf(shares));
    }




}
