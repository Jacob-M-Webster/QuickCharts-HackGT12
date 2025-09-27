<template>
  <div id="app">
    <!-- Header stays at top -->
    <Header />

    <!-- Homepage section only on Home route -->
    <div v-if="$route.name === 'Home'" class="homepage-container">
      <div class="left-center-text">
        <h1>Less Waiting</h1>
        <p>Your Questions, Answered Before You Arrive.</p>
        <!-- Make an Appointment button as router-link -->
        <router-link to="/login" class="appointment-button">Make an Appointment</router-link>
      </div>
    </div>

    <!-- Main content for all routes -->
    <router-view />
  </div>
</template>

<script>
import Header from './components/Header.vue';

export default {
  name: 'App',
  components: {
    Header,
  },
};
</script>

<style>
/* Import IBM Plex Mono font */
@import url('https://fonts.googleapis.com/css2?family=IBM+Plex+Mono&display=swap');

/* Homepage container — background fills entire area */
.homepage-container {
  height: calc(100vh - 80px); /* full viewport minus header */
  width: 100%;
  background-image: url('./assets/homepageimage.jpg'); /* your image */
  background-size: cover;          
  background-position: center 10%;     
  background-repeat: no-repeat;
  position: relative; /* allows absolute positioning for text */
}

/* Left center text */
.left-center-text {
  position: absolute;
  left: 50px;
  top: 30%;
  transform: translateY(-50%);
  color: #07182f;     
  max-width: 440px;   
  font-family: 'IBM Plex Mono', monospace;
}

.left-center-text h1 {
  font-size: 50px;
  margin-bottom: 15px;
}

.left-center-text p {
  font-size: 22px;
  margin-bottom: 50px; /* space before button */
}

/* Make an Appointment button styled for router-link */
.appointment-button {
  display: inline-block; /* ensures proper styling */
  background-color: #112d54; /* dark blue fill */
  color: white;              
  font-family: 'IBM Plex Mono', monospace;
  font-size: 18px;
  padding: 12px 25px;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  text-decoration: none; /* remove underline from router-link */
  transition: background-color 0.3s;
}

.appointment-button:hover {
  background-color: #0b2b52; /* slightly lighter on hover */
}
</style>
