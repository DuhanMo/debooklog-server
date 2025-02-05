# 📚 **Debooklog – 나만의 온라인 책장 & 독서 기록 서비스**
🚀 **Debooklog은 개인의 독서 기록을 관리하고 공유할 수 있는 웹 애플리케이션입니다.**
이곳에서 당신만의 책장을 만들고, 책을 검색하고, 독서 상태를 업데이트하고, 좋아하는 책에 ‘좋아요’를 남겨보세요!

## 🌍 **배포 URL**
🔗 [https://debooklog.vercel.app](https://debooklog.vercel.app)

---

## 🎯 **주요 기능**
- ✔ **책장 생성 및 관리** – 나만의 책장 추가 및 수정
- ✔ **도서 검색** – 원하는 책을 검색하고 추가 가능
- ✔ **독서 상태 변경** – 읽는 중 / 독서 완료 변경
- ✔ **좋아요 기능** – 책에 좋아요를 누르고 취소 가능
- ✔ **소셜 로그인 지원** – Google, Kakao 로그인

---

## 🛠 **기술 스택**
- **Frontend:** React, Styled-Components, React Router
- **Backend:** Kotlin, Spring Boot, Hexagonal Architecture
- **Database:** MySQL
- **Authentication:** OAuth 2.0 (Google, Kakao)
- **Deployment:** Vercel (Frontend), AWS Elastic Beanstalk (Backend)

---

## 📖 **API 문서 (선택 사항)**
API 문서는 `Swagger` 또는 `Postman`을 통해 확인 가능합니다.
🔗 [API 문서 링크](https://debooklog.com/swagger-ui/index.html)

---

## 시크릿 파일 생성

1. 아래 명령어를 이용하여 `application-secret.yml` 파일 생성
    ```shell
    cp application-secret.yml.tpl src/main/resources/application-secret.yml
    ```
2. 정의된 시크릿 키를 작성
