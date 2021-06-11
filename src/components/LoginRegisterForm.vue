<template>
  <div class="form-demo">
    <Dialog
      v-model:visible="showMessage"
      :breakpoints="{ '960px': '80vw' }"
      :style="{ width: '30vw' }"
      position="top"
    >
      <div class="p-d-flex p-ai-center p-dir-col p-pt-6 p-px-3">
        <i
          class="pi pi-check-circle"
          :style="{ fontSize: '5rem', color: 'var(--green-500)' }"
        ></i>
        <h5>Registration Successful!</h5>
        <p :style="{ lineHeight: 1.5, textIndent: '1rem' }">
          Your account is registered under name <b>{{ name }}</b>
        </p>
      </div>
      <template #footer>
        <div class="p-d-flex p-jc-center">
          <Button label="OK" @click="toggleDialog" class="p-button-text" />
        </div>
      </template>
    </Dialog>

    <Card class="container">
      <template #content>
      <div class="p-d-flex p-jc-center">
        <div class="card">
          <h1 style="text-align: center">{{ current }}</h1>
          <form @submit.prevent="handleSubmit(!v$.$invalid)" class="p-fluid">
            <div class="p-field">
              <div class="p-float-label">
                <InputText
                  v-if="current === 'Register'"
                  id="name"
                  v-model="v$.name.$model"
                  :class="{ 'p-invalid': v$.name.$invalid && submitted }"
                />
                <label v-if="current === 'Register'"
                  for="name"
                  :class="{ 'p-error': v$.name.$invalid && submitted }"
                  >Name*</label
                >
              </div>
              <small
                v-if="
                  current === 'Register' && ((v$.name.$invalid && submitted)
                  || v$.name.$pending.$response)
                "
                class="p-error"
                >{{ v$.name.required.$message.replace("Value", "Name") }}</small
              >
            </div>
            <div class="p-field">
              <div class="p-float-label p-input-icon-right">
                <i class="pi pi-envelope" />
                <InputText
                  id="email"
                  v-model="v$.email.$model"
                  :class="{ 'p-invalid': v$.email.$invalid && submitted }"
                  aria-describedby="email-error"
                />
                <label
                  for="email"
                  :class="{ 'p-error': v$.email.$invalid && submitted }"
                  >Email*</label
                >
              </div>
              <span v-if="v$.email.$error && submitted">
                <span
                  id="email-error"
                  v-for="(error, index) of v$.email.$errors"
                  :key="index"
                >
                  <small class="p-error">{{ error.$message }}</small>
                </span>
              </span>
              <small
                v-else-if="
                  (v$.email.$invalid && submitted) || v$.email.$pending.$response
                "
                class="p-error"
                >{{ v$.email.required.$message.replace("Value", "Email") }}</small
              >
            </div>
            <div class="p-field">
              <div class="p-float-label">
                <Password
                  id="password"
                  v-model="v$.password.$model"
                  :class="{ 'p-invalid': v$.password.$invalid && submitted }"
                  toggleMask
                >
                  <template #header>
                    <h6>Pick a password</h6>
                  </template>
                  <template #footer="sp">
                    {{ sp.level }}
                    <Divider />
                    <p class="p-mt-2">Suggestions</p>
                    <ul class="p-pl-2 p-ml-2 p-mt-0" style="line-height: 1.5">
                      <li>At least one lowercase</li>
                      <li>At least one uppercase</li>
                      <li>At least one numeric</li>
                      <li>Minimum 8 characters</li>
                    </ul>
                  </template>
                </Password>
                <label
                  for="password"
                  :class="{ 'p-error': v$.password.$invalid && submitted }"
                  >Password*</label
                >
              </div>
              <small
                v-if="
                  (v$.password.$invalid && submitted) ||
                  v$.password.$pending.$response
                "
                class="p-error"
                >{{
                  v$.password.required.$message.replace("Value", "Password")
                }}</small
              >
            </div>
            <Button type="submit" :label="current" class="p-mt-2" />
            <Button @click="$router.push(opposite.toLowerCase())"
          :label="opposite" class="p-mt-2 p-button-outlined" style="margin-top: 16px"/>
          </form>
        </div>
      </div>
      </template>
    </Card>
  </div>
</template>

<script>
import { email, required } from '@vuelidate/validators';
import { useVuelidate } from '@vuelidate/core';
import Button from 'primevue/button';
import Dialog from 'primevue/dialog';
import InputText from 'primevue/inputtext';
import Password from 'primevue/password';
import Card from 'primevue/card';
import Divider from 'primevue/divider';

export default {
  setup: () => ({ v$: useVuelidate() }),
  data() {
    return {
      name: '',
      email: '',
      password: '',
      submitted: false,
      showMessage: false,
    };
  },
  validations() {
    return {
      name: {
        required,
      },
      email: {
        required,
        email,
      },
      password: {
        required,
      },
    };
  },
  props: {
    current: String,
    opposite: String,
  },
  components: {
    Button,
    Dialog,
    InputText,
    Password,
    Card,
    Divider,
  },
  methods: {
    handleSubmit(isFormValid) {
      this.submitted = true;

      if (!isFormValid) {
        return;
      }

      this.toggleDialog();
    },
    toggleDialog() {
      this.showMessage = !this.showMessage;

      if (!this.showMessage) {
        this.resetForm();
      }
    },
    resetForm() {
      this.name = '';
      this.email = '';
      this.password = '';
      this.submitted = false;
    },
  },
};
</script>

<style scoped>
.form-demo .card {
  min-width: 450px;
}
.form-demo .card form {
  margin-top: 2rem;
}
.form-demo .card .p-field {
  margin-bottom: 1.5rem;
}
@media screen and (max-width: 960px) {
  .form-demo .card {
    width: 80%;
  }
}
.container {
  position: absolute;
  top: 50%;
  left: 50%;
  -moz-transform: translateX(-50%) translateY(-50%);
  -webkit-transform: translateX(-50%) translateY(-50%);
  transform: translateX(-50%) translateY(-50%);
}
</style>
