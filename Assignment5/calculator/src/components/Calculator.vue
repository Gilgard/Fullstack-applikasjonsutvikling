<template>
  <div class="calculator">
    <div class="display">{{ current || "0" }}</div>

    <div class="btn op" @click="clear()">C</div>
    <div class="btn op" @click="invert()">+/-</div>
    <div class="btn op" @click="percentage()">%</div>
    <div class="btn op" @click="setOperator('/')">÷</div>

    <div class="btn" @click="append('7')">7</div>
    <div class="btn" @click="append('8')">8</div>
    <div class="btn" @click="append('9')">9</div>
    <div class="btn op" @click="setOperator('*')">x</div>

    <div class="btn" @click="append('4')">4</div>
    <div class="btn" @click="append('5')">5</div>
    <div class="btn" @click="append('6')">6</div>
    <div class="btn op" @click="setOperator('-')">-</div>

    <div class="btn" @click="append('1')">1</div>
    <div class="btn" @click="append('2')">2</div>
    <div class="btn" @click="append('3')">3</div>
    <div class="btn op" @click="setOperator('+')">+</div>

    <div class="btn zero" @click="append('0')">0</div>
    <div class="btn" @click="dot()">.</div>
    <div class="btn op" @click="equals()">=</div>
  </div>
  <div class="divider"></div>
  <user-history />
</template>

<script>
import UserHistory from "@/components/calc-children/UserHistory.vue";
import CalculatorService from "@/services/calculator.service";

export default {
  name: "CalculatorComponent",
  components: {
    UserHistory,
  },
  data() {
    return {
      user: this.$store.state.auth.user,
      current: "",
      previous: null,
      operator: null,
      operatorClicked: false,
      operatorSign: "",
      toClear: false,
    };
  },
  methods: {
    clear() {
      this.current = "";
      this.toClear = false;
    },

    invert() {
      this.current = parseFloat(this.current) * -1;
    },

    percentage() {
      this.current = parseFloat(this.current) / 100;
    },

    append(number) {
      if (this.toClear) this.clear();
      this.current = `${this.current}${number}`;
    },

    dot() {
      if (this.current.indexOf(".") === -1 && !this.current.includes("."))
        this.append(".");
    },

    setPrevious() {
      this.previous = this.current;
      this.current = this.operatorSign;
      this.operatorClicked = true;
      this.clear();
    },

    setOperator(sign) {
      this.operatorSign = sign;
      this.operatorClicked = true;
      this.setPrevious();
    },

    equals() {
      if (this.operatorClicked) {
        let equation =
          this.previous + " " + this.operatorSign + " " + this.current;
        let response = CalculatorService.calculate(
          equation,
          this.user.username
        );
        console.log(response);
        //UserHistory.methods.addLocalHistory(response);
        this.current = "";
        this.operatorClicked = false;
        if (equation == null) {
          console.log("No equation found");
          return;
        }
      }
    },
  },
};
</script>

<style scoped>
.divider {
  height: 20px;
}

h1 {
  justify-self: left;
}

button {
  align-self: center;
  justify-self: right;
  width: 60%;
  height: 60%;
  background-color: #1c458a;
  border: none;
  color: white;
}

.btn {
  display: grid;
  align-items: center;
  justify-items: center;
  background-color: #eee;
}

.op {
  background-color: #1c458a;
  color: #eee;
}

body {
  max-width: 80%;
}

.calculator {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  grid-auto-rows: minmax(50px, auto);
  grid-gap: 2px;
  width: 100%;
  max-width: 400px;
  margin: 0 auto;
  font-size: 2em;
}

.display {
  font-size: 2;
  color: white;
  grid-column: 1 / 5;
  background-color: rgb(196, 196, 196);
  align-self: center;
  justify-self: center;
  width: 100%;
  height: 100%;
}

.zero {
  grid-column: 1 / 3;
}
</style>
