import vue from 'vue';
import router from 'vue-router';
import index from './components/index';
import testpage from './components/testpage';
import signin from "./components/SignIn"
import signup from "./components/SignUp"

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
            path : '/signup',
            component : signup
        },
        {
            path : '/signin',
            component : signin
        },
    ]
})