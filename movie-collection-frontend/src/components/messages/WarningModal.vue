<template>
  <div class="modal">
    <p class="title"><font-awesome-icon icon="fa-solid fa-triangle-exclamation" /></p>
    <p>{{ this.warningMessage }}</p>
  </div>
</template>

<script>
export default {
  name: "WarningModal",
  props: {
    warningMessage: {
      type: String,
      required: true
    }
  },
}
</script>

<style scoped>
.modal {
  position: fixed;
  width: 300px;
  height: 100px;
  text-align: center;
  top: 10px;
  right: 20px;
  font-size: 1.2rem;
  display: block;
  letter-spacing: 1px;
  padding: 10px;
  border-radius: 15px;
  border: 3px solid #9F6000;
  color: #9F6000;
  background-color: #FEEFB3;
}

.modal p{
  margin: 10px;
}
</style>