<template>
  <div class="container">
    <Toast />
    <Card style="height: 100%; width: 100%">
      <template #title>
        <div id="title">Report Issue</div>
      </template>
      <template #content>
        <div>
          <p style="margin-bottom: 5px; margin-top: -2px">Trip ID</p>
          <InputText type="text" v-model="id" disabled />
        </div>
        <div>
          <p style="margin-bottom: 5px; margin-top: 5vh">Write Issue Here...</p>
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
import InputText from "primevue/inputtext";
import Textarea from "primevue/textarea";
import Button from "primevue/button";

export default {
  name: "Report",
  props: ["rideID"],
  components: {
    Card,
    InputText,
    Textarea,
    Button,
  },
  data() {
    return {
      id: 2987,
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
        summary: "Report Submitted",
        detail: "We'll get back to you as soon as possible.",
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
