<template>
  <form @submit.prevent="submit" @reset="onReset">
    <div>
      <label>name</label>
      <input v-model="name" class="feedback-input"/>
    </div>

    <div>
      <label>email</label>
      <input v-model="email" class="feedback-input"/>
    </div>

    <div>
      <label>message</label>
      <textarea v-model="message" class="feedback-input"></textarea>
    </div>

    <button id="submit" :disabled="formValid == false" @click="submit">submit</button>

    <div>
      <div v-if="sending"><p>Sending</p></div>
      <div v-if="sent"><p>Sent</p></div>
    </div>

  </form>
</template>

<script>
//TODO: make button unclickable if not all fields have input
export default {
  name: "App",
  data() {
    return {
      name: "",
      email: "",
      message: "",
      sending:false,
      sent:false,
    };
  },
  computed: {
    formValid() {
      const { name, email, message } = this;
      return (
        name.length > 0 &&
        /(.+)@(.+){2,}.(.+){2,}/.test(email) &&
        message.length > 0
      );
    },
  },
  methods: {
    submit() {
      this.send()
      if (!localStorage.getItem("messages")) {
        localStorage.setItem("messages", JSON.stringify([]));
      }
      const messages = JSON.parse(localStorage.getItem("messages"));
      const { name, email, message } = this;
      messages.push({
        name,
        email,
        message,
      });
      localStorage.setItem("messages", JSON.stringify(messages));
    },
    send() {
      this.sending = true;
      setTimeout(() => {
        this.sending =false;
        this.sent = true;
      }, 2000);
      setTimeout(() => {
        this.sent = false;
      }, 4000);
    },
  },
};
</script>

<style>
form { max-width:420px; margin:50px auto; }

.feedback-input {
  font-family: Helvetica, Arial, sans-serif;
  font-weight:500;
  font-size: 18px;
  border-radius: 5px;
  line-height: 22px;
  background-color: transparent;
  border:2px solid #1c458a;
  transition: all 0.3s;
  padding: 13px;
  margin-bottom: 15px;
  width:100%;
  box-sizing: border-box;
  outline:0;
}

.feedback-input:focus { border:2px solid #1c458a; }

textarea {
  height: 150px;
  line-height: 150%;
  resize:vertical;
}

#submit {
  font-family: 'Montserrat', Arial, Helvetica, sans-serif;
  width: 100%;
  background:#1c458a;
  border-radius:5px;
  border:0;
  cursor:pointer;
  color:white;
  font-size:24px;
  padding-top:10px;
  padding-bottom:10px;
  transition: all 0.3s;
  margin-top:-4px;
  font-weight:700;
}
#submit:hover { background:#0f264d; }
#submit:disabled { background:#5475ad; }
</style>