<template>
  <div id="loginContainer">
    <div id="loginTitle">
        <label>Please login!</label>
    </div>
    <div id="username">
        <label id="usernameLabel">Username:</label>
        <textarea id="userfield" v-model="username"></textarea>
      </div>
      <div id="password">
        <label id="passwordLabel">Password: </label>
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
        this.loginStatus = resolvedResult.loginStatus;
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
#loginContainer {
    display: grid;
    justify-content: center;
    margin: 40px;
}

#loginTitle {
  font-size: x-large;
  font-weight: bold;
  margin-bottom: 20px;
}

#username, #password {
  display: flex;
  flex-direction: row;
  align-items: center;
  column-gap: 20px;
}

#usernameLabel, #passwordLabel {
    width: 100px;
}

</style>
