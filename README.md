# Banking-Server
___ 
[뱅킹 서버 노션](https://gilded-spade-880.notion.site/864adf959dd84bc5813df76a721ed5d7)

## Banking Server API
![Screenshot 2023-03-29 at 7.07.47 PM.png](..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fws%2Fhxrbp_sn2054vm0pmtym0b900000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_Bc2e2j%2FScreenshot%202023-03-29%20at%207.07.47%20PM.png)

## **구현하며 고민한 사항**

1. **동시성 문제 및 원자성 보장 방법에 대한 고민**

   동시성 문제에 대한 고민중에 DB에 직접적으로 락을 거는 방법과 redis를 사용하는 방법 중에 고민이 되었는데 이번 프로젝트에서는 **Pessimistic Lock(비관적 락)** 을 거는 방법을 선택했습니다.

   **선택한 이유**는 뱅킹서버를 생각해보았을때 엄청난 양의 순간적인 트래픽이 발생하는 사이트와는 다르게 동시 다발적으로 요청이 발생하는 경우가 적다고 생각이 들었고. 돈과 관련되어있는 프로젝트라는 특성상 속도 및 성능이 뛰어난 것 보다는 조금 느리더라도 강력하게 동시성을 제어하는 것을 중점적으로 생각해야 한다고 판단하였습니다.

2. 테스트 격리대한 고민
    - 테스트 항목

    <img width="514" alt="Screenshot%202023-03-17%20at%206 59 00%20PM" src="https://user-images.githubusercontent.com/75709176/228500603-c5470e36-b159-4b8f-b8c5-8f8cee88be79.png">

    예전 프로젝트에서 로컬은 h2를 사용하고 배포시 mysql을 사용해 테스트에 사용되는 더미 데이터 스크립트를 사용하지 못하는 경험을 했습니다.
    
    따라서 JPA와 Testcontainers를 사용하면 배포시에 DB환경에 제약이없고 온전히 자바코드에만 집중할 수 있는 환경을 만들어보고자 했습니다.
    
    **장점**
    
    - 인메모리 DB를 사용하지 않고 실제 운영 DB와 동일한 환경에서 테스트할 수 있다.
    - CI/CD 환경에서 테스트할 때 별도의 외부 인스턴스를 띄우지 않고 도커 컨테이너를 손쉽게 띄우고 내릴 수 있다.
    - 별도의 도커 컨테이너 관리를 하지 않아도 테스트마다 독립적으로 사용할 수 있다
    - 특정 DB 뿐만 아니라 Nginx, Elasticsearch, 커스텀한 도커 이미지에도 적용할 수 있다.
    
    **단점**
    
    - 테스트 코드 작성 비용이 증가한다.
    - 테스트 코드 실행 속도가 느리다. (테스트 실행 시 도커를 띄우고 내린다.)
    - 작업 속도 저하된다.

3. 일관된 예외를 설계하고 예외 응답을 내리도록 했습니다.

- 클라이언트에서 예외 처리 로직을 일관되게 구현할 수 있도록 ErrorResponse를 설계했습니다.
- `@Valid`에서 예외 발생 시 fieldError에서 예외가 발생한 필드를 ErrorResponse에 담아 예외 메시지를 클라이언트에 전달하고자 했습니다.
- 어플리케이션에서 발생한 예외는 ApplicationException 정의한 후 ErrorCode Enum을 통해 예외를 관리했습니다.
- `@ControllerAdvice`를 통해 Application 예외와`@Valid`에서 발생하는 예외를 핸들링 했습니다. 


