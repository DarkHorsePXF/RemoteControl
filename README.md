
# English Introduction:
## There are two packages in the project:
    * RemoteControl: Code for Android Application.
    * Server: Code for PC Server,which you can run after packeging it as a jar file.

## Guide:
    1.Run the server;
    2.Insert the ip on your app;
    3.Click "shutdown" on your app.
    
    Note:The jar file locates at Server/out/artifacts/Server_jar/

***

# 中文介绍：通过Android手机远程控制电脑

## 这里有两个文件夹：
    * RemoteControl：Android客户端代码
    * Server：服务器，可以把Server打包成jar在PC上运行

## 使用详情：
    1.运行Server后，即可弹窗服务器的IP地址；（jar文件在Server/out/artifacts/Server_jar/）
    2.然后手机输入IP地址，按“远程关机”即可让电脑关机；
    ps:Android通过SQLite保存历史输入正确的ip地址，通过Spinner显示。
    
    
