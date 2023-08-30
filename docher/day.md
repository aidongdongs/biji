### docker 命令

### **docker 服务相关命令**

```dockerfile

#关闭自动唤醒
1. 停用Docker自动唤醒机制：systemctl stop docker.socket
2. 查看Docker自动唤醒机制是否关闭：systemctl status docker，发现已经关闭。
# 启动docker
systemctl start docker
#停止 docker
systemctl stop docker
#重启 docker
systemctl restart docker
#查看docker 服务状态
systemctl status docker
#开机启动docker 服务
systemctl enable docker
```



### **docker镜像命令**

```dockerfile
#查看镜像
docker images
#搜索镜像
docker search redis
#拉取镜像
docker pull redis:3.2
#删除镜像
docker rmi 7614ae9453d1容器镜像id
#删除所有镜像
docker rmi `docker imgages -q`

#名称		  版本	   镜像id		创建时间  镜像大小
REPOSITORY   TAG       IMAGE ID   CREATED   SIZE

```





### **容器命令**

```dockerfile

#创建容器
 docker run -it --name=c1 centos:7 /bin/bash
	# -i 让容器一直运行
	# -t 分配一个终端
	# --name=c1 指定容器名字
	#  /bin/bash 创建容器并进入容器
#此命令创建的容器，使用exit退出该容器就关闭了了


docker run -id  --name=c2 centos:7 
	#使用该命令创建容器，不会直接进入容，容器会在后台运行，需要手动进入容器

#参数说明
* -i： 保持容器运行，通常与-t同时使用，加入it这个参数后，容器创建后自动进入容器中，推出容器后，容器自动关闭
* -t： 为容器分配一个为输入与终端，通常与-i一起使用
* -d：以为守护（后台）模式运行容器，创建一个容器在后台运行，需要使用docker exec 进入容器，推出后，容器不会关闭
* -it： 创建的容器一般称为 交换式容器，
* -id： 创建的容器一般称为守护式容器
* --name ： 为创建的容器明明


#退出容器
exit

#查看容器
dpcler ps  #查看容器
docker ps -a #查看全部容器

#容器id		镜像	 进入容器初始化命令  创建时间
CONTAINER ID   IMAGE      COMMAND       CREATED         STATUS       PORTS     NAMES


#进入容器

docker exec -it c2 /bin/bash

#启动容器
docker start c2
#启动所有容器
docker start $(docker ps -a -q)
#停止容器
docker stop c2 
# 停止所有容器
docker stop $(docker ps -a -q)

#删除容器
docker rm  容器id 容器名称
#删除全部容器
	docker rm `docker ps -aq`

#查看容器信息

docker inspect 容器名称

Vim中格式化JSON的命令 —— %!python -m json.tool


docker images
REPOSITORY TAG IMAGE ID CREATED SIZE
centos latest 9f38484d220f 7 weeks ago 202MB
```



### 容器的数据卷

**数据卷的概念**

```powershell
#思考
 	Docker 容器删除后，在容器中产生的数据还存在吗
 	Docker 容器和外部机器可以直接交换文件吗
 	容器之间想进行数据交换
 
#数据卷
	数据卷是宿主机中的一个目录或文件
	当容器目录和数据卷目录绑定后，对方的修改会立即同步
	一个数据卷可以被多个容器同时挂在
```



**配置数据卷**

- 启动容器时，使用-v参数 设置数据卷

  ```dockerfile
  docker run .. -v 宿主机目录（文件）：容器内目录（文件）...
  ```

- 注意事项

  ```
  1：目录必须是绝对路径
  2：如果目录不存在，会自动创建
  3：可以挂载多个数据卷
  ```

  

  **配置数据卷**

  ```powershell
  #挂载一个卷
  docker run -it --name=c1 -v /root/data:/root/data centos:7
  
  #挂宅多个卷
  docker run -it --name=c2  -v /root/data2:/root/data2 -v /root/data3:/root/data        
  
  #多个容器挂载一个数据卷
  docker run -it --name c3 -v /root/data:/root/data centos:7
  docker run -it --name c4 -v /root/data:/root/data centos:7
  ```

**数据卷容器**

多容器进行数据交换

- ​	多个容器挂载同一个数据卷
- 数据卷容器

**配置数据卷容器**

```dockerfile
#创建启动c3 数据卷容器，使用-v参数设置数据卷
docker run -it --name=c3 -v/volume:/volume centos:7

#创建c1 关联c3 数据卷
docker run -it --name=c1 --volumes-form c3 centos:7

#创建c2 关联c3 数据卷
docker run -it --name=c2 --volumes-form c3 centos:7 


```

**总结**

1. 数据卷的概念
   - 宿主机的一个目录或文件
2. 数据卷的作用
   -  容器数据的持久化
   - 客户端和容器数据交换
   - 容器间数据交换
3. 数据卷容器
   - 创建一个容器，挂载一个目录，让其容器继承自该容器 (volume-from)
   - 通过简单方式实现数据卷配置

### 应用部署

```powershell
# 在Docker容器中部署mysql ，并通过外部mysql客户端操作MySQLServer
1 搜索MySQL镜像
2 拉取mysql镜像
3 创建容器
4 操作容器中的mysql
```

```powershell
容器内的网络服务和外部机器不能直接通信
外部机器和宿主机可以直接通信
宿主机和容器可以直接通信
当容器中的网络服务需要被外部机器访问时，可以将容器中提过服务的端口映射到宿主机的端口上，外部机器访问宿主机的该端口，从而间接访问容器的服务

```

**部署实际操作在 资料中**





### DockerFile

```powershell
# Docker 
* Docker 镜像的本质是什么
* Docker 中一个centos 镜像为什么只要200MB 而一个centos操作系统的iso文件要几个G
* Docker 中一个tomcat镜像为什么500MB，而一个tomcat安装包只有70多MB
```

**操作系统的组成部分**

```powershell
# 进程调度子系统
# 进行通信子系统
# 内存管理子系统
# 设置管理子系统
  文件管理子系统
# 网络通信子系统
# 作业控制子雄他
```
