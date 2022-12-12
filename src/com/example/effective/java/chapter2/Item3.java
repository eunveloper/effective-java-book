package com.example.effective.java.chapter2;

// private 생성자나 열거 타입으로 싱글턴임을 보증하라
public class Item3 {

    // 싱글턴? 인스턴스를 오직 하나만 생성할 수 있는 클래스
    // 언제? 무상태 객체나 설계상 유일해야 하는 시스템 컴포넌트
    // 우리가 자주 사용하는 Spring 프레임워크도 싱글턴을 많이 사용한다!

    // 1. public static final 필드 방식의 싱글턴
    public static final Item3 INSTANCE = new Item3();
    // Item3.INSTANCE 가 static 변수로서 1회 초기화 될때만 생성자가 호출된다.
    // 그리고 생성자를 private 접근자로 구성했기 때문에 추가로 호출될 수 없다.
    // 이러한 방식으로 싱글턴을 지원한다.
    private Item3() {
        // 리플렉션을 사용하면 private 생성자가 있어도 생성할 수 있다.
        // 그런경우를 방지하려면 생성자로 인해 두번째 객체가 생성되려 할때 예외를 처리하면 된다.
        if (INSTANCE != null) {
            throw new AssertionError();
        }
    }

    public void function() {}

    public class Item3Test {
        public void function() {
            // public 접근제어자로 생성했기 때문에 어디서든 접근할 수 있다.
            INSTANCE.function();
        }
    }

    // 2. 정적 팩터리 방식의 싱글턴
    private static final Item3 INSTANCE2 = new Item3();
    public static Item3 getInstance() {
        // 클라이언트가 직접 인스턴스를 가져다 쓰지 않는다.
        // private 접근제어자를 통해 직접 접근하지 않고 메소드를 통해 항상 같은 객체를 리턴하기 떄문에 싱글턴을 보장한다.
        return INSTANCE2;
    }
    // 장점?
    // - 언제든지 싱글턴이 아니게 변경할 수 있다.
    // static 변수 지우고
    // return new Item3(); 하면 끝!

    // - 정적 팩터리를 제네릭 싱글턴 팩터리로 만들수 있다. (아이템 30)
    // - 정적 팩터리의 메서드를 참조를 공급자로 사용할 수 있다는 점이다. (아이템 43, 44)

    private Object readResolve() { // (아이템 89)
        // 싱글턴 클래스를 직렬화 하려면 해당 메소드를 꼭 생성해 줘야 한다.
        // 직렬화된 싱글턴 클래스를 역 질렬화할때 새로운 인스턴스가 만들어지기 때문이다.
        // 실제 싱글턴 클래스를 반환! 가짜 클래스는 가비지 컬렉터에 맡긴다.
        return INSTANCE2;
    }


    // 3. 열거 타입 방식의 싱글턴
    // enum 은 기본적으로 serializable 하다.
    public enum Item3Enum {
        INSTANCE;
        public void function() {}
    }
    // 더 간결하고 기본적으로 직렬화 하기때문에 신경 쓰지 않아도 되고
    // 리플렉션 공격도 방지 할 수 있다.
    // 상속이 필요한 구조라면 Enum을 사용할 수 없지만 그 외에는 좋은 방식이다.

}
