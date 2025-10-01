public class MathTool {
  public static void main(String[] args) {
    System.out.println("계산기 어플");

    // 덧셈 메소드 선언
    public static int add(int a, int b) {
      return a + b;
    }

    // 뺄셈 메소드 선언
    public static int subtract(int a, int b) {
      return a - b;
    }

    // 곱셈 메소드 선언
    public static int multiply(int a, int b) {
      return a * b;
    }

    // 나눗셈 메소드 선언
    public static int divide(int a, int b) {
      return a / b;
    }

    // 덧셈 메소드 호출
    int sum = add(1, 2);
    System.out.println("1 + 2 = " + sum);

    // 뺄셈 메소드 호출
    int diff = subtract(1, 2);
    System.out.println("1 - 2 = " + diff);

    // 곱셈 메소드 호출
    int prod = multiply(1, 2);
    System.out.println("1 * 2 = " + prod);

    // 나눗셈 메소드 호출
    int quot = divide(1, 2);
    System.out.println("1 / 2 = " + quot);

  }
}


