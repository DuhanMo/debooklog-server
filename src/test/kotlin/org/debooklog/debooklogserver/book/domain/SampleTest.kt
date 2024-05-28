package org.debooklog.debooklogserver.book.domain

import org.junit.jupiter.api.Test

class SampleTest {
    data class SampleBook(
        val title: String,
        val isbn: List<String>,
    )

    @Test
    fun sample() {
        val books =
            listOf(
                SampleBook("노인과바다", listOf("12345678", "1234567890")),
                SampleBook("노인과바다", listOf("12345678", "1234567890")),
                SampleBook("노인과바다", listOf("12345678", "1234567890")),
                SampleBook("노인과바다", listOf("12345678", "1234567890")),
                SampleBook("개미", listOf("22345678", "2234567890")),
                SampleBook("개미", listOf("22345678", "2234567890")),
                SampleBook("개미", listOf("22345678", "2234567890")),
                SampleBook("사피엔스", listOf("32345678", "3234567890")),
                SampleBook("사피엔스", listOf("32345678", "3234567890")),
                SampleBook("데미안", listOf("42345678", "4234567890")),
                SampleBook("코스모스", listOf("52345678", "5234567890")),
                SampleBook("코스모스", listOf("52345678", "5234567890")),
                SampleBook("코스모스", listOf("52345678", "5234567890")),
                SampleBook("코스모스", listOf("52345678", "5234567890")),
                SampleBook("코스모스", listOf("52345678", "5234567890")),
                SampleBook("코스모스", listOf("52345678", "5234567890")),
            )
        val isbnFrequency = books.groupingBy { it.isbn.first() }.eachCount()
        val isbnToBookName = books.associateBy { it.isbn.first() }.mapValues { it.value.title }
        val rankedIsbn = isbnFrequency.entries.sortedByDescending { it.value }
        println("isbnFrequency = $isbnFrequency")
        println("isbnToBookName = $isbnToBookName")
        println("rankedIsbn = $rankedIsbn")
        rankedIsbn.forEach { (isbn, count) ->
            val bookTitle = isbnToBookName[isbn]
            println("ISBN: $isbn, Count: $count, Book Name: $bookTitle")
        }
    }
}
