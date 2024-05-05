package org.debooklog.debooklogserver.bookshelf.service

import org.debooklog.debooklogserver.bookshelf.controller.port.BookShelfService
import org.debooklog.debooklogserver.bookshelf.domain.BookShelf
import org.debooklog.debooklogserver.bookshelf.domain.BookShelfNameGenerator
import org.debooklog.debooklogserver.bookshelf.service.port.BookShelfRepository
import org.debooklog.debooklogserver.member.service.port.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime.now

@Service
@Transactional
class BookShelfServiceImpl(
    private val bookShelfNameGenerator: BookShelfNameGenerator,
    private val bookShelfRepository: BookShelfRepository,
    private val memberRepository: MemberRepository,
) : BookShelfService {
    override fun create(memberId: Long) {
        val member = memberRepository.getById(memberId)
        val bookShelfName = bookShelfNameGenerator.generate(member.name)
        val bookShelf =
            BookShelf(
                memberId = memberId,
                name = bookShelfName,
                createdAt = now(),
            )
        bookShelfRepository.save(bookShelf)
    }
}
