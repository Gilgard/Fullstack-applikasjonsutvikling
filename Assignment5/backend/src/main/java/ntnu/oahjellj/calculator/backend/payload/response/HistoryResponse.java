package ntnu.oahjellj.calculator.backend.payload.response;

import java.util.List;

public class HistoryResponse {
    private List<String> history;

    public HistoryResponse(List<String> history) {
        this.history = history;
    }

    public List<String> getHistory() {
        return this.history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }

}
