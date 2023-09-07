# Milestone Rest Api


1. 마일스톤 생성

* Request

```
POST http://localhost:6060/task/milestones
Content-Type: application/json

{
"projectId": 1,
"name": "Milestone"
}
```

* Response

```
POST http://localhost:6060/task/milestones

HTTP/1.1 201 
Content-Length: 0
Date: Wed, 14 Jun 2023 06:15:05 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<Response body is empty>

Response code: 201; Time: 83ms (83 ms); Content length: 0 bytes (0 B)
```


2. 프로젝트에 대한 마일스톤 조회

* Request

```
GET http://localhost:6060/task/milestones/project/1
```

* Response

```
GET http://localhost:6060/task/milestones/project/1

HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 14 Jun 2023 06:15:57 GMT
Keep-Alive: timeout=60
Connection: keep-alive

[
  {
    "id": 1,
    "projectId": 1,
    "name": "Milestone",
    "createdAt": "2023-06-14T14:32:29"
  },
  {
    "id": 29,
    "projectId": 1,
    "name": "Milestone",
    "createdAt": "2023-06-14T15:15:05"
  }
]
Response file saved.
> 2023-06-14T151557.200.json

Response code: 200; Time: 116ms (116 ms); Content length: 154 bytes (154 B)
```

3. 해당 마일스톤에 대한 조회

* Request

```
GET http://localhost:6060/task/milestones/1
```

* Response

```
GET http://localhost:6060/task/milestones/1

HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 14 Jun 2023 06:17:08 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "id": 1,
  "projectId": 1,
  "name": "Milestone",
  "createdAt": "2023-06-14T14:32:29"
}
Response file saved.
> 2023-06-14T151708.200.json

Response code: 200; Time: 82ms (82 ms); Content length: 75 bytes (75 B)
```

4. 마일스톤 수정  

* Request

```
PUT http://localhost:6060/task/milestones
Content-Type: application/json

{
"id": 1,
"name": "Good",
"createdAt": "2023-06-09T00:00:00"
}
```

* Response

```
PUT http://localhost:6060/task/milestones

HTTP/1.1 200 
Content-Length: 0
Date: Wed, 14 Jun 2023 06:18:42 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<Response body is empty>

Response code: 200; Time: 79ms (79 ms); Content length: 0 bytes (0 B)
```

5. 마일스톤 삭제

* Request

```
DELETE http://localhost:6060/task/milestones/1
```

* Response
```
DELETE http://localhost:6060/task/milestones/1

HTTP/1.1 204 
Date: Wed, 14 Jun 2023 07:14:33 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<Response body is empty>

Response code: 204; Time: 78ms (78 ms); Content length: 0 bytes (0 B)
```

6. 마일스톤 조회 오류

* Request
```
GET http://localhost:6060/task/milestones/12
```

* Response

```
GET http://localhost:6060/task/milestones/12

HTTP/1.1 500 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 14 Jun 2023 07:30:39 GMT
Connection: close

{
  "timestamp": "2023-06-14T07:30:39.380+00:00",
  "status": 500,
  "error": "Internal Server Error",
  "trace": "com.nhnacademy.task_api.exception.MilestoneNotFoundException: 마일스톤이 존재하지 않습니다.\n\tat java.base/java.util.Optional.orElseThrow(Optional.java:408)\n\tat com.nhnacademy.task_api.service.impl.MilestoneServiceImpl.findMilestone(MilestoneServiceImpl.java:49)\n\tat com.nhnacademy.task_api.service.impl.MilestoneServiceImpl$$FastClassBySpringCGLIB$$aca63695.invoke(<generated>)\n\tat org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\n\tat org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:793)\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\n\tat org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\n\tat org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:123)\n\tat org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:388)\n\tat org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119)\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\n\tat org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\n\tat org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:708)\n\tat com.nhnacademy.task_api.service.impl.MilestoneServiceImpl$$EnhancerBySpringCGLIB$$27263fc8.findMilestone(<generated>)\n\tat com.nhnacademy.task_api.controller.MilestoneController.findMilestone(MilestoneController.java:39)\n\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.base/java.lang.reflect.Method.invoke(Method.java:566)\n\tat org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)\n\tat org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:150)\n\tat org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:117)\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)\n\tat org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\n\tat org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1072)\n\tat org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:965)\n\tat org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\n\tat org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898)\n\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:529)\n\tat org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\n\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:623)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:209)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)\n\tat org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)\n\tat org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)\n\tat org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)\n\tat org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)\n\tat org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)\n\tat org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)\n\tat org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:481)\n\tat org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:130)\n\tat org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)\n\tat org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\n\tat org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\n\tat org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:390)\n\tat org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)\n\tat org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:926)\n\tat org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1791)\n\tat org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52)\n\tat org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)\n\tat org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)\n\tat org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\n\tat java.base/java.lang.Thread.run(Thread.java:829)\n",
  "message": "마일스톤이 존재하지 않습니다.",
  "path": "/milestones/12"
}
Response file saved.
> 2023-06-14T163039.500.json

Response code: 500; Time: 87ms (87 ms); Content length: 6701 bytes (6.7 kB)
```