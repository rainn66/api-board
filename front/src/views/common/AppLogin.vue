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
                        <input style="width:30%;min-width:200px;" name="password" placeholder="Enter your password" v-model="password" type="password">
                    </p>
                    <p>
                        <button type="submit" class="w3-button w3-green w3-round">Login</button>
                        <button @click="fnGoSignUp" type="button" class="w3-button w3-green w3-round" style="margin-left:30px;">Sign Up</button>
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
            password: ''
        }
    },
    methods: {
        ...mapActions(['login']),

        async fnLogin() {
            if (this.userId === '') {
                alert('ID를 입력하세요.');
                return;
            }
            if (this.password === '') {
                alert('비밀번호를 입력하세요.');
                return;
            }
            try {
                let loginResult = await this.login({userId: this.userId, password:this.password});
                if (loginResult) {
                    this.$store.state.isLogin=true;
                    this.fnGoHome();
                }
            } catch (err) {
                console.log(err.message);
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
