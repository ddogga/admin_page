<div><h1>🛒 B마트 관리자 대시보드 </h1></div>

인프런 JPA 강의를 보고 만들어본 상품,회원,주문 관리 대시보드



## 프로젝트 소개 

<div align=center><h2>📚 STACKS</h2></div>

<div align=center> 
<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
<img src="https://img.shields.io/badge/jpa-DD0031?style=for-the-badge&logoColor=white">
<br>

<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black"> 
<img src="https://img.shields.io/badge/vue.js-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white"> 
<img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white">
<br>

<img src="https://img.shields.io/badge/H2-000000?style=for-the-badge&logo=h2&logoColor=white">
<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">
<img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white">

</div>

<br>
<br>
<p>
인프런 jpa 강의를 수강한 후 이를 더 잘 이해하기 위해 만든 프로젝트 입니다. B마트 알바를 해본 경험으로 관리자 페이지를 상상하며 만들어보았습니다.

추후 버전에서는 api 팝업 메세징 기능, QR코드를 발급하여 찍으면 주문 상태가 변경되는 로직과 지도 api를 연동한 배달 관리, 지점별 사용자 관리등의 기능도 추가할 예정입니다.
</p>
<br>

<div><h3>🍊 화면단 구현 리포지토리</h3></div>

: [https://github.com/ddogga/mng_page_front](https://github.com/ddogga/mng_page_front)

<br>
<br>
<br>

<div><h3>💻 학습 목표</h3></div>

- 엔티티 연관관계 이해하기
- spring data jpa를 사용하여 crud 구현
- RestFull API 규칙에 맞춰 URL매핑
- 클래스, 메서드 분리
- 자바8 문법 최대한 활용

<br>

<div><h3>📖 테이블 정의서</h3></div>
<div><img src="https://user-images.githubusercontent.com/98453102/232744688-83f8a17a-764c-46f0-9d78-41444d3041fd.PNG"></div>

<br>
<div><h3>🥥 구현요구사항</h3></div>

### ✅ 회원가입/로그인
+ [x] 관리자 회원가입 / 로그인

<div><img src="https://user-images.githubusercontent.com/98453102/232786503-599da128-6d37-4d3b-98bc-c253f716b208.PNG"></div>
<div><img src="https://user-images.githubusercontent.com/98453102/232786225-3a8a8fd1-99a8-4bf3-b5de-23fc490e6ed9.PNG"></div>

<br>
<br>

### ✅ 대시보드
+ [x] 통합정보 (대시보드)
  + [x] 이번달, 이번년도 수익 보여주기
  + [x] 현재 진행중인 주문 보여주기
  + [x] 이번주 일정 보여주기
  + [x] 오늘 날씨 보여주기
  + [x] 연간 월매출, 순이익 차트
  + [x] 인기 상품 10개 차트

<br>
<br>
<br>
<div><img src="https://user-images.githubusercontent.com/98453102/232787092-f97f834d-58a7-48a9-b934-936e09aecdfb.png"></div>
<br>

  + [x] 연간 주문수 차트
    

<br>
<div><img src="https://user-images.githubusercontent.com/98453102/232787103-aac125cf-b638-4925-adb7-65f99219d76a.png"></div>


<br>
<br>

### ✅ 회원 관리

+ [x] 회원 삭제 구현
<div><img src="https://user-images.githubusercontent.com/98453102/232788399-10c45fe4-15c3-47d6-89d1-c2433556d0f4.PNG"></div>

<br>
<br>

### ✅ 상품 관리
+ [x] 상품 등록

<div><img src="https://user-images.githubusercontent.com/98453102/232789115-6e938235-7c5f-499d-9fb8-33f89509cb8b.PNG"></div>
<br>
<br>

+ [x] 상품 리스트
+ [x] 상품 수정
<div><img src="https://user-images.githubusercontent.com/98453102/232789128-612a1662-2dda-483f-85e2-263eec8e3483.PNG"></div>

<br>
<br>

### ✅ 주문 관리
+ [x] 주문 리스트
+ [x] 주문 상태 변경
+ [x] 주문 아이템 팝업
+ [x] 주문 취소

<div><img src="https://user-images.githubusercontent.com/98453102/232789823-6ceed081-bcd1-4892-8500-9670cbd7ccb5.PNG"></div>
<div><img src="https://user-images.githubusercontent.com/98453102/232789831-fd68acce-410c-4aee-91cb-42720f23ce0f.PNG"></div>
<div><img src="https://user-images.githubusercontent.com/98453102/232789845-c1ca6630-f521-4751-8c15-1e5c1518fad5.PNG"></div>
<div><img src="https://user-images.githubusercontent.com/98453102/232789856-1b0255a3-b234-4736-a554-8b30c718f7f9.PNG"></div>

<br>
<br>
<br>

+ [x] 취소 주문 리스트
+ [x] 취소사유 수정

<div><img src="https://user-images.githubusercontent.com/98453102/232790630-76219b65-40e9-40a9-8ae6-1820a736d12e.PNG"></div>

<br>
<br>

### ✅ 일정 관리
+ [x] 일정 등록 캘린더 구현

<div><img src="https://user-images.githubusercontent.com/98453102/232790760-a1dfb253-b331-462d-b922-861df41876bc.PNG"></div>
<div><img src="https://user-images.githubusercontent.com/98453102/232790772-2f12bcf0-4a3e-42c4-b10b-ad24d251935f.png"></div>

<br>
<br>
<br>