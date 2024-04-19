package org.debooklog.debooklogserver.common.domain

import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null

    @CreatedDate
    var createdAt: LocalDateTime = LocalDateTime.MAX

    @LastModifiedDate
    var updatedAt: LocalDateTime = LocalDateTime.MAX
}
