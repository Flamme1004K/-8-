import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Collection {
    public static void main(String[] args) {
        //람다의 재사용성
        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

        final List<String> editors = Arrays.asList("Brian", "Jackie", "John", "Mike");

        final List<String> comrades = Arrays.asList("Kate", "Ken", "Nick", "Paula", "Zach");

        /*
        final long countFirendsStartN = friends.stream().filter(name -> name.startsWith("N")).count();
        final long countEditorsStartN = editors.stream().filter(name -> name.startsWith("N")).count();
        final long countComradesStartN = comrades.stream().filter(name -> name.startsWith("N")).count();
         */

        //filter 메서드는 이전 예제에서 람다 표현식을 전송받아 처리하는 메서드였으며, java.util.function.Predicate
        // 함수형 인터페이스에 대한 레퍼런스를 받는다.

        //자바 컴파일러는 주어진 람다 표현식에서 Predicate의 test()메서드의 구현을 합성한다.
        //인수를 정의하는 위치에서 메서드를 합성하는 것보다 더 명확하게 작업을 할 수 있다.
        //Predicate 타입의 명시적 레퍼런스에 저장할 수 있고 저장된 람다 표현식을 함수에 전달할 수 있다.

        // DRY(Don't Repeat Youreself-DRY-principle )
        final Predicate<String> startWithN = name -> name.startsWith("N");

        final long countFirendsStartN = friends.stream().filter(startWithN).count();
        final long countEditorsStartN = editors.stream().filter(startWithN).count();
        final long countComradesStartN = comrades.stream().filter(startWithN).count();

        //새로운 변수를 사용하면 의도하지 않게 발생하는 중복 코드를 효과적으로 제거할 수 있다.






    }
}
