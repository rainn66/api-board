<template>
    <SideBar class="side-bar"/>
</template>

<script>
import SideBar from "@/components/SideBar";


export default {
    name: 'App',
    components: {
        SideBar
    }
}
</script>

<style lang="scss">

#app {
    font-family: Avenir, Helvetica, Arial, sans-serif;
    position:absolute;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;

    > .side-bar {
        position: absolute;
        left: 0;
        top: 0;
        bottom: 0;
    }
}




</style>
