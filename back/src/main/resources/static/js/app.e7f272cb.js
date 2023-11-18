(function(){"use strict";var t={2723:function(t,e,n){var s=n(9963),o=(n(8394),n(6252));function r(t,e,n,s,r,i){const a=(0,o.up)("AppHeader"),l=(0,o.up)("SideBar"),u=(0,o.up)("router-view"),d=(0,o.up)("AppFooter");return(0,o.wg)(),(0,o.iD)(o.HY,null,[(0,o.Wm)(a),(0,o.Wm)(l,{class:"side-bar"}),(0,o.Wm)(u),(0,o.Wm)(d)],64)}var i=n(3577);const a=t=>((0,o.dD)("data-v-2984a29f"),t=t(),(0,o.Cn)(),t),l={class:"side-bar-wrapper"},u=a((()=>(0,o._)("div",{class:"side-bar"},null,-1)));function d(t,e,n,s,r,a){const d=(0,o.up)("VueResizable");return(0,o.wg)(),(0,o.iD)("div",l,[(0,o.Wm)(d,{class:"resizable-side-bar",width:200,"min-width":200,"max-width":1/0,active:["r"]},{default:(0,o.w5)((()=>[u])),_:1}),(0,o._)("button",{class:"side-bar-active-btn",onClick:e[0]||(e[0]=(...t)=>a.showSideBar&&a.showSideBar(...t))},(0,i.zw)(r.isVisibleSideBar?"Close":"Open"),1)])}var c=n(1671),b=n.n(c),h={name:"SideBar",components:{VueResizable:b()},data(){return{isVisibleSideBar:!0}},methods:{showSideBar(){this.isVisibleSideBar=!this.isVisibleSideBar}}},p=n(3744);const g=(0,p.Z)(h,[["render",d],["__scopeId","data-v-2984a29f"]]);var f=g;const w={id:"nav"};function y(t,e,n,s,r,i){const a=(0,o.up)("router-link");return(0,o.wg)(),(0,o.iD)("header",null,[(0,o._)("div",w,[(0,o.Wm)(a,{to:"/"},{default:(0,o.w5)((()=>[(0,o.Uk)("Home")])),_:1}),(0,o.Uk)(" | "),(0,o.Wm)(a,{to:"/about"},{default:(0,o.w5)((()=>[(0,o.Uk)("About")])),_:1}),(0,o.Uk)(" | "),(0,o.Wm)(a,{to:"/bbsMainList"},{default:(0,o.w5)((()=>[(0,o.Uk)("Board")])),_:1}),(0,o.Uk)(" | "),(0,o.Wm)(a,{to:"/login"},{default:(0,o.w5)((()=>[(0,o.Uk)("Login")])),_:1})])])}var m={};const I=(0,p.Z)(m,[["render",y]]);var v=I;const k=(0,o._)("hr",null,null,-1),C=(0,o._)("footer",null," footer space. ",-1);function U(t,e,n,s,r,i){return(0,o.wg)(),(0,o.iD)(o.HY,null,[k,C],64)}var B={name:"vueFooter",props:{msg:String}};window.onload=function(){console.log(""),console.log("[footer]")};const x=(0,p.Z)(B,[["render",U]]);var G=x,W={name:"App",components:{SideBar:f,AppHeader:v,AppFooter:G}};const R=(0,p.Z)(W,[["render",r]]);var V=R,z=n(2201),Z="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAYAAACtWK6eAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyNpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMDE0IDc5LjE1Njc5NywgMjAxNC8wOC8yMC0wOTo1MzowMiAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6OTk2QkI4RkE3NjE2MTFFNUE4NEU4RkIxNjQ5MTYyRDgiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6OTk2QkI4Rjk3NjE2MTFFNUE4NEU4RkIxNjQ5MTYyRDgiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIChNYWNpbnRvc2gpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6NjU2QTEyNzk3NjkyMTFFMzkxODk4RDkwQkY4Q0U0NzYiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6NjU2QTEyN0E3NjkyMTFFMzkxODk4RDkwQkY4Q0U0NzYiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz5WHowqAAAXNElEQVR42uxda4xd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";const P={class:"home"},E=(0,o._)("img",{alt:"Vue logo",src:Z},null,-1);function N(t,e,n,s,r,i){const a=(0,o.up)("HelloWorld");return(0,o.wg)(),(0,o.iD)("div",P,[E,(0,o.Wm)(a,{msg:"Welcome to Your Vue.js App"})])}var A={name:"AppHome",components:{}};const S=(0,p.Z)(A,[["render",N]]);var Q=S;const L=(0,o._)("h2",null,"Please Log In",-1),D={id:"loginForm"},Y=(0,o._)("br",null,null,-1),O=(0,o._)("p",null,[(0,o._)("button",{type:"submit",class:"w3-button w3-green w3-round"},"Login")],-1);function M(t,e,n,r,i,a){return(0,o.wg)(),(0,o.iD)("div",null,[(0,o._)("div",null,[L,(0,o._)("div",D,[(0,o._)("form",{onSubmit:e[2]||(e[2]=(0,s.iM)((t=>{a.fnLogin()}),["prevent"]))},[(0,o._)("p",null,[(0,o.wy)((0,o._)("input",{class:"w3-input",style:{width:"30%","min-width":"200px"},name:"userId",placeholder:"Enter your ID","onUpdate:modelValue":e[0]||(e[0]=t=>i.userId=t)},null,512),[[s.nr,i.userId]]),Y]),(0,o._)("p",null,[(0,o.wy)((0,o._)("input",{name:"userPassword",style:{width:"30%","min-width":"200px"},class:"w3-input",placeholder:"Enter your password","onUpdate:modelValue":e[1]||(e[1]=t=>i.userPassword=t),type:"password"},null,512),[[s.nr,i.userPassword]])]),O],32)])])])}var K={data(){return{userId:"",userPassword:""}},methods:{fnLogin(){""!==this.userId?""!==this.userPassword?alert("로그인 되었습니다."):alert("비밀번호를 입력하세요."):alert("ID를 입력하세요.")}}};const T=(0,p.Z)(K,[["render",M]]);var J=T;const j={class:"board-list",style:{"margin-top":"50px"}},X={style:{float:"left"}},q=(0,o._)("option",{value:""},"선택",-1),H=(0,o._)("option",{value:"regUserId"},"작성자",-1),F=(0,o._)("option",{value:"bbsTitle"},"제목",-1),_=(0,o._)("option",{value:"bbsContents"},"내용",-1),$=[q,H,F,_],tt={class:"common-buttons"},et={class:"w3-table-all w3-centered"},nt=(0,o._)("colgroup",null,[(0,o._)("col",{style:{width:"15%"}}),(0,o._)("col",{style:{width:"35%"}}),(0,o._)("col",{style:{width:"20%"}}),(0,o._)("col",{style:{width:"30%"}})],-1),st=(0,o._)("thead",null,[(0,o._)("tr",null,[(0,o._)("th",null,"No"),(0,o._)("th",null,"제목"),(0,o._)("th",null,"작성자"),(0,o._)("th",null,"등록일시")])],-1),ot=["onClick"],rt={key:0,class:"pagination w3-bar w3-padding-16 w3-small"},it={class:"pg"},at=["onClick"];function lt(t,e,n,r,a,l){return(0,o.wg)(),(0,o.iD)("div",j,[(0,o._)("div",X,[(0,o.wy)((0,o._)("select",{class:"w3-select w3-border",style:{"margin-right":"20px",float:"left",height:"50px",width:"20%","min-width":"100px"},"onUpdate:modelValue":e[0]||(e[0]=t=>a.searchKey=t)},$,512),[[s.bM,a.searchKey]]),(0,o.wy)((0,o._)("input",{class:"w3-input w3-border",type:"text",style:{height:"50px",width:"50%","min-width":"200px"},"onUpdate:modelValue":e[1]||(e[1]=t=>a.searchVal=t),onKeyup:e[2]||(e[2]=(0,s.D2)(((...t)=>l.fnGetList&&l.fnGetList(...t)),["enter"]))},null,544),[[s.nr,a.searchVal]])]),(0,o._)("div",tt,[(0,o._)("button",{class:"w3-button w3-round w3-blue-gray",onClick:e[3]||(e[3]=t=>l.fnGetList())},"검색"),(0,o.Uk)("  "),(0,o._)("button",{type:"button",class:"w3-button w3-round w3-blue-gray",onClick:e[4]||(e[4]=t=>l.fnGoForm(""))},"등록")]),(0,o._)("table",et,[nt,st,(0,o._)("tbody",null,[((0,o.wg)(!0),(0,o.iD)(o.HY,null,(0,o.Ko)(a.list,((t,e)=>((0,o.wg)(),(0,o.iD)("tr",{key:e},[(0,o._)("td",null,(0,i.zw)(e+1),1),(0,o._)("td",null,[(0,o._)("a",{onClick:e=>l.fnGoForm(`${t.bbsIdx}`),style:{cursor:"pointer","text-decoration":"underline"},class:"w3-text-blue w3-pointer"},(0,i.zw)(t.bbsTitle),9,ot)]),(0,o._)("td",null,(0,i.zw)(t.regUserId),1),(0,o._)("td",null,(0,i.zw)(t.regDt),1)])))),128))])]),a.paging.totalListCnt>0?((0,o.wg)(),(0,o.iD)("div",rt,[(0,o._)("span",it,[(0,o._)("a",{href:"javascript:",onClick:e[5]||(e[5]=t=>l.fnGetList(1)),class:"first w3-button"},"<<"),a.paging.startPage>10?((0,o.wg)(),(0,o.iD)("a",{key:0,href:"javascript:",onClick:e[6]||(e[6]=t=>l.fnGetList(""+(a.paging.startPage-1))),class:"prev w3-button w3-border"},"<")):(0,o.kq)("",!0),((0,o.wg)(!0),(0,o.iD)(o.HY,null,(0,o.Ko)(a.pageNavigator(),((t,e)=>((0,o.wg)(),(0,o.iD)(o.HY,null,[a.paging.page===t?((0,o.wg)(),(0,o.iD)("strong",{class:"w3-button w3-green",key:e},(0,i.zw)(t),1)):((0,o.wg)(),(0,o.iD)("a",{class:"w3-button",href:"javascript:;",onClick:e=>l.fnGetList(`${t}`),key:e},(0,i.zw)(t),9,at))],64)))),256)),a.paging.totalPageCnt>a.paging.endPage?((0,o.wg)(),(0,o.iD)("a",{key:1,href:"javascript:",onClick:e[7]||(e[7]=t=>l.fnGetList(`${a.paging.endPage+1}`)),class:"next w3-button w3-border"},">")):(0,o.kq)("",!0),(0,o._)("a",{href:"javascript:",onClick:e[8]||(e[8]=t=>l.fnGetList(`${a.paging.totalPageCnt}`)),class:"last w3-button"},">>")])])):(0,o.kq)("",!0)])}n(560);var ut={name:"BoardList",data(){return{requestBody:{},list:{bbsIdx:"",bbsTitle:"",regUserId:"",regDt:""},no:"",paging:{block:0,endPage:0,nextBlock:0,page:0,pageSize:0,prevBlock:0,startIndex:0,startPage:0,totalBlockCnt:0,totalListCnt:0,totalPageCnt:0,pagination:0},page:this.$route.query.page?this.$route.query.page:1,size:this.$route.query.size?this.$route.query.size:10,searchKey:this.$route.query.searchKey?this.$route.query.searchKey:"",searchVal:this.$route.query.searchVal?this.$route.query.searchVal:"",pageNavigator:function(){let t=[],e=this.paging.startPage,n=this.paging.endPage;for(let s=e;s<=n;s++)t.push(s);return t}}},mounted(){this.fnGetList()},methods:{fnGetList(t){void 0===t&&(t=1),this.requestBody={page:t,size:this.size,bbsCategoryCd:"B0001",searchKey:this.searchKey,searchVal:this.searchVal},this.$axios.get(this.$serverUrl+"/bbsMainList",{params:this.requestBody,headers:{}}).then((t=>{"OK"===t.data.resultCode&&(console.log(t.data),this.list=t.data.data,this.paging=t.data.pagination,this.no=this.paging.totalListCnt-(this.paging.page-1)*this.paging.pageSize)})).catch((t=>{console.log(t.message)}))},fnGoForm(t){console.log(t),this.requestBody.bbsIdx=t,this.$router.push({path:"/bbsMainInfo",query:this.requestBody})}}};const dt=(0,p.Z)(ut,[["render",lt]]);var ct=dt;const bt={class:"board-detail"},ht={class:"common-buttons"},pt={class:"board-contents"},gt={class:"board-contents"},ft={class:"board-contents"};function wt(t,e,n,r,i,a){return(0,o.wg)(),(0,o.iD)("div",bt,[(0,o._)("div",ht,[(0,o._)("button",{type:"button",class:"w3-button w3-round w3-blue-gray",onClick:e[0]||(e[0]=(...t)=>a.fnGoList&&a.fnGoList(...t))},"목록"),(0,o.Uk)("  "),(0,o._)("button",{type:"button",class:"w3-button w3-round w3-blue",onClick:e[1]||(e[1]=t=>{a.fnSaveForm()})},"저장"),(0,o.Uk)("  "),(0,o._)("button",{type:"button",class:"w3-button w3-round w3-blue-gray",onClick:e[2]||(e[2]=t=>{a.fnSaveForm("D")})},"삭제")]),(0,o._)("div",pt,[(0,o.wy)((0,o._)("input",{type:"text","onUpdate:modelValue":e[3]||(e[3]=t=>i.bbsTitle=t),class:"w3-input w3-border",placeholder:"제목을 입력해주세요."},null,512),[[s.nr,i.bbsTitle]])]),(0,o._)("div",gt,[(0,o.wy)((0,o._)("textarea",{id:"",cols:"30",rows:"10","onUpdate:modelValue":e[4]||(e[4]=t=>i.bbsContents=t),class:"w3-input w3-border",style:{resize:"none"}},"\r\n            ",512),[[s.nr,i.bbsContents]])]),(0,o._)("div",ft,[(0,o.wy)((0,o._)("input",{type:"text","onUpdate:modelValue":e[5]||(e[5]=t=>i.regUserId=t),class:"w3-input w3-border",placeholder:"작성자를 입력해주세요."},null,512),[[s.nr,i.regUserId]])]),(0,o.wy)((0,o._)("input",{type:"hidden","onUpdate:modelValue":e[6]||(e[6]=t=>i.bbsCategoryCd=t)},null,512),[[s.nr,i.bbsCategoryCd]]),(0,o.wy)((0,o._)("input",{type:"hidden","onUpdate:modelValue":e[7]||(e[7]=t=>i.bbsIdx=t)},null,512),[[s.nr,i.bbsIdx]])])}var yt={data(){return{requestBody:this.$route.query,bbsIdx:this.$route.query.bbsIdx,bbsCategoryCd:"B0001",bbsTitle:"",bbsContents:"",regDt:"",regUserId:""}},mounted(){this.fnGetForm()},methods:{fnGetForm(){void 0!==this.bbsIdx&&this.$axios.get(this.$serverUrl+"/bbsMainInfo/"+this.bbsIdx,{params:this.requestBody}).then((t=>{this.bbsIdx=t.data.bbsIdx,this.bbsCategoryCd=t.data.bbsCategoryCd,this.bbsTitle=t.data.bbsTitle,this.bbsContents=t.data.bbsContents,this.regDt=t.data.regDt,this.regUserId=t.data.regUserId})).catch((t=>{console.log(t)}))},fnGoList(){delete this.requestBody.bbsIdx,this.$router.push({path:"/bbsMainList",query:this.requestBody})},fnSaveForm(t){var e="";void 0===t?(t=void 0===this.bbsIdx||""===this.bbsIdx?"R":"U",e="저장"):e="삭제",confirm(e+"하시겠습니까?")&&(this.form={bbsIdx:this.bbsIdx,bbsCategoryCd:this.bbsCategoryCd,bbsTitle:this.bbsTitle,bbsContents:this.bbsContents,bbsTopFixYn:"N",regUserId:this.regUserId,chgUserId:this.regUserId,mode:t},this.$axios.post(this.$serverUrl+"/bbsMainExec/"+t,this.form).then((t=>{void 0!==t.data&&""!==t.data&&(alert(e+"되었습니다."),this.fnGoList())})).catch((t=>{console.log(t)})))}}};const mt=(0,p.Z)(yt,[["render",wt]]);var It=mt;const vt=[{path:"/",name:"AppHome",component:Q},{path:"/login",name:"AppLogin",component:J},{path:"/about",name:"AppAbout",component:()=>n.e(433).then(n.bind(n,1433))},{path:"/bbsMainList",name:"BoardList",component:ct},{path:"/bbsMainInfo",name:"BoardForm",component:It}],kt=(0,z.p7)({history:(0,z.PO)("/"),routes:vt});var Ct=kt,Ut=n(5121);const Bt=(0,s.ri)(V);Bt.config.globalProperties.$axios=Ut.Z,Bt.config.globalProperties.$serverUrl="//localhost:80",Bt.use(Ct).mount("#app")}},e={};function n(s){var o=e[s];if(void 0!==o)return o.exports;var r=e[s]={exports:{}};return t[s].call(r.exports,r,r.exports,n),r.exports}n.m=t,function(){var t=[];n.O=function(e,s,o,r){if(!s){var i=1/0;for(d=0;d<t.length;d++){s=t[d][0],o=t[d][1],r=t[d][2];for(var a=!0,l=0;l<s.length;l++)(!1&r||i>=r)&&Object.keys(n.O).every((function(t){return n.O[t](s[l])}))?s.splice(l--,1):(a=!1,r<i&&(i=r));if(a){t.splice(d--,1);var u=o();void 0!==u&&(e=u)}}return e}r=r||0;for(var d=t.length;d>0&&t[d-1][2]>r;d--)t[d]=t[d-1];t[d]=[s,o,r]}}(),function(){n.n=function(t){var e=t&&t.__esModule?function(){return t["default"]}:function(){return t};return n.d(e,{a:e}),e}}(),function(){n.d=function(t,e){for(var s in e)n.o(e,s)&&!n.o(t,s)&&Object.defineProperty(t,s,{enumerable:!0,get:e[s]})}}(),function(){n.f={},n.e=function(t){return Promise.all(Object.keys(n.f).reduce((function(e,s){return n.f[s](t,e),e}),[]))}}(),function(){n.u=function(t){return"js/"+t+".b0ccbbc4.js"}}(),function(){n.miniCssF=function(t){}}(),function(){n.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(t){if("object"===typeof window)return window}}()}(),function(){n.o=function(t,e){return Object.prototype.hasOwnProperty.call(t,e)}}(),function(){var t={},e="front:";n.l=function(s,o,r,i){if(t[s])t[s].push(o);else{var a,l;if(void 0!==r)for(var u=document.getElementsByTagName("script"),d=0;d<u.length;d++){var c=u[d];if(c.getAttribute("src")==s||c.getAttribute("data-webpack")==e+r){a=c;break}}a||(l=!0,a=document.createElement("script"),a.charset="utf-8",a.timeout=120,n.nc&&a.setAttribute("nonce",n.nc),a.setAttribute("data-webpack",e+r),a.src=s),t[s]=[o];var b=function(e,n){a.onerror=a.onload=null,clearTimeout(h);var o=t[s];if(delete t[s],a.parentNode&&a.parentNode.removeChild(a),o&&o.forEach((function(t){return t(n)})),e)return e(n)},h=setTimeout(b.bind(null,void 0,{type:"timeout",target:a}),12e4);a.onerror=b.bind(null,a.onerror),a.onload=b.bind(null,a.onload),l&&document.head.appendChild(a)}}}(),function(){n.r=function(t){"undefined"!==typeof Symbol&&Symbol.toStringTag&&Object.defineProperty(t,Symbol.toStringTag,{value:"Module"}),Object.defineProperty(t,"__esModule",{value:!0})}}(),function(){n.p="/"}(),function(){var t={143:0};n.f.j=function(e,s){var o=n.o(t,e)?t[e]:void 0;if(0!==o)if(o)s.push(o[2]);else{var r=new Promise((function(n,s){o=t[e]=[n,s]}));s.push(o[2]=r);var i=n.p+n.u(e),a=new Error,l=function(s){if(n.o(t,e)&&(o=t[e],0!==o&&(t[e]=void 0),o)){var r=s&&("load"===s.type?"missing":s.type),i=s&&s.target&&s.target.src;a.message="Loading chunk "+e+" failed.\n("+r+": "+i+")",a.name="ChunkLoadError",a.type=r,a.request=i,o[1](a)}};n.l(i,l,"chunk-"+e,e)}},n.O.j=function(e){return 0===t[e]};var e=function(e,s){var o,r,i=s[0],a=s[1],l=s[2],u=0;if(i.some((function(e){return 0!==t[e]}))){for(o in a)n.o(a,o)&&(n.m[o]=a[o]);if(l)var d=l(n)}for(e&&e(s);u<i.length;u++)r=i[u],n.o(t,r)&&t[r]&&t[r][0](),t[r]=0;return n.O(d)},s=self["webpackChunkfront"]=self["webpackChunkfront"]||[];s.forEach(e.bind(null,0)),s.push=e.bind(null,s.push.bind(s))}();var s=n.O(void 0,[998],(function(){return n(2723)}));s=n.O(s)})();
//# sourceMappingURL=app.e7f272cb.js.map