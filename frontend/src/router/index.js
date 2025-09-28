import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '../pages/home.vue';
import LoginPage from '../pages/login.vue';
import RegistrationPage from '../pages/register.vue';
import ProfilePage from '../pages/profile.vue';
import About from "../pages/about.vue";


const routes = [
  { path: '/', name: 'Home', component: HomePage },
  { path: '/login', name: 'Login', component: LoginPage },
  { path: '/registration', name: 'Register', component: RegistrationPage },
  { path: '/profile', name: 'Profile', component: ProfilePage},
  { path: "/about", name: "About", component: About },

];

const router = createRouter({
  history: createWebHistory(),
  routes,
  scrollBehavior() {
    // Always scroll to top when navigating to a new route
    return { top: 0 };
  },
});

export default router;
