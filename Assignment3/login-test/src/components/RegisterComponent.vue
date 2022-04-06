<template>
<div class="container">
    <div id="title">
        <label>Sign up, here!</label>
    </div>
    <form @submit.prevent="registerUser">
    <div id="name" class="input">
        <label>Name:</label>
        <textarea id="namefield" v-model="name" required></textarea>
    </div>
    <div id="username" class="input">
        <label>Username:</label>
        <textarea id="userfield" v-model="username" required></textarea>
    </div>
    <div id="password" class="input">
        <label>Password: </label>
        <textarea id="passfield" v-model="password" required></textarea>
    </div>
    <button type="submit">Create Account</button>
    </form>
</div>
</template>

<script>
import axios from "axios";
import router from '@/router'

export default {
    name: "register",
    data() {
        return {
        name: "",
        username: "",
        password: "",
        };
    },
    methods: {
        async registerUser() {
        await axios.post("", {
            name: this.name,
            username: this.username,
            password: this.password,
        });
        router.push({ name: 'Home' })
        },
    },
};
</script>