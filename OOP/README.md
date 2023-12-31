## 사전만들기
Java의 Collections Framework에서 제공하는 여러 클래스 중, 가장 걸맞은 자료구조를 선택하여 아래 요구사항을 만족하는 프로그램을 작성하세요.


1. 프로그램이 실행되면 words.txt 파일을 로드하여 단어들을 메모리에 로드합니다.
2. 로드가 끝나면 "검색한 단어를 입력하세요: " 라는 프롬프트를 출력합니다.
3. 프롬프트에 단어를 입력하면 검색 결과를 보여줍니다.
4. exit() 를 입력하면 프로그램을 종료합니다.

## 자판기
아래는 조선대학교 IT 융합대학 1층에 설치되어 있는 음료 자판기입니다.
![image](https://github.com/lsw9395/nhnacademy/assets/62645150/cedb561e-ced7-4b5c-b235-2612e3cb9032)

음료 자판기는 아래와 같이 동작합니다.


1. 따뜻한 음료와 시원한 음료를 판매합니다.
2. 따뜻한 음료: 아메리카노, 카페라떼, 모카치노, 핫 초코 네 가지 음료를 판매합니다.
3. 시원한 음료: 아이스 아메리카노, 아이스 초코, 아이스 카페라떼, 복숭아 아이스티 음료를 판매합니다.


따뜻한 음료가 판매되는 절차는 아래와 같습니다.
1. 결재합니다.
2. 컵 나오는 곳에서 종이컵을 받습니다.
3. 음료 추출대에서 음료를 받습니다.


시원한 음료가 판매되는 절차는 아래와 같습니다.
1. 결재합니다.
2. 컵 나오는 곳에서 플라스틱 컵을 받습니다.
3. 얼음 추출대에서 얼음을 받습니다.
3. 음료 추출대에서 음료를 받습니다.


결재 방식
현금과 신용카드, 온라인 페이를 사용할 수 있습니다. (실제는 현금은 안되지만 된다고 가정합니다)
1. 현금을 받을 경우 현금을 받고 거스름돈을 동전으로 반환합니다.
2. 신용카드를 선택할 경우 신용카드에 연결하여 체크를 하고 true일 경우 음료 판매를 진행합니다.
3. 온라인 페이를 선택할 경우 온라인 페이사에 연결하여 체크하고 true일 경우 음료 판매를 진행합니다.


문제
조선대학교 IT 융합대학 음료 자판기 프로그램을 Java로 작성합니다.
결재
여러 결재 방식에 대해 객체지향적 처리를 프로그래밍 합니다.
현금 결재의 경우 잔액을 동전으로 반환할 때 가장 적은 숫자의 동전을 돌려주기 위해 Greedy Method 기법을 사용합니다.
음료 판매
음료를 판매하는 방식을 framework에 정의하고 모델링합니다.
각 음료는 제조 방식과 판매 절차가 다릅니다. 이를 모델링해야 합니다.
음료를 판매를 구현합니다.
어느날 사장이 미친척하고 티백 차를 팔기로 결정하고 티백 추출기를 설치하려 합니다.
이 요구사항을 적용할 수 있도록 모델링 되어야 합니다.


이 요구사항을 모두 만족하는 응용 프로그램을 작성하세요.
## Color Tokenizer
Visitor 패턴은 데이터와 연산을 분리하여, 변화가 자주 일어나지 않는 데이터에 대해 빈번하게 추가되는 연산들을 분리하여 객체 상호작용으로 연산을 처리하게 하는 객체지향 설계 방식입니다.
이 패턴은 Iterator에서 다양한 데이터 처리, 컴파일러 등의 다양한 분야에서 활용도가 높은 패턴입니다.


문제.
Visitor 패턴을 활용하여 Java 소스 코드 파일을 Syntax Coloring이 적용된 웹 문서(Html)로 작성하는 프로그램을 작성하세요.
1. 작성된 프로그램은 아래와 같이 실행되어야 합니다.
   > java ColorTokenizer <파일 이름>.java
  프로그램의 실행결과로 생성되는 파일의 이름은 <파일 이름>.html


2. 프로그램은 Java 파일을 파라미터로 받아들여, 아래의 처리를 수행할 수 있어야 합니다.
   - Java의 키워드를 특정한 색으로 표현합니다.
   - 심볼(rlgh)을 특정한 색으로 표현합니다.


html 파일은 아래와 같은 형식이 됩니다.
![image](https://github.com/lsw9395/nhnacademy/assets/62645150/0e84b034-9abd-4224-bfe4-4ebc0bc2db09)

