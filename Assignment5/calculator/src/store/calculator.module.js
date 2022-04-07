import CalcService from "@/services/calculator.service";

const historyItem = JSON.parse(localStorage.getItem("historyItem"));
const history = JSON.parse(localStorage.getItem("history"));

export const calc = {
  namespaced: true,

  state: {
    historyItem,
    history,
  },

  actions: {
    calculate({ commit }, equation) {
      return CalcService.calculate(equation).then(
        (response) => {
          commit("calcSuccess", response);
          return Promise.resolve(response);
        },
        (error) => {
          commit("calcFailure");
          return Promise.reject(error);
        }
      );
    },

    getHistory({ commit }, username) {
      return CalcService.getHistory(username).then(
        (history) => {
          commit("historySuccess", history);
          return Promise.resolve(history);
        },
        (error) => {
          commit("historyFailure", error);
          return Promise.reject(error);
        }
      );
    },
  },

  mutations: {
    calcSuccess(state, response) {
      state.historyItem = response;
    },
    calcFailure(state) {
      state.historyItem = null;
    },
    historySuccess(state, history) {
      state.history = history;
    },
    registerFailure(state) {
      state.history = null;
    },
  },
};
