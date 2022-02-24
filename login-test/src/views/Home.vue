<template>
<div class="button-block">
  <button v-if="!$auth.isAuthenticated" @click="login" class="button is-xl is-dark">Sign Up to Browse Events</button>
  <h3 v-if="$auth.isAuthenticated" class="is-size-3 has-background-dark welcome">Welcome, {{ $auth.user.name }}!</h3>
</div>
</template>

<script>
export default {
  name: 'home',
  methods: {
    // Log the user in
    login() {
      this.$auth.loginWithRedirect();
    },
  },
};
</script>
