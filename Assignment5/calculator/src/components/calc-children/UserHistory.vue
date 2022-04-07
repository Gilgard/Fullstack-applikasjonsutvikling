<template>
  <div class="flex justify-center">
    <ul
      v-for="(equation, index) in history"
      :key="equation"
      class="bg-white rounded-lg border border-gray-200 w-96 text-gray-900"
    >
      <li :class="getLiClass(index)">{{ equation }}</li>
    </ul>
  </div>
</template>

<script>
import CalculatorService from "@/services/calculator.service";

export default {
  name: "UserHistory",

  data() {
    return {
      user: this.$store.state.auth.user,
      history: ["No history found!"],
    };
  },
  methods: {
    getLiClass(index) {
      if (index == 0)
        return "px-6 py-2 border-b border-gray-200 w-full rounded-t-lg bg-blue-600 text-white";
      return "px-6 py-2 border-b border-gray-200 w-full";
    },
    addLocalHistory(equation) {
      this.history.push(equation);
    },
  },
  mounted() {
    if (!this.user) {
      this.$router.push("/login");
    }
    let username = this.user.username;
    this.history = CalculatorService.getHistory(username);
  },
};
</script>
