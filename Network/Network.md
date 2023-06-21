## UTM을 이용한 Linux 설치
UTM을 이용한 Linux 설치하기
UTM을 이용한 Linux 설치하기
UTM은 iOS 및 macOS을 위한 완전한 기능을 갖춘 시스템 에뮬레이터 및 가상 머신 호스트이다. QEMU를 기반으로 합니다. 다시 말해, Mac, iPhone 및 iPad에서 Windows, Linux 등을 실행할 수 있다.

UTM 파일 받기
UTM은 Mac App Store 또는 UTM 사이트에서 받을 수 있다.

Mac App Store 버전은 UTM 사이트 버전과 동일하여 기능적 차이는 없다. 다만, Mac App Store에서 유료로 구매할 경우, 자동 업데이트를 받을 수 있고, UTM 개발에 직접 자금을 지원한다고 볼 수 있다.

UTM 사이트 : https://mac.getutm.app/
![image](https://github.com/lsw9395/nhnacademy/assets/62645150/533adfb4-e302-48c2-a71d-267a3fcb984a)


Mac App Store : https://apps.apple.com/us/app/utm-virtual-machines/id1538878817
![image](https://github.com/lsw9395/nhnacademy/assets/62645150/44628c4c-e938-44d4-b708-37117991017b)


오픈 소스의 지원을 위해 Mac App Store에서 유료로 받을 수도 있지만, 여기서는 UTM 사이트에서 무료로 받기로 한다.

UTM 사이트의 Download를 눌러서 TUM.dmg를 받는다.

UTM 설치하기
UTM 사이트에서 다운로드 받았은 UTM.dmg 파일을 더블 클릭 또는 DiskImageManager를 이용해 실행하면 아래와 같은 실행된다.
![image](https://github.com/lsw9395/nhnacademy/assets/62645150/92bb3fb7-e091-4b12-a3ee-0b9bcb6bd315)


일반적인 dmg 파일 실행 형태와 비슷하면, UTM 아이콘을 드레그해서 Applications에 넣는 과정을 통해 설치를 진행한다.

가상머신 생성하기
UTM을 설치하였다면 실행해 보자. 앱스토어를 통해 설치되지 않은 경우 아래와 같은 확인창이 뜬다.
![image](https://github.com/lsw9395/nhnacademy/assets/62645150/a5f7bd84-ce91-4c1c-a609-5ce00d6b3fc1)



믿을 수 있는 사이트에서 받았다고 생각되면 열기를 눌러 UTM을 실행한다.
![image](https://github.com/lsw9395/nhnacademy/assets/62645150/095844cf-116e-4535-ad99-4d4946c27e67)




가상머신 정보 설정
UTM에 Ubuntu를 구동시킬 가상머신 정보를 설정해 보자

가상머신 종류

UTM에 새 가상머신을 만들기 위해 위 화면중 "새 가상머신 만들기"를 클릭하면 아래와 같이 두가지 가상머신 만들기중 하나를 선택하도록 한다.
![image](https://github.com/lsw9395/nhnacademy/assets/62645150/6ae9393f-3b04-4e0d-a8ce-fd6a5bf6fad6)


Virtualize
실행 중인 CPU에서 구동이 가능한 OS를 설치할때 사용한다
빠르다
예 : Apple ARM64에서 ARM64용 Ubuntu 설치
Emulate
실행 중인 CPU와 다른 CPU Architecture용 OS를 설치할때 사용한다
에뮬레이터를 통해 구동 되므로 느리다
예 : Apple ARM64에서 AMD64용 Ubuntu 설치
설치할 OS는 ARM64용 Ubuntu로서 Apple ARM64에서 구동 가능하므로, Virtualize를 선택한다.

설치할 OS 종류

Virtualize를 선택하면 설치할 OS를 지정하도록 한다.
![image](https://github.com/lsw9395/nhnacademy/assets/62645150/0c188f84-1484-488e-9c72-c6a3ed405c0d)


Ubuntu를 설치하기 위해 Linux를 선택한다.

Linux 설치 모드에서는 기존에 설치된 Linux 관련 이미지가 있을 경우 이를 이용하거나 ISO 이미지를 이용해 설치 가능하다.

설정 화면에서 Boot ISO Image의 탐색 버튼을 눌러 앞에서 받아 둔 Ubuntu ISO 이미지를 선택한다.
![image](https://github.com/lsw9395/nhnacademy/assets/62645150/b8bc82a3-495c-4f9a-9470-5f3ded5f9451)


기본 장치 설정

설치할 이미지 선택이 끝나면, 우측 아래에 있는 continue 버튼을 눌러 다음 과정을 진행하다.

다음 과정은 장치 설정 과정으로 가상머신에서 사용할 메모리 용량, CPU 갯수 등을 지정한다. 무엇을 설정해야할지 모를 경우에는 기본 설정을 이용하고, 사용중 필요한 경우 변경하면 된다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/22a7fc4a-794a-47ca-ad0d-0c4b19180ea9)


Memory : 4GB
CPU Cores : 2
Hardware OpenGL Acceleration : Disable
기본 설정 후 continue 버튼을 눌러 저장 공간을 설정하는 스토리지 설정으로 넘어간다.

저장 용량 설정

다음 Ubuntu 패키지를 설치하고, 주 저장공간으로 사용할 스토리지를 정한다.

기본은 64GB이며 예상되는 필요 용량만큼으로 변경한다.

여기서 설정한 용량은 가상머신에서 사용하는 저장공간 용량으로 생성과 동시에 설정된 용량을 사용하지는 않는다. 하지만, 너무 작게 잡을 경우, 사용중 변경 작업이 필요할 수 있으므로 허용되는 범위내에서의 적절한 용량으로 설정한다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/246231a5-cdae-4866-b507-c6939d506d6b)


저장 공간의 용량 설정이 완료되면, continue 버튼을 눌러 다음 단계인 공유 폴더 설정으로 넘어 간다.

공유 폴더 설정

공유 폴더랑 호스트와 가상머신이 동시에 접근 가능한 영역으로 작업 중인 데이터 공유등으로 사용된다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/5c3d127a-9a11-4194-ba80-6b9c8f3bb4cf)


호스트와 가상머신간 공유할 영역이 설정되면, continue 버튼을 눌러 기본 설정을 완료하고 가상머신 생성전 마지막 확인을 한다.

가상머신 생성 정보 확인

Summary에는 앞서 설정한 가상머신 설정 정보와 부가적으로 가공된 정보가 보여진다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/18e0101b-1922-43f0-ab28-625ec00022c2)


Summary 정보가 확인되었으면, 저장 버튼을 눌러 설정 정보를 저장되면서 우측편에 해당 이름의 가상머신 정보가 저장된 것을 볼 수 있다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/c2b221ad-ff4b-40ec-92f3-548a2b2b025a)

## Linux 개발 환경 구성하기
Linux 개발 환경 구성하기
구성
우분투(Ubuntu 22.04)
우분투 파일 받기
우분투 설치 파일은 ubuntu.com에서 받을 수 있다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/b86cee60-1b85-4274-80bf-0fee813800d3)


우분투 설치하기
설치 시작
대시보드에서 설치한 가상머신의 실행 버튼을 눌러 실행하면 새로운 창이 생성되면서 가상머신이 동작하면서 설정 과정에서 등록해둔 Ubuntu 22.04 ISO 이미지를 이용해 설치과정이 시작된다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/6ebb53bf-6446-4fc2-ae5a-c31336a0c5da)


설치 화면에서 첫줄인 "Try or Install Ubuntu Server"를 선택한다.

이후 나오는 설정 화면들을 아래의 설명에 따라 선택한다.

언어 선택
English를 선택하고, 엔터를 입력한다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/59fb577a-6166-4304-9d69-0100693815f5)


