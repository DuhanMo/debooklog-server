package org.debooklog.debooklogserver.common.security

class LoginFailedException(message: String = "로그인 정보가 정확하지 않습니다.") : RuntimeException(message)
