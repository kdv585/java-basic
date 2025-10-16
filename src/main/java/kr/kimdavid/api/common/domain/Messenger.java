package kr.kimdavid.api.common.domain;

public class Messenger {
    private int code;
    private String message;

    // 기본 생성자
    public Messenger() {
    }

    // 전체 매개변수 생성자
    public Messenger(int code, String message) {
        this.code = code;
        this.message = message;
    }

    // Getter 메서드들
    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    // Setter 메서드들
    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
}
