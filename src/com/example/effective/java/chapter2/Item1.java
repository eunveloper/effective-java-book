package com.example.effective.java.chapter2;

// 생성자 대신 정적 팩터리 메서드를 고려하라.
public class Item1 {

    private int value;

    private String name;

    // 생성자
    public Item1() {}

    public Item1(int value) {
        this.value = value;
    }

    public void example() {
        // Boolean 객체가 가지고 있는 정적 팩터리 메서
        // 기본 boolean 값을 받아 박싱 클래스를 리턴
        Boolean value = Boolean.valueOf(false);
    }

    // 장점1. 이름을 가질 수 있다.
    public static Item1 multiply(int value, int multiply) {
        // 벨류를 곱한다는 의미.
        return new Item1(value * multiply);
    }

    public Item1 (int value, int multiply) {
        // 이런 생성자가 있다면 이 생성자가 벨류를 곱한다는걸 클라이언트가 알 수 있을까?
        // 생성자의 행위를 내부 코드를 들어가봐야 알 수 있다.
        this.value = value * multiply;
    }

    // 둘중에 어떤 생성자가 무슨일을 하는지 클라이언트가 알 수 있을까?
    // 또한 오버로딩의 제한으로 파라미터 순서와 타입이 동일하게 동일한 생성자를 만들 수 없다.
    // 그래서 파라미터 순서를 변경하므로서 기능을 제한했다.
    // 이걸 만든 나 말고 이걸 사용할 사람은 이걸 알 수 있을까?
    public Item1 (int value, String numberStr) {
        this.value = value + Integer.parseInt(numberStr);
    }

    // 이건 그냥 벨류를 세팅하고 그 벨류에 이름을 저장하는게 끝
    public Item1 (String name, int value) {
        this.name = name;
        this.value = value;
    }

    public static Item1 sumNumbericString(int value, String numberStr) {
        return new Item1(value + Integer.parseInt(numberStr));
    }

    public static Item1 init(int value, String name) {
        return new Item1(name, value);
    }

    // 장점2. 호출될 떄마다 인스턴스를 새로 생성하지는 않아도 된다.

    // 장점3. 반호나 타입의 하위 타입 객체를 반환할 수 있는 능력이 있다.

    // 장점4. 입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.

    // 장점5. 정적 팩터리 메스드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.

    // 단점1. 상속을 하려면 public이나 protected 생성자가 필요하니 정적 팩터리 메서드만 제공하면 하위 클래스를 만들 수 없다.

    // 단점2. 정적 팩터리 메서드는 프러그래머가 찾기 어렵다.

}
