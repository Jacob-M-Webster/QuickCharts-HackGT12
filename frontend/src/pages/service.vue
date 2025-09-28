<template>
  <div class="min-h-screen bg-gray-100 p-8">
    <!-- Page Title -->
    <h1 class="text-3xl font-bold text-center mb-10 text-blue-900">Our Services</h1>

    <!-- Services Grid -->
    <div class="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-8">
      <div
          v-for="(service, index) in services"
          :key="index"
          class="bg-white shadow-lg rounded-lg p-4 flex flex-col items-center hover:shadow-xl transition"
      >
        <!-- Service Image -->
        <img
            :src="service.image"
            :alt="service.name"
            class="w-32 h-32 object-cover rounded-full mb-4"
        />
        <!-- Service Name -->
        <h2 class="text-lg font-semibold text-center text-blue-900">{{ service.name }}</h2>
        <!-- Service Description -->
        <p class="text-sm text-center mt-2 text-gray-600">
          {{ service.description }}
        </p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "ServicePage",
  data() {
    return {
      services: [
        {
          name: "Pharmacy Service",
          image: new URL("../assets/pharmacy.jpg", import.meta.url).href,
          description:
              "Our pharmacy service provides easy access to prescription medications, over-the-counter drugs, and health products. Our trained pharmacists ensure that every prescription is accurate and safe. We also provide guidance on proper medication usage and potential side effects. Our goal is to help you maintain optimal health conveniently and reliably.",
        },
        {
          name: "Medical Specialist",
          image: new URL("../assets/specialist.jpg", import.meta.url).href,
          description:
              "We offer consultations with experienced medical specialists across various fields such as cardiology, dermatology, and orthopedics. Each specialist provides personalized care tailored to your unique medical needs. We ensure timely appointments and thorough evaluations to support effective diagnosis and treatment. Your health and well-being are our top priority.",
        },
        {
          name: "Medical Checkup",
          image: new URL("../assets/checkup.jpg", import.meta.url).href,
          description:
              "Our comprehensive medical checkup services help detect potential health issues early. We provide a variety of tests, including blood tests, imaging, and physical assessments. These checkups are designed to monitor overall health and prevent complications before they arise. Our team guides you through the results and recommends appropriate follow-up care.",
        },
        {
          name: "Health Consultation",
          image: new URL("../assets/consultation.png", import.meta.url).href,
          description:
              "Health consultation services offer personalized advice and guidance for maintaining a healthy lifestyle. Our professionals discuss nutrition, fitness, mental well-being, and chronic disease management. Each consultation is tailored to your personal health goals and medical history. We aim to empower you with the knowledge to make informed health decisions.",
        },
        {
          name: "Ambulance Service",
          image: new URL("../assets/ambulance.png", import.meta.url).href,
          description:
              "Our ambulance service provides rapid and reliable emergency transportation to medical facilities. Equipped with state-of-the-art medical equipment and staffed by trained paramedics, we handle emergencies efficiently. Our service operates 24/7 to ensure you receive timely medical attention whenever needed. Safety, speed, and professionalism are the foundation of our ambulance service.",
        },
        {
          name: "Diagnostic Service",
          image: new URL("../assets/diagnostic.png", import.meta.url).href,
          description:
              "Our diagnostic services include advanced laboratory tests and imaging studies to accurately assess your health. We utilize modern technology to provide quick and precise results. Each test is performed with strict quality control standards to ensure reliability. Our team helps interpret results and guides you on the next steps for effective treatment.",
        },
      ],
    };
  },
};
</script>

<style scoped>
/* Apply IBM Plex Mono font to the whole component */
* {
  font-family: 'IBM Plex Mono', monospace;
}
</style>
