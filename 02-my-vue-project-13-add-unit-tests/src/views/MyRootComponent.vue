<template>
  <!--3. Use -->
  <login-component></login-component>
  <sync-async-example></sync-async-example>
</template>

<script>
// 1. Import
import LoginComponent from "../components/LoginComponent.vue";
import SyncAsyncExample from "../components/SyncAsyncExample.vue";

// 2. Bind
export default {
  name: 'LoginView',
  components: {
    LoginComponent,
    SyncAsyncExample
  }
}
</script>
