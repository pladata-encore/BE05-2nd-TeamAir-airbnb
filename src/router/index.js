import { createWebHistory, createRouter } from "vue-router";

const routes = [
    // {
    //   path: "/login",
    //   component: LoginView,
    // },
  ];
  const router = createRouter({
    history: createWebHistory(),
    routes,
  });
  export default router;