package com.sifat.slushflicks.model

import com.sifat.slushflicks.utils.EMPTY_STRING
import com.sifat.slushflicks.utils.INVALID_ID
import org.junit.Assert.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test

internal class GenreModelTest {

    private val model = GenreModel(1233, "name1")

    @Test
    internal fun isDiffGenresWithSamePropertiesAreEqual() {
        //Arrange
        val model1 = GenreModel(1233, "name1")
        //Act
        //Assert
        assertEquals(model, model1)
    }

    @Test
    internal fun isDiffGenresDiffIdAreNotEqual() {
        //Arrange
        val model1 = GenreModel(1234, "name1")
        //Act
        //Assert
        assertNotEquals(model, model1)
    }

    @Test
    internal fun isDiffGenresDiffNameAreNotEqual() {
        //Arrange
        val model1 = GenreModel(1233, "name2")
        //Act
        //Assert
        assertNotEquals(model, model1)
    }

    @Test
    internal fun checkDefaultValue() {
        //Arrange
        val model1 = GenreModel()
        assertEquals(model1.id, INVALID_ID.toLong())
        //Act
        //Assert
        assertEquals(model1.name, EMPTY_STRING)
    }
}