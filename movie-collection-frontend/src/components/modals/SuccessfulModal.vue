<template>
  <div class="modal">
    <p class="title"><font-awesome-icon icon="fa-solid fa-circle-check" /></p>
    <p>Successful film editing!</p>
  </div>
</template>

<script>
export default {
  name: "SuccessfulModal"
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
  font-size: 1rem;
  display: block;
  letter-spacing: 1px;
  padding: 10px;
  border-radius: 15px;
  border: 3px solid #4F8A10;
  color: #4F8A10;
  background-color: #DFF2BF;
}

.modal p{
  margin: 10px;
}
</style>