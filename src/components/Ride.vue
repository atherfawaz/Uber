<template>
  <div id="container">
    <div id="left-div" class="inner">
      <p class="location">🔵{{ ride.start }}</p>
      <p id="line" class="location">⇣</p>
      <p class="location">🟢{{ ride.destination }}</p>
    </div>
    <div id="mid-div" class="inner">
      <p id="driver">{{ ride.driver }}</p>
    </div>
    <div id="right-div" class="inner">
      <p id="cost">{{ ride.cost }}</p>
    </div>
    <div id="report-div" class="inner">
      <Button
        id="btn"
        icon="pi pi-exclamation-triangle"
        @click="goToHome"
        label="Report"
        style="height: 100%; width: 100%"
      />
    </div>
  </div>
</template>

<script>
import Button from "primevue/button";

export default {
  name: "Ride",
  props: {
    ride: Object,
  },
  components: {
    Button,
  },
};
</script>

<style scoped>
div {
  /* border-style: solid; */
  border-color: red;
}
.location {
  margin: 0px;
}
.inner {
  margin: 6px;
}
#report-div {
  margin: 10px;
}
#left-div {
  margin-left: 1.5vw;
}
#mid-div,
#right-div {
  margin-top: 1vh;
}
#driver,
#cost {
  font-size: 20px;
  font-weight: 580;
}
#cost {
  font-style: italic;
}
#container {
  display: flex;
  justify-content: space-between;
  background-color: rgba(253, 245, 230, 0.1);
  margin-bottom: 6px;
  border-radius: 6px;
}
p {
  text-align: center;
  font-family: "Roboto Slab", serif;
}
.location {
  text-align: left;
}
#line {
  margin-left: 7px;
}
</style>
