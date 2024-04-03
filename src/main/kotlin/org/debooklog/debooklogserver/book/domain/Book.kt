package org.debooklog.debooklogserver.book.domain

import jakarta.persistence.Entity
import org.debooklog.debooklogserver.global.domain.BaseEntity

@Entity
class Book(
    val memberId: Long,
    val title: String,
    val author: String,
    val isbn: String,
    val thumbnail: String,
) : BaseEntity()
