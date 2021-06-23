# SmartKey
“平x安x门x禁”替代品，需要自行抓包替换该参数

```
App#unionId
```

可使用PC版微信小程序抓包,

```
request:
/service/system/user/getUnionidByCode/v2/xxxxxxxxx

response:
	"data": {
		"encryptionUnionid": "xxxx",
		"unionid": "xxx"
	}

```

使用“encryptionUnionid”替换“App#unionId”



Token刷新一次即可

勾选AutoOpen，打开App会自动调用一次开门

![image](https://github.com/aoe-iu/SmarKey/blob/master/screenshot/p1.png)



增加快捷图块

![image](https://github.com/aoe-iu/SmarKey/blob/master/screenshot/p2.png)
