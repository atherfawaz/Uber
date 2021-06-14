<template>
  <h1>Ride is on the way!</h1>
  <div class="imgcontainer">
    <img src="../images/car.jpg" class="img" />
  </div>

  <div class="float-container">
    <div class="float-child">
      <Card>
        <template #title>
          <h2 style="text-align: center">Driver Details</h2>
        </template>
        <template #content>
          <p class="details">Name: Muhammad Hamza</p>
          <p class="details">Rating: 4.8⭐️</p>
          <p class="details">Car: Suzuki Cultus</p>
          <p class="details">License Number: LZA-1234</p>
        </template>
      </Card>
    </div>
    <div class="float-child">
      <Card>
        <template #title>
          <h2 style="text-align: center">Ride Details</h2>
        </template>
        <template #content>
          <p class="details">From: Askari 11</p>
          <p class="details">To: Fast-NU</p>
          <p class="details">Type: Go</p>
          <p class="details" style="color: transparent">aa⭐️</p>
        </template>
      </Card>
    </div>
    <div style="text-align: center">
      <Button
        @click="onCancel"
        label="Cancel"
        icon="pi pi-trash"
        class="p-button-lg"
      />
    </div>
  </div>
</template>

<script>
import Card from "primevue/card";
import Button from "primevue/button";

export default {
  name: "CurrentRide",
  components: {
    Card,
    Button,
  },
  methods: {
    onCancel() {
      this.$router.push("profile");
    },
  },
};
</script>

<style scoped>
.imgcontainer {
  text-align: center;
}

.img {
  width: 700px;
  height: 450px;
  border: 5px solid #213266;
  border-radius: 5px;
}

h1 {
  text-align: center;
  color: #213266;
  font-size: 40px;
}

.float-container {
  padding: 20px;
}

.float-child {
  width: 50%;
  float: left;
  padding: 10px;
}

.details {
  font-weight: bold;
  font-size: 18px;
  text-align: center;
}
</style>
