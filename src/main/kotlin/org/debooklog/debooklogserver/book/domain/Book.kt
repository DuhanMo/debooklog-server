package org.debooklog.debooklogserver.book.domain

import jakarta.persistence.Convert
import jakarta.persistence.Entity
import org.debooklog.debooklogserver.common.domain.BaseEntity
import org.debooklog.debooklogserver.common.domain.StringListConverter

@Entity
class Book(
    val memberId: Long,
    val title: String,
    val author: String,
    @Convert(converter = StringListConverter::class)
    val isbn: List<String>,
    val thumbnail: String,
) : BaseEntity()
