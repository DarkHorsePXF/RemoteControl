# RemoteControl
通过Android手机远程控制电脑

这里有两个文件夹：
RemoteControl：Android客户端代码
Server：服务器，可以把Server打包成jar在PC上运行

使用详情：
    运行Server后，即可弹窗服务器的IP地址；
    然后手机输入IP地址，按“远程关机”即可让电脑关机；
    Android通过SQLite保存历史输入正确的ip地址，通过Spinner显示。
