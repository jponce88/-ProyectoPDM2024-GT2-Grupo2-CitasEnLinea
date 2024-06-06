package sv.edu.ues.fia.eisi.proyectoinnovador.classes;

public class Status {
    private String timestamp;
    private int error_code;
    private String error_message;
    private int elapsed;
    private int credit_count;
    private String notice;
    private int total_count;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public int getElapsed() {
        return elapsed;
    }

    public void setElapsed(int elapsed) {
        this.elapsed = elapsed;
    }

    public int getCredit_count() {
        return credit_count;
    }

    public void setCredit_count(int credit_count) {
        this.credit_count = credit_count;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }
}
