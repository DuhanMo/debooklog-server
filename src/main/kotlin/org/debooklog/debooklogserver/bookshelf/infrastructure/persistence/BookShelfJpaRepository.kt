package org.debooklog.debooklogserver.bookshelf.infrastructure.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface BookShelfJpaRepository : JpaRepository<BookShelfEntity, Long>
