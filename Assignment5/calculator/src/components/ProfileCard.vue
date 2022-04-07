<template>
  <div
    class="max-w-sm bg-white rounded-lg border border-gray-200 shadow-md dark:bg-gray-800 dark:border-gray-700"
  >
    <div class="flex flex-col items-center pb-10">
      <img
        class="mb-3 w-24 h-24 rounded-full shadow-lg"
        src=""
        alt="Profile image"
      />
      <h5 class="mb-1 text-xl font-medium text-gray-900 dark:text-white">
        {{ currentUser.username }}
      </h5>
      <span class="text-sm text-gray-500 dark:text-gray-400">
        <strong>Token: </strong>
        {{ currentUser.accessToken.substring(0, 20) }} ...
        {{
          currentUser.accessToken.substr(currentUser.accessToken.length - 20)
        }}</span
      >
      <span class="text-sm text-gray-500 dark:text-gray-400">
        <strong>Id:</strong>
        {{ currentUser.id }}</span
      >
      <span class="text-sm text-gray-500 dark:text-gray-400">
        <strong>Email:</strong>
        {{ currentUser.email }}</span
      >
    </div>
  </div>
</template>

<script>
export default {
  name: "ProfileCard",
  computed: {
    currentUser() {
      return this.$store.state.auth.user;
    },
  },
  mounted() {
    if (!this.currentUser) {
      this.$router.push("/login");
    }
  },
};
</script>