키보드 선택
키보드는 기본으로 사용하는 English(US)을 선택하고, 아래 Done을 선택한다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/25862a62-d9e3-4b08-8310-f61c0cbc6127)


서버 이미지 종류 선택
설치하고자하는 서버의 이미지가 기본적인 패키지 구성이 완료된 버전을 설치할 것으로 "Ubuntu Server"를 선택하고, 아래 Done을 선택한다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/cc83226f-d2fb-40dd-9bdf-b047d21bcbfc)


네트워크 설정
설치할 시스템에는 최소 하나 이상의 네트워크 인터페이스가 있어야 하고, 이와 관련된 네트워크 인터페이스 정보를 설정한다. 특별히 변경해야될 것이 없으면 기본 상태를 두고, 아래의 Done을 선택한다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/6af4a176-61f3-4577-b98d-e1c84fd8a6d3)


Proxy 설정
설정할게 없으므로 빈 상태로 두고, 아래의 Done을 선택한다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/a93c986d-eafa-49cd-9430-aac7b2281bda)


우분투 보관소 설정
우분투 패키기등을 다운로드 하기 위한 보관소를 설정한다. 별도의 관리 서버가 없다면 기본상태로 두고, 아래의 Done을 선택한다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/316411f9-dafa-43ec-9090-1c5c99130aba)


저장공간 설정
실제 머신의 경우, 머신에 장착되어 있는 하드 디스크나 SSD로 설정하겠지만, 가상머신의 경우 지정된 크기까기 가능한 파일을 연결하여 가상 저장 장치를 구성한다.

