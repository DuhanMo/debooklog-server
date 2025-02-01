package org.debooklog.core.member.port

import org.debooklog.core.member.model.Member

interface MemberRepository {
    fun save(member: Member): Member

    fun getById(id: Long): Member

    fun findAll(): List<Member>

    fun findByEmail(email: String): Member?

    fun findById(id: Long): Member?
}
