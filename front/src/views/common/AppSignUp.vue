<template>
    <div>
        <div>
            <h2>sign up</h2>
            <div id="signUpForm">
                <form @submit.prevent="fnSignUp();">
                    <p>
                        <input style="width:30%;min-width:200px;" name="userId" placeholder="Enter your ID" v-model="userId"><br>
                    </p>
                    <p>
                        <input style="width:30%;min-width:200px;" name="userNm" placeholder="Enter your name" v-model="userNm"><br>
                    </p>
                    <p>
                        <input style="width:30%;min-width:200px;" name="password" placeholder="Enter your password" type="password" v-model="password">
                    </p>
                    <p>
                        <input style="width:30%;min-width:200px;" name="passwordChk" placeholder="Check your password" type="password" v-model="passwordChk">
                    </p>
                    <p>
                        <button @click="fnSignUp" type="button" class="w3-button w3-green w3-round">Sign Up</button>
                    </p>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "AppSignUp",
    data() {
        return {
            userId: '',
            userNm: '',
            password: '',
            passwordChk: ''
        }
    },
    methods: {
        fnSignUp() {
            if (this.password !== this.passwordChk) {
                alert("비밀번호가 일치하지 않습니다.");
                return;
            }
            this.form = {
                "userId": this.userId,
                "userNm": this.userNm,
                "password": this.password,
                "passwordChk": this.passwordChk
            };
            this.$axios.post(
                this.$serverUrl + '/user/signup',
                JSON.stringify(this.form),
                {
                    headers: {'Content-Type': 'application/json'}
                }
            ).then((res) => {
                alert(res.data.userNm + " 님 환영합니다. 로그인을 진행해주세요");
                this.$router.push({
                    name: 'AppLogin',
                });
            }).catch((err) => {
                alert(err);
            });
        }
    }
}
</script>

<style scoped>

</style>