import vue from 'vue';
import router from 'vue-router';
import index from './components/index';
import testpage from './components/testpage';
import Login from './views/Login.vue';
import ChatRoom from './views/ChatRoom.vue';
vue.use(router);

export default new router({
    mode : 'history',
    routes : [
        {
            path : '/',
            component : index
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
            path: '/char-room/:username',
            name: 'ChatRoom',
            component: ChatRoom,
          },
    ]
})