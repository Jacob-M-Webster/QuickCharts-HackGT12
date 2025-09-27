<template>
  <div class="p-8 text-center">
    <h1 class="text-2xl font-bold mb-4">CONNECTED</h1>
    <p v-if="message" class="text-green-600 text-lg">
      {{ message }}
    </p>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "App",
  data() {
    return {
      message: null,
    };
  },
  mounted() {
    axios.get("http://localhost:8080/api/hello")
      .then(response => {
        this.message = response.data.message;
      })
      .catch(error => {
        console.error("Error fetching from backend:", error);
      });
  }
};
</script>

<style>
</style>
