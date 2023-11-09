const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
    // build 경로 설정
    outputDir: '../back/src/main/resources/static',
    devServer: {
        proxy: {
            '/api': {
                target: 'http://localhost:80',
                changeOrigin: true
            }
        }
    },
})