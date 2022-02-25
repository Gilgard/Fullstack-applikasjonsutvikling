<template>
  <div id="container">
    <div id="title">
        <label>Please login!</label>
    </div>
    <div id="username" class="input">
        <label>Username:</label>
        <textarea id="userfield" v-model="username"></textarea>
    </div>
    <div id="password" class="input">
      <label>Password: </label>
      <textarea id="passfield" v-model="password"></textarea>
      <button v-on:click="handleClickSignin_2">Sign in</button>
      <label id="loginstatusLabel">{{loginStatus}}</label>
    </div>
  </div>
</template>


<script>
import { doLogin } from '@/utils/apiutil.js'
import router from '@/router'

export default {
  name: 'LoginComponent',
  methods: {
    async handleClickSignin (){
      //alert("You entered, username: " + this.username);
      const loginRequest = { username:this.username, password: this.password };
      const loginResponse = await doLogin(loginRequest);
      console.log(loginResponse);
      alert("Login: " + loginResponse.loginStatus);
    },
    handleClickSignin_2 () {
      const loginRequest = { username: this.username, password: this.password };
      const loginResponse = doLogin(loginRequest);
      console.log(loginResponse)
      loginResponse.then((resolvedResult) => {
        this.$store.state.loginStatus = resolvedResult.loginStatus;
        // alert("Login2: " + resolvedResult.loginStatus);
      }
      );
      if(this.loginStatus == true) {
          router.push({ name: 'Home' })
        }else{
          router.push({ name: 'RegisterView' })
        }
    },

  },
  data() {
    return {
      username: '',
      password: '',
      loginStatus: '',
    }
  }

}
</script>
<style scoped>

</style>
