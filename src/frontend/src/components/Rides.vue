<template>
  <div>
    <div :key="ride.id" v-for="ride in rides">
      <Ride :ride="ride" />
    </div>
  </div>
</template>

<script>
import Ride from "./Ride.vue";

export default {
  name: "Rides",
  components: {
    Ride,
  },
  props: {
    rides: Array,
  },
};
</script>

<style scoped></style>
