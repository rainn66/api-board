<template>
    <div>
        <div>
            <h2>Please Log In</h2>
            <div id="loginForm">
                <form @submit.prevent="fnLogin();">
                    <p>
                        <input style="width:30%;min-width:200px;" name="userId" placeholder="Enter your ID" v-model="userId"><br>
                    </p>
                    <p>
                        <input style="width:30%;min-width:200px;" name="userPassword" placeholder="Enter your password" v-model="userPassword" type="password">
                    </p>
                    <p>
                        <button type="submit" class="w3-button w3-green w3-round">Login</button>
                        <button @click="fnGoSignUp" type="button" class="w3-button w3-green w3-round">Sign Up</button>
                    </p>
                </form>
            </div>
        </div>
    </div>
</template>

<script>
import {mapActions, mapGetters} from 'vuex'

export default {
    data() {
        return {
            userId: '',
            userPassword: ''
        }
    },
    methods: {
        ...mapActions(['login']),

        async fnLogin() {
            if (this.userId === '') {
                alert('ID를 입력하세요.');
                return;
            }
            if (this.userPassword === '') {
                alert('비밀번호를 입력하세요.');
                return;
            }
            try {
                let loginResult = await this.login({userId: this.userId, userPassword:this.userPassword});
                if (loginResult) {
                    this.$store.state.isLogin=true;
                    this.fnGoHome();
                }
            } catch (err) {
                alert(err.message);
            }
        },
        fnGoHome() {
            this.$router.push({
                name: 'AppHome'
            })
        },
        fnGoSignUp() {
            this.$router.push({
                name: 'AppSignUp'
            })
        }
    },
    computed: {
        ...mapGetters({
            errorState: 'getErrorState'
        })
    }
}
</script>

<style scoped>

</style>
