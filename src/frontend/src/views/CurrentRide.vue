<template>
  <div>
    <h1>Ride is on the way!</h1>
    <div class="imgcontainer">
      <img src="../assets/car.jpg" class="img" />
    </div>

    <div class="float-container">
      <div class="float-child">
        <Card>
          <template #title>
            <h2 style="text-align: center">Driver Details</h2>
          </template>
          <template #content>
            <p class="details">Name: {{ getDriverName() }}</p>
            <p class="details">Rating: {{ getDriverRating() }}⭐️</p>
            <p class="details">Car: {{ getCar() }}</p>
            <p class="details">License Number: {{ getLicense() }}</p>
          </template>
        </Card>
      </div>
      <div class="float-child">
        <Card>
          <template #title>
            <h2 style="text-align: center">Ride Details</h2>
          </template>
          <template #content>
            <p class="details">From: {{ getFrom() }}</p>
            <p class="details">To: {{ getTo() }}</p>
            <p class="details">Type: {{ getType() }}</p>
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
  mounted() {
    this.$store.dispatch("getVehicleDetails").then(
      (response) => {
        console.log(response.data);
        this.$store.commit("setDriverDets", {
          name: response.data["DriverName"],
          email: response.data["Email"],
          rating: response.data["Rating"],
          car: response.data["VehicleMake"],
          license: response.data["VehicleRegistration"],
        });

        this.$store
          .dispatch("addRide", {
            driverName: response.data["Email"],
            passEmail: this.$store.getters.getUserEmail,
            to: this.$store.getters.getTo,
            from: this.$store.getters.getFrom,
            fare: Math.floor(Math.random() * 300) + 100,
            tripID: Math.floor(Math.random() * 1000) + 1,
            vehicleReg: response.data["VehicleRegistration"],
          })
          .then(
            () => {
              // add getTrips here
            },
            () => {}
          );
      },
      () => {}
    );
  },
  methods: {
    onCancel() {
      this.$store.commit("setRideBooked", false);
      this.$router.push("feedback");
    },
    getTo() {
      return this.$store.getters.getTo;
    },
    getFrom() {
      return this.$store.getters.getFrom;
    },
    getType() {
      return this.$store.getters.getType;
    },
    getDriverName() {
      return this.$store.getters.getDriverName;
    },
    getDriverRating() {
      return this.$store.getters.getDriverRating;
    },
    getCar() {
      return this.$store.getters.getCar;
    },
    getLicense() {
      return this.$store.getters.getLicense;
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
