<template>
  <div id="container">
    <Toast />
    <div id="top-div">
      <div id="profile-div">
        <ProfileCard />
      </div>
      <div id="wallet-div">
        <WalletCard />
      </div>
      <div id="button-div">
        <Button
          @click="$router.push('bookride')"
          id="btn"
          label="Book Ride"
          style="height: 100%; width: 100%"
        />
      </div>
    </div>
    <div id="bottom-div">
      <RideHistoryCard />
    </div>
  </div>
</template>

<script>
import Button from "primevue/button";
import ProfileCard from "../components/ProfileCard.vue";
import WalletCard from "../components/WalletCard.vue";
import RideHistoryCard from "../components/RideHistoryCard.vue";

export default {
  name: "Profile",
  components: {
    Button,
    ProfileCard,
    WalletCard,
    RideHistoryCard,
  },
  data() {
    return {
      rating: 0,
      balance: 0,
    };
  },
};
</script>

<style scoped>
#container {
  height: 95vh;
  width: 98vw;
}
#top-div {
  height: 35%;
  width: 100%;
  display: flex;
}
#profile-div {
  height: 100%;
  width: 55%;
}
#wallet-div {
  height: 100%;
  width: 25%;
}
#button-div {
  padding: 2px;
  height: 100%;
  width: 20%;
}
#btn {
  font-size: 24px;
  font-weight: 800;
}
#bottom-div {
  height: 65%;
  width: 100%;
}
</style>
