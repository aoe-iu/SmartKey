# SmartKey
“平x安x门x禁”、“宝x安x视x频x门x禁”替代品，需要自行抓包输入unionid

- 方便快捷，点一下app图标或快捷图块就可以开门，比先打开小程序再点击开门快多了
- 官方版绑定微信，只能一人一号，使用此app可以一号多台手机用，理论上一家n口只需注册一个账号即可

使用PC版微信小程序抓包,输入unionid

```
host/service/system/user/getToken/xxx
xxx就是所需unionid
```



![image](https://github.com/aoe-iu/SmarKey/blob/master/screenshot/p3.png)



Token刷新一次即可

勾选AutoOpen，打开App会自动调用一次开门

![image](https://github.com/aoe-iu/SmarKey/blob/master/screenshot/p1.png)



增加快捷图块

![image](https://github.com/aoe-iu/SmarKey/blob/master/screenshot/p2.png)

# FAQ

添加快捷开关后不可用或重启后不可用（灰色状态）？

目前发现在MIUI 部分机型上有这个问题，会导致快捷方式TileService失效（原生Android不会），暂时没有有效解决办法。
有动手能力的可以尝试监听开机广播，启动服务
