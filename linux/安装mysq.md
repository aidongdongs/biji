## 安装mysql

![](img/Snipaste_2023-04-04_11-41-23.png)

![](img/Snipaste_2023-04-04_11-41-47.png)



```
rpm -e --nodeps 
```



![](img/Snipaste_2023-04-05_19-07-05.png)

```
cd /usr/local
mkdir mysql

tar -zxvf  mysql-5.7.25-1.el7.x86_64.rpm-bundle.tar.gz  -C /usr/local/mysql
```

![](img/Snipaste_2023-04-05_19-07-27.png)



```
 rpm -ivh mysql-community-common-5.7.25-1.el7.x86_64.rpm
rpm -ivh mysql-community-libs-5.7.25-1.el7.x86_64.rpm
rpm -ivh mysql-community-devel-5.7.25-1.el7.x86_64.rpm
rpm -ivh mysql-community-libs-compat-5.7.25-1.el7.x86_64.rpm
rpm -ivh mysql-community-client-5.7.25-1.el7.x86_64.rpm
```



![](img/Snipaste_2023-04-05_19-11-00.png)



![](img/Snipaste_2023-04-24_12-18-34.png)

```
 cat /var/log/mysqld.log | grep password

```

![](img/Snipaste_2023-04-05_19-12-21.png)

```
set global validate_password_length =4; 
set global validate_password_policy=LOW;
set password =password('root');
grant all on *.*to'root'@'%'identified by 'root'
flush privileges;
```

