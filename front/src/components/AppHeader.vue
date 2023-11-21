<template>
    <header>
        <div id="nav">
            <router-link to="/">Home</router-link> |
            <router-link to="/about">About</router-link> |
            <router-link to="/bbsMainList">Board</router-link> |
            <router-link to="/login">Login</router-link>
        </div>
    </header>
</template>

<script>
export default {
    name: 'AppHeader',
    props: {
        msg: String
    }
}

// window.onload = function() {
//     console.log("");
//     console.log("[header]")
// }
</script>



<style>

</style>