<template>
  <section
    class="max-w-4xl p-6 mx-auto bg-white rounded-md shadow-md dark:bg-gray-800"
  >
    <h2 class="text-lg font-semibold text-gray-700 dark:text-white">
      Har du noen tilbakemeldinger til oss:
    </h2>

    <Form @submit="send" :validation-schema="schema">
      <div v-if="!successful" class="grid grid-cols-1 gap-6 mt-4">
        <div>
          <label class="text-gray-700 dark:text-gray-200" for="password"
            >Melding</label
          >
          <Field
            name="feedback"
            id="feedback"
            type="text"
            class="block w-full px-4 py-2 mt-2 text-gray-700 bg-white border border-gray-200 rounded-md dark:bg-gray-800 dark:text-gray-300 dark:border-gray-600 focus:border-blue-400 focus:ring-blue-300 focus:ring-opacity-40 dark:focus:border-blue-300 focus:outline-none focus:ring"
          />
          <ErrorMessage name="input" />
        </div>
      </div>

      <div class="flex justify-end mt-6">
        <button
          :disabled="loading"
          class="px-6 py-2 leading-5 text-white transition-colors duration-200 transform bg-gray-700 rounded-md hover:bg-gray-600 focus:outline-none focus:bg-gray-600"
        >
          Send inn!
        </button>
      </div>
    </Form>
  </section>

  <div v-if="message" :class="successful ? 'alert-success' : 'alert-danger'">
    {{ message }}
  </div>
</template>

<script>
import { Form, Field, ErrorMessage } from "vee-validate";
import * as yup from "yup";

export default {
  name: "App",
  components: {
    Form,
    Field,
    ErrorMessage,
  },
  data() {
    const schema = yup.object().shape({
      input: yup.string().required("Melding kreves!"),
    });
    return {
      successful: false,
      loading: false,
      message: "",
      schema,
    };
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn;
    },
  },
  methods: {
    send() {
      this.message = "Sender...";
      setTimeout(() => {
        this.message = "Sendt!";
      }, 2000);
      setTimeout(() => {
        this.message = "";
      }, 4000);
    },
  },
};
</script>
