<template>
  <div class="container">
    <Card class="card" style="height: 100%; width: 100%">
      <template #subtitle> Profile Details </template>
      <template #content>
        <div id="outer-div">
          <div id="image">
            <img
              src="https://pbs.twimg.com/media/EnyMTfFUcAACQny?format=jpg&name=medium"
            />
          </div>
          <div id="info">
            <p id="p-name">John Doe</p>
            <p id="p-rating">4.8⭐</p>
            <p id="p-email">johndoe@gmail.com</p>
          </div>
          <div id="btn">
            <Button
              id="btn"
              icon="pi pi-sign-out"
              @click="goToHome"
              label="Log Out"
            />
          </div>
        </div>
      </template>
    </Card>
  </div>
</template>

<script>
import Card from "primevue/card";
import Button from "primevue/button";

export default {
  name: "ProfileCard",
  components: {
    Card,
    Button,
  },
  methods: {
    goToHome() {
      this.$router.push({ name: "Home" });
    },
  },
};
</script>

<style scoped>
/* div {
  border-style: solid;
} */
.container {
  height: 100%;
  width: 100%;
  padding: 2px;
}
#outer-div {
  border-color: green;
  display: flex;
  height: 100%;
  width: 100%;
}
#image {
  border-color: blue;
  margin-top: 1vh;
  margin-left: 5vh;
}
img {
  width: 105px;
  border-radius: 50%;
  border-style: outset;
}
#info {
  border-color: red;
  height: 100%;
  width: 47%;
  margin-top: -1vh;
  margin-left: 10vh;
}
p {
  font-family: "Roboto Slab", serif;
  margin-bottom: -2px;
}
#btn {
  border-color: yellow;
  margin-top: -4vh;
  margin-left: 1vw;
}
</style>
