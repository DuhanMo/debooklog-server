package org.debooklog.debooklogserver.adapter.config

import org.debooklog.debooklogserver.adapter.security.LoginMemberResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class AuthenticationConfig(
    private val loginMemberResolver: LoginMemberResolver,
) : WebMvcConfigurer {
    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(loginMemberResolver)
    }
}
