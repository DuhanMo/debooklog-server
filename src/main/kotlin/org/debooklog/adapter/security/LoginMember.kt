package org.debooklog.adapter.security

import io.swagger.v3.oas.annotations.Parameter

@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Parameter(hidden = true) // Swagger 문서에서 숨김
annotation class LoginMember
