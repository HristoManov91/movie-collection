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
      <li class="paginationElement prev-next" @click="changeCurrentPage('First')">First</li>
      <li class="paginationElement prev-next" @click="changeCurrentPage('Prev')">Prev</li>
      <li class="paginationElement" @click="changeCurrentPage(value)"
          :class="{currentPageStyle : value === currentPage}"
          v-for="(value , index) in this.offset" :key="index">{{ value }}
      </li>
      <li class="paginationElement prev-next" @click="changeCurrentPage('Next')">Next</li>
      <li class="paginationElement prev-next" @click="changeCurrentPage('Last')">Last</li>
    </ul>
    <p class="totalPages">Total pages: {{pagination.totalPages}}</p>
  </div>
</template>

<script>
export default {
  name: "MyPagination",
  props: {
    pagination: {
      empty: Boolean,
      first: Boolean,
      last: Boolean,
      currentPage: Number,
      perPage: Number,
      totalElements: Number,
      totalPages: Number,
    },
  },
  data() {
    return {
      currentPage: 1,
      perPage: 10,
      offset: [1,2,3,4,5]
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

      } else if (typeof value === 'number' && value !== this.currentPage && value >= 1 && value <= this.pagination.totalPages) {

        this.currentPage = value;
        this.$emit('changeCurrentPage', this.currentPage);

      } else if (value === 'First' && !this.pagination.first) {

        this.currentPage = 1;
        this.$emit('changeCurrentPage', this.currentPage);

      } else if (value === 'Last' && !this.pagination.last) {

        this.currentPage = this.pagination.totalPages;
        this.$emit('changeCurrentPage', this.currentPage)
      }

      this.setOffset()
    },
    setOffset(){

      if (this.pagination.totalPages < 5) {

        this.offset = [];
        let count = 1;
        for (let i = 0; i < this.pagination.totalPages; i++) {
          this.offset.push(count++)
        }

      } else if (this.currentPage < 3) {

        this.offset = [1, 2, 3, 4, 5]

      } else if (this.currentPage >= this.pagination.totalPages - 2) {

        let lastPage = this.pagination.totalPages;
        this.offset = [lastPage - 4, lastPage - 3, lastPage - 2, lastPage - 1, lastPage]

      } else {

        this.offset = [this.currentPage - 2, this.currentPage - 1, this.currentPage, this.currentPage + 1, this.currentPage + 2]
      }
    }
  },
  watch: {
    perPage(newValue , oldValue) {
      if (oldValue >= this.pagination.totalElements){
        return;
      }

      this.currentPage = 1;
      this.setOffset();
      this.$emit('changePerPage', newValue);

    },
    "pagination.totalElements": {
      handler() {
        this.currentPage = 1;
        this.setOffset();
      },
    },
  }
}
</script>

<style scoped>

div.pagination {
  font-size: 1.2rem;
  margin: 2rem 2rem;
  display: flex;
  justify-content: space-around;
}

div.pagination div.onPageChange {
  display: inline-block;
  padding: 0.3rem 1rem 0.2rem 1rem;
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

p.totalPages {
  display: inline-block;
  background-color: black;
  color: orange;
  font-size: 1.2rem;
  padding: 0.5rem 0.8rem;
  border-radius: 5px;
  font-weight: bold;
  letter-spacing: 1px;
}
</style>