最近MySQL发布了新版本，MySQL8.0,基于8.0做了诸多得优化，我们想在自己的业务中使用这个版本，但是为了安全起见，需要做相关的测试工作。同时为了保证其在相同的硬件工况下，所以我们选择在同一个机器上部署5.7和8.0两个不同的版本。因为是两个不同的版本，所以安装的时候需要有较多注意的地方，下面我就针对这样的一种背景对“同一机器安装不同版本的安装步骤进行一个记录和分享”。
第一步，使用默认方式（普通安装） 
先选择使用rpm安装8.0版本，具体选择哪个版本无所谓，都行，
1. 首先去mysql网站上下载mysql社区版的完全包，分清32位还是64位。
  Red Hat Enterprise Linux 7 / Oracle Linux 7 (x86, 64-bit), RPM Bundle
  wget https://dev.mysql.com/get/Downloads/MySQL-8.0/mysql-8.0.13-1.el7.x86_64.rpm-bundle.tar
2. 安装的准备工作
安装时候会提示与已经安装的RPM包有冲突，所以我们先卸载一些RPM包。
我们要卸载的是包含有mariadb关键字的RPM包，执行命令：rpm -qa|grep mariadb来查看一下我们要卸载哪些软件。
rpm -e 卸载，有可能因为依赖卸载不掉。可以使用这个rpm -e --nodeps 忽略依赖卸载，也可以解决依赖。   
3. 卸载完了，我们就开始安装吧
首先解开包
tar -xf mysql-8.0.13-1.el7.x86_64.rpm-bundle.tar
安装前，先讲一下，
虽然官方文档说安装mysql-community-client-8.0.13-1.el7.x86_64.rpm和mysql-community-server-8.0.13-1.el7.x86_64.rpm就可以获得标准功能的MySQL。
但是由于RPM包的依赖关系,实际上我们需要安装4个包，并且是按照顺序的。
rpm -Uvh --force mysql-community-common-8.0.13-1.el7.x86_64.rpm
rpm -Uvh --force mysql-community-libs-8.0.13-1.el7.x86_64.rpm
rpm -Uvh --force mysql-community-client-8.0.13-1.el7.x86_64.rpm
rpm -Uvh --force mysql-community-server-8.0.13-1.el7.x86_64.rpm    
4.
这样我们就把MySQL安装好了，当然最重点的地方也就来了，那就是初始化。
初始化之前一定要配置你的my.cnf文件.默认路径是/etc/my.cnf
生成密码的初始化：mysqld --initialize --user=mysql
不生成密码的初始化：mysqld --initialize-insecure --user=mysql
你也可以指定自己的目录：
mysqld --initialize --user=mysql --basedir=/home/mysql --datadir=/home/mysql/data

如果你不指定目录,
mysql的basedir是/var/lib
mysql的datadir是/var/lib/mysql
mysql的cnf文件在/etc/my.cnf

在Unix系列系统中，有一点是很重要的，那就是确保数据库目录与文件的所有者为mysql登录账户，
以便在你运行mysqld服务的时候，mysql服务可以对这些目录和文件进行读取与写入操作
如果你是以root身份运行mysqld服务，执行初始化命令时一定要带有--user=mysql
当你是以mysql的账户登录并执行程序的情况下，你可以将--user=mysql选项从命令中去掉。

5.
mysql的命令在/usr/bin/ ,可以使用ll /usr/bin/ |grep mysql查看
mysql的文件在/var/lib,可以使用ll /var/lib |grep mysql查看
初始化的时候如果报错。[ERROR] --initialize specified but the data directory exists. Aborting.
遇到这种情况，就将数据目录删除后，重新再初始化一次.

6.
mysql的初始密码在 /var/log/mysqld.log
在这句话后面的就是密码[Note] A temporary password is generated for root@localhost:

7.
启动服务 systemctl start mysqld
查看服务 systemctl status mysqld

8.
我们来对上一步作个补充，因为你也有可能会遇到。如果你在执行命令：
systemctl  start  mysqld时，出现了这样的提示：
Job for mysqld.service failed. See 'systemctl status mysqld.service' and 'journalctl -xn' for details.
我们先查看一下/var/lib/mysql-files目录是否存在，执行命令：ls  -ld  /var/lib/mysql-files。
如果不显示任何信息就表示不存在，执行命令：mkdir  /var/lib/mysql-files，创建/var/lib/mysql-files目录。
这时，我们试着执行命令：systemctl  start  mysqld。
如果还出现图一样的提示，我们执行另一条命令：chown -R mysql:mysql  /var/lib/mysql
将/var/lib/mysql目录及其下所有文件和子目录的所有者和所属组都改为mysql。我们再执行一下：systemctl  start  mysqld应该就可以启动mysqld服务了。

