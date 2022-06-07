<template>
    <div class="modal">
      <p class="title"><font-awesome-icon icon="fa-solid fa-circle-exclamation"/></p>
      <p>Title cannot be empty string!</p>
    </div>
</template>

<script>
export default {
  name: "ErrorModal",
  props: {
    errorModal: {
      errorTitle: {
        required: true,
        type: String
      },
      errorMessage: {
        required: true,
        type: String
      }
    }
  }
}
</script>

<style scoped>

.modal {
  position: fixed;
  width: 330px;
  height: 150px;
  text-align: center;
  top: 10px;
  right: 10px;
  font-size: 1.9rem;
  display: block;
  color: #D8000C;
  letter-spacing: 1px;
  padding: 10px;
  background-color: #FFBABA;
  border-radius: 15px;
  border: 4px solid #D8000C;
}

.modal p{
  margin: 10px;
}
</style>