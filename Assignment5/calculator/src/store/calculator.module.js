import CalcService from "@/services/calculator.service";
import userService from "@/services/user.service";

const historyItem = JSON.parse(localStorage.getItem("historyItem"));
const history = JSON.parse(userService.getUserBoard());

export const calc = {
  namespaced: true,
  state: {
    historyItem,
    history,
  },
  actions: {
    calculate({ commit }, equation) {
      return CalcService.calculate(equation).then(
        (equation) => {
          commit("calcSuccess", equation);
          return Promise.resolve(equation);
        },
        (error) => {
          commit("calcFailure");
          return Promise.reject(error);
        }
      );
    },
  },
  mutations: {},
};
