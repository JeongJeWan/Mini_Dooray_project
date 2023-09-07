# /Accoount Rest Api

1. 회원가입

* Request

```
POST http://localhost:7070/account/users/signup
Content-Type: application/json

{
"userId": "marco",
"userPassword": "1234",
"userEmail": "marco@nhnacademy.com"
}
```

* Response

```
POST http://localhost:7070/account/users/signup

HTTP/1.1 201 
Content-Length: 0
Date: Wed, 14 Jun 2023 05:12:46 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<Response body is empty>

Response code: 201; Time: 208ms (208 ms); Content length: 0 bytes (0 B)
```

2. 로그인

* Request

```
POST http://localhost:7070/account/users/login
Content-Type: application/json

{
"userId": "marco"
}
```

* Response

```
POST http://localhost:7070/account/users/login

HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 14 Jun 2023 05:13:33 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "id": 1,
  "userId": "marco",
  "userPassword": "1234",
  "userEmail": "marco@nhnacademy.com",
  "status": "가입"
}
Response file saved.
> 2023-06-14T141333.200.json

Response code: 200; Time: 101ms (101 ms); Content length: 96 bytes (96 B)
```

3. 전체 유저 가져오기

* Request

```
GET http://localhost:7070/account/users
```

* Reponse

```
GET http://localhost:7070/account/users

HTTP/1.1 200 
Content-Type: application/account/json
Transfer-Encoding: chunked
Date: Wed, 14 Jun 2023 05:14:32 GMT
Keep-Alive: timeout=60
Connection: keep-alive

[
  {
    "id": 1,
    "userId": "marco",
    "userPassword": "1234",
    "userEmail": "marco@nhnacademy.com",
    "status": "가입"
  },
  {
    "id": 2,
    "userId": "admin",
    "userPassword": "$2a$10$8klfQe2c29Kgpq7ll2axu.rjqYg9Ac1XXNwTyozc3hGLW/yBKfCvq",
    "userEmail": "jewan8821@naver.com",
    "status": "가입"
  }
]
Response file saved.
> 2023-06-14T141432.200.json

Response code: 200; Time: 90ms (90 ms); Content length: 404 bytes (404 B)

```

4. 상태 바꾸기(Leave)

* Request

```
PUT http://localhost:7070/account/users/changeStatusLeave/1
```

* Response

```
PUT http://localhost:7070/account/users/changeStatusLeave/1

HTTP/1.1 200 
Content-Length: 0
Date: Wed, 14 Jun 2023 05:20:02 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<Response body is empty>

Response code: 200; Time: 81ms (81 ms); Content length: 0 bytes (0 B)
```

5. 상태바꾸기(Rest)

* Request

```
PUT http://localhost:7070/account/users/changeStatusRest/1
```

* Response

```
PUT http://localhost:7070/account/users/changeStatusRest/1

HTTP/1.1 200 
Content-Length: 0
Date: Wed, 14 Jun 2023 05:21:08 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<Response body is empty>

Response code: 200; Time: 75ms (75 ms); Content length: 0 bytes (0 B)
```

6. 이메일로 찾기

* Request

```
POST http://localhost:7070/account/users/email
Content-Type: application/json

{
  "userEmail": "marco@nhnacademy.com"
}
```

* Response

```
POST http://localhost:7070/account/users/email

HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 14 Jun 2023 05:21:45 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "id": 1,
  "userId": "marco",
  "userPassword": "1234",
  "userEmail": "marco@nhnacademy.com",
  "status": "휴식"
}
Response file saved.
> 2023-06-14T142145.200.json

Response code: 200; Time: 76ms (76 ms); Content length: 96 bytes (96 B)
```

7. 이메일 없음 오류

* Request
```
POST http://localhost:7070/account/users/email
Content-Type: application/json

{
  "userEmail": "abcde@nhnacademy.com"
}
```

* Response

```
POST http://localhost:7070/account/users/email

HTTP/1.1 400 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 15 Jun 2023 00:20:23 GMT
Connection: close

{
  "message": "해당 이메일과 일치하는 회원이 존재하지 않습니다."
}
Response file saved.
> 2023-06-15T092023.400.json

Response code: 400; Time: 226ms (226 ms); Content length: 41 bytes (41 B)

```

8. Id 중복 오류

* Request

```
POST http://localhost:7070/account/users/signup

HTTP/1.1 400 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 15 Jun 2023 00:22:13 GMT
Connection: close

{
  "message": "marco중복된 사용자입니다."
}
Response file saved.
> 2023-06-15T092213.400.json

Response code: 400; Time: 65ms (65 ms); Content length: 30 bytes (30 B)
```

* Response

```
POST http://localhost:7070/account/users/signup

HTTP/1.1 400 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 15 Jun 2023 00:22:13 GMT
Connection: close

{
  "message": "marco중복된 사용자입니다."
}
Response file saved.
> 2023-06-15T092213.400.json

Response code: 400; Time: 65ms (65 ms); Content length: 30 bytes (30 B)
```

9. 유저 없음 오류

* Request

```
POST http://localhost:7070/account/users/login
Content-Type: application/json

{
  "userId": "abcd"
}
```

* Response

```
POST http://localhost:7070/account/users/login

HTTP/1.1 400 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 15 Jun 2023 00:24:32 GMT
Connection: close

{
  "message": "해당 사용자를 찾을 수 없습니다."
}
Response file saved.
> 2023-06-15T092432.400.json

Response code: 400; Time: 100ms (100 ms); Content length: 52 bytes (52 B)
```