<template>
  <div class="min-h-screen bg-gray-100 p-6 flex flex-col md:flex-row gap-8">

    <!-- Left: Appointment Section -->
    <AppointmentSection />

    <!-- Right: Profile Section -->
    <div class="w-full md:w-1/2 flex flex-col items-center mt-6 md:mt-0">

      <!-- Profile Picture -->
      <div class="w-36 h-36 rounded-full bg-gray-300 flex items-center justify-center mb-8">
        <svg class="w-20 h-20 text-gray-500" fill="currentColor" viewBox="0 0 24 24">
          <path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/>
        </svg>
      </div>

      <!-- Profile Form -->
      <form class="w-full max-w-3xl bg-white shadow-lg rounded-lg p-10">

        <!-- Name -->
        <div class="mb-6">
          <label class="block text-gray-700 font-semibold mb-2 text-lg">Name</label>
          <input
              type="text"
              class="w-full border rounded px-3 py-3 text-lg"
              :value="userData.name"
              readonly
              placeholder="Name will appear here"
          />
        </div>

        <!-- Age + Preferred Language -->
        <div class="mb-6 flex gap-4">
          <div class="flex-1">
            <label class="block text-gray-700 font-semibold mb-2 text-lg">Age</label>
            <input
                type="number"
                class="w-full border rounded px-3 py-3 text-lg"
                :value="userData.age"
                readonly
                placeholder="Age will appear here"
            />
          </div>

          <div class="flex-1">
            <label class="block text-gray-700 font-semibold mb-2 text-lg">Preferred Language</label>
            <input
                type="text"
                class="w-full border rounded px-3 py-3 text-lg"
                :value="userData.preferredLanguage"
                readonly
                placeholder="Preferred Language"
            />
          </div>
        </div>

        <!-- Birthdate + Gender -->
        <div class="mb-6 flex gap-4">
          <div class="flex-1">
            <label class="block text-gray-700 font-semibold mb-2 text-lg">Birthdate</label>
            <input
                type="date"
                class="w-full border rounded px-3 py-3 text-lg"
                :value="userData.birthdate"
                readonly
            />
          </div>

          <div class="flex-1">
            <label class="block text-gray-700 font-semibold mb-2 text-lg">Gender</label>
            <input
                type="text"
                class="w-full border rounded px-3 py-3 text-lg"
                :value="userData.gender"
                readonly
                placeholder="Gender"
            />
          </div>
        </div>
      </form>

    </div>
  </div>
</template>

<script>
import AppointmentSection from './appointment.vue';
import axios from 'axios';

export default {
  name: "ProfilePage",
  components: { AppointmentSection },
  data() {
    return {
      userData: {
        name: "",
        age: "",
        preferredLanguage: "",
        birthdate: "",
        gender: ""
      }
    };
  },
  mounted() {
    const userId = this.$route.params.id;

    axios.get(`http://localhost:8080/api/auth/users/${userId}`)
      .then(response => {
        const user = response.data;
        this.userData = {
          name: user.username,
          age: user.age,
          preferredLanguage: user.language,
          birthdate: user.dob,
          gender: user.gender
        };
      })
      .catch(err => console.error(err));
  }
};
</script>