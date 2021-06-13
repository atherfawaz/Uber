<template>
  <div>
    <LoginRegisterForm current="Login" opposite="Register" />
  </div>
</template>

<script>
import LoginRegisterForm from "../components/LoginRegisterForm.vue";

export default {
  name: "Login",
  components: {
    LoginRegisterForm,
  },
};
</script>

<style></style>
