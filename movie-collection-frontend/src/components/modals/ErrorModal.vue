<template>
    <div class="modal">
      <p class="title"><font-awesome-icon icon="fa-solid fa-circle-exclamation"/></p>
      <p>{{ this.errorMessage }}</p>
    </div>
</template>

<script>
export default {
  name: "ErrorModal",
  props: {
    errorMessage: {
      type: String,
      required: true
    }
  }
}
</script>

<style scoped>

.modal {
  position: fixed;
  width: 300px;
  height: 100px;
  text-align: center;
  top: 10px;
  right: 10px;
  font-size: 1.2rem;
  display: block;
  color: #D8000C;
  letter-spacing: 1px;
  padding: 10px;
  background-color: #FFBABA;
  border-radius: 15px;
  border: 3px solid #D8000C;
}

.modal p{
  margin: 5px;
}
</style>