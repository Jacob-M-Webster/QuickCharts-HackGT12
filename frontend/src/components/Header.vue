<template>
  <header>
    <!-- Logo -->
    <div class="logo">
      <router-link to="/">
        <ComputerLogo class="logo-icon" />
      </router-link>
    </div>

    <!-- Navigation Links -->
    <nav>
      <router-link to="/">Home</router-link>
      <router-link to="/Login">About</router-link>
      <router-link to="/Login">Services</router-link>
    </nav>

    <!-- Login Link -->
    <div class="profile">
      <router-link to="/Login" class="login-link">Login</router-link>
    </div>
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
}

/* Logo styling */
.logo-icon {
  height: 45px;     /* size of logo */
  fill: white;      /* color of logo (change this!) */
  width: auto;
}

/* Navigation styling */
nav a {
  color: white;
  margin: 0 10px;
  text-decoration: none;
}

nav a.router-link-active {
  font-weight: bold;
}

/* Login link styling */
.login-link {
  color: white;
  text-decoration: none;
  font-weight: bold;
}

.login-link:hover {
  text-decoration: underline;
}
</style>
