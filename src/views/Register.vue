<template>
  <div>
    <LoginRegisterForm current="Register" opposite="Login" />
  </div>
</template>

<script>
import LoginRegisterForm from '../components/LoginRegisterForm.vue';

export default {
  name: 'Register',
  components: {
    LoginRegisterForm,
  },
};
</script>

<style>

</style>
