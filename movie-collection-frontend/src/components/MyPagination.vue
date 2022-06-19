<template>
  <div class="pagination">
    <div class="onPageChange">
      <span>ON PAGE:</span>
      <select v-model="perPage">
        <option value="10">10</option>
        <option value="20">20</option>
        <option value="30">30</option>
      </select>
    </div>
    <ul>
      <li class="paginationElement prev-next" @click="changeCurrentPage('Prev')">Prev</li>
      <!-- ToDo fix css circle reset -->
      <li class="paginationElement" @click="changeCurrentPage(n)" :class="{currentPageStyle : n === currentPage}"
          v-for="n in this.pagination.totalPages" :key="n">{{n}}</li>
      <li class="paginationElement prev-next" @click="changeCurrentPage('Next')">Next</li>
    </ul>
  </div>
</template>

<script>
export default {
  name: "MyPagination",
  props: {
    pagination: {
      empty: null,
      first: null,
      last: null,
      currentPage: 1,
      perPage: 10,
      totalElements: null,
      totalPages: null,
    },
  },
  mounted() {
    this.currentPage = this.pagination.currentPage;
    this.perPage = this.pagination.perPage;
  },
  data() {
    return {
      currentPage: null,
      perPage: null,
    }
  },
  methods: {
    changeCurrentPage(value) {
      if (value === 'Prev' && this.pagination.first === false) {
        this.currentPage = this.currentPage - 1;
        this.$emit('changeCurrentPage', this.currentPage);
      } else if (value === 'Next' && this.pagination.last === false) {
        this.currentPage = this.currentPage + 1;
        this.$emit('changeCurrentPage', this.currentPage);
      } else if (typeof value === 'number' && value !== this.currentPage && value >= 1 && value <= this.pagination.totalPages){
        this.currentPage = value;
        this.$emit('changeCurrentPage', this.currentPage);
      }
    },
  },
  watch: {
    perPage(value) {
      this.$emit('changePerPage', value);
    },
  }
}
</script>

<style scoped>

div.pagination {
  font-size: 1.2rem;
  margin: 2rem 1.5rem;
}

div.pagination div.onPageChange {
  display: inline-block;
  margin-left: 2.1rem;
  padding: 0.2rem 1rem;
  font-weight: bold;
  border-radius: 5px;
  background-color: black;
  color: orange;
}

div.pagination div.onPageChange select {
  font-size: 1.2rem;
  color: white;
  background-color: transparent;
  padding: 0.1rem;
  margin-left: 0.5rem;
  border: none;
}

div.pagination div.onPageChange select:hover {
  cursor: pointer
}

div.pagination div.onPageChange select option {
  background-color: black;
  color: orange;
}

div.pagination ul {
  display: inline-block;
  width: 66.5%;
  text-align: center;
}

div.pagination ul li.paginationElement {
  display: inline-block;
  padding: 0.3rem 0.6rem;
  margin: 0.2rem 0.2rem;
}

div.pagination ul li.paginationElement:hover {
  cursor: pointer;
  font-weight: bold;
}

div.pagination ul li.prev-next {
  border-radius: 4px;
  background-color: black;
  color: orange;
}

li.currentPageStyle {
  color: black;
  font-weight: bold;
  border: 2px solid black;
  border-radius: 60%;
}

</style>