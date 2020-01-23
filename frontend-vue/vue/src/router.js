import vue from 'vue';
import router from 'vue-router';
import index from './components/index';
import testpage from './components/testpage';
import Login from './views/Login.vue';
import ChatRoom from './views/ChatRoom.vue';
import signin from './components/SignIn';
import signup from './components/SignUp';
vue.use(router);

export default new router({
    mode : 'history',
    routes : [
        {
            name : 'index',
            path : '/',
            component : index,
            alias : '/index'
        },
        {
            path : '/test',
            component : testpage
        },
        {
            path: '/chat',
            name: 'Login',
            component: Login,
        },
        {
            path : '/signin',
            component : signin
        },
        {
            path: '/signup',
            component: signup,
        },
        {
            path: '/char-room/:username',
            name: 'ChatRoom',
            component: ChatRoom,
        },
    ]
})