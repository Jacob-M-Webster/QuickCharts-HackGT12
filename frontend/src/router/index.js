import { createRouter, createWebHistory } from 'vue-router';
import HomePage from '../pages/home.vue';
import LoginPage from '../pages/login.vue';
import RegistrationPage from '../pages/register.vue';
import ProfilePage from '../pages/profile.vue';
import QuestionnairePage from '../pages/questionnaire.vue';
import ServicePage from '../pages/Service.vue';

const routes = [
    { path: '/', name: 'Home', component: HomePage },
    { path: '/login', name: 'Login', component: LoginPage },
    { path: '/registration', name: 'Register', component: RegistrationPage },
    { path: '/profile', name: 'Profile', component: ProfilePage },
    { path: '/questionnaire', name: 'Questionnaire', component: QuestionnairePage },
    { path: '/services', name: 'Services', component: ServicePage },
];

const router = createRouter({
    history: createWebHistory(),
    routes,
    scrollBehavior() {
        return { top: 0 };
    },
});

export default router;
