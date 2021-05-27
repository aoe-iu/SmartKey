# SmarKey
“平安门禁”小程序替代品，需要自行抓包替换该参数

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

<img src="\screenshot\1.png" style="zoom:25%;" />



增加快捷图块

<img src="\screenshot\2.png" style="zoom:25%;" />
