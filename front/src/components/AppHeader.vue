<template>
  <header>
    <div id="nav">
      <a href="/">Home</a>
    </div>
  </header>
</template>

<script>
export default {
    name: 'vueHeader',
    props: {
        msg: String
    }
}

window.onload = function() {
    console.log("");
    console.log("[header]")
}
</script>



<style>

</style>