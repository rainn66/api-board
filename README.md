## 👶api-board (Spring Boot + Vue.js)
### 일반적인 게시판 기능을 수행합니다.
Spring Dataa JPA, Vue.js 로 간단한 게시판 기능을 만들어보고,
회원관련 기능을 만들어보며 Spring Security 에 대해 공부할수 있었습니다.
Spring boot v3, java17, Vue.js v3

### 기능
- 로그인/회원가입(**Spring Security 6.0, JWT**) -> Filter 활용 로그인 여부 확인, 토큰 만료 기간 처리
  - 로그아웃 jwt 에 대한 처리 미적용
- 글쓰기/수정/삭제(글 작성자만 수정/삭제 가능)
- 파일처리

### 사용기술
- JPA(Querydsl)
- MySQL -> h2 변경
- Vue.js
- W3 CSS

### References

