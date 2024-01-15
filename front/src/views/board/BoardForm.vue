<template>
    <div class="board-detail">
        <div class="common-buttons">
            <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnGoList">목록</button>&nbsp;
            <button type="button" class="w3-button w3-round w3-blue" v-on:click="fnSaveForm();">저장</button>&nbsp;
            <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnSaveForm('D');">삭제</button>
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
            regDt: '',
            regUserId: ''
        }
    },
    mounted() {
        this.fnGetForm();
    },
    methods: {
        fnGetForm() {
            if (this.boardIdx !== undefined && this.boardIdx !== '') {
                this.$axios.get(this.$serverUrl + '/board/' + this.boardIdx).then((res) => {
                    this.boardIdx = res.data.boardIdx;
                    this.boardTitle = res.data.boardTitle;
                    this.boardContent = res.data.boardContent;
                    this.topFixyn = res.data.topFixyn;
                    this.delYn = res.data.delYn;
                    this.regDt = res.data.regDt;
                    this.regUserId = res.data.regUserId;
                }).catch((err) => {
                    console.log(err);
                });
            } else {
                console.log("등록");
            }
        },
        fnGoList() {
            delete this.requestBody.boardIdx;
            this.$router.push({
                path: '/board',
                query: this.requestBody
            });
        },
        fnSaveForm(mode) {
            var message = "";
            var delYn = "N";
            if (mode === undefined) {
                if (this.boardIdx === undefined || this.boardIdx === '') {
                    mode = 'R';
                } else {
                    mode = 'U';
                }
                message = "저장";
            } else {
                message = "삭제";
                delYn = "Y";
            }

            if (confirm(message + "하시겠습니까?")) {
                this.form = {
                    "boardIdx": this.boardIdx,
                    "boardMainIdx": this.boardMainIdx,
                    "boardTitle": this.boardTitle,
                    "boardContent": this.boardContent,
                    "topFixYn": "N",
                    "delYn": delYn,
                    "regUserId": this.regUserId
                }
                this.$axios.post(
                    this.$serverUrl + '/board/Exec?' + "mode=" + mode
                    , JSON.stringify(this.form)
                    , {
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    }
                ).then((res) => {
                    console.log(res);
                    if (res.data.resultCd === "FAIL") {
                        alert(res.data.msg);
                    } else {
                        alert(message+"되었습니다.");
                        this.fnGoList();
                    }
                }).catch((err) => {
                    alert("" + err);
                    console.log(err);
                });
            }
        }
    }
}
</script>
<style scoped>

</style>