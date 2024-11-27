<template>
  <Header />
  <!--    侧边栏-->
  <div style="display: flex">
    <aside-com />
    <router-view style="flex: 1"/>
  </div>
</template>

<script>
import Header from "../components/Header";
import asideCom from "../components/asideCom";
export default {
  name: "Layout",
  components:{
    Header,
    asideCom
  }
}
</script>

<style scoped>

</style>