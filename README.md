# mockserver介绍
> 基于netty实现的mockserver，目前示例只针对于pushworker
## 执行步骤
- git clone到本地之后用maven打成jar包之后运行即可，java -jar mockserver-0.0.1-SNAPSHOT.jar &, 默认监听8000端口
- 关于目前pushworker提供下面3个接口
1. mock pushworker的第三方服务

```
path: /createmsg, method: post
```
2. 用于给mnsclient定时轮询查看status(统计qps)

```
path: /status, method: get
```
2. 归置status为0

```
path: /reset, method: get
```