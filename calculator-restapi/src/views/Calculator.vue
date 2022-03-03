<template>
  <div class="calculator">
    <div class="display">{{ current || "0" }}</div>

    <div class="btn op" @click="clear()">C</div>
    <div class="btn op" @click="invert()">+/-</div>
    <div class="btn op" @click="percentage()">%</div>
    <div class="btn op" @click="divide()">÷</div>

    <div class="btn" @click="append('7')">7</div>
    <div class="btn" @click="append('8')">8</div>
    <div class="btn" @click="append('9')">9</div>
    <div class="btn op" @click="multiply()">x</div>

    <div class="btn" @click="append('4')">4</div>
    <div class="btn" @click="append('5')">5</div>
    <div class="btn" @click="append('6')">6</div>
    <div class="btn op" @click="subtract()">-</div>

    <div class="btn" @click="append('1')">1</div>
    <div class="btn" @click="append('2')">2</div>
    <div class="btn" @click="append('3')">3</div>
    <div class="btn op" @click="add()">+</div>

    <div class="btn zero" @click="append('0')">0</div>
    <div class="btn" @click="dot()">.</div>
    <div class="btn op" @click="equals()">=</div>
  </div>
  <div class="divider"></div>
  <div class="history">
    <h1>History:</h1>
    <button @click="clearHistory()">Clear</button>
    <li v-for="item in history" :key="item">{{ item }}</li>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      current: "",
      previous: null,
      operator: null,
      operatorClicked: false,
      operatorSign: "",
      history: [],
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
      if (this.current.indexOf(".") === -1 && !this.current.includes('.')) this.append(".");
    },
    setPrevious() {
      this.previous = this.current;
      this.current = this.operatorSign;
      this.operatorClicked = true;
      this.clear();
    },
    divide() {
      this.operator = (a, b) => b / a;
      this.operatorSign = "÷";
      this.setPrevious();
    },
    multiply() {
      this.operator = (a, b) => a * b;
      this.operatorSign = "*";
      this.setPrevious();
    },
    add() {
      this.operator = (a, b) => a + b;
      this.operatorSign = "+";
      this.setPrevious();
    },
    subtract() {
      this.operator = (a, b) => b - a;
      this.operatorSign = "-";
      this.setPrevious();
    },
    equals() {
      if (this.operatorClicked) {
        var equation = this.previous + " " + this.operatorSign + " " + this.current

        if(equation == null) {
          console.log("No equation found");
        }

        axios({
          url: "http://localhost:8080/calculator",
          method: "post",
          params: {
            equation: equation
          }
        })
        .then(response => {
          var result = response.data
          this.pushToHistory(result);
          this.operatorClicked = false;
          this.operatorSign = "";
          this.previous = result;
          this.current = result;
          this.toClear = true;
        })
        .catch(error => console.error(error));
      }
    },
    pushToHistory(result) {
      this.history.push(
        this.previous +
          " " +
          this.operatorSign +
          " " +
          this.current +
          " = " +
          result
      );
      this.operatorSign = "";
    },
    clearHistory() {
      this.history = [];
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.divider {
  height: 20px;
}

.history {
  width: 100%;
  max-width: 400px;
  margin: 0 auto;
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: auto;
  padding: 10px;
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

li {
  list-style-image: url('../assets/point.png');
  font-size: 1.5em;
  grid-column: 1 / 3;
  text-align: left;
}
</style>