9.
连接数据库
mysql -uroot -p;
修改密码.
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'newpassword';
设置开放远程登陆
USE `mysql`;
SELECT `host` FROM `user` WHERE `user` = "root";
UPDATE `user` SET HOST = '%' WHERE `user` = "root";
FLUSH PRIVILEGES;
grant all privileges on *.* to 'root'@'%';
GRANT ALL ON *.* TO 'root'@'%';
FLUSH PRIVILEGES;
SELECT `host` FROM `user` WHERE `user` = "root";

如果有主从库的话还要让主从库进行同步.看5.6的安装指南即可

10.
mysql8.0默认字符集是utf8mb4

这样，我们的MySQL8.0就安装好了。

第二步 安装第二个版本
我选用了MySQL5.7的版本
第二个版本的MySQL不能再使用rpm的方式安装了，需要换一种方式，这里用源码的方式安装（使用tar.gz安装包））

 1. 准备工作：下载tar.gz，链接:https://dev.mysql.com/downloads/mysql/5.7.html#downloads，选择Linux通用包
 2. 解压缩下载的xxxx.tar.gz文件，并重命名，注意自己存放的文件路径名称。
 3. 使用tar -zxvf xxx.tar.gz来解压文件，根据自己的文件管理方式，对解压后的文件进行重命名，方便后续的配置管理工作
 4. 创建数据库配置文件，在你解压的安装包下，和bin同级目录下创建一个文件夹etc,然后创建配置文件，一般名称为my.cnf 。其实在根目录  /etc/my.cnf 有这个文件，所以只需要复制就行了，因为在安装8.0的时候已经产生了一个，所以复制过来就行。

```
#在目录etc目录下，执行命令
cp /etc/my.cnf ./
```
如图和我的路径
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190801150132124.png)
 5. 编辑刚复制的my.cnf文件，由于我的服务器没有vim命令，所以我就使用vi命令了 vi etc/my.cnf 。编辑如下内容
 ```
[root@localhost etc]# cat my.cnf 
[mysqld]
basedir= /home/soft/mysql5.7
datadir = /home/data/mysql5.7/data
port = 3308
socket = /home/soft/mysql5.7/mysql.sock
 
[client]
socket = /home/soft/mysql5.7/mysql.sock
 
#不区分大小写
lower_case_table_names = 
```

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190801150308589.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0xlZWdvb1dhbmc=,size_16,color_FFFFFF,t_70)
 6. 安装依赖包，因为是源码安装，需要有安装依赖包
 
```
yum -y install make gcc-c++ cmake bison-devel ncurses ncurses-devel libaio-devel
```

 7. 指定配置文件初始化
 ```
./mysqld_safe --defaults-file=/home/soft/mysql5.7/etc/my.cnf  --user=root &
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190801151305707.png)
注意保存初始化密码
 8. 接着初始化数据库
 

```
./bin/mysqld_safe --defaults-file=/home/soft/mysql5.7/etc/my.cnf  --user=root &
```
上面的命令注意结尾符号& ，接着执行mysql.server start，如果出现如下的问题，那就修改mysql.server文件
将mysql.server的内容修改：
basedir=你的路径
datadir=你的路径,改成如下图所示
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190801151911408.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0xlZWdvb1dhbmc=,size_16,color_FFFFFF,t_70)
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190801151928588.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0xlZWdvb1dhbmc=,size_16,color_FFFFFF,t_70)
 9. 启动
 修改好之后再执行 ./support-files/mysql.server start ，如果正确，该数据库实例将正常启动了，可以使用命令查看: netstat -tlunp
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20190801152207339.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0xlZWdvb1dhbmc=,size_16,color_FFFFFF,t_70)
 10. 
 11. 
 12.
最后一步，登录修改账号配置
 #./bin/mysql --socket=../mysql.sock --port=3308 -u root -p
使用初始化的时候得到的密码 xxxxx(前面初始化时候日志里的).;

进入后直接修改密码，初始化密码修改后才能操作。
  mysql>alter user 'root'@'localhost' identified by '123456';
  mysql>use mysql;
  msyql>update user set user.Host='%' where user.User='root';
  mysql>flush privileges;
  mysql>quit

找个客户端远程链接测试一下，是ok得

![在这里插入图片描述](https://img-blog.csdnimg.cn/20190801153410410.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L0xlZWdvb1dhbmc=,size_16,color_FFFFFF,t_70)

至此，两个数据可以就都可以同时和谐共处了


(参考)[https://blog.csdn.net/u010898329/article/details/83064373]
(资料)[https://blog.csdn.net/itjin45/article/details/40303497]

