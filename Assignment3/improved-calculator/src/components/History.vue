<template>
<div id="history">
    <h1>History:</h1>
    <button @click="clearHistory()">Clear</button>
    <li v-for="item in history" :key="item">{{ item }}</li>
</div>
</template>

<script>
export default {
    name: "History",
    data(){
        return {
            history
        };
    },
    props: [history],
    methods: {
        clearHistory() {
            this.history = [];
        },
    },
}
</script>

<style scoped>
#history {
  width: 100%;
  max-width: 400px;
  margin: 0 auto;
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: auto;
  padding: 10px;
}

.h1 {
  display: inline;
  float: center;
}

.button {
  max-width: 120px;
  float: right;
}

li {
  font-size: 1.5em;
}
</style>