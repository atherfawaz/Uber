<template>
  <div class="form-demo">
    <Card class="container">
      <template #content>
        <div class="p-d-flex p-jc-center">
          <div class="card">
            <h1 style="text-align: center">Book Ride</h1>
            <form @submit.prevent="handleSubmit(!v$.$invalid)" class="p-fluid">
              <Toast />
              <div class="p-field">
                <div class="p-float-label">
                  <InputText
                    id="from"
                    v-model="v$.from.$model"
                    :class="{ 'p-invalid': v$.from.$invalid && submitted }"
                  />
                  <label
                    for="from"
                    :class="{ 'p-error': v$.from.$invalid && submitted }"
                    >From*</label
                  >
                </div>
                <small
                  v-if="
                    (v$.from.$invalid && submitted) ||
                    v$.from.$pending.$response
                  "
                  class="p-error"
                  >{{
                    v$.from.required.$message.replace("Value", "From")
                  }}</small
                >
              </div>
              <div class="p-field">
                <div class="p-float-label">
                  <InputText
                    id="to"
                    v-model="v$.to.$model"
                    :class="{ 'p-invalid': v$.to.$invalid && submitted }"
                  />
                  <label
                    for="to"
                    :class="{ 'p-error': v$.to.$invalid && submitted }"
                    >To*</label
                  >
                </div>
                <small
                  v-if="
                    (v$.to.$invalid && submitted) || v$.to.$pending.$response
                  "
                  class="p-error"
                  >{{ v$.to.required.$message.replace("Value", "To") }}</small
                >
              </div>
              <div>
                <Dropdown
                  v-model="selectedType"
                  :options="types"
                  optionLabel="name"
                  :placeholder="'Select a Type, default is ' + selectedType"
                  style="margin-bottom: 30px"
                />
              </div>
              <Button type="submit" label="Chalo!" class="p-mt-2" />
            </form>
          </div>
        </div>
      </template>
    </Card>
  </div>
</template>

<script>
import { required } from "@vuelidate/validators";
import { useVuelidate } from "@vuelidate/core";
import Button from "primevue/button";
import Toast from "primevue/toast";
import InputText from "primevue/inputtext";
import Card from "primevue/card";
import Dropdown from "primevue/dropdown";

export default {
  setup: () => ({ v$: useVuelidate() }),
  data() {
    return {
      from: "",
      to: "",
      submitted: false,
      showMessage: false,
      selectedType: "Go",
      types: [
        { name: "Go", code: "Go" },
        { name: "Go+", code: "GoP" },
        { name: "VIP", code: "VIP" },
      ],
    };
  },
  validations() {
    return {
      from: {
        required,
      },
      to: {
        required,
      },
    };
  },
  components: {
    Button,
    Toast,
    InputText,
    Card,
    Dropdown,
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
        this.$store.commit("setRideBooked", true);
        this.$store.commit("setRideDets", {
          to: this.to,
          from: this.from,
          type: this.selectedType.name,
        });
        this.$toast.add({
          severity: "success",
          summary: "Ride Booked",
          detail: "Be Patient, the driver is on the way!",
          life: 2500,
        });
        setTimeout(() => this.$router.push("/currentride"), 2500);
      }
    },
    resetForm() {
      this.from = "";
      this.to = "";
      this.submitted = false;
      this.selectedType = "";
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
