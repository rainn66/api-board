<template>
  <vueHeader></vueHeader>
  
  <vueFooter></vueFooter>
</template>

<script>
import vueHeader from './components/vueHeader.vue'
import vueFooter from './components/vueFooter.vue'

export default {
  name: 'App',
  components: {
    vueHeader
    , vueFooter
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}




</style>
