package org.debooklog.debooklogserver.member.domain

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull

fun MemberRepository.getOrThrow(id: Long): Member =
    findByIdOrNull(id)
        ?: throw NoSuchElementException("회원이 존재하지 않습니다. id: $id")

interface MemberRepository : JpaRepository<Member, Long>
