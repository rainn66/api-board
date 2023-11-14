<template>
    <div class="board-list">
        <div class="common-buttons">
            <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnGoForm('')">등록</button>
        </div>
        <table class="w3-table-all w3-centered">
            <colgroup>
                <col style="width:15%;"/>
                <col style="width:35%;"/>
                <col style="width:20%;"/>
                <col style="width:30%;"/>
            </colgroup>
            <thead>
            <tr>
                <th>No</th>
                <th>제목</th>
                <th>작성자</th>
                <th>등록일시</th>
            </tr>
            </thead>
            <tbody>
            <tr v-for="(row, idx) in list" :key="idx">
                <td>{{ idx + 1 }}</td>
                <td><a v-on:click="fnGoForm(`${row.bbsIdx}`)">{{ row.bbsTitle }}</a></td>
                <td>{{ row.regUserId }}</td>
                <td>{{ row.regDt }}</td>
            </tr>
            </tbody>
        </table>
        <div class="pagination w3-bar w3-padding-16 w3-small" v-if="paging.total_list_cnt > 0">
      <span class="pg">
      <a href="javascript:" @click="fnPage(1)" class="first w3-button w3-border">&lt;&lt;</a>
      <a href="javascript:" v-if="paging.start_page > 10" @click="fnPage(`${paging.start_page-1}`)"
         class="prev w3-button w3-border">&lt;</a>
      <template v-for=" (n,index) in pageNavigator()">
          <template v-if="paging.page==n">
              <strong class="w3-button w3-border w3-green" :key="index">{{ n }}</strong>
          </template>
          <template v-else>
              <a class="w3-button w3-border" href="javascript:;" @click="fnPage(`${n}`)" :key="index">{{ n }}</a>
          </template>
      </template>
      <a href="javascript:" v-if="paging.total_page_cnt > paging.end_page"
         @click="fnPage(`${paging.end_page+1}`)" class="next w3-button w3-border">&gt;</a>
      <a href="javascript:" @click="fnPage(`${paging.total_page_cnt}`)" class="last w3-button w3-border">&gt;&gt;</a>
      </span>
        </div>
    </div>
</template>

<script>
export default {
    name: "BoardList",
    data() {
        return {
            requestBody: {},
            list: {bbsIdx: '', bbsTitle: '', regUserId:'', regDt: ''},
            no: '',
            paging: {
                block: 0,
                end_page: 0,
                next_block: 0,
                page: 0,
                page_size: 0,
                prev_block: 0,
                start_index: 0,
                start_page: 0,
                total_block_cnt: 0,
                total_list_cnt: 0,
                total_page_cnt: 0,
            }, //페이징 데이터
            page: this.$route.query.page ? this.$route.query.page : 1,
            size: this.$route.query.size ? this.$route.query.size : 10,
            keyword: this.$route.query.keyword,
            pageNavigator: function () { //페이징 처리 for문 커스텀
                let pageNumber = [] //;
                let start_page = this.paging.start_page;
                let end_page = this.paging.end_page;
                for (let i = start_page; i <= end_page; i++) pageNumber.push(i);
                return pageNumber;
            }
        }
    },
    mounted() {
        this.fnGetList()
    },
    methods: {
        fnGetList() {
            this.requestBody = {
                keyword: this.keyword
                , page: this.page
                , size: this.size
                , bbsCategoryCd: "B0001"
            }
            this.$axios.get(this.$serverUrl + '/bbs/list', {
                params: this.requestBody,
                headers: {}
            }).then((res) => {
                console.log(res.data);
                this.list = res.data
            }).catch((err) => {
                console.log(err.message);
            })
        },
        fnGoForm(bbsIdx) {
            console.log(bbsIdx);
            this.requestBody.bbsIdx = bbsIdx
            this.$router.push({
                path: '/bbs/form',
                query: this.requestBody
            })
        }
    }
}
</script>

<style scoped>

</style>