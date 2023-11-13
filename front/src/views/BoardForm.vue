<template>
    <div class="board-detail">
        <div class="common-buttons">
            <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnSave">저장</button>&nbsp;
            <button type="button" class="w3-button w3-round w3-gray" v-on:click="fnList">목록</button>
        </div>
        <div class="board-contents">
            <input type="text" v-model="bbsTitle" class="w3-input w3-border" placeholder="제목을 입력해주세요.">
            <input type="text" v-model="regUserId" class="w3-input w3-border" placeholder="작성자를 입력해주세요.">
        </div>
        <div class="board-contents">
      <textarea id="" cols="30" rows="10" v-model="bbsContents" class="w3-input w3-border" style="resize: none;">
      </textarea>
        </div>
        <div class="common-buttons">
            <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnSave">저장</button>&nbsp;
            <button type="button" class="w3-button w3-round w3-gray" v-on:click="fnList">목록</button>
        </div>
    </div>
</template>

<script>
export default {
    data() { //변수생성
        return {
            requestBody: this.$route.query,
            bbsIdx: this.$route.query.bbsIdx,
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
                this.$axios.get(this.$serverUrl + '/bbs/' + this.bbsIdx, {
                    params: this.requestBody
                }).then((res) => {
                    this.bbsIdx = res.data.bbsIdx
                    this.bbsTitle = res.data.bbsTitle
                    this.bbsContents = res.data.bbsContents
                    this.regDt = res.data.regDt
                    this.regUserId = res.data.regUserId
                }).catch((err) => {
                    console.log(err)
                })
            }
        },
        fnList() {
            delete this.requestBody.idx
            this.$router.push({
                path: '/bbs/list',
                query: this.requestBody
            })
        }
    }
}
</script>
<style scoped>

</style>