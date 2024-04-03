package org.debooklog.debooklogserver.book.domain

import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long>
