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
                        <input style="width:30%;min-width:200px;" name="userPassword" placeholder="Enter your password" type="password">
                    </p>
                    <p>
                        <input style="width:30%;min-width:200px;" name="userPassword" placeholder="Check your password" type="password">
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
            password: ''
        }
    },
    methods: {
        fnSignUp() {
            
        }
    }
}
</script>

<style scoped>

</style>