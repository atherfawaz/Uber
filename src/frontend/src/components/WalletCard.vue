<template>
  <div class="container">
    <Card class="card" style="height: 100%; width: 100%">
      <template #subtitle> Wallet Details </template>
      <template #content>
        <div>
          <p id="p-balance">{{ getBalance() }}</p>
          <p id="p-text">Remaining Balance</p>
        </div>
      </template>
      <template id="footer" #footer> </template>
    </Card>
  </div>
</template>

<script>
import Card from "primevue/card";

export default {
  name: "WalletCard",
  components: {
    Card,
  },
  methods: {
    getBalance() {
      return this.$store.getters.getBalance;
    },
  },
};
</script>

<style scoped>
div {
  /* border-style: solid; */
  border-color: red;
}
.container {
  height: 100%;
  width: 100%;
  padding: 2px;
}
.card {
  width: 100%;
}
p {
  text-align: center;
  font-family: "Roboto Slab", serif;
}
#p-balance {
  font-size: 40px;
  margin-top: 2.5vh;
  font-weight: 700;
}
#p-text {
  font-size: 13px;
}
</style>
