package org.debooklog.debooklogserver.book.controller

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.just
import io.mockk.runs
import org.debooklog.debooklogserver.book.controller.dto.BookRegisterRequest
import org.debooklog.debooklogserver.book.service.BookQueryService
import org.debooklog.debooklogserver.book.service.BookService
import org.debooklog.debooklogserver.book.service.dto.BookInformationResponse
import org.debooklog.debooklogserver.support.RestControllerTest
import org.junit.jupiter.api.Test
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@WebMvcTest(BookController::class)
class BookControllerTest : RestControllerTest() {
    @MockkBean
    lateinit var bookQueryService: BookQueryService

    @MockkBean
    lateinit var bookService: BookService

    @Test
    fun `책을 검색한다`() {
        val response =
            listOf(
                BookInformationResponse(
                    title = "책 제목",
                    author = "작가",
                    isbn = listOf("1231231"),
                    thumbnail = "https://thumbnail.co.kr",
                ),
            )

        every { bookQueryService.search(any()) } returns response

        mockMvc.get("/api/books/search") {
            param("title", "검색할 책 제목")
        }.andExpect {
            status { isOk() }
            content { success(response) }
        }.andDo {
            handle(document("book-search-get"))
        }
    }

    @Test
    fun `책을 등록한다`() {
        val request =
            BookRegisterRequest(
                title = "책 제목",
                author = "작가",
                isbn = listOf("1231231"),
                thumbnail = "https://thumbnail.co.kr",
            )

        every { bookService.register(any()) } just runs

        mockMvc.post("/api/books") {
            jsonContent(request)
        }.andExpect {
            status { isNoContent() }
        }.andDo {
            handle(document("book-register-post"))
        }
    }
}
