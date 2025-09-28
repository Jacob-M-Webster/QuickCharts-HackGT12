import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '../pages/home.vue';
import LoginPage from '../pages/login.vue';
import LogEdit from '../pages/logedit.vue';
import RegistrationPage from '../pages/register.vue';
import RegEdit from '../pages/regedit.vue';
import ProfilePage from '../pages/profile.vue';
import About from "../pages/about.vue";
import ServicePage from '../pages/service.vue';
import QuestionnairePage from '../pages/questionnaire.vue';
<<<<<<< HEAD
import SchedulePage from '../pages/schedule.vue'; // Make sure this is imported

const routes = [
    { path: '/', name: 'Home', component: HomePage },
    { path: '/login', name: 'Login', component: LogEdit }, // use your new login page
    { path: '/registration', name: 'Register', component: RegEdit },
    { path: '/profile', name: 'Profile', component: ProfilePage },
    { path: '/about', name: 'About', component: About },
    { path: '/services', name: 'Services', component: ServicePage },
    { path: '/questionnaire', name: 'Questionnaire', component: QuestionnairePage },
    { path: '/schedule', name: 'Schedule', component: SchedulePage },
=======
import Question2Page from '../pages/question2.vue'; // ✅ Import Question2.vue

const routes = [
  { path: '/', name: 'Home', component: HomePage },
  { path: '/login', name: 'Login', component: LoginPage },
  { path: '/registration', name: 'Register', component: RegistrationPage },
  { path: '/profile', name: 'Profile', component: ProfilePage },
  { path: "/about", name: "About", component: About },
  { path: '/services', name: "Services", component: ServicePage },
  { path: '/questionnaire', name: "Questionnaire", component: QuestionnairePage },
  { path: '/question2', name: "Question2", component: Question2Page }, // ✅ Add new route
>>>>>>> question2
];

const router = createRouter({
    history: createWebHistory(),
    routes,
    scrollBehavior() {
        return { top: 0 };
    },
});

export default router;
