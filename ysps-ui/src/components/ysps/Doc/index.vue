<template>
  <div>
    <svg-icon icon-class="question" @click="goto" />
  </div>
</template>

<script>
export default {
  name: 'yspsDoc',
  data() {
    return {
      url: 'http://doc.ysps.vip/ysps-vue'
    }
  },
  methods: {
    goto() {
      window.open(this.url)
    }
  }
}
</script>