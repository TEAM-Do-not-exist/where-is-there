import vue from 'vue';
import router from 'vue-router';
import index from './components/index';

vue.use(router);

export default new router({
    mode : 'history',
    routes : [
        {
            path : '/',
            component : index
        }
    ]
})