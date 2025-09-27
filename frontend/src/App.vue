<template>
  <div id="app">
    <!-- Header -->
    <Header />

    <!-- Main content (router renders pages here) -->
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
/* Global styles if needed */
body {
  margin: 0;
  font-family: Arial, sans-serif;
}
</style>
