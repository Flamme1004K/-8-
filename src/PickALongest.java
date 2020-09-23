import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PickALongest {

    /*

    엘리먼트들을 비교하고 컬렉션에서 하나의 값으로 연산하는 기술

    */
    public static void main(String[] args) {

        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

        System.out.println("Total number of characters in all name : " +
                friends.stream().mapToInt(name -> name.length())
                .sum()
                );

        //map 오퍼레이션(mapToInt(), mapToDouble() 등 IntStream과 DoubleStream과 같은 특정 타입 스트림을 생성한다.)
        //의 다양한 메서드 중에서 mapToInt() 메서드를 사용한 후에 합계의 결과 길이를 리듀스(reduce)시킨다.

        //sum(), max(), min(), sorted(), average() 메서드가 있다.

        /* 맵리듀스 패턴(MapReduce pattern) */
        // reduce() 메서드를 사용하여 두 개의 엘리먼트를 서로 비교하고 컬렉션에 남아있는 엘리먼트와의 비교를 통해 결과를 얻어낸다.
        // reduce() 메서드는 다른 고차 함수들과 마찬가지로 컬렉션을 이터레이션한다.
        // 게다가 리턴된 람다 표현식으로 연산 결과를 얻어낸다.

        final Optional<String> aLongName =
                friends.stream()
                .reduce((name1, name2) -> name1.length() >= name2.length() ? name1 : name2);

        aLongName.ifPresent(name ->
                        System.out.println(String.format("A longest name : %s", name))
                );

        // reduce() 메서드에 전달된 람다 표현식은 name1 과 name2라는 두 개의 파라미터를 갖는다.
        // reduce() 메서드는 우리가 원하는 의도에 대해 알지 못한다. 문제는 이 메서드가 우리가 전달한 람다 표현식과 분리되어 있다는 것이다.
        // 스트레티지 패턴(Strategy Pattern)의 경량화 애플리케이션이다.
        // reduce() 메서드는 컬렉션을 이터레이션한다.
        /*
            1. 리스트에 있는 처음 두개 엘리먼트를 사용하여 람다 표현식을 호출한다.
            2. 호출해서 name1은 이전 호출의 결과이며 name2는 컬렉션의 세번째 엘리먼트이다.
            람다 표현식의 호출은 컬렉션에 엘리먼트가 남아있는 동안 계속된다.
            마지막 호출에 대한 결과는 reduce() 메서드 호출의 전체결과로 리턴한다.

            reduce() 메서드의 결과는 Optional이다
            그 이유는 결과가 비워져 있을 수도 있기 때문이다.
            리스트가 오직 하나의 엘리먼트만을 갖고 있다면 reduce()는 그 엘리먼트를 리턴하고 전달한 람다 표현식은 호출되지 않는다.

        */

        final String steveOrLonger =
                friends.stream()
                    .reduce("Steve", (name1, name2) -> name1.length() >= name2.length() ? name1 : name2);

        System.out.println(steveOrLonger);
        // 이렇게 하면 Steve가 리턴된다.
        // reduce()의 이 버전은 컬렉션이 비어있다고 하더라도 Optional을 리턴하지 않는다.
        // 그 이유는 컬렉션이 비어 있다면 기본값이 리턴되기 때문이다. 따라서 값이 없거나 존재하지 않는다하더라도 걱정할 필요가 없다.








    }

}
