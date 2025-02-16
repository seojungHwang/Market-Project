# 황서정

### 사용기술
* Java 17
* SpringBoot 3.4.2
* MariaDB 10.11.2
* JPA

### 구현기능

* 회원가입 시 장바구니가 생성된다.
* 장바구니에 상품을 조회, 추가, 수정, 삭제를 할 수 있다.
* 상품을 조회, 등록, 수정을 할 수 있다.
* 장바구니에 담긴 상품을 주문 할 수 있다.
* 상품 재고가 0 이거나, 장바구니에 담으려는 수량보다 재고가 적을 시 재고 에러 응답을 보낸다. 
* 주문 시 주문한 상품의 수량만큼 상품의 재고가 차감된다.
* 주문 시 결제가 요청된다. (모의 API URL : https://allra.free.beeceptor.com/api/v1/payment)
* 주문이 완료되면 장바구니가 비워진다.
* 주문한 내역을 조회 할 수 있다.

* API 명세서(URL: http://localhost:8080/swagger-ui/index.html#)

### ERD

![Image](https://github.com/user-attachments/assets/ff929644-bb51-461b-ab63-ee2c2f3db1da)

