프로젝트 모듈 구성

1. api - 실행 applicaiton 포함하는 모듈
2. infra - kafka, redis 등 프로젝트 실행에 기본적으로 필요한 외부 모듈들 (kafka, redis 등) 모아놓은 모듈
3. domain - member, memberDto, bike, bikeDto, loginRequest, chat 등 객체 모듈
4. security - 스프링 시큐리티 관련 파일 모듈
5. config - 프로젝트 설정과 관련된 configuration, bean 파일 모듈


### 주요 기능
- 서울시 실시간 따릉이 api를 json 형식으로 가져온 후 객체로 변환
- 가공한 데이터들을 kafka 토픽에 메시지 발행, consumer 에서 메시지를 수신한 후에 redis 에 저장
- 따릉이 대여소 상태를 redis 에 저장, 조회
- 실시간 업데이트를 위해 websocket 구현
- 로그인 할 때 , 세션 유지할 때 토큰을 발급 받고 그 토큰을 통해서 사용자 인증 (스프링 시큐리티 이용)
- 소셜 로그인을 통한 로그인 기능
- 따릉이 예약, 사용, 반환 기능

## 기타
추가 필요한 기능은 추후에 추가 예정

# Real-time-Public-Bicycle-Sharing
전국 공영 자전거 실시간 API 활용 저장소 구축
