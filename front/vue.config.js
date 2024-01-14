// const { defineConfig } = require('@vue/cli-service')
module.exports = {
    // build 경로 설정
    outputDir: '../back/src/main/resources/static',
    devServer: {
        port:8081,
        proxy: {
            '/api': {
                target: 'http//localhost:8080',
                pathRewrite: {
                    "^/api": "",
                }
            }
        }
    },
    indexPath: '../templates/index.html'
}