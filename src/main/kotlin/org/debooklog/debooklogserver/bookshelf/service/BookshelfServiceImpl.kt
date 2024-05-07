package org.debooklog.debooklogserver.bookshelf.service

import org.debooklog.debooklogserver.bookshelf.controller.port.BookshelfService
import org.debooklog.debooklogserver.bookshelf.domain.Bookshelf
import org.debooklog.debooklogserver.bookshelf.domain.BookshelfNameGenerator
import org.debooklog.debooklogserver.bookshelf.service.port.BookshelfRepository
import org.debooklog.debooklogserver.member.service.port.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime.now

@Service
@Transactional
class BookshelfServiceImpl(
    private val bookshelfNameGenerator: BookshelfNameGenerator,
    private val bookshelfRepository: BookshelfRepository,
    private val memberRepository: MemberRepository,
) : BookshelfService {
    override fun create(memberId: Long) {
        val member = memberRepository.getById(memberId)
        val bookshelfName = bookshelfNameGenerator.generate(member.name)
        val bookshelf =
            Bookshelf(
                memberId = memberId,
                name = bookshelfName,
                createdAt = now(),
            )
        bookshelfRepository.save(bookshelf)
    }
}
