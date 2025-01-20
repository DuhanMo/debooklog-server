package org.debooklog.debooklogserver.core.book.model

class DuplicateBookException(message: String = "이미 저장한 책입니다!") : RuntimeException(message)
