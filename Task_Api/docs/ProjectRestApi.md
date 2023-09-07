# Project Rest Api

1. 프로젝트 생성

* Request

```
POST http://localhost:6060/task/projects
Content-Type: application/json

{
    "projectAdmin": "marco",
    "name": "Project",
    "content": "Good Marco"
}
```

* Response

```
POST http://localhost:6060/task/projects

HTTP/1.1 201 
Content-Length: 0
Date: Wed, 14 Jun 2023 05:42:40 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<Response body is empty>

Response code: 201; Time: 182ms (182 ms); Content length: 0 bytes (0 B)
```

2. 해당 프로젝트 조회

* Request
```
GET  http://localhost:6060/task/projects/1
```     

* Response

```
GET http://localhost:6060/task/projects/1

HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 14 Jun 2023 05:43:05 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
  "id": 1,
  "name": "Project",
  "status": "활성",
  "projectAdmin": "marco",
  "startDate": "2023-06-14 14:32:24"
}
Response file saved.
> 2023-06-14T144305.200.json

Response code: 200; Time: 97ms (97 ms); Content length: 96 bytes (96 B)
```


3. 해당 프로젝트에 멤버 조회

* Request

```
GET http://localhost:6060/task/projects/members/marco
```

* Response

```
GET http://localhost:6060/task/projects/members/marco

HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 14 Jun 2023 05:45:21 GMT
Keep-Alive: timeout=60
Connection: keep-alive

[
  {
    "id": 1,
    "name": "Project",
    "status": "활성",
    "projectAdmin": "marco",
    "startDate": "2023-06-14 14:32:24"
  },
  {
    "id": 23,
    "name": "Project",
    "status": "활성",
    "projectAdmin": "marco",
    "startDate": "2023-06-14 14:42:40"
  }
]
Response file saved.
> 2023-06-14T144521.200.json

Response code: 200; Time: 139ms (139 ms); Content length: 196 bytes (196 B)

```
4. 프로젝트 상태 종료 처리

* Request

```
PUT http://localhost:6060/task/projects/1/terminate
```

* Response

```
PUT http://localhost:6060/task/projects/1/terminate

HTTP/1.1 200 
Content-Length: 0
Date: Wed, 14 Jun 2023 05:45:53 GMT
Keep-Alive: timeout=60
Connection: keep-alive

<Response body is empty>

Response code: 200; Time: 93ms (93 ms); Content length: 0 bytes (0 B)
```

5. 프로젝트 조회 오류

* Request

```
GET http://localhost:6060/task/projects/12
```

* Response

```
GET http://localhost:6060/task/projects/12

HTTP/1.1 500 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Wed, 14 Jun 2023 07:27:22 GMT
Connection: close

{
  "timestamp": "2023-06-14T07:27:22.952+00:00",
  "status": 500,
  "error": "Internal Server Error",
  "trace": "com.nhnacademy.task_api.exception.ProjectNotFoundException: 해당 프로젝트가 존재하지 않습니다.\n\tat java.base/java.util.Optional.orElseThrow(Optional.java:408)\n\tat com.nhnacademy.task_api.service.impl.ProjectServiceImpl.findProject(ProjectServiceImpl.java:46)\n\tat com.nhnacademy.task_api.service.impl.ProjectServiceImpl$$FastClassBySpringCGLIB$$35c0bd4c.invoke(<generated>)\n\tat org.springframework.cglib.proxy.MethodProxy.invoke(MethodProxy.java:218)\n\tat org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.invokeJoinpoint(CglibAopProxy.java:793)\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163)\n\tat org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\n\tat org.springframework.transaction.interceptor.TransactionInterceptor$1.proceedWithInvocation(TransactionInterceptor.java:123)\n\tat org.springframework.transaction.interceptor.TransactionAspectSupport.invokeWithinTransaction(TransactionAspectSupport.java:388)\n\tat org.springframework.transaction.interceptor.TransactionInterceptor.invoke(TransactionInterceptor.java:119)\n\tat org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:186)\n\tat org.springframework.aop.framework.CglibAopProxy$CglibMethodInvocation.proceed(CglibAopProxy.java:763)\n\tat org.springframework.aop.framework.CglibAopProxy$DynamicAdvisedInterceptor.intercept(CglibAopProxy.java:708)\n\tat com.nhnacademy.task_api.service.impl.ProjectServiceImpl$$EnhancerBySpringCGLIB$$b0683e4d.findProject(<generated>)\n\tat com.nhnacademy.task_api.controller.ProjectController.findProject(ProjectController.java:40)\n\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n\tat java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)\n\tat java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n\tat java.base/java.lang.reflect.Method.invoke(Method.java:566)\n\tat org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:205)\n\tat org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:150)\n\tat org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:117)\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:895)\n\tat org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:808)\n\tat org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87)\n\tat org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1072)\n\tat org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:965)\n\tat org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006)\n\tat org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898)\n\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:529)\n\tat org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883)\n\tat javax.servlet.http.HttpServlet.service(HttpServlet.java:623)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:209)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)\n\tat org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)\n\tat org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)\n\tat org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)\n\tat org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\n\tat org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:117)\n\tat org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)\n\tat org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)\n\tat org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)\n\tat org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)\n\tat org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:481)\n\tat org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:130)\n\tat org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)\n\tat org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\n\tat org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)\n\tat org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:390)\n\tat org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)\n\tat org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:926)\n\tat org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1791)\n\tat org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52)\n\tat org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)\n\tat org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)\n\tat org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)\n\tat java.base/java.lang.Thread.run(Thread.java:829)\n",
  "message": "해당 프로젝트가 존재하지 않습니다.",
  "path": "/projects/12"
}
Response file saved.
> 2023-06-14T162722.500.json

Response code: 500; Time: 132ms (132 ms); Content length: 6685 bytes (6.68 kB)
```