import common from "./common"

export default {
    install : (app) => {
        app.config.globalProperties.$common = common;
    }
}