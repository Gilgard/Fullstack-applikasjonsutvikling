package ntnu.oahjellj.calculator.backend.payload.request;

public class CalculateRequest {
    private String username;
    private String equation;

    public String getEquation() {
        return this.equation;
    }

    public void setEquation(String equation) {
        this.equation = equation;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
