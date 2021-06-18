<template>
  <div class="container">
    <Toast />
    <Card style="height: 100%; width: 100%">
      <template #title>
        <div id="title">Feedback</div>
      </template>
      <template #content>
        <div>
          <p
            style="
              margin-bottom: 5px;
              margin-top: 5vh;
              font-weight: bold;
              font-size: 20px;
            "
          >
            How was the ride?
          </p>
          <Textarea style="height: 50vh; width: 40vw" />
        </div>
        <div id="btn-div">
          <Button id="" label="Submit" @click="submit" />
        </div>
      </template>
    </Card>
  </div>
</template>

<script>
import Card from "primevue/card";
import Textarea from "primevue/textarea";
import Button from "primevue/button";

export default {
  name: "Report",
  props: ["rideID"],
  components: {
    Card,
    Textarea,
    Button,
  },
  data() {
    return {
      id: 2987,
      star_pressed: "pi pi-star",
      star_npressed: "pi pi-star-o",
    };
  },
  created() {
    console.log(this.rideID);
  },
  methods: {
    submit() {
      console.log(this.id);
      this.$toast.add({
        severity: "success",
        summary: "Feedback Submitted",
        detail: "Thank you for your valuable feedback!",
        life: 2500,
      });
      setTimeout(() => this.$router.push({ name: "Profile" }), 2500);
    },
  },
};
</script>

<style scoped>
div {
  /* border-style: solid; */
  border-color: red;
  text-align: center;
  font-family: "Roboto Slab", serif;
}
#title {
  color: rgb(255, 213, 79);
  font-weight: 900;
  font-size: 30px;
}
#btn-div {
  margin-top: 15px;
}
.container {
  height: 97vh;
  width: 98vw;
  padding: 2px;
}
</style>
