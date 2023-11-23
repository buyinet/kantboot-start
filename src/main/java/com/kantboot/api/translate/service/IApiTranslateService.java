package com.kantboot.api.translate.service;

/**
 * <p>
 *     翻译服务接口
 * </p>
 * @author 方某方
 */
public interface IApiTranslateService {

    /**
     * 翻译
     */
    String translate(String text,String from,String to);

}
