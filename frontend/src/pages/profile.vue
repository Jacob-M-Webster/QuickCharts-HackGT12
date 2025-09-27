<template>
  <div class="flex flex-col items-center justify-center min-h-screen bg-gray-100 p-6">
    <!-- Blank Profile Picture -->
    <div class="w-32 h-32 rounded-full bg-gray-300 mb-6 flex items-center justify-center">
      <svg class="w-16 h-16 text-gray-500" fill="currentColor" viewBox="0 0 24 24">
        <path d="M12 12c2.21 0 4-1.79 4-4s-1.79-4-4-4-4 1.79-4 4 1.79 4 4 4zm0 2c-2.67 0-8 1.34-8 4v2h16v-2c0-2.66-5.33-4-8-4z"/>
      </svg>
    </div>

    <!-- Profile Form -->
    <form class="w-full max-w-sm bg-white shadow-lg rounded-lg p-6">
      <div class="mb-4">
        <label class="block text-gray-700 font-semibold mb-2">Name</label>
        <!-- Backend: Populate with user's name from API -->
        <input
            type="text"
            class="w-full border rounded px-3 py-2"
            :value="userData.name"
            readonly
            placeholder="Name will appear here"
        />
      </div>
      <div class="mb-4">
        <label class="block text-gray-700 font-semibold mb-2">Age</label>
        <!-- Backend: Populate with user's age from API -->
        <input
            type="number"
            class="w-full border rounded px-3 py-2"
            :value="userData.age"
            readonly
            placeholder="Age will appear here"
        />
      </div>
      <div class="mb-4">
        <label class="block text-gray-700 font-semibold mb-2">Birthdate</label>
        <!-- Backend: Populate with user's birthdate from API (format: YYYY-MM-DD) -->
        <input
            type="date"
            class="w-full border rounded px-3 py-2"
            :value="userData.birthdate"
            readonly
        />
      </div>
      <!-- Backend: Add edit functionality if needed -->
      <button type="button" class="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700">
        Edit Profile (Future Feature)
      </button>
    </form>
  </div>
</template>

<script>
export default {
  name: "ProfilePage",
  data() {
    return {
      // Backend: Replace this empty data with API response
      userData: {
        name: "",        // Backend: Get from user profile API
        age: "",         // Backend: Get from user profile API
        birthdate: ""    // Backend: Format must be YYYY-MM-DD for date input
      }
    };
  },
  mounted() {
    // Backend: Add API call here to fetch user profile data
    // Example:
    // axios.get('/api/user/profile')
    //   .then(response => {
    //     this.userData = response.data;
    //   })
    //   .catch(error => {
    //     console.error('Error fetching profile:', error);
    //   });
  }
};
</script>