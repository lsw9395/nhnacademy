## simple-nc
snc(simple-nc) 만들기
과제

snc(simple-nc) 만들기
nc (netcat) 프로그램이 있습니다. 이 프로그램과 유사하게 동작하는 simple-nc 를 작성합니다.
nc 는 다음과 같이 동작합니다.
클라이언트 모드
입력인자로 받은 서버에 TCP 연결을 합니다.
사용자로 부터의 입력(STDIN)을 서버로 전송합니다.
서버로 부터 받은 데이타는 표준 출력(STDOUT) 합니다.
ctrl-c 로 프로그램을 종료합니다.
서버 모드
입력인자로 listen 포트를 입력 받습니다.
해당 포트로 TCP 서버를 실행하여 접속을 기다립니다.
클라이언트가 접속하여 데이타를 보내면, 표준 출력합니다.
사용자의 입력(STDIN) 을 클라이언트로 전송합니다.
ctrl-c 로 프로그램을 종료합니다.
Usage
Usage: snc [option] [hostname] [port]
Options:
-l   <port>     서버 모드로 동작, 입력 받은 포트로 listen
사용 예제1
$ snc -l 12345
서버로 동작.
사용자 입력을 받아 클라이언트 전송합니다.
클라이언트로부터의 응답을 표준 출력 합니다.
ctrl-c 로 프로그램 종료합니다.
한 커넥션만 처리합니다.
사용 예제2
$ snc 127.0.0.1 12345
클라이언트로 동작
사용자 입력을 받아 서버로 전송합니다.
서버로 부터의 응답을 표준 출력 합니다.
hostname 으로는 fqdn,ip 모두 사용할 수 있습니다.
ctrl-c 로 프로그램 종료합니다.
