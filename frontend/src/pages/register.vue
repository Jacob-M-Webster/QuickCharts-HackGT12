<template>
  <div class="max-w-md mx-auto mt-10">
    <h2 class="text-2xl font-bold mb-4">Register</h2>
    <form @submit.prevent="registerUser" class="space-y-4">
      <div>
        <label class="block mb-1">Username</label>
        <input v-model="username" type="text" class="border rounded w-full p-2"/>
      </div>
      <div>
        <label class="block mb-1">Password</label>
        <input v-model="password" type="password" class="border rounded w-full p-2"/>
      </div>
      <button type="submit" class="bg-green-500 text-white px-4 py-2 rounded">
        Register
      </button>
    </form>
    <p v-if="message" class="mt-4 text-red-500">{{ message }}</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "RegisterPage",
  data() {
    return {
      username: '',
      password: '',
      message: ''
    };
  },
  methods: {
    async registerUser() {
      try {
        const response = await axios.post('http://localhost:8080/api/auth/register', {
          username: this.username,
          password: this.password
        });
        this.message = `Registered: ${response.data.username}`;
      } catch (error) {
        this.message = error.response?.data || 'Registration failed';
      }
    }
  }
};
</script>

<style scoped>
</style>
