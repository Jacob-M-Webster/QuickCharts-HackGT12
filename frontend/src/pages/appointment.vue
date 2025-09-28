<template>
  <div class="w-1/2 bg-white shadow-lg rounded-lg p-6 flex flex-col gap-3">

    <!-- Title -->
    <h2 class="text-lg font-bold mb-3">Appointments</h2>

    <!-- Scrollable section -->
    <div class="flex-0.5 overflow-hidden text-sm leading-tight space-y-2 pr-1">

      <!-- Previous Appointments -->
      <div>
        <h3 class="font-semibold mb-2">Previous</h3>
        <ul class="list-disc list-inside text-gray-700">
          <template v-if="previousAppointments && previousAppointments.length">
            <li
                v-for="(appt, i) in previousAppointments"
                :key="'prev-' + i"
                class="mb-3"
            >
              <!-- Display date, type, doctor -->
              <span class="font-medium">{{ formatDateTime(appt.date, appt.time) }}</span>
              — {{ appt.type }} with {{ appt.doctor || 'Doctor Name' }}

              <!-- Display optional notes -->
              <p v-if="appt.notes" class="text-xs text-gray-500 italic mt-1 ml-4">
                Notes: {{ appt.notes }}
              </p>
            </li>
          </template>
          <li v-else class="text-gray-400">No previous appointments</li>
        </ul>
      </div>

      <!-- Upcoming Appointments -->
      <div>
        <h3 class="font-semibold mb-2">Upcoming</h3>
        <ul class="list-disc list-inside text-gray-700">
          <template v-if="upcomingAppointments && upcomingAppointments.length">
            <li
                v-for="(appt, i) in upcomingAppointments"
                :key="'up-' + i"
                class="mb-5"
            >
              <!-- Display date, type, doctor -->
              <span class="font-medium">{{ formatDateTime(appt.date, appt.time) }}</span>
              — {{ appt.type }} with {{ appt.doctor || 'Doctor Name' }}

              <!-- Display appointment status -->
              <span
                  v-if="appt.status"
                  class="ml-2 px-2 py-0.5 rounded-full text-xs"
                  :class="{
                    'bg-green-100 text-green-700': appt.status === 'Confirmed',
                    'bg-yellow-100 text-yellow-700': appt.status === 'Pending',
                    'bg-red-100 text-red-700': appt.status === 'Canceled'
                  }"
              >
                {{ appt.status }}
              </span>

              <!-- Display optional notes -->
              <p v-if="appt.notes" class="text-xs text-gray-500 italic mt-1 ml-4">
                Notes: {{ appt.notes }}
              </p>

              <!-- Actions -->
              <div class="flex gap-3 mt-1 ml-4 text-xs">
                <!-- Backend: reschedule button should open a form/modal to update the appointment in the database -->
                <button class="text-blue-600 hover:underline" @click.prevent="reschedule(appt)">
                  Reschedule
                </button>

                <!-- Backend: cancel button should mark the appointment as canceled in the database -->
                <button class="text-red-600 hover:underline" @click.prevent="cancel(appt)">
                  Cancel
                </button>
              </div>
            </li>
          </template>
          <li v-else class="text-gray-400">No upcoming appointments</li>
        </ul>
      </div>
    </div>

    <hr class="my-2 border-t border-gray-300" />

    <!-- Summary + Button -->
    <div class="mt-2 text-sm text-gray-600 font-semibold">
      <!-- Backend: total previous appointments can be dynamically calculated -->
      <p class="mb-1"><span class="font-bold">Total Previous Appointments:</span> {{ previousAppointments.length }}</p>

      <!-- Backend: next appointment should fetch the soonest upcoming appointment -->
      <p><span class="font-bold">Next Appointment: </span>
        <span v-if="upcomingAppointments.length">
          {{ formatDateTime(upcomingAppointments[0].date, upcomingAppointments[0].time) }}
        </span>
        <span v-else>None</span>
      </p>
    </div>

    <!-- Backend: Schedule Appointment button should route to a scheduling page or open a scheduling form -->
    <button
        class="mt-6 bg-blue-600 text-white py-3 rounded hover:bg-blue-700 text-sm"
        @click="scheduleAppointment"
    >
      Schedule Appointment
    </button>
  </div>
</template>

<script>
export default {
  name: "AppointmentSection",
  data() {
    return {
      // Backend: These would normally be fetched from a database
      previousAppointments: [
        { date: "2025-09-01", time: "10:00 AM", type: "Annual Checkup", doctor: "Doctor Name", notes: "Bring blood test results" },
        { date: "2025-08-15", time: "2:30 PM", type: "Eye Doctor", doctor: "Doctor Name" }
      ],
      upcomingAppointments: [
        { date: "2025-10-05", time: "11:00 AM", type: "Blood Sugar Test", doctor: "Doctor Name", status: "Confirmed", notes: "Fasting required" },
        { date: "2025-11-01", time: "9:30 AM", type: "Sports Physical", doctor: "Doctor Name", status: "Pending" }
      ]
    };
  },
  methods: {
    // TODO: Connect to backend API to open schedule appointment page or modal
    scheduleAppointment() {
      console.log("Redirect to schedule appointment page");
    },
    // TODO: Connect to backend API to reschedule the appointment
    reschedule(appt) {
      console.log("Reschedule clicked for", appt);
    },
    // TODO: Connect to backend API to cancel the appointment
    cancel(appt) {
      console.log("Cancel clicked for", appt);
    },
    // Format date and time nicely
    formatDateTime(dateStr, timeStr) {
      const parts = dateStr.split("-");
      if (parts.length !== 3) return dateStr;
      const [year, month, day] = parts;
      return `${month}/${day}/${year} at ${timeStr}`;
    }
  }
};
</script>
