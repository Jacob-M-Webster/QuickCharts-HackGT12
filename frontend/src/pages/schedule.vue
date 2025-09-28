<template>
  <div class="max-w-3xl mx-auto mt-10 p-6 bg-white rounded-lg shadow-md">
    <!-- Page Title -->
    <h2 class="text-3xl font-bold mb-6 text-center text-blue-700">
      Book an Appointment
    </h2>

    <!-- Appointment Form -->
    <form @submit.prevent="submitAppointment" class="space-y-4">
      <!-- Full Name (Editable) -->
      <div>
        <label class="block font-semibold mb-1">Full Name</label>
        <input
            v-model="form.name"
            type="text"
            class="w-full border rounded p-2 bg-white"
        />
      </div>

      <!-- Birthdate -->
      <div>
        <label class="block font-semibold mb-1">Birthdate</label>
        <input
            v-model="form.birthdate"
            type="date"
            class="w-full border rounded p-2 focus:ring focus:ring-blue-300"
            required
        />
      </div>

      <!-- Gender -->
      <div>
        <label class="block font-semibold mb-1">Gender</label>
        <select
            v-model="form.gender"
            class="w-full border rounded p-2 focus:ring focus:ring-blue-300"
            required
        >
          <option disabled value="">Select gender</option>
          <option>Male</option>
          <option>Female</option>
          <option>Other</option>
          <option>Prefer not to say</option>
        </select>
      </div>

      <!-- Preferred Language -->
      <div>
        <label class="block font-semibold mb-1">Preferred Language</label>
        <select
            v-model="form.language"
            class="w-full border rounded p-2 focus:ring focus:ring-blue-300"
            required
        >
          <option disabled value="">Select language</option>
          <option>English</option>
          <option>Español</option>
          <option>中文</option>
          <option>한국어</option>
          <option>日本語</option>
          <option>Français</option>
          <option>Deutsch</option>
          <option>العربية</option>
          <option>हिन्दी</option>
          <option>Português</option>
          <option>Русский</option>
          <option>Other</option>
        </select>
      </div>

      <!-- Appointment Type -->
      <div>
        <label class="block font-semibold mb-1">Appointment Type</label>
        <select
            v-model="form.type"
            class="w-full border rounded p-2 focus:ring focus:ring-blue-300"
            required
        >
          <option disabled value="">Select appointment type</option>
          <option>Annual Check-up</option>
          <option>Eye Doctor</option>
          <option>Blood Sugar Test</option>
          <option>Sports Physical</option>
          <option>Vaccination</option>
          <option>Consultation</option>
          <option>Other</option>
        </select>
      </div>

      <!-- Appointment Date & Time (Same Row) -->
      <div class="flex gap-4">
        <div class="flex-1">
          <label class="block font-semibold mb-1">Appointment Date</label>
          <input
              v-model="form.date"
              type="date"
              class="w-full border rounded p-2 focus:ring focus:ring-blue-300"
              required
          />
        </div>
        <div class="flex-1">
          <label class="block font-semibold mb-1">Appointment Time</label>
          <input
              v-model="form.time"
              type="time"
              class="w-full border rounded p-2 focus:ring focus:ring-blue-300"
              required
          />
        </div>
      </div>

      <!-- Submit Button -->
      <div class="text-center">
        <button
            type="submit"
            class="bg-blue-600 text-white px-6 py-2 rounded hover:bg-blue-700"
        >
          Confirm Appointment
        </button>
      </div>
    </form>

    <!-- Confirmation Message -->
    <div
        v-if="submitted"
        class="mt-6 p-4 bg-green-100 border border-green-400 rounded text-green-800"
    >
      {{ confirmationMessage }}
    </div>
  </div>
</template>

<script setup>
import { reactive, ref, computed } from "vue";
import { useRouter } from "vue-router";

// ===============================
// Router
// ===============================
const router = useRouter();

// ===============================
// Form state
// ===============================
const form = reactive({
  name: "",       // Full name (editable now)
  birthdate: "",
  gender: "",
  language: "",
  type: "",       // Appointment type
  date: "",
  time: ""
});

const submitted = ref(false);

// ===============================
// Convert 24-hour time to 12-hour format
// ===============================
const formattedTime = computed(() => {
  if (!form.time) return "";
  const [hourStr, minute] = form.time.split(":");
  let hour = parseInt(hourStr, 10);
  const ampm = hour >= 12 ? "PM" : "AM";
  hour = hour % 12 || 12;
  return `${hour}:${minute} ${ampm}`;
});

// ===============================
// Multilingual confirmation message
// ===============================
const confirmationMessage = computed(() => {
  const name = form.name;
  const date = form.date;
  const time = formattedTime.value;
  const type = form.type;

  switch (form.language) {
    case "English":
      return `Appointment booked successfully for ${name} on ${date} at ${time} (${type}).`;
    case "Español":
      return `Cita reservada con éxito para ${name} el ${date} a las ${time} (${type}).`;
    case "中文":
      return `${name} 的预约已成功，时间：${date} ${time} (${type})。`;
    case "한국어":
      return `${name}님의 예약이 성공적으로 예약되었습니다: ${date} ${time} (${type}).`;
    case "日本語":
      return `${name} の予約が ${date} ${time} (${type}) に正常に完了しました。`;
    case "Français":
      return `Rendez-vous réservé avec succès pour ${name} le ${date} à ${time} (${type}).`;
    default:
      return `Appointment booked successfully for ${name} on ${date} at ${time} (${type}).`;
  }
});

// ===============================
// Submit Appointment
// ===============================
function submitAppointment() {
  submitted.value = true;
  console.log("Appointment Details:", form);

  // Navigate to questionnaire page
  router.push({ name: "Questionnaire" });
}
</script>

<style scoped>
* {
  font-family: 'IBM Plex Mono', monospace;
}
</style>
