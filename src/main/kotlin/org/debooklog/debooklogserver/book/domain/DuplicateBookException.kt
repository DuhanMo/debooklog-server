package org.debooklog.debooklogserver.book.domain

class DuplicateBookException(message: String = "이미 저장한 책입니다!") : RuntimeException(message)
