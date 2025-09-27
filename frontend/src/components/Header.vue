<template>
  <header>
    <!-- Logo + Name -->
    <div class="logo">
      <router-link to="/" class="logo-container">
        <ComputerLogo class="logo-icon" />
        <span class="logo-text">QuickChart</span>
      </router-link>
    </div>

    <!-- Navigation Links + Login -->
    <nav class="nav-links">
      <router-link to="/">Home</router-link>
      <router-link to="/login">About</router-link>
      <router-link to="/login">Services</router-link>
      <router-link to="/login" class="login-link">Login</router-link>
    </nav>
  </header>
</template>

<script>
import ComputerLogo from '../assets/computerLogo.svg';

export default {
  components: {
    ComputerLogo,
  },
};
</script>

<style scoped>
header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background: #8da9c7;
  color: white;
  padding: 1em;
  font-family: 'IBM Plex Mono', monospace;
}

/* Logo container (logo + name) */
.logo-container {
  display: flex;
  align-items: center;
  text-decoration: none;
}

/* Logo styling */
.logo-icon {
  height: 45px;
  fill: white;
  width: auto;
}

/* Logo name styling */
.logo-text {
  font-family: 'IBM Plex Mono', monospace;
  color: rgb(13, 44, 76);
  font-size: 1.5em;
  font-weight: bold;
  margin-left: 10px;
}

/* Navigation styling */
.nav-links {
  display: flex;
  flex: 1; /* Fill space */
  justify-content: flex-end; /* Push links to right */
  gap: 40px; /* Space between links */
}

.nav-links a {
  color: white;
  text-decoration: none;
  font-size: 1.1em;
  padding: 8px 12px;
}

/* Login link styling (button look) */
.login-link {
  background-color: #003366;
  color: white;
  text-decoration: none;
  font-weight: bold;
  padding: 8px 20px;
  border-radius: 50px;
  transition: background-color 0.3s ease;
}

.login-link:hover {
  background-color: #002244;
}
</style>
