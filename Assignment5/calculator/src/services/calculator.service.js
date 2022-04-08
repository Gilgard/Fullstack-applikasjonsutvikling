import axios from "axios";

const API_URL = "https://localhost:8443/api/calc/";

class CalcService {
  async calculate(equation, username) {
    axios
      .post(API_URL + "calc", {
        equation: equation,
        username: username,
      })
      .then((response) => {
        console.log(response.data.message);
        return response.data.message;
      })
      .catch((error) => {
        console.error(error);
      });
  }
  getHistory(username) {
    axios
      .post(API_URL + "history", { username: username })
      .then((response) => {
        return response.data;
      })
      .catch((error) => {
        console.error(error);
      });
  }
}

export default new CalcService();
