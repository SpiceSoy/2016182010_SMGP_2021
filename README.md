Flappy Ball
===================

## 1. 게임 소개
> 플래피 버드와 골프공 게임을 합친 간단한 모바일 게임  
> 골프공 조작을 이용해 새를 최대한 멀리 날려보자

## 2. 진행 상황

|항목|세부|진행율|비고|
|--|--|--|--|
|**맵**|블럭|100%|일정 남으면 블럭 종류 추가 예정|
|-|배경|100%|레이어링, 스크롤링 구현 완료|
|-|맵 제네레이터|100%|랜덤 생성 및 JSON에 지정한 패턴 2종에 따라 생성 완료|
|**플레이어**|조작|100%|-|
|-|애니메이션|100%|-|
|-|물리처리|90%|특정 상황에서 물리 처리 부자연스러움|
|**UI**|점수|100%|-|
|-|게임오버|100%|-|
|-|시작메뉴|100%|-|
|-|랭킹|100%|랭킹 연결 및 로컬 저장 완료|
|**사운드**||100%|간단한 배경음 및 효과음 추가 완료|


## 3. 커밋 관련 자료
<img src="/Doc/commit_06_11.png"></img>
|주차|횟수|
|--|--|
|1주차|4회|
|2주차|0회|
|3주차|3회|
|4주차|3회|
|5주차|10회|
|6주차|0회|
|7주차|10회|
|8주차|8회|
|9주차|5회|
|10주차|3회|
|총합|46회|


*발표 준비를 위한 readme 파일 수정 커밋 제외

## 4. 사용된 기술
### 4.1 직접 개발한 것
* 카메라  
canvas.translate 함수를 이용하여 플레이어 위치를 따라가며 그리기 수행

* 탄성 처리  
물리 충돌 성공 시 블럭에서 설정한 탄성계수를 통해 Ball의 속력을 변경


* 랭킹 저장  
SQLite를 사용하여 랭킹 저장 및 로드  

* 맵 생성을 위한 JSON  
맵 생성 시 일직선 이나 계단식 배치 등의 패턴식 배치를 위한 JSON 파일 작성  
작성된 JSON 파일을 파싱하여 맵 배치 수행

* 액티비티 전환
UI와 게임 뷰 간 전환을 안드로이드 액티비티를 이용하여 구현

필요에 따라 구글 검색 및 문서, 자료 참고하여 개발

### 4.2 수업내용에서 차용한 것
* 충돌 검사 : 수업시간에 사용한 것에 방향 판단하는 기능 추가하여 사용
* 백그라운드 : 카메라에 맞게 응용하여 사용
* 프레임워크 구조
* 터치
* 사운드
* 스코어 UI

## 5. 아쉬운 것들
### 5.1 보충해야 할 것들
기본적으로 기획 당시 생각했던 것들은 다 추가하였으나 실제 개발하면서 부족하다고 느낀 것
1. 패턴, 블럭 타입 종류 증가  
패턴이나 블럭 타입을 다양하게 넣었으면 좋았을 것 같았다.
2. 리소스 변경  
지금 플래피 버드 이미지 그대로 쓰고 있는데 저작권이나 게임컨셉에 어울리지 않는다.
3. 액티비티 기반 씬 전환 변경
액티비티를 사용한 씬 전환은 애니메이션이 부자연스러워 게임에 어울리지 않는 것 같음
4. 해상도나 비율 관련 이슈  
카메라 밖으로 나가면 죽는 게임에서 카메라 크기, 비율이 기기의 화면에 따라 달라짐  
기기마다 게임 플레이가 달라질 수 있으니 대응해야 할 것 같음
5. UI 보강 필요  
튜토리얼, 조작법 등등 추가 필요

### 5.2 결국 해결하지 못한 문제
바운딩 박사의 모서리와 모서리 충돌 시 반작용 적용 시 부자연스럽게 보이는 문제  

### 5.3 기말 프로젝트하면서 겪은 어려움
위에서 설명한 것처럼 씬 구현을 안드로이드 액티비티를 이용하여 구현했는데  
액티비티 전환이나 삭제 등, 관리하는 것이 어려웠다.

## 6. ETC
1차(기획) 발표 리드미 : [커밋 스냅샷](https://github.com/SpiceSoy/2016182010_SMGP_2021/tree/265320c8bc195aaf3b1441171d1bd1987ed544f8/README.md)

2차(중간) 발표 리드미 : [커밋 스냅샷](https://github.com/SpiceSoy/2016182010_SMGP_2021/blob/d42d1066cac9429e7dbfb08207845035bf03ffe3/README.md)

