<template>
    <AppHeader></AppHeader>
    <SideBar class="side-bar"/>
    <router-view :key="$route.fullPath"/>
    <AppFooter></AppFooter>
</template>

<script>
import SideBar from "@/components/SideBar";
import AppHeader from "@/components/AppHeader";
import AppFooter from "@/components/AppFooter";


export default {
    name: 'App',
    components: {
        SideBar,
        AppHeader,
        AppFooter
    }
}
</script>

<style lang="scss">


#app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-left:200px;

    > .side-bar {
        position: absolute;
        left: 0;
        top: 0;
        bottom: 0;
    }
}




</style>
