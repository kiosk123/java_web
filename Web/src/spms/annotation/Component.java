package spms.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)

/*애노테이션은 interface앞에 @를 붙인다.*/
public @interface Component {
	/*객체 이름을 저장하는 용도로 사용할 value 기본 속성을 정의한다. 
	 *value속성은 값을 설정할때 이름을 생략할 수 있는 특별한 기능이 있다.*/
	String value() default"";
}
