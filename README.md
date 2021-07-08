# SmartKey
“平x安x门x禁”替代品，需要自行抓包输入该参数

官方版绑定微信，只能一人一号，使用此app可以一号多台手机用，理论上一家n口只需注册一个账号即可

使用PC版微信小程序抓包,输入encryptionUnionid

```
request:
/service/system/user/getUnionidByCode/v2/xxxxxxxxx

response:
	"data": {
		"encryptionUnionid": "xxxx",(使用这个参数)
		"unionid": "xxx"(这个是未加密的，不能用)
	}

```

![image](https://github.com/aoe-iu/SmarKey/blob/master/screenshot/p3.png)



Token刷新一次即可

勾选AutoOpen，打开App会自动调用一次开门

![image](https://github.com/aoe-iu/SmarKey/blob/master/screenshot/p1.png)



增加快捷图块

![image](https://github.com/aoe-iu/SmarKey/blob/master/screenshot/p2.png)
