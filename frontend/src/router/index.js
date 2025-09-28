import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '../pages/home.vue';
import LoginPage from '../pages/login.vue';
import RegistrationPage from '../pages/register.vue';
import ProfilePage from '../pages/profile.vue';
import About from "../pages/about.vue";
import ServicePage from '../pages/service.vue';
import QuestionnairePage from '../pages/questionnaire.vue';


const routes = [
  { path: '/', name: 'Home', component: HomePage },
  { path: '/login', name: 'Login', component: LoginPage },
  { path: '/registration', name: 'Register', component: RegistrationPage },
  { path: '/profile', name: 'Profile', component: ProfilePage},
  { path: "/about", name: "About", component: About },
  { path: '/services', name: "Services", component: ServicePage },
  { path: '/questionnaire', name: "Questionnaire", component: QuestionnairePage },

];

const router = createRouter({
    history: createWebHistory(),
    routes,
    scrollBehavior() {
        return { top: 0 };
    },
});

export default router;
