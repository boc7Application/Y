<template>
  <div>
    <router-view/>
  </div>
  </div>
</template>

<script>
import {Divider, Tabbar, TabbarItem} from 'vux'
export default {
  name: 'App',
  components: {
    Divider,
    Tabbar,
    TabbarItem
  },
  created () {
  },
  methods: {
    
  }
  // data() {
  //   return {
  //     dot : false
  //   }
  // }
}
</script>

<style>
.full-screen {
    background-color: #212239;
  }
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /*margin-top: 60px;*/
}
#bar {
  position: fixed;
}
#bar p{
  margin: 5px 0;
}
#bar>a>div{
  margin-top: 6px;
}

#divider {
  margin-top: 20px;
  margin-bottom: 80px;
}

</style>
