import { createStore } from "vuex";
import { auth } from "./auth.module";
import { calc } from "./calculator.module";

const store = createStore({
  modules: {
    auth,
    calc,
  },
});

export default store;
