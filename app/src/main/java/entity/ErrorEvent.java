package entity;

/**
 * Created by ipicomar on 12/03/2018.
 */

public class ErrorEvent {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ErrorEvent{" +
                "message='" + message + '\'' +
                '}';
    }
}
