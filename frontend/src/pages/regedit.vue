<template>
  <div class="min-h-screen flex items-center justify-center bg-gradient-to-br from-blue-100 to-blue-200">
    <!-- Registration Card -->
    <div class="w-full max-w-4xl bg-white rounded-xl shadow-2xl overflow-hidden flex flex-col md:flex-row">

      <!-- Registration Form on Left -->
      <div class="w-full md:w-1/2 p-10 order-1 md:order-1">
        <div class="text-center mb-6">
          <h1 class="text-4xl font-bold text-blue-700">Create Account</h1>
          <p class="text-gray-500 mt-2">Sign up to start using our platform</p>
        </div>

        <form @submit.prevent="registerUser" class="space-y-5">
          <!-- Username -->
          <div>
            <label class="block text-gray-700 font-semibold mb-1">Username</label>
            <input
                v-model="username"
                type="text"
                placeholder="Enter your username"
                class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-300 transition"
                required
            />
          </div>

          <!-- Password -->
          <div>
            <label class="block text-gray-700 font-semibold mb-1">Password</label>
            <input
                v-model="password"
                type="password"
                placeholder="Enter your password"
                class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-300 transition"
                required
            />
          </div>

          <!-- Confirm Password -->
          <div>
            <label class="block text-gray-700 font-semibold mb-1">Confirm Password</label>
            <input
                v-model="confirmPassword"
                type="password"
                placeholder="Confirm your password"
                class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-300 transition"
                required
            />
          </div>

          <!-- Submit Button -->
          <button
              type="submit"
              class="w-full bg-green-600 text-white font-semibold py-2 rounded-lg hover:bg-green-700 hover:scale-105 transition-transform duration-200"
          >
            Register
          </button>
        </form>

        <!-- Error / Message -->
        <p v-if="message" class="mt-4 text-red-500 text-center">{{ message }}</p>

        <!-- Login link -->
        <p class="mt-6 text-center text-gray-500 text-sm">
          Already have an account?
          <router-link to="/login" class="text-blue-600 hover:underline">
            Login
          </router-link>
        </p>
      </div>

      <!-- Right Illustration / Image -->
      <div
          class="hidden md:block w-full md:w-1/2 bg-cover bg-center order-2"
          style="background-image: url('/src/assets/register.png');"
      ></div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "RegEdit",
  data() {
    return {
      username: '',
      password: '',
      confirmPassword: '',
      message: ''
    };
  },
  methods: {
    async registerUser() {
      if (this.password !== this.confirmPassword) {
        this.message = 'Passwords do not match';
        return;
      }

      try {
        const response = await axios.post('http://localhost:8080/api/auth/register', {
          username: this.username,
          password: this.password
        });
        this.message = `Registered successfully: ${response.data.username}`;
      } catch (error) {
        this.message = error.response?.data?.message || 'Registration failed';
      }
    }
  }
};
</script>

<style scoped>
* {
  font-family: 'IBM Plex Mono', monospace;
}
</style>
