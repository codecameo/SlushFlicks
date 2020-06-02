package com.sifat.slushflicks.data.impl

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sifat.slushflicks.data.LocalDataManager
import com.sifat.slushflicks.model.GenreModel
import com.sifat.slushflicks.utils.getGenreList
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalDataManagerImplTest {

    private lateinit var sut: LocalDataManager

    @Before
    fun setup() {
        sut = LocalDataManagerImpl(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun testAddingAndGettingGenre() {
        // Arrange
        val id = 1L
        val name = "genreName"
        //Act
        var genre = sut.getGenre(id)
        //Assert
        assertNull(genre)
        //Act
        sut.addGenre(id, name)
        genre = sut.getGenre(id)
        //Assert
        assertEquals(name, genre)
    }

    @Test
    fun testAddingGenreNotResettingMap() {
        // Arrange
        val id1 = 1L
        val name1 = "genreName1"
        val id2 = 2L
        val name2 = "genreName2"

        //Act
        sut.addGenre(id1, name1)
        var genre1 = sut.getGenre(id1)
        var genre2 = sut.getGenre(id2)
        //Assert
        assertNotNull(genre1)
        assertNull(genre2)
        //Act
        sut.addGenre(id2, name2)
        genre1 = sut.getGenre(id1)
        genre2 = sut.getGenre(id2)
        //Assert
        assertNotNull(genre1)
        assertEquals(name1, genre1)
        assertNotNull(genre2)
        assertEquals(name2, genre2)
    }

    @Test
    fun testAddingGenreListNotResettingMap() {
        // Arrange
        val id1 = 7L
        val name = "genreName1"
        val list = getGenreList()

        //Act
        sut.addGenre(id1, name)
        sut.addGenre(list)
        //Act
        val genre = sut.getGenre(id1)
        //Assert
        assertNotNull(genre)
        assertEquals(name, genre)
    }

    @Test
    fun testAddingGettingGenreList() {
        // Arrange
        val list = getGenreList()
        val map = sut.getGenres()
        //Assert
        for (genre in list) {
            assertNull(map[genre.id])
        }
        //Act
        sut.addGenre(list)
        //Assert
        for (genre in list) {
            assertEquals(genre.name, map[genre.id])
        }
    }

    @Test
    fun testInitGenres() {
        // Arrange
        val list = getGenreList()
        val map = sut.getGenres()
        //Assert
        assertEquals(true, map.isEmpty())
        //Act
        sut.initGenres(list)
        //Assert
        assertEquals(list.size, map.size)
        for (genre in list) {
            assertEquals(genre.name, map[genre.id])
        }
    }

    @Test
    fun testInitEmptyGenres() {
        // Arrange
        val list = emptyList<GenreModel>()
        val map = sut.getGenres()
        //Assert
        assertEquals(true, map.isEmpty())
        //Act
        sut.initGenres(list)
        //Assert
        assertEquals(true, map.isEmpty())
    }

    @Test
    fun testInitNullGenresIgnoreUpdate() {
        // Arrange
        val list = getGenreList()
        val map = sut.getGenres()
        //Act
        sut.initGenres(list)
        sut.initGenres(null)
        //Assert
        assertEquals(list.size, map.size)
        for (genre in list) {
            assertEquals(genre.name, map[genre.id])
        }
    }
}