그리고, 가상 저장 장치를 어떠한 영역으로 나누어 사용할지를 설정한다.

특별히 파티션 관련 지식이 없다면 기본 설정을 사용하면 된다.
![image](https://github.com/lsw9395/nhnacademy/assets/62645150/b228ab28-cd52-46f0-b949-5c85d65505d9)


![image](https://github.com/lsw9395/nhnacademy/assets/62645150/25515b70-9217-48d3-b0e0-2b658e26e43a)


저장 공간 파티션에 대한 설정이 완료되면, 최종 확인에서 Continue를 선택하여 저장한다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/54233835-9e90-4fb6-b481-31ea782e2342)


시스템 기본 정보 설정
설치되는 시스템의 이름, 사용자 아이디, 패스워드등 기본 정보를 입력한다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/ef2b88eb-8ab6-4a4d-bcb8-f9f43ee496d8)


SSH 서버 설치
Ubuntu 서버를 원격으로 접속하기 위해서는 ssh 서버가 필요하므로 설치를 선택한다.

그리고, 접속 과정을 별도의 키로 관리하려면 "Import SSH Identity"를 선택하고 그렇지 않고 아이디와 패스워드만으로 접속하려면 "No"를 선택한다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/43fb4edb-a141-4849-ac88-a5051dc8c888)


기타 서버 프로그램 설치
특별히 원하는 프로그램이 있는 경우 해당 프로그램을 선택한다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/97d42ce9-58ac-40d5-83d5-8554fd90090d)


설치 시작
모든 설정이 완료되면 설치를 시작한다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/69c4b3ce-4271-4a7c-bee2-577344d28d8b)


모든 설치가 완료될때까지 기다린다.

설치가 완료되면 아래와 같이 "Reboot Now" 선택 버튼이 출력된다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/f8f3771d-1591-4beb-b535-c4cf7a985c64)


설치 이미지 제거
"Reboot Now"를 누르면 시스템이 재부팅 중 멈추거나 다시 설치 화면이 나올 수도 있다.

이때, 가상머신을 완전히 끈다.

그리고, 대시보드의 좌측에 나열되어 있는 가상머을 선택하고, 오른쪽 상단 끝에 있는 설정에 들어 간다.

설정의 좌측 아래에 있는 드라이브에서 USB Drive를 선택한 후 Delete Drive를 선택해 제거하고, 설정을 저장한다.
![image](https://github.com/lsw9395/nhnacademy/assets/62645150/1eb7c37d-da96-4dd9-a1c3-190b05a13bde)


Ubuntu 초기 설정
Ubuntu 설치가 되었으므로 가상머신을 동작시켜 Ubuntu를 실행해 본다. 정상적으로 설치되었다면 아래와 같은 터미널 화면을 볼 수 있다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/40f17f01-a825-431b-9d09-e2d748ef76d1)


![image](https://github.com/lsw9395/nhnacademy/assets/62645150/ae931ba9-5018-44e1-ac9b-caccd05ed1f3)


Apt 정보 최신화
apt(Advanced Package Tool)는 우분투 계열의 소프트웨어 관리 툴이다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/3db70b2d-9565-47a1-8d98-79b7f96df6be)


GUI환경을 위한 Ubuntu-Desktop 설치
우분투에서는 GUI환경을 제공하는 Desktop을 지원하지만, Mac M1 버전을 위한 Desktop은 설치 패키지로 제공하지 않는다.

이로 인해 GUI 환경 구성을 위해서는 사이트에서 다운로드 가능한 Server 버전을 받아 설치하고,

apt를 통해 desktop을 설치할 필요가 있다.

ubuntu-desktop는 apt 명령을 통해 설치 가능하다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/9b57bebe-d9a0-47af-9362-18911db89ecc)


Ubuntu-desktop 설치 마지막 단계에서는 시스템 시작시 실행할 서비스를 지정하도록 안내되며 설치를 완료한다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/af202e0d-a9c3-465d-b5a3-1e152e537014)


Ubuntu-desktop 설치가 완료되면 시스템을 재시작시켜 GUI 환경을 실행시켜 보자.
![image](https://github.com/lsw9395/nhnacademy/assets/62645150/f9d3a285-d9f3-43ad-bd5d-290a6377ea4f)


정상적으로 설치가 완료되면, 아래와 같이 로그인 화면을 볼 수 있다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/c1298336-5fb3-4003-97a2-7a72aaa070f1)


개발에 필요한 패키지 설치
자바 개발 환경 구성을 위해 아래의 패키지를 설치한다.

OpenJDK
Chrome
Visual Studio Code
OpenJDK
JDK 11을 기본으로 사용하기 위해 설치한다. 다른 버전이 필요한 경우, 추가로 설치하고 사용시 선택하면 된다.

