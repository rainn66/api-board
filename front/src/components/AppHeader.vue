<template>
    <header>
        <div id="nav">
            <router-link to="/login" v-if="!this.$store.state.isLogin" style="margin-right:30px;">Login</router-link>
            <router-link to="/signUp" v-if="!this.$store.state.isLogin">SignUp</router-link>
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
            location.reload();
        }
    }
}

//브라우저 종료시 회원정보 제거
// window.addEventListener("unload", deleteToken)
// function deleteToken() {
//     localStorage.removeItem("payload")
//     localStorage.removeItem("userToken")
//     localStorage.removeItem("refresh")
// }

// window.onload = function() {
//     console.log("");
//     console.log("[header]")
// }
</script>



<style>

</style>