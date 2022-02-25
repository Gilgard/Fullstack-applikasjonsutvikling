<template>
  <!--3. Use -->
  <register-component></register-component>
  <sync-async-example></sync-async-example>
</template>

<script>
// 1. Import
import RegisterComponent from "../components/RegisterComponent.vue";
import SyncAsyncExample from "../components/SyncAsyncExample.vue";

// 2. Bind
export default {
  name: 'RegisterView',
  components: {
    RegisterComponent,
    SyncAsyncExample
  }
}
</script>