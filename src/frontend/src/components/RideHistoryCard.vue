<template>
  <div class="container">
    <Card class="card" style="height: 100%; width: 100%">
      <template #subtitle> Ride History </template>
      <template #content>
        <ScrollPanel
          id="scroll"
          style="width: 100%; height: 52vh; margin-top: -2vh"
        >
          <Rides :rides="$store.getters.getRides" />
        </ScrollPanel>
      </template>
    </Card>
  </div>
</template>

<script>
import Card from "primevue/card";
import ScrollPanel from "primevue/scrollpanel";
import Rides from "./Rides.vue";

export default {
  name: "RideHistoryCard",
  components: {
    Card,
    ScrollPanel,
    Rides,
  },
  mounted() {
    this.$store.dispatch("getTrips").then(
      (res) => {
        let d = res.data;
        let trips = [];
        for (const key in d) {
          trips.push(d[key]);
        }
        console.log(trips);
        this.$store.commit("setRides", trips);
      },
      () => {}
    );
  },
};
</script>

<style scoped>
.container {
  height: 100%;
  width: 100%;
  padding: 2px;
}
.card {
  width: 100%;
}
</style>
