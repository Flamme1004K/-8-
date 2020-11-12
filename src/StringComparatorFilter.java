public class StringComparatorFilter {
    public static void main(String[] args) {
        //람다표현식과 메서드 레퍼런스를 사용하여 스트링을 이터레이션하고 Comparators를 구현하며, 디렉터리에 있는 파일들을 리스트하고
        // 파일과 디렉터리의 변경 상태를 모니터링할 수 있다.

        //스트링 잉터레이션
        final String str = "w00t";

        str.chars()
                .filter(ch -> Character.isDigit(ch))
                .mapToObj(ch -> (char) ch)
                .forEach(System.out::println);

    }
}
