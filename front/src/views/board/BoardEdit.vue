<template>
    <div class="board-detail">
        <div class="common-buttons">
            <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnGoList">목록</button>&nbsp;
            <button type="button" class="w3-button w3-round w3-blue" v-on:click="fnEditForm();">저장</button>&nbsp;
            <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnDeleteForm();">삭제</button>
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
                <input type="radio" v-model="topFixYn" class="w3-radio" id="topFixY" name="topFixYn" value="Y" :checked="topFixYn == 'Y'">
                <label for="topFixY">Y</label>&nbsp;
                <input type="radio" v-model="topFixYn" class="w3-radio" id="topFixN" name="topFixYn" value="N" :checked="topFixYn == 'N'">
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
        <input type="hidden" v-model="boardIdx" />
    </div>
</template>

<script>
export default {
    data() { //변수생성
        return {
            requestBody: this.$route.query,
            boardIdx: this.$route.query.boardIdx,
            boardTitle: '',
            boardContent: '',
            boardMainIdx: '',
            topFixYn: '',
            regDt: '',
            regUserId: '',
            boardMainList: {boardMainIdx:'', boardNm:''}
        }
    },
    mounted() {
        this.fnGetForm();
    },
    methods: {
        fnGetForm() {
            if (this.boardIdx !== undefined && this.boardIdx !== '') {
                this.$axios.get(this.$serverUrl + '/board/edit/' + this.boardIdx
                ).then((res) => {
                    this.boardMainList = res.data.boardMainList;

                    this.boardIdx = res.data.boardInfo.boardIdx;
                    this.boardMainIdx = res.data.boardInfo.boardMainIdx;
                    this.boardTitle = res.data.boardInfo.boardTitle;
                    this.boardContent = res.data.boardInfo.boardContent;
                    this.topFixYn = res.data.boardInfo.topFixYn;
                    this.delYn = res.data.boardInfo.delYn;
                    this.regDt = res.data.boardInfo.regDt;
                    this.regUserId = res.data.boardInfo.regUserId;
                }).catch((err) => {
                    alert(err);
                });
            }
        },
        fnGoList() {
            delete this.requestBody.boardIdx;
            this.$router.push({
                path: '/board',
                query: this.requestBody
            });
        },
        fnEditForm() {
            if (this.boardIdx === undefined || this.boardIdx === '') {
                //error page 이동
            }
            if (confirm("수정하시겠습니까?")) {
                this.form = {
                    "boardIdx": this.boardIdx,
                    "boardMainIdx": this.boardMainIdx,
                    "boardTitle": this.boardTitle,
                    "boardContent": this.boardContent,
                    "topFixYn": this.topFixYn,
                    "regUserId": this.regUserId
                }
                this.$axios.post(
                    this.$serverUrl + '/board/edit'
                    , JSON.stringify(this.form)
                    , {
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    }
                ).then((res) => {
                    if (res.data.resultCd === "FAIL") {
                        alert(res.data.msg);
                    } else {
                        alert("수정되었습니다.");
                        this.fnGoList();
                    }
                }).catch((err) => {
                    alert(err);
                });
            }
        },
        fnDeleteForm() {
            if (this.boardIdx === undefined || this.boardIdx === '') {
                //error page 이동
            }

            if (confirm("삭제하시겠습니까?")) {
                this.form = {
                    "boardIdx": this.boardIdx,
                    "regUserId": this.regUserId
                }
                this.$axios.post(
                    this.$serverUrl + '/board/delete'
                    , JSON.stringify(this.form)
                    , {
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    }
                ).then((res) => {
                    if (res.data.resultCd === "FAIL") {
                        alert(res.data.msg);
                    } else {
                        alert("삭제되었습니다.");
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