import java.util.Scanner;

public class Calculator {
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

  // 나눗셈 메소드 선언 (정수 나눗셈)
  public static int divide(int a, int b) {
    if (b == 0) {
      throw new ArithmeticException("0으로 나눌 수 없습니다");
    }
    return a / b;
  }
 
  public static void main(String[] args) {
    System.out.println("계산기 어플");

      // 덧셈 메소드 호출
      Scanner scanner = new Scanner(System.in);
      System.out.print("첫 번째 정수: ");
      int a = scanner.nextInt();
      System.out.print("두 번째 정수: ");
      int b = scanner.nextInt();
      int sum = add(a, b);
      System.out.println(a + " + " + b + " = " + sum);

        // 뺄셈 메소드 호출
      int diff = subtract(a, b);
      System.out.println(a + " - " + b + " = " + diff);

        // 곱셈 메소드 호출
      int prod = multiply(a, b);
      System.out.println(a + " * " + b + " = " + prod);

        // 나눗셈 메소드 호출
      try {
        int quot = divide(a, b);
        System.out.println(a + " / " + b + " = " + quot);
      } catch (ArithmeticException e) {
        System.out.println("나눗셈 오류: " + e.getMessage());
      }

      scanner.close();
  }
}


