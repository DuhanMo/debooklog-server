# Project setting

## 시크릿 파일 생성

1. 아래 명령어를 이용하여 `application-secret.yml` 파일 생성
    ```shell
    cp application-secret.yml.tpl src/main/resources/application-secret.yml
    ```
2. 정의된 시크릿 키를 작성


# API

- 책장 목록 조회
- 책장 이름 변경
- 책 검색 (Daum Book Search API 사용)
- 책 등록
- 책 삭제
- 대표 책 지정
- 많이 읽은 책 조회