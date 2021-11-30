package utils;

import java.util.StringJoiner;


public class BasicResponse {

    protected int code;
    protected String message;
    protected String status;

    public BasicResponse(int code, String message, String status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return new StringJoiner(", ", BasicResponse.class.getSimpleName() + "[", "]")
                .add("code=" + code)
                .add("message='" + message + "'")
                .add("status='" + status + "'")
                .toString();
    }
}