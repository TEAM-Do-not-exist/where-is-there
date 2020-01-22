<template>
    <div>
        <v-container>
        </v-container>
    </div>
</template>

<script>
import axios from 'axios'
export default {
    data() {
        return {
            id: "",
            pw: "",
            res: null,
        }
    },
    methods: {
        send() {
            axios
            .get("")
            .then(response => {
                this.res = response.data;
                this.router.push("testpage");
            })
        }
    }
}
</script>