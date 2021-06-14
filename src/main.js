import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import PrimeVue from "primevue/config";

import "primevue/resources/themes/arya-orange/theme.css";
import "primevue/resources/primevue.min.css";
import "primeicons/primeicons.css";

import Toast from "primevue/toast";
import ToastService from "primevue/toastservice";

createApp(App)
  .use(router)
  .use(PrimeVue, { ripple: true })
  .use(ToastService)
  .component("Toast", Toast)
  .mount("#app");
