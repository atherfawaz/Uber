<template>
  <div class="container">
    <Card class="card" style="height: 100%; width: 100%">
      <template #subtitle> Profile Details </template>
      <template #content>
        <div id="outer-div">
          <div id="image">
            <img src="../assets/user.png" />
          </div>
          <div id="info" v-if="dataPresent">
            <p id="p-name">{{ getName() }}</p>
            <p id="p-rating">{{ getRating() }} ⭐</p>
            <p id="p-email">{{ getEmail() }}</p>
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
  data() {
    return {
      dataPresent: false,
    };
  },
  methods: {
    goToHome() {
      this.$store.commit("authenticated", false);
      this.$store.commit("setUser", "", "", "", "");
      this.$router.push({ name: "Login" });
    },
    getName() {
      return this.$store.getters.getName;
    },
    getRating() {
      return this.$store.getters.getRating;
    },
    getEmail() {
      return this.$store.getters.getEmail;
    },
  },
  mounted() {
    this.$store.dispatch("getPassengerDetails").then(
      (response) => {
        this.$store.commit("setUser", {
          email: response.data["Email"],
          name: response.data["Name"],
          rating: parseFloat(response.data["Rating"]),
          balance: parseFloat(response.data["Balance"]),
        });
        this.dataPresent = true;
      },
      () => {}
    );
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
