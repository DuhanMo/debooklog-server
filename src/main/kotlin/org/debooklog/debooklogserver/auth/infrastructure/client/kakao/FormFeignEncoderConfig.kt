package org.debooklog.debooklogserver.auth.infrastructure.client.kakao

import feign.codec.Encoder
import feign.form.spring.SpringFormEncoder
import org.springframework.beans.factory.ObjectFactory
import org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.cloud.openfeign.support.SpringEncoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Scope

class FormFeignEncoderConfig {
    @Bean
    @Primary
    @Scope(SCOPE_PROTOTYPE)
    fun encoder(converters: ObjectFactory<HttpMessageConverters>): Encoder {
        return SpringFormEncoder(SpringEncoder(converters))
    }
}
