<template>
    <div class="board-detail">
        <div class="common-buttons">
            <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnGoList">목록</button>&nbsp;
            <button type="button" class="w3-button w3-round w3-blue" v-on:click="fnAddForm();">저장</button>&nbsp;
        </div>
        <div class="board-contents" style="display:flex;align-items: baseline">
            <span class="w3-text" style="margin-right:30px;">게시판 카테고리</span>
            <div v-for="(boardMain, idx) in boardMainList" :key="idx">
                <input class="w3-radio" type="radio" v-model="boardMainIdx" :id="idx" v-bind:value="boardMain.boardMainIdx" :checked="boardMain.boardMainIdx == boardMainIdx">
                <label :for="idx">{{boardMain.boardNm}}</label>&nbsp;
            </div>
        </div>
        <div class="board-contents" style="display:flex;align-items: baseline">
            <span class="w3-text" style="margin-right:30px;">상단 고정 여부</span>
            <div>
                <input type="radio" v-model="topFixYn" class="w3-radio" id="topFixY" name="topFixYn" value="Y">
                <label for="topFixY">Y</label>&nbsp;
                <input type="radio" v-model="topFixYn" class="w3-radio" id="topFixN" name="topFixYn" value="N" checked>
                <label for="topFixN">N</label>&nbsp;
            </div>
        </div>
        <div class="board-contents">
            <input type="text" v-model="boardTitle" class="w3-input w3-border" placeholder="제목을 입력해주세요.">
        </div>
        <div class="board-contents">
            <textarea id="" cols="30" rows="10" v-model="boardContent" class="w3-input w3-border" style="resize: none;">
            </textarea>
        </div>
        <div class="board-contents">
            <input type="text" v-model="regUserId" class="w3-input w3-border" placeholder="자동 등록 항목입니다." readonly>
        </div>
        <div class="board-contents">
            <input type="text" v-model="regDt" class="w3-input w3-border" placeholder="자동 등록 항목입니다." readonly>
        </div>
    </div>
</template>

<script>
export default {
    data() { //변수생성
        return {
            requestBody: this.$route.query,
            boardTitle: '',
            boardContent: '',
            boardMainIdx: this.$route.query.boardMainIdx,
            topFixYn: 'N',
            regDt: '',
            regUserId: '',
            boardMainList: {boardMainIdx:'', boardNm:''}
        }
    },
    mounted() {
        this.fnGetForm();
    },
    methods: {
        fnGoList() {
            this.$router.push({
                name: 'BoardList',
                query: this.requestBody
            });
        },
        fnGetForm() {
            this.$axios.get(this.$serverUrl + '/board/add'
            ).then((res) => {
                this.boardMainList = res.data.boardMainList;
            });
        },
        fnAddForm() {
            if (confirm("저장하시겠습니까?")) {
                this.form = {
                    "boardMainIdx": this.boardMainIdx,
                    "boardTitle": this.boardTitle,
                    "boardContent": this.boardContent,
                    "topFixYn": this.topFixYn,
                }
                this.$axios.post(
                    this.$serverUrl + '/board/add'
                    , JSON.stringify(this.form)
                    , {
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    }
                ).then((res) => {
                    if (res.data.resultCd === "FAIL") {
                        alert(res.data.msg);
                    } else if (res.data.resultCd === "SUCCESS") {
                        alert(res.data.msg);
                        this.fnGoList();
                    }
                }).catch((err) => {
                    alert(err);
                });
            }
        }
    }
}
</script>
<style scoped>

</style>