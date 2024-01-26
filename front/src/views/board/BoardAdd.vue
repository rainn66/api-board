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
        <div class="board-contents" id="fileContents">
            <div v-for="(row, idx) in boardFiles" :key="row.boardFileIdx">
                <input style="display:none;" type="file" name="uploadFile" :id="'upload_' + row.boardFileIdx" @change="fnChangeFileNm(row.boardFileIdx)">
                <label style="margin-right:10px;cursor:pointer;" :for="'upload_' + row.boardFileIdx">{{row.fileOrgNm}}</label>
                <button style="margin-right:10px;" v-if="idx === 0" type="button" class="w3-button w3-round w3-blue" v-on:click="fnAddFile">파일추가</button>
                <button type="button" class="w3-button w3-round w3-blue-gray" v-on:click="fnDelFile">파일삭제</button>
            </div>
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
            boardMainList: {boardMainIdx:'', boardNm:''},
            boardFiles: [{boardFileIdx:'', fileSaveNm: '', fileOrgNm: ''}],
            newFileCnt: 1
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
                this.boardFiles = [{boardFileIdx:this.newFileCnt + '_0', fileSaveNm: '', fileOrgNm: '파일을 선택해주세요.'}]
            }).catch((err) => {
                alert(err.response.data.errorCode + " : " + err.response.data.message);
                this.$store.state.loadingStatus = false;
            });
        },
        fnAddForm() {
            if (confirm("저장하시겠습니까?")) {
                const frm = new FormData();

                //파일데이터 FormData 추가
                document.getElementsByName('uploadFile').forEach((htmlObj) => {
                    let file = document.getElementById(htmlObj.id).files[0];
                    if (file !== undefined) {
                        frm.append("file", file);
                    }
                });

                //파라미터 FormData 추가
                this.body = {
                    "boardMainIdx": this.boardMainIdx,
                    "boardTitle": this.boardTitle,
                    "boardContent": this.boardContent,
                    "topFixYn": this.topFixYn,
                }
                frm.append('body', new Blob([JSON.stringify(this.body)], {type: 'application/json'}));

                this.$axios.post(
                    this.$serverUrl + '/board/add', frm,
                    {
                        headers: {
                            'Content-Type': 'multipart/form-data'
                        }
                    }
                ).then(() => {
                    alert("저장되었습니다.");
                    this.fnGoList();
                }).catch((err) => {
                    alert(err.response.data.errorCode + " : " + err.response.data.message);
                    this.$store.state.loadingStatus = false;
                });
            }
        },
        fnAddFile() {
            this.newFileCnt += 1
            this.boardFiles.push({boardFileIdx:this.newFileCnt + '_0', fileSaveNm:'', fileOrgNm:'파일을 선택하세요.'})
        },
        fnDelFile() {
            const fileCnt = document.getElementById('fileContents').getElementsByTagName('input').length
            if (fileCnt === 1) {
                alert("더이상 삭제할 수 없습니다.");
            } else {
                if (confirm("삭제하시겠습니까?")) {
                    document.getElementById('uploadFile' + fileCnt).remove();
                }
            }
        },
        fnChangeFileNm(idx) {
            document.querySelector('label[for=upload_' + idx + ']').innerText = document.getElementById('upload_' + idx).files[0].name;
        }
    }
}
</script>
<style scoped>

</style>