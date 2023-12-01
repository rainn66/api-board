<template>
    <div class="board-detail">
        <div class="common-buttons">
            <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnGoList">목록</button>&nbsp;
            <button type="button" class="w3-button w3-round w3-blue" v-on:click="fnSaveForm();">저장</button>&nbsp;
            <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnSaveForm('D');">삭제</button>
        </div>
        <div class="board-contents">
            <input type="text" v-model="bbsTitle" class="w3-input w3-border" placeholder="제목을 입력해주세요.">
        </div>
        <div class="board-contents">
            <textarea id="" cols="30" rows="10" v-model="bbsContents" class="w3-input w3-border" style="resize: none;">
            </textarea>
        </div>
        <div class="board-contents">
            <input type="text" v-model="regUserId" class="w3-input w3-border" placeholder="자동 등록 항목입니다." readonly>
        </div>
        <input type="hidden" v-model="bbsCategoryCd" />
        <input type="hidden" v-model="bbsIdx" />
    </div>
</template>

<script>
export default {
    data() { //변수생성
        return {
            requestBody: this.$route.query,
            bbsIdx: this.$route.query.bbsIdx,
            bbsCategoryCd: 'B0001',
            bbsTitle: '',
            bbsContents: '',
            regDt: '',
            regUserId: ''
        }
    },
    mounted() {
        this.fnGetForm()
    },
    methods: {
        fnGetForm() {
            if (this.bbsIdx !== undefined) {
                this.$axios.get(this.$serverUrl + '/bbsMainInfo/' + this.bbsIdx, {
                    params: this.requestBody
                }).then((res) => {
                    this.bbsIdx = res.data.bbsIdx
                    this.bbsCategoryCd = res.data.bbsCategoryCd
                    this.bbsTitle = res.data.bbsTitle
                    this.bbsContents = res.data.bbsContents
                    this.regDt = res.data.regDt
                    this.regUserId = res.data.regUserId
                }).catch((err) => {
                    console.log(err)
                })
            }
        },
        fnGoList() {
            delete this.requestBody.bbsIdx
            this.$router.push({
                path: '/bbsMainList',
                query: this.requestBody
            })
        },
        fnSaveForm(mode) {
            var message = "";
            if (mode === undefined) {
                if (this.bbsIdx === undefined || this.bbsIdx === '') {
                    mode = 'R';
                } else {
                    mode = 'U';
                }
                message = "저장";
            } else {
                message = "삭제";
            }

            if (confirm(message + "하시겠습니까?")) {
                this.form = {
                    "bbsIdx": this.bbsIdx
                    , "bbsCategoryCd": this.bbsCategoryCd
                    , "bbsTitle": this.bbsTitle
                    , "bbsContents": this.bbsContents
                    , "bbsTopFixYn": "N"
                    , "regUserId": this.regUserId
                    , "chgUserId": this.regUserId
                    , "mode": mode
                }

                this.$axios.post(
                    this.$serverUrl + '/bbsMainExec/' + mode
                    , this.form
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