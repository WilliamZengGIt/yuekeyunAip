# FastDFS学习

## （一）架构解析

### 1      FastDFS介绍

#### 1.1    什么是FastDFS

##### FastDFS是用c语言编写的一款开源的分布式文件系统。FastDFS为互联网量身定制，充分考虑了冗余备份、负载均衡、线性扩容等机制，并注重高可用、高性能等指标，使用FastDFS很容易搭建一套高性能的文件服务器集群提供文件上传、下载等服务。 

#### 1.2    FastDFS架构

#####  FastDFS架构包括 Tracker server和Storage server。客户端请求Tracker server进行文件上传、下载，通过Tracker server调度最终由Storage server完成文件上传和下载。

#####          Tracker server作用是负载均衡和调度，通过Tracker server在文件上传时可以根据一些策略找到Storage server提供文件上传服务。可以将tracker称为追踪服务器或调度服务器。

#####          Storage server作用是文件存储，客户端上传的文件最终存储在Storage服务器上，Storage server没有实现自己的文件系统而是利用操作系统的文件系统来管理文件。可以将storage称为存储服务器。

##### FastDFS架构如下图：

![img](https://img-blog.csdn.net/20180627123524821?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pob3VqaWFuX0xpdQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

#### 1.2.1  Tracker 集群

##### FastDFS集群中的Tracker server可以有多台，Tracker server之间是相互平等关系同时提供服务，Tracker server不存在单点故障。客户端请求Tracker server采用轮询方式，如果请求的tracker无法提供服务则换另一个tracker。 

#### 1.2.2  Storage集群

##### Storage集群采用了分组存储方式。storage集群由一个或多个组构成，集群存储总容量为集群中所有组的存储容量之和。一个组由一台或多台存储服务器组成，组内的Storage server之间是平等关系，不同组的Storage server之间不会相互通信，同组内的Storage server之间会相互连接进行文件同步，从而保证同组内每个storage上的文件完全一致的。一个组的存储容量为该组内存储服务器容量最小的那个，由此可见组内存储服务器的软硬件配置最好是一致的。

#####          采用分组存储方式的好处是灵活、可控性较强。比如上传文件时，可以由客户端直接指定上传到的组也可以由tracker进行调度选择。一个分组的存储服务器访问压力较大时，可以在该组增加存储服务器来扩充服务能力（纵向扩容）。当系统容量不足时，可以增加组来扩充存储容量（横向扩容）。

##### 1.2.3  Storage状态收集

#####    Storage server会连接集群中所有的Tracker server，定时向他们报告自己的状态，包括磁盘剩余空间、文件同步状况、文件上传下载次数等统计信息。 

#### 1.2.4  文件上传流程

![img](https://img-blog.csdn.net/201806271632240?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pob3VqaWFuX0xpdQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70) 

简略过程：

 

##### \1. client询问tracker上传到的storage；

##### \2. tracker返回一台可用的storage；

##### **3. client直接和storage通信完成文件上传，storage返回文件ID**

##### 客户端上传文件存储服务器将ID返回给客户端，此文件ID用于以后访问该文件的索引信息。**文件索引信息包括：组名，虚拟磁盘路径，数据两级目录，文件名。**

```
如：group1/M00/00/00/rBPHn1sy_T6AV6zHAADSHRhFPm8873.jpg
```

- ##### 组名：文件上传后所在的storage组名称

- ##### 虚拟磁盘目录：storage配置的虚拟路径，如果配置了store_path0则是Moo,如果配置了store_path1则是M01。

- ##### 文件名：与文件上传时不同。是由存储服务器根据特定信息生成，文件名包含：源存储服务器IP地址、文件创建时间戳、文件大小、随机数和文件拓展名等信息。

#### 1.2.5  文件下载流程

![img](https://img-blog.csdn.net/2018062716471691?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pob3VqaWFuX0xpdQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70) 

##### 简略流程： 

##### \1. client询问tracker可以下载指定文件的storage，参数为文件 ID（组名和文件名）；

##### \2. tracker返回一台可用的storage；

**3. client直接和storage通信完成文件下载。**

##### tracker根据请求的文件路径即文件ID 来快速定义文件。

##### 比如请求下边的文件：

```
如：group1/M00/00/00/rBPHn1sy_T6AV6zHAADSHRhFPm8873.jpg
```

##### 1.通过组名tracker能够很快的定位到客户端需要访问的存储服务器组是group1，并选择合适的存储服务器提供客户端访问。 

##### 2.存储服务器根据“文件存储虚拟磁盘路径”和“数据文件两级目录”可以很快定位到文件所在目录，并根据文件名找到客户端需要访问的文件。

## （二）Linux中的搭建

### 前言：本文采用centos 7系统搭建一个最简单的FastDFS分布式文件系统。

###            搭建结构：一个Tracker和一个Storage,为了方便都搭建在了一台电脑上。

### 1.1  下载

#### tracker和storage使用相同的安装包

#### 下载地址：http://sourceforge.net/projects/FastDFS/或https://github.com/happyfish100/FastDFS（推荐）

#### 本教程下载：FastDFS_v5.05.tar.gz

### 1.2 FastDFS安装环境

####    安装FastDFS需要先将官网下载的源码进行编译，编译依赖gcc环境，如果没有gcc环境，需要安装gcc;

```
yum install gcc-c++ 
```

### 1.3  安装libevent

#### FastDFS依赖libevent库，需要安装：

```
yum -y install libevent
```

### 1.4  安装libfastcommon

#### libfastcommon是FastDFS官方提供的，libfastcommon包含了FastDFS运行所需要的一些基础库。

#### 将libfastcommonV1.0.7.tar.gz拷贝至/usr/local/下

```
cd /usr/local



tar -zxvf libfastcommonV1.0.7.tar.gz



cd libfastcommon-1.0.7



./make.sh



./make.sh install
```

**注意：libfastcommon安装好后会自动将库文件拷贝至/usr/lib64，由于FastDFS程序引用usr/lib目录所以需要将/usr/lib64下的库文件拷贝至/usr/lib下。要拷贝的文件名称为：libfastcommon.so**

### 1.5.1 tracker（storage）编译安装

#### 将FastDFS_v5.05.tar.gz拷贝至/usr/local/下

```
tar -zxvf FastDFS_v5.05.tar.gz



cd FastDFS



./make.sh



./make.sh install
```

#### 安装成功将安装目录下的conf下的文件拷贝到/etc/fdfs/下。

如下：

![img](https://img-blog.csdn.net/20180606210510863?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pob3VqaWFuX0xpdQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

### 1.5.2  配置tracker

安装成功后进入/etc/fdfs目录：

 ![img](https://img-blog.csdn.net/20180606211008968?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pob3VqaWFuX0xpdQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70) 

拷贝一份新的tracker.conf

```
cp tracker.conf.sample tracker.conf

```

修改tracker.conf

```
vim tracker.conf
```

 

修改为自己设置的日志文件存储目录，注意路径必须存在!

### 1.5.3  启动tracker 

```
/usr/bin/fdfs_trackerd  /etc/fdfs/tracker.conf
```

### 1.6.1  安装storage 

若是storage和tracker安装在同一台电脑，则可以直接配置storage;

若不在同一台电脑，则需要继续执行上述1.2~1.5，storage编译安装和tracker编译安装本质相同，里面既包含了storage.conf，又包含了tracker.conf。

### 1.6.2  配置storage

进入/etc/fdfs目录：

![img](https://img-blog.csdn.net/20180606211008968?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pob3VqaWFuX0xpdQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

拷贝一份新的storage.conf配置文件

```
cp storage.conf.sample storage.conf
```

修改storage.conf

```
vim storage.conf



group_name=group1



base_path=/home/yuqing/FastDFS 改为：base_path=/home/FastDFS/storage



store_path0=/home/yuqing/FastDFS改为：store_path0=/home/FastDFS/storage



#如果有多个挂载磁盘则定义多个store_path，如下



#store_path1=.....



#store_path2=......



tracker_server=192.168.101.3:22122   #配置tracker服务器:IP



#如果有多个则配置多个tracker



tracker_server=192.168.101.4:22122
```

 1.6.3  启动storage

```
/usr/bin/fdfs_storaged  /etc/fdfs/storage.conf
```

启动storage后，会在/home/FastDFS/storage下面产生一个data目录和一个logs目录

data目录下会产生两级目录（00~FF）用来存储文件（作用：将文件打散存放）：

第一级目录：

![img](https://img-blog.csdn.net/20180606212844983?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pob3VqaWFuX0xpdQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70) 

在上述每个目录中还会有一层目录：

![img](https://img-blog.csdn.net/20180606213009671?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pob3VqaWFuX0xpdQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

至此，简单的FastDFS就搭建好了，我们来简单的测试一下。

 1.7    上传图片测试----通过fdfs_test程序

FastDFS安装成功可通过/usr/bin/fdfs_test测试上传、下载等操作。

修改/etc/fdfs/client.conf

base_path=/home/FastDFS/client

tracker_server=192.168.101.3:22122(自己的tracker服务器地址)

```
比如将/home下的图片上传到FastDFS中：



/usr/bin/fdfs_test /etc/fdfs/client.conf upload /home/tomcat.png
```

结果如下：

![img](https://img-blog.csdn.net/20180606213655373?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pob3VqaWFuX0xpdQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70) 

来到home/FastDFS/storage/00/00目录查看：

![img](https://img-blog.csdn.net/20180606213825582?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pob3VqaWFuX0xpdQ==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

文件上传成功！说明环境搭建成功！

此时文件可以上传了，但是当我们想要通过浏览器访问时，还需要配置http服务器。

## （三）选择策略

- **FastDFS如何选择tracker和客户端通信?**

> 在trakcer集群中，每个tracker的地位都是平等的，因此client可以随机选择一个tracker进行连接。client会通过轮询的方式选择一个tracker，要是此tracker不能提供服务，则换下一个tracker。

### 

- **FastDFS如何选择一个group？**

> 1. 可以手动指定一个group
> 2. 可以通过所有存储节点组轮询的方式选择一个group
> 3. 可以根据剩余存储空间最多的方式选择一个group

### 

- **FastDFS如何选择一个storage？**

> 1. 该存储节点组group内轮询选择
> 2. 按ip排序
> 3. 按storage存储节点设置的优先级排序，优先存储在级别高的storage

### 

- **FastDFS如何选择一个Storage Path？**

> 1. 多个存储目录间轮询
> 2. 剩余存储空间最多的优先。  

- **FastDFS如何根据文件名称找到文件？**

> 首先通过组名可以知道文件所在的组信息，通过选择策略选择一个storage,之后通过虚拟磁盘目录和storage两级目录知道文件所在的位置，通过文件名称，即可查找到文件。

## （四）如何通过浏览器访问FastDFS中的图片资源？

#### 要想通过浏览器访问FastDFS中的资源，需要安装http服务器，如nginx、apache等,这篇文件中安装的是nginx。 

### [CentOS7安装Nginx](https://www.cnblogs.com/boonya/p/7907999.html) 

## 安装所需环境

Nginx 是 C语言 开发，建议在 Linux 上运行，当然，也可以安装 Windows 版本，本篇则使用 [CentOS](http://www.linuxidc.com/topicnews.aspx?tid=14) 7 作为安装环境。

 

**一. gcc 安装**
安装 nginx 需要先将官网下载的源码进行编译，编译依赖 gcc 环境，如果没有 gcc 环境，则需要安装：

```
yum install gcc-c++
```

**二. PCRE pcre-devel 安装**
PCRE(Perl Compatible Regular Expressions) 是一个Perl库，包括 perl 兼容的正则表达式库。nginx 的 http 模块使用 pcre 来解析正则表达式，所以需要在 linux 上安装 pcre 库，pcre-devel 是使用 pcre 开发的一个二次开发库。nginx也需要此库。命令：

```
yum install -y pcre pcre-devel
```

**三. zlib 安装**
zlib 库提供了很多种压缩和解压缩的方式， nginx 使用 zlib 对 http 包的内容进行 gzip ，所以需要在 Centos 上安装 zlib 库。

```
yum install -y zlib zlib-devel
```

**四. OpenSSL 安装**
OpenSSL 是一个强大的安全套接字层密码库，囊括主要的密码算法、常用的密钥和证书封装管理功能及 SSL 协议，并提供丰富的应用程序供测试或其它目的使用。
nginx 不仅支持 http 协议，还支持 https（即在ssl协议上传输http），所以需要在 Centos 安装 OpenSSL 库。

```
yum install -y openssl openssl-devel
```

## 官网下载

1.直接下载`.tar.gz`安装包，地址：<https://nginx.org/en/download.html>

![img](https://images2015.cnblogs.com/blog/26794/201704/26794-20170415143759345-1159867247.png)

 

2.使用`wget`命令下载（推荐）。确保系统已经安装了wget，如果没有安装，执行 yum install wget 安装。

```
wget -c https://nginx.org/download/nginx-1.12.0.tar.gz
```

![img](https://images2015.cnblogs.com/blog/26794/201704/26794-20170415143847533-1700322865.png)

 

我下载的是1.12.0版本，这个是目前的稳定版。

## 解压

依然是直接命令：

```
tar -zxvf nginx-1.12.0.tar.gz
cd nginx-1.12.0
```

## 配置

其实在 nginx-1.12.0 版本中你就不需要去配置相关东西，默认就可以了。当然，如果你要自己配置目录也是可以的。
1.使用默认配置

```
./configure
```

2.自定义配置（不推荐）

```
./configure \
--prefix=/usr/local/nginx \
--conf-path=/usr/local/nginx/conf/nginx.conf \
--pid-path=/usr/local/nginx/conf/nginx.pid \
--lock-path=/var/lock/nginx.lock \
--error-log-path=/var/log/nginx/error.log \
--http-log-path=/var/log/nginx/access.log \
--with-http_gzip_static_module \
--http-client-body-temp-path=/var/temp/nginx/client \
--http-proxy-temp-path=/var/temp/nginx/proxy \
--http-fastcgi-temp-path=/var/temp/nginx/fastcgi \
--http-uwsgi-temp-path=/var/temp/nginx/uwsgi \
--http-scgi-temp-path=/var/temp/nginx/scgi
```

> 注：将临时文件目录指定为/var/temp/nginx，需要在/var下创建temp及nginx目录

## 编译安装

```
make
make install
```

查找安装路径：

```
whereis nginx
```

## 启动、停止nginx

```
cd /usr/local/nginx/sbin/
./nginx 
./nginx -s stop
./nginx -s quit
./nginx -s reload
```

```
启动时报80端口被占用:
nginx: [emerg] bind() to 0.0.0.0:80 failed (98: Address already in use)
```

```

```

` 解决办法：1、安装net-tool 包：`yum install net-tools

```
 
```

> `./nginx -s quit`:此方式停止步骤是待nginx进程处理任务完毕进行停止。
> `./nginx -s stop`:此方式相当于先查出nginx进程id再使用kill命令强制杀掉进程。

查询nginx进程：

```
ps aux|grep nginx
```

## 重启 nginx

1.先停止再启动（推荐）：
对 nginx 进行重启相当于先停止再启动，即先执行停止命令再执行启动命令。如下：

```
./nginx -s quit
./nginx
```

2.重新加载配置文件：
当 ngin x的配置文件 nginx.conf 修改后，要想让配置生效需要重启 nginx，使用`-s reload`不用先停止 ngin x再启动 nginx 即可将配置信息在 nginx 中生效，如下：
./nginx -s reload

启动成功后，在浏览器可以看到这样的页面：

![nginx-welcome.png](http://www.linuxidc.com/upload/2016_09/160905180451093.png)

## 开机自启动

即在`rc.local`增加启动代码就可以了。

```
vi /etc/rc.local
```

增加一行 `/usr/local/nginx/sbin/nginx`
设置执行权限：

```
chmod 755 rc.local
```

![nginx-rclocal.png](http://www.linuxidc.com/upload/2016_09/160905180451095.png)

到这里，nginx就安装完毕了，启动、停止、重启操作也都完成了，当然，你也可以添加为系统服务，我这里就不在演示了

## 配置nginx 系统路径

1：进入 vim /etc/profile 文件

![img](https://img2018.cnblogs.com/blog/1501071/201811/1501071-20181122105736040-226173388.png)

2：添加配置 如下 

因为我也是第一次配置这个， 然后按照说明 创建添加 了 一个 PATH（PATH 和windows上配置环境变量的理解应该差不多，主要害怕解释不好误导你们）

export 应该就是 导入、启用的作用。

![img](https://img2018.cnblogs.com/blog/1501071/201811/1501071-20181122105940672-275675563.png)

vim /etc/profile
PATH=$PATH:/路径/sbin
export PATH
source /etc/profile
			



## 1 FastDFS 和nginx整合

> 1.1  在tracker上安装nginx

> 在每个tracker上安装nginx，的主要目的是做负载均衡及实现高可用。如果只有一台tracker服务器可以不配置nginx。

> 1.2  在storage上安装nginx

> 1.2.1 FastDFS-nginx-module

> ```
> 将FastDFS-nginx-module_v1.16.tar.gz传至/usr/local/下
> 
> 
> 
> cd /usr/local
> 
> 
> 
> tar -zxvf FastDFS-nginx-module_v1.16.tar.gz
> 
> 
> 
> cd FastDFS-nginx-module/src
> 
> 
> 
> 修改config文件将/usr/local/路径改为/usr/
> ```
>
> 
>
>  

> ```
> 将FastDFS-nginx-module/src下的mod_FastDFS.conf拷贝至/etc/fdfs/下
> ```

> ```
> cp mod_FastDFS.conf /etc/fdfs/
> ```

> ```
> 并修改mod_FastDFS.conf的内容：
> ```

```
vi /etc/fdfs/mod_FastDFS.conf
```

```
base_path=/home/FastDFS  #fastdfs 地址
```

```
tracker_server=本机ip:22122
```

```
#tracker_server=192.168.101.4:22122（多个tracker配置多行）
```

```
url_have_group_name=true            #url中包含group名称
```

```
store_path0=/home/FastDFS/fdfs_storage   #指定文件存储路径 即data文件夹存放处
```

```
将libfdfsclient.so拷贝至/usr/lib下
```

```
cp /usr/lib64/libfdfsclient.so /usr/lib/
创建nginx/client目录
mkdir -p /var/temp/nginx/client
```

> #### 1.2.2 nginx安装

> 添加FastDFS-nginx-module模块
>
> ```
> ./configure \
> 
> --add-module=/usr/local/fastdfs-nginx-module/src 
> make 
> make install
> ```
>
>  

> #### 1.2.3 nginx配置文件

> 新建一个nginx配置文件nginx-fdfs.conf 可在 nginx配置文件中 配置
>
> 1. 添加server:
>
> 2. 
>
>     
>
> 3. 
>
>    server {
>
> 4. 
>
>     
>
> 5. 
>
>    ​        listen       80;
>
> 6. 
>
>     
>
> 7. 
>
>    ​        server_name  192.168.101.3;
>
> 8. 
>
>     
>
> 9. 
>
>    ​        location /group1/M00/{
>
> 10. 
>
>      
>
> 11. 
>
>     ​                \#root/home/FastDFS/fdfs_storage/data;
>
> 12. 
>
>      
>
> 13. 
>
>     ​                ngx_fastdfs_module;
>
> 14. 
>
>      
>
> 15. 
>
>     ​        }
>
> 16. 
>
>      
>
> 17. 
>
>  

说明：

server_name指定本机ip

location /group1/M00/：group1为nginx 服务FastDFS的分组名称，M00是FastDFS自动生成编号，对应store_path0=/home/FastDFS/fdfs_storage，如果FastDFS定义store_path1，这里就是M01



测试。。。























~~~linux

~~~

