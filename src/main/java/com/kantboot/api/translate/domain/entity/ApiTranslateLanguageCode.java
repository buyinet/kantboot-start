package com.kantboot.api.translate.domain.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Table(name="api_translate_language_code")
@Entity
@Getter
@Setter
@Accessors(chain = true)
@EntityListeners(AuditingEntityListener.class)
public class ApiTranslateLanguageCode implements java.io.Serializable {

    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * 国际标准语言编码
     */
    @Column(name = "t_code",length = 64)
    private String code;

    /**
     * 百度翻译语言编码
     */
    @Column(name = "baidu_translate_code",length = 64)
    private String baiduTranslateCode;

}
