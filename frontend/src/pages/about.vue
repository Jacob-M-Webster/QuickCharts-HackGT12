<template>
  <div class="about-page">
    <!-- Hero Section -->
    <section class="hero">
      <div class="hero-text">
        <h1>About Our Product</h1>
        <p>
          Our mission is to streamline healthcare by enabling patients to complete pre-appointment forms, saving doctors time and enhancing patient care.
        </p>
      </div>
    </section>

    <!-- Image Section -->
    <section class="image-placeholders">
      <div class="image-placeholder">
        <img :src="doctor1" alt="Doctor 1" class="placeholder-image" />
      </div>
      <div class="image-placeholder">
        <img :src="doctor2" alt="Doctor 2" class="placeholder-image" />
      </div>
      <div class="image-placeholder">
        <img :src="doctor3" alt="Doctor 3" class="placeholder-image" />
      </div>
      <div class="image-placeholder">
        <img :src="doctor4" alt="Doctor 4" class="placeholder-image" />
      </div>
    </section>

    <!-- Information Sections -->
    <section class="info-section">
      <h2>Why We Exist</h2>
      <p>
        Healthcare is a vital part of everyone’s life, but waiting times and inefficient information sharing can slow things down.  
        Our product streamlines the process by allowing patients to provide important information before their appointments.
      </p>
    </section>

    <section class="info-section">
      <h2>How It Works</h2>
      <p>
        Patients fill out a simple questionnaire before arriving for their appointment. This allows doctors to review their information ahead of time, saving valuable consultation time and improving the quality of care.
      </p>
    </section>

    <section class="info-section">
      <h2>Our Commitment</h2>
      <p>
        We are committed to maintaining privacy, providing an easy-to-use system, and improving the healthcare experience for both patients and providers.
      </p>
    </section>

    <section class="info-section">
      <h2>Contact Us</h2>
      <p>
        For questions or inquiries, please reach out to our support team.  
        Email: support@healthcareproduct.com  
        Phone: (123) 456-7890
      </p>
    </section>
  </div>
</template>

<script>
import doctor1 from "../assets/doctor.png";
import doctor2 from "../assets/doctor2.png";
import doctor3 from "../assets/doctor3.png";
import doctor4 from "../assets/doctor4.png";

export default {
  name: "AboutPage",
  data() {
    return {
      doctor1,
      doctor2,
      doctor3,
      doctor4,
    };
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Mono&display=swap');

.about-page {
  font-family: 'IBM Plex Mono', monospace;
  padding: 20px;
  background-color: #f0faff; /* Light sky-blue background */
  color: #333;
}

/* Hero Section */
.hero {
  background-color: #e0f7fa; /* Slightly darker sky blue */
  padding: 40px;
  border-radius: 10px;
  text-align: center;
}

.hero-text h1 {
  font-size: 2.5em;
  margin-bottom: 10px;
  color: #15305d;
}

.hero-text p {
  font-size: 1.2em;
}

/* Image Section */
.image-placeholders {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
  gap: 20px;
  margin: 40px 0;
}

.image-placeholder {
  width: 300px;
  height: 250px;
  background-color: #d9edf7;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.placeholder-image {
  width: 100%;
  height: 100%;
  border-radius: 8px;
  object-fit: cover;
}

/* Information Sections */
.info-section {
  background: white;
  margin: 20px 0;
  padding: 30px;
  border-radius: 10px;
  box-shadow: 0px 2px 6px rgba(0, 0, 0, 0.1);
}

.info-section h2 {
  font-size: 2em;
  margin-bottom: 10px;
  color: #113e71;
}

.info-section p {
  font-size: 1.1em;
  line-height: 1.6;
}
</style>
