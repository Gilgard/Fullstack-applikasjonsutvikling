<template>
  <div class="home">
    <h1>Home</h1>
    <h3 :v-if="loginStatus == true">Welcome, {{this.$store.state.username}}</h3>
    <button :v-if="loginStatus == false" @click="onclick()">Log in</button>
  </div>
</template>

<script>
import router from '@/router'

export default {
  name: 'Home',
  methods: {
    onclick() {
      router.push({ name: 'LoginView' })
    }
  }
}
</script>
