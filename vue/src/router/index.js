import { createRouter, createWebHistory } from 'vue-router'

import Layout from "../layout/Layout";

const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect:"/login",
    children:[
      {
        path: '/userdet',
        name: 'userdet',
        component: () => import("../views/userdet"),
      },{
        path:'/person',
        name:'Person',
        component: () => import("../views/Person")
      },{
        path: '/newsdet',
        name: 'Newsdet',
        component: () => import("../views/newsdet")
      },{
      path:'/reviewdet',
      name: 'Reviewdet',
      component:() => import("../views/reviewdet")
      },
      {
        path: '/adperson',
        name: 'adperson',
        component:() => import("../views/adperson")
      },{
      path:'/pass',
        name:'Pass',
        component:() => import("../views/pass")
      },{
      path: '/usnewdet',
        name:'usnewdet',
        component:() => import("../views/usnewdet")

      }
    ]
  },{
    path: '/login',
    name: 'Login',
    component: () => import("../views/Login")
  },{
    path: '/register',
    name: 'Register',
    component: () => import("../views/Register")
  }


]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
