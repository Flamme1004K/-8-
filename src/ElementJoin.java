import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ElementJoin {

    /*

     join() 함수가 없다면 간결하고 우아하게 만든 코드의 장점을 발휘하지 못한다.

     */

    public static void main(String[] args) {

        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

        for (String name : friends) {
            System.out.println(name + ", ");
        }
        System.out.println();

        for (int i = 0; i < friends.size() - 1; i++) {
            System.out.println(friends.get(i) + ", ");
        }
        if(friends.size() > 0) {
            System.out.println(friends.get(friends.size() - 1));
        }

        // --------> join() 함수 이용

        System.out.println(String.join(", ", friends));

        // reduce() 메서드를 사용하여 엘리먼트를 하나의 스트링으로 합쳤지만, 아직 해야할 것들이 남아있다.
        // JDK는 collect()라고 하는 컨비니언스 메서드(covenience method)를 제공한다.
        // 이 메서드는 값을 타깃으로 값을 모으는 리듀스(reduce)의 다른 형태이다.

        // collect() 메서드는 리덕션(reduction)이지 실제 구현을 위임하거나 타깃이 컬렉터가 되는 것이 아니다.

        System.out.println(
                friends.stream()
                .map(String::toUpperCase)
                .collect(Collectors.joining(", "))
        );

        // 변경된 리스트에서 collect()를 호출해서 그 결과를 joining() 메서드에 의해 리턴된 컬렉터에 제공한다.
        // 이 메서드는 Collectors 유틸리티 클래스에 있는 정적 메서드다.
    }
}
