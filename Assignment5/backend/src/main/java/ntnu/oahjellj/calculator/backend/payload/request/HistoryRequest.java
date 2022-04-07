package ntnu.oahjellj.calculator.backend.payload.request;

import javax.validation.constraints.NotBlank;

public class HistoryRequest {
    @NotBlank
    private String username;

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
}
