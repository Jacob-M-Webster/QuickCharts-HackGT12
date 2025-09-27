<template>
  <div>
    <header>My App Header</header>
    <router-view />  <!-- renders the current page -->
    <footer>Footer</footer>
  </div>
</template>

<script>
export default {
  name: "App"
};
</script>