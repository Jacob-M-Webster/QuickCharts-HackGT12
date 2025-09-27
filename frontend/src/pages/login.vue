<template>
  <div class="max-w-md mx-auto mt-10">
    <h2 class="text-2xl font-bold mb-4">Login</h2>
    <form @submit.prevent="loginUser" class="space-y-4">
      <div>
        <label class="block mb-1">Username</label>
        <input v-model="username" type="text" class="border rounded w-full p-2"/>
      </div>
      <div>
        <label class="block mb-1">Password</label>
        <input v-model="password" type="password" class="border rounded w-full p-2"/>
      </div>
      <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded">
        Login
      </button>
    </form>
    <p v-if="message" class="mt-4 text-red-500">{{ message }}</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "LoginPage",
  data() {
    return {
      username: '',
      password: '',
      message: ''
    };
  },
  methods: {
    async loginUser() {
      try {
        const response = await axios.post('http://localhost:8080/api/auth/login', {
          username: this.username,
          password: this.password
        });
        this.message = response.data;
      } catch (error) {
        this.message = error.response?.data || 'Login failed';
      }
    }
  }
};
</script>

<style scoped>
</style>
