<template>
    <header>
        <div id="nav">
            <router-link to="/">Home</router-link> |
            <router-link to="/about">About</router-link> |
            <router-link to="/bbsMainList">Board</router-link> |
            <router-link to="/login" v-if="!this.$store.state.isLogin">Login</router-link>
            <a style="cursor:pointer;text-decoration:underline" v-if="this.$store.state.isLogin" @click="fnLogout()">Logout</a>
        </div>
    </header>
</template>

<script>
export default {
    name: 'AppHeader',
    props: {
        msg: String
    },
    methods: {
        fnLogout() {
            localStorage.removeItem("userToken");
            localStorage.removeItem("userRole");
            location.reload();
        }
    }
}

// window.onload = function() {
//     console.log("");
//     console.log("[header]")
// }
</script>



<style>

</style>