<template>
    <div class="side-bar-wrapper">
        <VueResizable class="resizable-side-bar" style="height:100%;" :width="200" :min-width="200" :max-width="Infinity" :active="['r']">
            <div class="side-bar">
                <div>
                    <router-link to="/">Home</router-link>
                </div>
                <div v-for="(sideItem, idx) in sideList" :key="idx">
                    <router-link :to="{name:'BoardList', query:{boardMainIdx:sideItem.boardMainIdx}}">{{sideItem.boardNm}}</router-link>
                </div>
                <br>
            </div>
        </VueResizable>
        <button class="side-bar-active-btn" @click="showSideBar">
            {{ isVisibleSideBar ? 'Close' : 'Open' }}
        </button>
    </div>
</template>

<script>
import VueResizable from 'vue-resizable'

export default {
    name: "SideBar",
    components: {
        VueResizable
    },
    data() {
        return {
            isVisibleSideBar: true
            , sideList: {boardMainIdx:'', boardNm:''}
        }
    },
    mounted() {
        this.fnGetSide();
    },
    methods: {
        showSideBar() {
            this.isVisibleSideBar = !this.isVisibleSideBar;
        },
        fnGetSide() {
            this.$axios.get(this.$serverUrl + '/side'
            ).then((res) => {
                if (res.data.boardMainList !== null) {
                    this.sideList = res.data.boardMainList;
                }
            });
        }
    }
}
</script>

<style lang="scss" scoped>
.side-bar-wrapper {
    > .resizable-side-bar{
        > .side-bar {
            background-color: #000000;
            opacity: 0.5;
            width: 100%;
            height: 100%;
        }
    }

    > .side-bar-active-btn {
        flex-shrink: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: #000000;
        padding: 0;
        border: none;
        border-radius: unset;
        color: #fff;
        opacity: 0.5;
        width: 40px;
        height: 40px;
    }
}

</style>