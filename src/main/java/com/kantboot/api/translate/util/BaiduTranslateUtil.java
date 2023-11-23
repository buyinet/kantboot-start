package com.kantboot.api.translate.util;

import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.SneakyThrows;

import java.net.URLEncoder;
import java.util.UUID;

public class BaiduTranslateUtil {


    /**
     * 翻译
     * @param appid 百度翻译的appid
     * @param key 百度翻译的key
     * @param q     要翻译的文本
     * @param from  源语言
     * @param to    目标语言
     * @return 翻译后的文本
     */
    @SneakyThrows
    public static String translate(
            String q,
            String from,
            String to,
            String appid,
            String key) {
        System.err.println(q+","+from+","+to+","+appid+","+key);
        String uri = "https://fanyi-api.baidu.com/api/trans/vip/translate";

        String salt = UUID.randomUUID().toString().replaceAll("-", "");

        // 签名
        String sign = MD5.create().digestHex(appid + q + salt + key, "UTF-8");

        // 参数,要url转换
        String param="?q="+ URLEncoder.encode(q,"UTF-8")+ "&from="+from+"&to="+to+"&appid="+appid+"&salt="+salt+"&sign="+sign;

        String url = uri + param;

        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .get()
                .build();

        okhttp3.Response response = new okhttp3.OkHttpClient().newCall(request).execute();
        String text = response.body().string();
        // 获取trans_result中的第1个dst
        JSONObject jsonObject = JSON.parseObject(text);
        System.out.println(text);
        JSONArray transResult = jsonObject.getJSONArray("trans_result");
        if(transResult==null||transResult.size()==0){
            return BaiduTranslateUtil.translate(q, from, "en", appid, key);
        }

        String result = transResult.getJSONObject(0).getString("dst");
        if(to.equals("yue")){
            return BaiduTranslateUtil.translate(result, "zh", "cht", appid, key);
        }
        return result;
    }

}
