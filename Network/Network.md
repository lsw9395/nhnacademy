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
