package delegate2;

import java.awt.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

@SuppressWarnings("unckeked")
public class Camera {

    // 이 필터 함수는 Color를 받아서 Color를 반환한다.
    // 다양한 필터를 얻기 위해서는 디폴트 메서드(default method)라고 하는 특별한 타입에 속한 메서드를 사용해야 한다.
    // Java8의 기능임.
    // 추상 메서드에 덧붙여서 인터페이스는 구현 부분이 있는 메서드를 갖게 되며 default로 마크된다.
    // 이 메서드는 인터페이스를 구현하는 클래스에 자동으로 추가된다.
    // 새로운 메소드를 갖는 기존 클래스의 추가 작업 없이 기능을 강화할 수 있도록 한다.
    // 또한 이 인터페이스는 정적(static) 메서드이다.
    // apply() 추상 메서드의 추가와 함께 Function 인터페이스는 디폴트 메서드이며 compose()는 여러 Function을 조합하거나 연결한다.
    // compose() method는 두 개의 Function을 조합하거나 연결한다.


    private Function<Color, Color> filter;

    public void setFilters(final Function<Color, Color>... filters) {
        filter = Stream.of(filters).reduce((filter, next) -> filter.compose(next)).orElse(color -> color);
    }

    public Camera() {setFilters();}

    public Color capture(final Color inputColor) {
        final Color processedColor = filter.apply(inputColor);
        return processedColor;
    }

    public static void main(String[] args) {
        final Camera camera = new Camera();
        final Consumer<String> printCaptued = (filterInfo) -> System.out.println(
                String.format("with %s: %s",filterInfo,camera.capture(new Color(200,100,200))));

        printCaptued.accept("no filter");

        camera.setFilters(Color::brighter);
        printCaptued.accept("brighter filter");

        camera.setFilters(Color::darker);
        printCaptued.accept("darker filter");

        camera.setFilters(Color::brighter, Color::darker);
        printCaptued.accept("brighter & darker");



    }
}
