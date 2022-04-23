# jdbc
목표 - jdbc를 짤때 반복적인 코드 줄이기

# class별 기능
## JDBCUtil
- Connection생성
- Connection, Statement, ResultSet 자원 반납

## Query
- 생성자: 사용자로부터 sql문 받아오기
- preparedStatement 생성

## QuerySet
- 생성자: Connection, PreparedStatement
- String, Int별로 parameter value 저장
- ResultSet, PreparedStatement 반환
- 사용자원 반납

## SessionConst
URL, USERNAME, PASSWORD 저장
