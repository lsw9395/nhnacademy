## 이력서 1
과제1 - 이력서1
이력서1
HTML 실습하기
금일 진행했던 교제를 활용하여 직접 실습한 html 파일을 제출합니다.
파일형식 : 본인이름.실습1.zip 형태로 제출해주세요.
제출은 이력서 만들기 제출링크에 함께 파일을 첨부합니다.
이력서 만들기
이력서는 자신의 이력서를 만들 필요는 없습니다.
특정 대상을 선정하여 이력서를 만들어서 html 파일로 제출합니다.
파일형식 : 본인이름.html ex) 홍길동.html

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/76042ae1-469c-4b83-9555-5676b8127f9d)

## 장바구니
과제2
javaScript 실습에서 사용할 ui를 미리 만들어 주세요.
장바구니 테이블의 데이터는 모두 입력할 필요는 없습니다. ( sample로 2 row 정도만 입력해주세요 )
디자인은 자유 형식입니다. ( 적절히 잘 꾸며보세요 )
![image](https://github.com/lsw9395/nhnacademy/assets/62645150/11b2aa10-62f1-49fd-90db-4b6ab8a6acd2)

## 항공예약
과제3
과제2) javascript 실습에서 사용할 ui 미리 만들기

## TODO LIST
step1 ~ step6 todo를 따라가면서 실습해주세요.
step 1 - queryString에 대해서 이해하는 부분  - 직접 todo 를 따라가면서 만들어보세요.
Navigator.js
step 2 ~ step3 까지는 기본적인 ui만들기
'<template>' 테그를 이용해서 만들기
소스에는 보이지만. 브라우저 화면에는 보이지 않음. ( 직접 확인해보기 )
step 4 - memoryStore
step 5 - localStorageStore
step 6 - api
https://nhnacademy.dooray.com/share/pages/7i6-W8S2TI6e-oKUJVL57g/3373349495003408976
fetch , await 사용하기
step 1 ~ 6 완료 후 해야할 것들
성능 개선하기
일자별로 event를 조회해서 처리하던 것을..
1일 ~31일 까지 있다면 31번 비동기 호출이 일어나고 있는 상황...
api 문서의 event - 조회 - 월단위 api를 활용해서 월단위로 한 번에 조회해서 ui를 표현하는 방식으로 개선하기


table에 데이터는 모두 입력할 필요 없습니다. ( sample 2row 정도만 입력해주세요 )
디자인은 자유 형식입니다.
![image](https://github.com/lsw9395/nhnacademy/assets/62645150/444571a8-9e5e-4870-9272-255afbc12027)

## 코로나 19 현황 만들기
코로나바이러스-19 현황 만들기
첨부해드린 파일을 참고하여 만들어 주세요.
요구사항
디자인 : 다르게 ..
첨부해드린 파일의 html, CSS 사용하시면 감점입니다. 직접 구현해보세요.
'연령대별' 현황에서 남성, 여성을 제외한 연령대별 현황 데이터 중 각 column 별 max 값을 찾아서 배경색을 변경해주세요. (반드시 배경색은 yellow일 필요는 없습니다)
로딩 이미지 구현하기.
첨부해드린 파일을 참고하셔서 로딩 바를 구현해보세요.
모든데이터가 표현이 완료되는 시점에.. 로딩 이미지가 사라져야 합니다.(반드시..)
API 호출되는 속도를 고려하여 구현해주세요.
순차적으로(동기적으로) 데이터를 로딩해서 보여준다면. 사용자 입장에서 오랜 시간 동안 기다려야겠죠?
이전, 다음, 오늘 버튼을 구현해주세요.
일단위로 이동할 수 있도록 구현해주세요.
API List
보건복지부_코로나19 감염현황 총괄 통계
https://www.data.go.kr/data/15098780/openapi.do
누적 사망자수 누적 확진자수, 누적 검사수  부분 개발시 이용합니다.
보건복지부_코로나19 확진자 성별 연령별 현황
https://www.data.go.kr/data/15098771/openapi.do
연령대 (table) 영역에 보이는 부분 개발시 이용합니다.
정렬순서는 아래 이미지를 참고해주세요
0 ~ 80 , 남성, 여성 순서로 정렬 합니다.
0~80 범위의 데이터중 각 컬럼별 max 값을 background 색상을 노랑으로 설정합니다.
질병관리청_코로나19 국내발생현황 조회
https://www.data.go.kr/data/15099842/openapi.do
일일 확진, 일일 사망 , 일일 신규입원, 일일 재원 위중증, 인구 10만명당 사망  정보를 구현할 때 이용합니다.
개발 참고사항 ( 반드시 읽어주세요.)
API 호출시 serviceKey 본인 서비스 키를 사용합니다.
apiType=JSON을 사용합니다. JSON은 반드시 대문자로 사용합니다.
let url = 'http://apis.data.go.kr/1352000/ODMS_COVID_02/callCovid02Api'; /*URL*/
        let queryParams = '?' + encodeURIComponent('serviceKey') + '=' + SERVICE_KEY; /*Service Key*/
        queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
        queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('500'); /**/
        queryParams += '&' + encodeURIComponent('apiType') + '=' + encodeURIComponent('JSON'); /**/
        queryParams += '&' + encodeURIComponent('status_dt') + '=' + encodeURIComponent(statusDt); /**/
![image](https://github.com/lsw9395/nhnacademy/assets/62645150/bce1d84a-7151-40be-a858-0061cf098a30)

