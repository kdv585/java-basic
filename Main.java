import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
  private static final Pattern SIMPLE_EXPRESSION_PATTERN = Pattern.compile(
    "^\\s*([+-]?\\d+(?:\\.\\d+)?)\\s*([+\\-*/])\\s*([+-]?\\d+(?:\\.\\d+)?)\\s*$"
  );

  public static void main(String[] args) {
    printWelcome();
    try (Scanner scanner = new Scanner(System.in)) {
      while (true) {
        System.out.print("> ");
        if (!scanner.hasNextLine()) {
          break;
        }
        String line = scanner.nextLine();
        if (line == null) {
          break;
        }
        String trimmed = line.trim();
        if (trimmed.equalsIgnoreCase("exit") || trimmed.equalsIgnoreCase("quit")) {
          System.out.println("Bye");
          break;
        }

        if (trimmed.isEmpty()) {
          continue;
        }

        try {
          Double result = evaluateSimpleExpression(trimmed);
          if (result == null) {
            System.out.println("지원 형식: <number> <op> <number> (op: + - * /)");
          } else {
            if (Math.floor(result) == result && result <= Long.MAX_VALUE && result >= Long.MIN_VALUE) {
              System.out.println((long) Math.floor(result));
            } else {
              System.out.println(result);
            }
          }
        } catch (ArithmeticException ae) {
          System.out.println("오류: " + ae.getMessage());
        } catch (Exception e) {
          System.out.println("입력 해석 실패. 예: 3 + 5, 10/2, -2.5 * 4");
        }
      }
    }
  }

  private static void printWelcome() {
    System.out.println("간단 콘솔 계산기 (종료: exit 또는 quit)");
    System.out.println("예: 3 + 5, 10-2, 7*8, 9 / 3, -2.5 * 4");
  }

  private static Double evaluateSimpleExpression(String input) {
    Matcher matcher = SIMPLE_EXPRESSION_PATTERN.matcher(input);
    if (!matcher.matches()) {
      return null;
    }

    double left = Double.parseDouble(matcher.group(1));
    String op = matcher.group(2);
    double right = Double.parseDouble(matcher.group(3));

    switch (op) {
      case "+":
        return left + right;
      case "-":
        return left - right;
      case "*":
        return left * right;
      case "/":
        if (right == 0.0) {
          throw new ArithmeticException("0으로 나눌 수 없습니다");
        }
        return left / right;
      default:
        return null;
    }
  }
}
