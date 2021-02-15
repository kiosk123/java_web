package spms.util;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Set;

import javax.servlet.ServletRequest;





public class ServletRequestDataBinder {
	
	/*프런트 컨트롤러에서 호출하는 메서드
	 *요청 매개변수의 값과 데이터 이름, 데이터 타입을 받아서 데이터 객체(Member,String,Date..등)
	 *을 만드는 일을 한다. 
	 */
	public static Object bind(
			ServletRequest request,Class<?> dataType,String dataName)throws Exception{		
		
		//dataType이 기본 타입일때 즉시 객체를 생성하여 반환
		if(isPrimitiveType(dataType)){
			return createValueObject(dataType, request.getParameter(dataName));
		}
		
		/*Member타입처럼 기본타입이 아닌 경우는 요청 매개변수의 이름과 일치하는 셋터 메서드를 찾아 호출
		
		  1.먼저 요청 매개변수의 이름 목록을 얻는다.*/
		Set<String> paramNames=request.getParameterMap().keySet();
		
		/*2.값을 저장할 객체를 생성한다. Class의 newInstance메소드를 사용하면 해당 클래스의 인스턴스를
		      얻을 수 있다.*/
		Object dataObject=dataType.newInstance();
		Method m=null;
		
		/*3.매개변수 목록이 준비되어 있으면 for 반복문을 실행한다.*/
		for(String paramName:paramNames){
			
			/*4.데이터 타입 클래스에서 매개변수 이름과 일치하는 프로퍼티(세터메소드)를 찾는다.*/
			m=findSetter(dataType, paramName);
			
			/*5.세터 메서드(프로퍼티)를 찾았으면 이전에 생성한 dataObject에 대해 호출한다.*/
			if(m!=null){
				/*m.getParameterTypes()[0] 세터메소드의 첫번째 파라미터 타입을 의미
				   세터 메소드를 찾았으면 이전에 생성한 dataObject에 대해 호출한다.*/
				
				m.invoke(dataObject, createValueObject(m.getParameterTypes()[0],
						request.getParameter(paramName)));
			}
			
		}
		
		return dataObject;
	}
	
	//메소드 로직안에 있는 타입들을 기본타입으로 간주하며 true를 반환
	private static boolean isPrimitiveType(Class<?> type){
		if(type.getName().equals("int")||type==Integer.class||
		   type.getName().equals("float")||type==Float.class||
		   type.getName().equals("double")||type==Double.class||
		   type.getName().equals("long")||type==Long.class||
		   type.getName().equals("boolean")||type==Boolean.class||
		   type==Date.class||type==String.class){
			return true;
		}
		return false;
	}
	
	//기본 타입 객체를 생성할때 호출 요청매개변수의 값을로부터 String이나 Date등의 기본타입 객체를 생성
	private static Object createValueObject(Class<?> type, String value){
		if(type.getName().equals("int")||type==Integer.class){
			return new Integer(value);
		}else if(type.getName().equals("float")||type==Float.class){
			return new Float(value);
		}else if(type.getName().equals("double")||type==Double.class){
			return new Double(value);
		}else if(type.getName().equals("long")||type==Long.class){
			return new Long(value);
		}else if(type.getName().equals("boolean")||type==Boolean.class){
			return new Boolean(value);
		}else if(type==Date.class){
			return java.sql.Date.valueOf(value);
		}else{
			return value;
		}
	}
	
	//클래스타입에 맞는 프로퍼티를 찾는다.
	private static Method findSetter(Class<?> type,String name){
		Method[] methods=type.getMethods();
		String propName=null;
		for(Method m:methods){
			if(!m.getName().startsWith("set")) continue;
			
			propName=m.getName().substring(3);
			if(propName.toLowerCase().equals(name.toLowerCase())){
				return m;
			}
		}
		return null;
	}
}
