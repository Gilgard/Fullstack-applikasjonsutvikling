<template>
<div class="container">
    <form @submit.prevent="registerUser">
    <h1>Sign up</h1>
    <input
        type="text"
        placeholder="Full Name"
        v-model="name"
        required
    />

    <input
        type="text"
        placeholder="Email"
        v-model="email"
        required
    />

    <input
        type="password"
        placeholder="Password"
        v-model="password"
        required/>
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
        email: "",
        password: "",
        };
    },
    methods: {
        async registerUser() {
        await axios.post("", {
            name: this.name,
            email: this.email,
            password: this.password,
        });
        router.push({ name: 'Home' })
        },
    },
};
</script>