sudo apt install openjdk-11-jdk
설치가 완료되면, 아래와 같이 버전을 확인하여 정상적으로 설치됨을 확인한다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/8ab3a23b-f2c1-44f1-83aa-017cb99d2d19)


크로미움(Chromium)
웹서비스 개발 테스트등을 위해 크로미움 브라우저를 설치한다. 일반적으로 많이 사용되고 있는 크롬(Chrome) 브라우저를 설치하는 것이 좋지만, 개발환경이 Apple ARM 코어에 리눅스를 설치하여 해당 패키지는 지원하지 않는다.

크로미움 브라우저는 크로미움 프로젝트에서 유지 관리하는 오픈 소스 웹 브라우저이다. 또한, 현재 많이 사용되고 있는 크롬, 엣지, 오페라, 비발드 등의 다양한 브라우저들의 기반이기도 합니다.

sudo apt install chromium-browser
설치가 완료되면, 응용 프로그램 목록에서 chromium을 찾을 수 있다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/10a00cde-607b-4b5d-817e-a56093dba36e)


아이콘을 클릭하여 실행시키면 브라우저가 실행되는 것을 볼 수 있다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/02209676-e7de-4760-ac96-c4b9aa682212)


Visual Studio Code
GUI환경에서의 통합 개발 환경 프로그램으로 Visual Studio Code(이하 vscode)를 설치해 보자.

vscode 사이트에 접속하여 Linux ARM64 설치 버전이 있는지 확인한다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/22bcbe75-3513-43a7-8ff2-9cac6fb8f10b)


우분투에 설치할 것이므로 .deb 패키지를 내려 받는다.

내려 받은 패키지를 설치한다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/b94ac76a-b37b-4ded-83e2-347d3e1e9486)


설치가 완료되면, 응용프로그램 목록에서 vscode를 확인할 수 있다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/894912a8-7962-44b2-9dd5-c4d02c8c1e2c)


Extension 설치
vscode가 설치되었다면, 자바 개발에 필요한 몇가지 확장 모듈을 설치한다.

vscode를 실행시키고, 왼쪽 아이콘 메뉴에서 Extensions를 클릭한다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/daa1e4a3-3836-470a-948d-73442947ac68)


Extension Pack for Java
Extension pack for java를 찾아 설치하면, 자바 개발에 필요한 6가지 extension을 설치한다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/37000061-6d5e-4d7e-a63a-11fe2dc9f508)


Vim
vi에 익숙한 개발자라면 vim을 설치하여,에디터를 vi 모드로 사용할 수 있다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/bf509859-95d9-41dd-a7cf-66452b79b78e)


프로젝트 생성
기본적인 extension이 설치되면, 프로젝트를 생성해 보자.

메뉴에서 View -> Command Palette를 선택하거나, F1 키를 눌러 명령어 입력창이 나오면 java를 입력한다.

java관련된 명령들이 출력되고, 이중에서 Java: Create Java Project... 를 선택해 새로운 자바 프로젝트를 생성해 보자.

![image-20221106093702951](/Users/nhn/Library/Application Support/typora-user-images/image-20221106093702951.png)

Java: Create Java Project...를 선택하면 프로젝트 생성을 위한 단계들이 진행된다.

빌드 툴 선택

특정한 빌드 도구을 원할 경우, 리스트에 나와 있는 도구들중 해당 도구를 선택하고 그렇지 않으면 No build tools를 선택한다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/be642f88-c98f-44cb-9623-5bc1c33c57ea)

프로젝트를 저장할 위치를 지정한다.

없다면, 오른쪽 위에 있는 디렉토리 추가 아이콘으로 생성한다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/95e73b3e-2bed-44e5-a1c5-3ae6db031229)


마지막으로 프로젝트 이름을 설정하면, 해당 프로젝트를 관리하기 위한 프로젝트 디렉토리가 만들어 진다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/b4c08858-4308-4df1-9401-f2282a196360)


프로젝트 생성 후 아래와 같은 확인 창이 뜨게 되는데, 외부에서 파일을 가져올 경우 자동 실행으로 인한 보안 사고 방지를 확인하는 것으로 자신이 만들었거나 신뢰할 수 있는 프로젝트라면 체크하고 확인한다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/8ed1bdfb-0a88-43fd-85af-d1fba783dc44)


생성된 프로젝트는 아래와 같은 구성을 갖는다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/20ea15b1-a2d7-4cb8-a85a-b0efb6b9d626)


프로젝트 생성과 함께 추가되어 있는 기본 코드를 실행하면, 아래 터미널에서 실행되고 결과가 출력된다.

![image](https://github.com/lsw9395/nhnacademy/assets/62645150/618f43db-984c-4071-9bc5-b598015e0496)

