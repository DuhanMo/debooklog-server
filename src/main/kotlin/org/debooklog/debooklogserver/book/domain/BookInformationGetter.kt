package org.debooklog.debooklogserver.book.domain

interface BookInformationGetter {
    fun search(title: String): List<BookInformationData>
}
