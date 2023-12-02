<template>
    <div class="board-list" style="margin-top:50px;">
        <div style="float:left;">
            <select class="w3-select w3-border" style="margin-right:20px;float:left;height:50px;width:20%;min-width:100px;" v-model="searchKey">
                <option value="">선택</option>
                <option value="regUserId">작성자</option>
                <option value="bbsTitle">제목</option>
                <option value="bbsContents">내용</option>
            </select>
            <input class="w3-input w3-border" type="text" style="height:50px;width:50%;min-width:200px;" v-model="searchVal" @keyup.enter="fnGetList();"/>
            <input type="hidden" v-model="bbsCategoryCd" />
        </div>
        <div class="common-buttons">
            <button class="w3-button w3-round w3-blue-gray" @click="fnGetList();">검색</button>&nbsp;
            <button type="button" class="w3-button w3-round w3-blue-gray" @click="fnGoForm('');">등록</button>
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
            <tr v-if="list.size < 1">
                <td>조회된 값이 없습니다.</td>
            </tr>
            <tr v-for="(row, idx) in list" :key="idx">
                <td>{{ idx + 1 }}</td>
                <td><a v-on:click="fnGoForm(`${row.bbsIdx}`)" style="cursor:pointer;text-decoration: underline;" class="w3-text-blue w3-pointer">{{ row.bbsTitle }}</a></td>
                <td>{{ row.regUserId }}</td>
                <td>{{ row.regDt }}</td>
            </tr>
            </tbody>
        </table>
        <div class="pagination w3-bar w3-padding-16 w3-small" v-if="paging.totalListCnt > 0">
            <span class="pg">
                <a href="javascript:" @click="fnGetList(1)" class="first w3-button">&lt;&lt;</a>
                <a href="javascript:" v-if="paging.startPage > 10" @click="fnGetList(`${paging.startPage-1}`)"
                class="prev w3-button w3-border">&lt;</a>
                <template v-for="(n,index) in pageNavigator()">
                    <template v-if="paging.page===n">
                        <strong class="w3-button w3-green" :key="index">{{ n }}</strong>
                    </template>
                    <template v-else>
                        <a class="w3-button " href="javascript:;" @click="fnGetList(`${n}`)" :key="index">{{ n }}</a>
                    </template>
                </template>
                <a href="javascript:" v-if="paging.totalPageCnt > paging.endPage" @click="fnGetList(`${paging.endPage+1}`)" class="next w3-button w3-border">&gt;</a>
                <a href="javascript:" @click="fnGetList(`${paging.totalPageCnt}`)" class="last w3-button">&gt;&gt;</a>
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
                endPage: 0,
                nextBlock: 0,
                page: 0,
                pageSize: 0,
                prevBlock: 0,
                startIndex: 0,
                startPage: 0,
                totalBlockCnt: 0,
                totalListCnt: 0,
                totalPageCnt: 0,
                pagination: 0
            }, //페이징 데이터
            page: this.$route.query.page ? this.$route.query.page : 1,
            size: this.$route.query.size ? this.$route.query.size : 10,
            searchKey: this.$route.query.searchKey ? this.$route.query.searchKey : '',
            searchVal: this.$route.query.searchVal ? this.$route.query.searchVal : '',
            bbsCategoryCd: this.$route.query.bbsCategoryCd ? this.$route.query.bbsCategoryCd : 'B0001',
            pageNavigator: function () { //페이징 처리 for문 커스텀
                let pageNumber = [] //;
                let start_page = this.paging.startPage;
                let end_page = this.paging.endPage;
                for (let i = start_page; i <= end_page; i++) pageNumber.push(i);
                return pageNumber;
            }
        }
    },
    mounted() {
        this.fnGetList()
    },
    methods: {
        fnGetList(pageNum) {

            if (pageNum === undefined) {
                pageNum = 1;
            }
            this.requestBody = {
                page: pageNum
                , size: this.size
                , bbsCategoryCd: this.bbsCategoryCd
                , searchKey: this.searchKey
                , searchVal: this.searchVal
            }
            this.$axios.get(this.$serverUrl + '/bbsMainList', {
                params: this.requestBody,
                headers: {}
            }).then((res) => {
                if (res.data.resultCode === "OK") {
                    //console.log(res.data);
                    this.list = res.data.data;
                    this.paging = res.data.pagination;
                    this.no = this.paging.totalListCnt - ((this.paging.page - 1) * this.paging.pageSize);
                }
            }).catch((err) => {
                console.log(err.message);
            })
        },
        fnGoForm(bbsIdx) {
            //console.log(bbsIdx);
            this.requestBody.bbsIdx = bbsIdx
            this.$router.push({
                path: '/bbsMainInfo',
                query: this.requestBody
            })
        }
    }
}


</script>

<style scoped>

</style>