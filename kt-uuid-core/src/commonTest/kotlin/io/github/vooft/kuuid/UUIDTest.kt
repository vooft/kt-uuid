package io.github.vooft.kuuid

import io.kotest.matchers.shouldBe
import kotlin.test.Test

class UUIDTest {
    @Test
    fun should_generate_random_uuid() {
        val uuid = UUID.randomUUID()
        println(uuid.toString())
    }

    @Test
    fun should_format_uuid() {
        val uuid = UUID(0x1122334455667788, 0x2233445566778899)

        uuid.toString() shouldBe "11223344-5566-7788-2233-445566778899"
    }

    @Test
    fun should_parse_and_format_uuid() {
        val expected = "11223344-5566-7788-2233-445566778899"

        val uuid = UUID.fromString(expected)

        uuid.toString() shouldBe expected
    }
}
