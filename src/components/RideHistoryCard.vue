<template>
  <div class="container">
    <Card class="card" style="height: 100%; width: 100%">
      <template #subtitle> Ride History </template>
      <template #content>
        <ScrollPanel
          id="scroll"
          style="width: 100%; height: 52vh; margin-top: -2vh"
        >
          <Rides :rides="rides" />
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
  data() {
    return {
      rides: [
        {
          id: 1,
          start: "Lahore",
          destination: "Islamabad",
          driver: "Jane Doe",
          cost: "PKR 230",
        },
        {
          id: 2,
          start: "Lahore",
          destination: "Islamabad",
          driver: "Jane Doe",
          cost: "PKR 230",
        },
        {
          id: 3,
          start: "Lahore",
          destination: "Islamabad",
          driver: "Jane Doe",
          cost: "PKR 230",
        },
        {
          id: 4,
          start: "Lahore",
          destination: "Islamabad",
          driver: "Jane Doe",
          cost: "PKR 230",
        },
        {
          id: 5,
          start: "Lahore",
          destination: "Islamabad",
          driver: "Jane Doe",
          cost: "PKR 230",
        },
        {
          id: 6,
          start: "Lahore",
          destination: "Islamabad",
          driver: "Jane Doe",
          cost: "PKR 230",
        },
        {
          id: 7,
          start: "Lahore",
          destination: "Islamabad",
          driver: "Jane Doe",
          cost: "PKR 230",
        },
        {
          id: 8,
          start: "Lahore",
          destination: "Islamabad",
          driver: "Jane Doe",
          cost: "PKR 230",
        },
        {
          id: 9,
          start: "Lahore",
          destination: "Islamabad",
          driver: "Jane Doe",
          cost: "PKR 230",
        },
        {
          id: 10,
          start: "Lahore",
          destination: "Islamabad",
          driver: "Jane Doe",
          cost: "PKR 230",
        },
        {
          id: 11,
          start: "Lahore",
          destination: "Islamabad",
          driver: "Jane Doe",
          cost: "PKR 230",
        },
        {
          id: 12,
          start: "Lahore",
          destination: "Islamabad",
          driver: "Jane Doe",
          cost: "PKR 230",
        },
      ],
    };
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
