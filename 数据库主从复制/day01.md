## 数据库库的主从复制

### 安装数据库1

![](img/Snipaste_2023-04-06_08-28-03.png)

```powershell
rpm  -qa #查询系统当前安装的全部如见
rpm -qa | grep mysql #查询当前系统安装软件名称带mysql的软件
rpm -qa | grep mariadb #查询当前系统安装软件名称带mariadb的软件
```

### 安装数据库2

![](img/Snipaste_2023-04-06_08-31-37.png)

```powershell
rpm -e --nodeps 
```

### 安装数据库3

![](img/Snipaste_2023-04-06_08-32-19.png)

### 安装数据库库4

![](img/Snipaste_2023-04-06_08-32-51.png)

```powershell
！！！！！在安装最后一个rpm的时候使用这个命令
rpm -ivh mysql-community-server-5.7.25-1.el7.x86_64.rpm --force --nodeps
```

### 安装数据库5

![](img/Snipaste_2023-04-06_08-34-42.png)

### 安装数据库6 登录

![](img/Snipaste_2023-04-06_08-35-15.png)

```powershell
cat /var/log/mysqld.log | grep password
```

### 安装数据库7

![](img/Snipaste_2023-04-06_08-36-15.png)

### 安装数据库8

![](img/Snipaste_2023-04-06_08-36-55.png)

### 



### 主从复制1 ：主库配置

![](img/Snipaste_2023-04-06_08-37-40.png)

```
log-bin=mysql-bin #[必须]启用二进制日志
server-id=100; #[必须]服务器唯一id
```



### 主从复制2：主库配置

![](img/Snipaste_2023-04-06_08-38-12.png)

```
stystemctl restart mysqld
```



### 主从复制3：主库配置

![](img/Snipaste_2023-04-06_08-38-35.png)

```mysql
GRANT REPLICATION SLAVE ON *.* to 'aidong'@'%' identified by 'aidong1010';
```



### 主从复制4：主库配置

![](img/Snipaste_2023-04-06_08-40-16.png)



### 主从复制5：从库配置

![](img/Snipaste_2023-04-06_08-41-16.png)



### 主从复制6：从库配置

![](img/Snipaste_2023-04-06_08-41-31.png)



### 主从复制7：从库配置

![](img/Snipaste_2023-04-06_08-42-02.png)

### 主从复制8：从库配置

![](img/Snipaste_2023-04-06_08-42-41.png)



### 注意事项

**如果是自己虚拟机复制的两个服务器，数据库的uuid会一致，需要进行修改，否则无法主从复制**

![](img/Snipaste_2023-04-06_08-45-41.png)

![](img/Snipaste_2023-04-06_08-46-02.png)



```powershell
show variables like 'server_uuid'; #查看数据库的uuid
select uuid(); #生成一个新的uuid  用于修改
```

