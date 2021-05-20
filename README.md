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
|-|맵 제네레이터|30%|랜덤 생성 구현 완료, 삭제, 무한 생성 및 JSON 기반 패턴 작업 필요|
|**플레이어**|조작|100%|-|
|-|애니메이션|100%|-
|-|물리처리|90%|블럭 모서리에서 물리 처리 부자연스러움
|**UI**|점수|90%|위치 세부 조정 필요|
|-|게임오버|80%|랭킹 연결 필요
|-|시작메뉴|80%|게임 재시작, 랭킹 연결 필요|
|-|랭킹|0%|랭킹 뷰 및 랭킹 정보 저장 필요|
|**사운드**||0%|효과음 및 배경음 추가 필요|


## 3. 커밋 관련 자료
<img src="/Doc/commit_05_20.png"></img>
|주차|횟수|
|--|--|
|1주차|4회|
|2주차|0회|
|3주차|3회|
|4주차|3회|
|5주차|10회|
|6주차|0회|
|7주차|10회|

## 4. 게임 오브젝트 설명

기본적인 프레임워크 구조는 수업에서 사용하는 프레임워크 구조 사용.

### Ball  
<img src="/Doc/bird1_1.png" width="5%" height="5%"></img>
<br>
플레이어 조작 처리 및 블럭 충돌 시 행동 처리  
카메라 밖으로 나가는지 체크도 해당 클래스에서 수행

### Block  
<img src="/Doc/bg_pillardown.png" width="5%" height="5%"></img>
<img src="/Doc/bg_pillarup.png" width="5%" height="5%"></img>
<img src="/Doc/bg_pillardown_red.png" width="5%" height="5%"></img>
<img src="/Doc/bg_pillarup_red.png" width="5%" height="5%"></img>
<br>
블럭 타입 및 방향에 따른 이미지 출력 및 물리 정보 보유  
충돌 시 탄성을 적용하여 Ball을 튕구는 일반 블럭 과 바로 게임오버 시키는 즉사 블럭 구현

### BlockManager  
맵 제네레이터 역할
블럭 생성, Block 클래스를 한번에 위, 아래로 생성  
생성 시 블럭 간의 간격, 타입 지정 및 랜덤 생성  
이 후 블럭 삭제, 무한 생성 및 JSON 기반 블럭 배치 구현 예정 

### HorizontalScrollBackground
수업에서 배운 것과 동일하나 Camera 정보 고려하여 배경 스크롤링 및 draw 수행

### Camera
canvas.translate를 이용하여 플레이어 위치를 따라가며 그리도록 수행  
플레이어가 앞이 아닌 뒤로 이동 시 카메라는 고정되어 움직이지 않음

### Collision Helper
수업시간에 배운 것을 응용하여 충돌 여부 + 상하좌우 충돌 방향 계산하는 utill 클래스

### Score
수업에서 배운 것과 동일, 플레이어가 날아간 최대 거리 출력

## 5. ETC
기획 발표 리드미 : [README_prev0.md](README_prev0.md)
