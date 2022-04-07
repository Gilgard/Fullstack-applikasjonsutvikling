import axios from "axios";

const API_URL = "https://localhost:8443/api/calc/";

class CalcService {
  calculate(equation) {
    axios
      .get(API_URL + "calculate", {
        equation: equation,
      })
      .then((response) => {
        if (response.data.accessToken) {
          localStorage.setItem("historyItem", JSON.stringify(response.data));
        }
        return response.data;
      })
      .catch((error) => {
        console.error(error);
      });
  }
}

export default new CalcService();
