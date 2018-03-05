package http.tct.jony.com.tcthttp;

/**
 * Created by Jony on 2018/3/4 0004.
 * Email: litaocreate@gmail.com
 * Github: https://github.com/Jony-Li/
 */

public class ResponseData {
    private String resultCode;
    private String reason;
    private String error_code;
    private String result;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "resultCode='" + resultCode + '\'' +
                ", reason='" + reason + '\'' +
                ", result='" + result + '\'' +
                ", error_code='" + error_code + '\'' +
                '}';
    }
}
