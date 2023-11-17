<template>
    <div>
        <div>
            <h2>Please Log In</h2>
            <div id="loginForm">
                <form @submit.prevent="fnLogin();">
                    <p>
                        <input class="w3-input" style="width:30%;min-width:200px;" name="userId" placeholder="Enter your ID" v-model="userId"><br>
                    </p>
                    <p>
                        <input name="userPassword" style="width:30%;min-width:200px;" class="w3-input" placeholder="Enter your password" v-model="userPassword" type="password">
                    </p>
                    <p>
                        <button type="submit" class="w3-button w3-green w3-round">Login</button>
                    </p>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            userId: '',
            userPassword: ''
        }
    },
    methods: {
        fnLogin() {
            if (this.userId === '') {
                alert('ID를 입력하세요.')
                return
            }

            if (this.userPassword === '') {
                alert('비밀번호를 입력하세요.')
                return
            }

            alert('로그인 되었습니다.')
        }
    }
}
</script>

<style scoped>

</style>
