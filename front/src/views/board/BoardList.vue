<template>
    <div class="board-list" style="margin-top:50px;">
        <div style="float:left;">
            <select class="w3-select w3-border" style="margin-right:20px;float:left;height:50px;width:20%;min-width:100px;" v-model="searchKey">
                <option value="">선택</option>
                <option value="regUserId">작성자</option>
                <option value="boardTitle">제목</option>
                <option value="boardContent">내용</option>
            </select>
            <input class="w3-input w3-border" type="text" style="height:50px;width:50%;min-width:200px;" v-model="searchVal" @keyup.enter="fnGetList();"/>
            <input type="hidden" v-model="boardMainIdx" />
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
                <td><a @click="fnGoForm(row.boardIdx)" style="cursor:pointer;text-decoration: underline;" class="w3-text-blue w3-pointer">{{ row.boardTitle }}</a></td>
                <td>{{ row.regUserId }}</td>
                <td>{{ row.regDt }}</td>
            </tr>
            </tbody>
        </table>
        <div class="pagination w3-bar w3-padding-16 w3-small" v-if="totalPages > 0">
            <span class="pg">
                <a href="javascript:" @click="fnGetList(1)" class="first w3-button">&lt;&lt;</a>
                <a href="javascript:" v-if="paging.pageNumber > 0" @click="fnGetList(paging.pageNumber)"
                class="prev w3-button w3-border">&lt;</a>
                <template v-for="(n,index) in pageNavigator()">
                    <template v-if="paging.pageNumber===n">
                        <strong class="w3-button w3-green" :key="index">{{ n + 1 }}</strong>
                    </template>
                    <template v-else>
                        <a class="w3-button " href="javascript:;" @click="fnGetList(Number(n) + 1)" :key="index">{{ n + 1 }}</a>
                    </template>
                </template>
                <a href="javascript:" v-if="totalPages-1 > paging.pageNumber" @click="fnGetList(Number(paging.pageNumber)+2)" class="next w3-button w3-border">&gt;</a>
                <a href="javascript:" @click="fnGetList(totalPages)" class="last w3-button">&gt;&gt;</a>
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
            list: {boardIdx: '', boardTitle: '', regUserId:'', regDt: ''},
            no: '',
            paging: {
                pageNumber:0,
                offset:0,
                pageSize:0,
            },
            totalPages:0,
            totalElements:0,
            searchKey: this.$route.query.searchKey ? this.$route.query.searchKey : '',
            searchVal: this.$route.query.searchVal ? this.$route.query.searchVal : '',
            boardMainIdx: this.$route.query.boardMainIdx ? this.$route.query.boardMainIdx : '1', //없으면 error 페이지 이동
            pageNavigator: function () { //페이징 처리
                let pageNumber = [];
                let start_page = 0;
                let end_page = this.totalPages;
                for (let i = start_page; i < end_page; i++) pageNumber.push(i);
                return pageNumber;
            }
        }
    },
    mounted() {
        this.fnGetList();
    },
    methods: {
        fnGetList(pageNum) {

            if (pageNum === undefined) {
                pageNum = 0;
            }
            this.requestBody = {
                page: pageNum
                , size: this.size
                , boardMainIdx: this.boardMainIdx
                , searchKey: this.searchKey
                , searchVal: this.searchVal
            }
            this.$axios.get(this.$serverUrl + '/board', {
                params: this.requestBody,
                headers: {}
            }).then((res) => {
                this.list = res.data.content;
                this.paging = res.data.pageable;
                this.totalPages = res.data.totalPages;
            }).catch((err) => {
                alert(err);
            });
        },
        fnGoForm(boardIdx) {
            var routerName = 'BoardAdd';
            if (boardIdx !== undefined && boardIdx !== '') {
                routerName = 'BoardEdit';
                this.requestBody.boardIdx = boardIdx;
            }
            this.$router.push({
                name: routerName,
                query: this.requestBody
            });

        }
    }
}


</script>

<style scoped>

</style>