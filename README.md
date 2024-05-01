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

[이미지]

여기서 서비스를 User, Room, Coment, Reservation, Wishlist 5개로 구분하여

각 서비스가 사용하는 데이터 테이블이 독립되도록 FK 관계를 끊고 데이터 필드를 추가하였다.

최종적인 DB의 구조는 다음과 같다.

[이미지]

<br>

<br>

## 구현한 기능

이번 프로젝트에서 구현한 기능들중 주요 기능들은 다음과 같다

1. 유저 데이터 토큰화

2. Feign을 통한 마이크로 서비스 호출

3. 예약에 동시 접근시 충돌 해결

4. 다중 쿼리 파라미터를 통한 데이터 검색

그 외에도 로그인, 회원가입, 위시리스트 생성 및 등록, 호스트 방 추가 및 수정 등의 기본 기능들도 구현하였다.

<br>

<br>

## 맡은 역할 및 후기

김재윤 - Comment, Wishlist 백 담당
> 여기에 후기

박분도 - Reservation 백 담당
> 여기에 후기

이수진 - Room 백 담당
> 여기에 후기

정희석 - User, Eureka 백 담당
> 여기에 후기
