import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class PickAnElementt {
    public static void main(String[] args) {

        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
        pickName(friends, "Z");
        pickName(friends, "N");

    }

    public static void pickName(final List<String>names, final String startingLetter) {
        /* basic
        String foundName = null;
        for(String name : names ){
            if(name.startsWith(startingLetter)) {
                foundName = name;
                break;
            }
        }

        System.out.println(String.format("A name starting with %s : ", startingLetter));

        if(foundName != null) {
            System.out.println(foundName);
        } else {
            System.out.println("No name found");
        }
         */

        /* lambda*/
        final Optional<String> foundName = names.stream().filter(name -> name.startsWith(startingLetter)).findFirst();
        /*
        Optional 클래스는 결과가 없는 경우에 유용하다.
        우연히 NullPointerException이 발생하는 것을 막아주며 "결과가 없다"라는 것을 가능한 출력해서 사용자에게 명확하게 알려준다.
        isPresent() 메서드를 사용하는 경우에 객체가 존재하는지를 알아보고 get()메서드를 사용하여 현재 값을 얻어온다.
        한편 놓친 인스턴스에 대해서는 대신할 값을 제안할 수 있으며 이전 코드에서 봤듯이 orElse() 메서드를 사용하여 가능하다.

        */


        System.out.println(String.format("A name starting with %s : %s", startingLetter, foundName.orElse("No name found")));
        foundName.ifPresent(name -> System.out.println("Hello " + name));




    }
}
