package org.debooklog.debooklogserver.book.domain

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.extensions.spring.SpringTestExtension
import io.kotest.extensions.spring.SpringTestLifecycleMode
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.date.shouldBeBefore
import org.debooklog.debooklogserver.support.RepositoryTest
import java.time.LocalDateTime

@RepositoryTest
class BookRepositoryTest(private val bookRepository: BookRepository) : ExpectSpec({
    extensions(SpringTestExtension(SpringTestLifecycleMode.Root))

    context("책 조회") {
        bookRepository.save(Book(1L, "책제목", "작가", "123123123", "http://thumbnail.co.kr"))

        expect("모든 책을 조회한다") {
            bookRepository.findAll() shouldHaveSize 1
        }

        expect("생성시각과 수정시각은 현재보다 같거나 작다") {
            val book = bookRepository.findAll().first()
            book.createdAt shouldBeBefore LocalDateTime.now()
            book.updatedAt shouldBeBefore LocalDateTime.now()
        }
    }
})
