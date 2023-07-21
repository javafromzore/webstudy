package org.test.pay.config;

public class PayAutoConfig {
    // 商户appid
    public static String APPID = "9021000123601867";
    // 私钥 pkcs8格式的
    public static String RSA_PRIVATE_KEY = "MIIEuwIBADANBgkqhkiG9w0BAQEFAASCBKUwggShAgEAAoIBAQCD4MyijSh2wD45pEJ+svF4XG7DtSWLKaZy2VNMK0QP7TliIMqSls6XXSo1YFFbbFy28TWAX/FeCpaUzcyDOG6d0oBBt9lKMQmSC9T6fLg7/G/bQoK+0bIzjKRLsdc/9JuyMU3Cwyn2pmsC2+/qcqpNYRRbIhJ/bjePa/2QSzm2W86+KQJ3qk0jnm2czE5dV+UOWPQNzXNinUjhlt5JfTJSTMRIJ4jtQxADXRKNIXNGHRboirY1STKxxwgwd10nO+JRdQAJb/VSpwG4oBmIHtVRIb7+HC/OoKcY1PkVY7U/0qxtAs1ACUVlXCqifJxhtz3SMhZXYWrURu7iK5AqpnFNAgMBAAECggEAfsh2Uuu78/Y7nDTDTIUBygGq5vFWp6QiBhXSk86gf2NDoIf/18+tyyzg8DGp5WeW9rWMBt+17v/k5ie3rotpJMcDUKrL9hyQo9xGI44VnG3Cp5sYkRSN6isSpEbTtgOWT0hBBrWTuTEifbwtuUoFWaCz7i0idWnjdjmOqtL3J81WeOslQT7xUsRi/LsBB+Vpk/OQO8jcoGRcw2ul9gcdYciHXE3/aZSRd0/ISJlzed4YvDoxJjZWhbzqBgytU9QOx7Yv7n1W77b+j6ZEbasgWAItPBU5UgxKQxc4v+1EJMvx4KX+XiDrkxLi1lWxY5IeuCGIXo8CfEgZYBcI2ju3LQKBgQDJfH0iTYP6M4nkRmj9D76GswYKv9IlajC646a3rvwngvrjffkhDdvdirl/mWvXG265HeSndcq7cRkVjwqGbe6xfiqylSfxQKQ0V1P3Wo8tM6t6ljD+w211lgK+41vzQMmGr6ZrJrNjzfXaFrOOlo5MxX6bHjOFsGmNAYzLMlggEwKBgQCnjw1MLJ6K8AZGl17Jwp/ss0VGTAXXv1UeHx/XvR0kuq3mcTukAVa6m7UI/VUDDZH7kTU64GAQ91NHdBTWTJkVjeZKAYMNUWJthvvm30k3sNGfXfdVsk2wJXKVxDgRj5tk9+Qvuz5VERPZUc5L5vIk/wgVJvAr2ao0PC2Q0GMVHwJ/K0mTmMsOj7KC7YgzBitbw5Syt5x+iYtG7N/78XqmsdZyEG4AGBniIrarI77SbGR4ZU0G0vN7msmxmfAhUsBLh9aTVcq1yh8ZKCWa5ImQOSXgsXWTESjrW1ffPyOZnalNoqIaHusxnz+gkjpOWVP/Pn8vtCPI7e/4vVKX7Q/j3wKBgEk7+O/mQJUxO0z3M/KzIfruj8SdzZ/yfF1h9STx/x7iiMdDjnGsNB/zljiLoPBoqfluCfnlv5cqwjdkwQa5C9+j2ZdW+dSEUaTVbpwF05aHrdcd1tCZ0eK/O0nCLU9fmOeUgSNrj17Wy/ocCzKZihQpc3rrPeWsiPuzoaa9rdC5AoGBAIdbGDIUwETA3OuzB3kdrW6XoTUyO0H4m4fqGpxHlpHejcrQ/CYBFL/wrR3yzEyCROVp/5osqXUBZsE6fUAELD1l/fJt3iAGeehfKEKn28kisohb7lQ1v0KpDU9SnHHgA4W86V6OiTYvw1Z+0ujhZ2m0wh/Xa0aW93dypzP4qN2h";
    // 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    //todo 异步通知地址会产生变化
    public static String notify_url = "http://localhost:1113/alipay/notify";
    // 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
    public static String return_url = "http://localhost:1113/alipay/return";
    // 请求网关地址,沙箱是:https://openapi.alipaydev.com/gateway.do
    public static String URL = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";
    // 编码
    public static String CHARSET = "UTF-8";
    // 返回格式
    public static String FORMAT = "json";
    // 支付宝公钥(在应用中可以获取)
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjGvyku4Pkd6s3CRWtss/YFjG3UyvTTl9phsMPwTSS3z0cuRhJifsvhtDywGf1NDUp9MElv95fE/DlMl/qbmy75ALLaZf6afHK46ZGU6n4rYoFztvzvom7dSE4/nJ6IdidJN/WK3rDdxHapH7SuW61AeqNzcKd/xa5Wm9hEvWim0zhtJjQNiydkSq4L7u/wzmGiPanWUKSqMuqsH/8GtsHVE9cQdHIZ2rLYjuw4VAQeBOUN7Cm4zZr/UMAsoVqLX/AJdhq0l56lbLfk5/91yG1PiRTIDJuByxSLnXu1ln8hACdd/ZD9SUsNKveQTKHl7a5kiPXxzMgcPsZNM9bo9oiQIDAQAB";
    // RSA2
    public static String SIGNTYPE = "RSA2";
}
