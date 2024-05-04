# Airbnb 클론 코딩

<br>

## 프로젝트 목적
에어비엔비의 도메인을 분석하여 유사한 시스템을 구축해보는것을 목표로 한다.

<br>

<br>

## 사용 기술

[![My Skills](https://skillicons.dev/icons?i=vue,spring,postgres)](https://skillicons.dev)

프론트엔드 서버 - vue.js

백엔드 서버 - Spring

ORM - JPA

DB - PostgreSQL

<br>

<br>

## 데이터 및 MSA 구성

대략적인 DB의 전체 구조는 다음과 같다.

![이미지](https://private-user-images.githubusercontent.com/163799874/327932608-1b0dff12-e0f6-433f-a0da-6b0a160e6988.png?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3MTQ4MDg1NTEsIm5iZiI6MTcxNDgwODI1MSwicGF0aCI6Ii8xNjM3OTk4NzQvMzI3OTMyNjA4LTFiMGRmZjEyLWUwZjYtNDMzZi1hMGRhLTZiMGExNjBlNjk4OC5wbmc_WC1BbXotQWxnb3JpdGhtPUFXUzQtSE1BQy1TSEEyNTYmWC1BbXotQ3JlZGVudGlhbD1BS0lBVkNPRFlMU0E1M1BRSzRaQSUyRjIwMjQwNTA0JTJGdXMtZWFzdC0xJTJGczMlMkZhd3M0X3JlcXVlc3QmWC1BbXotRGF0ZT0yMDI0MDUwNFQwNzM3MzFaJlgtQW16LUV4cGlyZXM9MzAwJlgtQW16LVNpZ25hdHVyZT01MzQ5ZGU0ZDE1MjRkY2U3MTU2N2UwMGIwZmYyMzdkZGM5MDMxMGY1YmJhOWJmMDcwYThjNWQ1YWI0ZWE5MDJhJlgtQW16LVNpZ25lZEhlYWRlcnM9aG9zdCZhY3Rvcl9pZD0wJmtleV9pZD0wJnJlcG9faWQ9MCJ9.uG9DUjNXYxiphVI0JoZXkJNRSHZn2IgSSPeU5a3lbhE)

여기서 서비스를 User, Room, Coment, Reservation, Wishlist 5개로 구분하여

각 서비스가 독립적으로 실행 될 수 있도록 테이블의 FK관계를 조정하여 데이터도 독립적일수 있게 번경하였다.

최종적인 DB의 구조는 다음과 같다.

[이미지]

<br>

<br>

## 구현한 기능

이번 프로젝트에서 구현한 주요 기능들은 다음과 같다   
   
### 1. 유저 데이터 JWT Encoding   
유저와 관련된 데이터를 요청할때는 서버에 보내는 요청에 유저ID를 같이 보내야 한다.   
이 요청은 DB에 직간접적으로 간섭할 수 있는데 유저ID가 위조되거나 변조된다면 위험하기 때문에   
유저 관련 정보를 JWT로 Encoding해 전송하여 위조 및 변조를 방지했다.   
<br>

### 2. 예약에 동시 접근시 충돌 해결   
방 정보를 불러와 읽는 시간과 예약을 시작하는 시간은 같지 않다.   
두 시간 사이에 유저가 예약하고자 하는 날짜가 예약되었다면 중복하여 예약할 수 없어야 한다.   
또는 여러 유저가 동시에 예약을 진행 할 경우 이를 구분할 방법이 필요하다.   
이 프로젝트에서는 예약 가능 날짜를 저장하는 테이블을 하나 추가하여,   
예약 버튼을 누르면 가능 날짜 테이블을 확인하고 바로 추가하도록 구성했다.   
또한 추가된지 5분이 지나도 예약이 완료되지 않으면 다시 예약 가능으로 변경한다.   
<br>

### 3. 다중 쿼리 파라미터를 통한 데이터 검색   
에어비엔비의 방 정보는 상세하게 구성되어있고 또 상세하게 검색이 가능하다.   
방 리스트를 불러올 때 여러 쿼리파라미터로 값을 받아오고,   
값을 기반으로 방 리스트를 필터링 한 뒤 반환하여   
다양한 검색옵션을 한번에 적용할수 있도록 구성했다.   
<br>

### 4. MSA 개념 도입
서비스를 기준으로 서버를 구분하여 독립적으로 실행되게 구성하였다.   
여러 서버를 묶는데는 Eureka와 Gateway를 사용하였다.   
하나의 깃 레포지토리 안에 모든 서비스가 포함되어 있지만, 실제 구현에서는   
각자의 컴퓨터에서 서버를 실행하여 진행했고 정상적으로 작동하는것을 볼 수 있었다.   
<br>

### 5. Feign을 통한 마이크로 서비스 호출   
여러 서비스를 나누어 독립적으로 만들더라도 어쩔수 없이 다른 서비스를 호출해야 할 일이 생긴다.   
그럴 경우 Feign을 사용하여 간단하게 다른 서비스를 호출할 수 있도록 구성했다.   

<br>

그 외에도 로그인, 회원가입, 위시리스트 생성 및 등록, 호스트 방 추가 및 수정 등의 기본 기능들도 구현하였다.

<br>

<br>

## 맡은 역할 및 후기

김재윤 - Comment, Wishlist 백 담당
> 여기에 후기

박분도 - Reservation 백 담당
> 여기에 후기

이수진 - Room 백 담당
> 이번 프로젝트는 JPA를 처음으로 사용해보았습니다. RDB 쿼리 문법과 다른 부분이 많아 처음에는 고생하였지만 적응하고 나니 JPA에서 미리 정의해놓은 메서드를 가져와 쓰는것이 매우 편리하게 느껴졌습니다.   
> 에어비엔비의 일부만 따라해보기로 하였는데도 테이블의 수와 필드 수가 정말 많았습니다. 나름대로 MSA를 적용하여 분담하니 크게 어렵진 않았지만 진행하다보니 테이블에 비효율적인 부분이 눈에 띄어 도메인 분석이 미흡했다는것을 알 수 있었습니다.   
> 프로젝트를 진행할때마다 정말 많은것들을 배우고 있어 다음 프로젝트는 더 잘 할 수 있겠다는 생각이 들어 자신감이 생겼습니다.

정희석 - User, Eureka 백 담당
> 여기에 후기
