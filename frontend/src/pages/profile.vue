<template>
  <!-- Top-level container: row -->
  <div class="min-h-screen bg-gray-100 p-6 flex flex-col md:flex-row gap-8">

    <!-- Left: Appointment Section -->
    <!-- Backend: This component will fetch appointment data from the database -->
    <AppointmentSection />

    <!-- Right: Profile Section -->
    <div class="w-full md:w-1/2 flex flex-col items-center mt-6 md:mt-0">

      <!-- Profile Picture -->
      <!-- Backend: Should fetch and display the user's profile picture URL from database -->
      <div class="w-36 h-36 rounded-full bg-gray-300 flex items-center justify-center mb-8">
        <svg class="w-20 h-20 text-gray-500" fill="currentColor" viewBox="0 0 24 24">
          <path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/>
        </svg>
      </div>

      <!-- Profile Form -->
      <form class="w-full max-w-3xl bg-white shadow-lg rounded-lg p-10">

        <!-- Name -->
        <!-- Backend: Fetch user's full name from the database -->
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
        <!-- Backend: Fetch user's age and preferred language -->
        <div class="mb-6 flex gap-4">
          <!-- Age -->
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

          <!-- Preferred Language -->
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
        <!-- Backend: Fetch user's birthdate and gender from database -->
        <div class="mb-6 flex gap-4">
          <!-- Birthdate -->
          <div class="flex-1">
            <label class="block text-gray-700 font-semibold mb-2 text-lg">Birthdate</label>
            <input
                type="date"
                class="w-full border rounded px-3 py-3 text-lg"
                :value="userData.birthdate"
                readonly
            />
          </div>

          <!-- Gender -->
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

        <!-- Button -->
        <!-- Backend: Optional — if "Edit Profile" is implemented, this button should route to a profile editing form -->
        <button
            type="button"
            class="w-full bg-blue-600 text-white py-3 rounded hover:bg-blue-700 text-lg"
        >
          Edit Profile (Future Feature)
        </button>
      </form>

    </div>
  </div>
</template>

<script>
import AppointmentSection from './appointment.vue';

export default {
  name: "ProfilePage",
  components: {
    AppointmentSection
  },
  data() {
    return {
      userData: {
        name: "",                // Backend: fetch from user table
        age: "",                 // Backend: calculate from birthdate or fetch directly
        preferredLanguage: "",   // Backend: fetch user's preferred language
        birthdate: "",           // Backend: fetch from user table
        gender: "",              // Backend: fetch from user table
      }
    };
  },
  mounted() {
    // TODO: Fetch profile data from backend API
    // Example: axios.get('/api/user/profile').then(response => this.userData = response.data)
  }
};
</script>
