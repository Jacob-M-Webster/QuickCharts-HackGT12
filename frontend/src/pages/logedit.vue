<template>
  <div class="min-h-screen flex items-center justify-center bg-gradient-to-br from-blue-100 to-blue-200">
    <!-- Login Card -->
    <div class="w-full max-w-4xl bg-white rounded-xl shadow-2xl overflow-hidden flex">

      <!-- Left Illustration (hidden on small screens) -->
      <div class="hidden md:block w-1/2 bg-cover bg-center"
           style="background-image: url('/src/assets/login.png');">
      </div>

      <!-- Login Form -->
      <div class="w-full md:w-1/2 p-10">
        <div class="text-center mb-6">
          <h1 class="text-4xl font-bold text-blue-700">Welcome Back</h1>
          <p class="text-gray-500 mt-2">Please login to your account</p>
        </div>

        <form @submit.prevent="loginUser" class="space-y-5">
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

          <!-- Remember me & forgot password -->
          <div class="flex justify-between items-center text-sm text-gray-500">
            <label class="flex items-center gap-2">
              <input type="checkbox" class="w-4 h-4 rounded border-gray-300" />
              Remember me
            </label>
            <a href="#" class="hover:text-blue-500">Forgot password?</a>
          </div>

          <!-- Submit Button -->
          <button
              type="submit"
              class="w-full bg-blue-600 text-white font-semibold py-2 rounded-lg hover:bg-blue-700 hover:scale-105 transition-transform duration-200"
          >
            Login
          </button>
        </form>

        <!-- Error / Message -->
        <p v-if="message" class="mt-4 text-red-500 text-center">{{ message }}</p>

        <!-- Sign up link -->
        <p class="mt-6 text-center text-gray-500 text-sm">
          Don't have an account?
          <router-link to="/registration" class="text-blue-600 hover:underline">
            Sign up
          </router-link>
        </p>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "LogEdit",
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

        const loggedInUser = response.data;
        console.log(loggedInUser);

        // Save user info locally (optional)
        localStorage.setItem('userId', loggedInUser.id);

        // Redirect to profile page with user ID as route param
        this.$router.push(`/profile/${loggedInUser.id}`);
      } catch (error) {
        this.message = error.response?.data?.message || 'Login failed';
      }
    }
  }
};
</script>

<style scoped>
* {
  font-family: 'IBM Plex Mono', monospace;
}

/* Optional: make the illustration responsive */
</style>
