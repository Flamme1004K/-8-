import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class CollectionLexicalScopingAndClosures {
    public static void main(String[] args) {

        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

        final List<String> editors = Arrays.asList("Brian", "Jackie", "John", "Mike");

        final List<String> comrades = Arrays.asList("Kate", "Ken", "Nick", "Paula", "Zach");

        // 렉시컬 스코프(Lexical Scoping)와 클로저(Closures) 사용하기

        // 람다 표현식에서의 중복

        final Predicate<String> startWithN = name -> name.startsWith("N");
        final Predicate<String> startWithB = name -> name.startsWith("B");

        /*
        final long countFriendsStartN = friends.stream().filter(startWithN).count();
        final long countFriendsStartB = friends.stream().filter(startWithB).count();
         */
        //두개의 Predicate. N을 위한 검색 B를 위한 검색에 대한 코드는 중복을 일으킨다.

        // 렉시컬 스코프로 중복 제거하기
        //Predicate 란? 컬렉션에 있는 컨텍스트 엘리먼트를 표현하기 위해 하나의 파라미터를 받는 함수를 수신하며 boolean 결과를 리턴하는 것.

        /*
        final long countFriendsStartN = friends.stream().filter(checkIfStartsWith("N")).count();
        final long countFriendsStartB = friends.stream().filter(checkIfStartsWith("B")).count();
         */

        final Function<String , Predicate<String>> startWithLatter =
                (String letter) -> {
                    Predicate<String> checkStarts = (String name) -> name.startsWith(letter);
                    return  checkStarts;
                };

        // 이 람다 표현식은 정적 메서드 checkIfStartsWith()를 대체하며 함수 안에 존재한다.
        // startsWithLetter 변수는 스트링을 인수로 갖는 Function을 참조하며 Predicate를 리턴한다.

        final Function<String, Predicate<String>> startWithLatterBefore =
                (String letter) -> (String name) -> name.startsWith(letter);

        final Function<String, Predicate<String>> startWithLatterBefore2 = letter -> name -> name.startsWith(letter);

        final long countFriendsStartN = friends.stream().filter(startWithLatterBefore2.apply("N")).count();
        final long countFriendsStartB = friends.stream().filter(startWithLatterBefore2.apply("B")).count();

        // Predicate<T>는 타입 T인 파라미터 하나를 받아 체크한 결과에 대해 알려주기 위해 boolean 결과를 리턴한다.
        // 후보 엘리먼트를 평가하는 filter()와 같은 메서드들은 파라미터로 Predicate를 갖는다.

        // Function<T,R)은 타입 T를 파라미터로 갖고 타입 R을 결과로 리턴하는 함수이다.
        // 이것은 항상 boolean 값을 리턴하는 Predicate보다 더 일반적이다.

        // 입력을 다른 값으로 변경하고 싶은 경우에는 Function을 사용하며, 따라서 map() 메서드가 Function을 파라미터로 사용하는 것도
        // 꽤 논리적이다.


    }

    // 렉시컬 스코프
    public static Predicate<String> checkIfStartsWith(final String letter) {
        return name -> name.startsWith(letter);
        //변수 letter의 범위는 이 어노니머스(anonymous) 함수의 범위에 있지 않기 때문에 람다 표현식의 정의 에 대해 범위를 정하고
        // 그 범위 안에서 변수 letter를 찾는다.
        //레시컬 스코프는 캐시해 두었다가 나중에 다른 컨텍스트에서 사용하는 강력한 기술이다.
        //레이스 컨디션을 피하기 위해, 스코프 내에서 엑세스하는 지역 변수는 초기화된 후에 변경하면 안 된다.
        //이 변수에 대한 변경 시도는 컴파일 오류를 발생시킨다.

        // final 로 되어 있는 변수를 사용하면 이 문제를 해결할 수 있지만 자바에서는 반드시 그렇게 해야하는 것은 아니다.
        // 자바는 두가지가 필요하다
        // 1. 엑세스된 변수는 람다 표현식이 정의되기 전에 변수를 사용하는 메서드에서 초기화되어야 한다.
        // 2. 이 변수들의 값은 어느 곳에서도 변경할 수 없다.
    }


}
