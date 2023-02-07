package com.elshafee.androiden.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.elshafee.androiden.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ShoppingDaoTest {

    @get:Rule
    var instanceTaskExecutorRole = InstantTaskExecutorRule()

    private lateinit var database: ShoppingItemDatabase
    private lateinit var dao: ShoppingDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingItemDatabase::class.java,
        ).allowMainThreadQueries().build()
        dao = database.shoppingDao()
    }

    @After
    fun teardown() {
        database.close()
    }


    @Test
    fun inserShoppingItem() = runBlocking {
        val shoppingItem = ShoppingItem("name", 1, 1f, "url", id = 1)

        dao.insertShopingItem(shoppingItem)

        val allShoppingItem = dao.observeAllShoppingItems().getOrAwaitValue()

        assertThat(allShoppingItem).contains(shoppingItem)
    }


    @Test
    fun deleteShoppingItem() = runBlocking {
        val shoppingItem = ShoppingItem("apple", 1, 1f, "url", id = 1)
        dao.insertShopingItem(shoppingItem)
        dao.deleteShoppingItem(shoppingItem)

        val allShoppingItems = dao.observeAllShoppingItems().getOrAwaitValue()
        assertThat(allShoppingItems).doesNotContain(shoppingItem)


    }

    @Test
    fun observeTotalPrice() = runBlocking {
        val shoppingItem1 = ShoppingItem("apple", 10, 1f, "url", id = 1)
        val shoppingItem2 = ShoppingItem("apple", 20, 1f, "url", id = 2)//30
        val shoppingItem3 = ShoppingItem("apple", 100, 1f, "url", id = 3)//130
        val shoppingItem4 = ShoppingItem("apple", 4, 1f, "url", id = 4)//134
        val shoppingItem5 = ShoppingItem("apple", 5, 1f, "url", id = 5)//139
        val shoppingItem6 = ShoppingItem("apple", 90, 1f, "url", id = 6)//229


        dao.insertShopingItem(shoppingItem1)
        dao.insertShopingItem(shoppingItem2)
        dao.insertShopingItem(shoppingItem3)
        dao.insertShopingItem(shoppingItem4)
        dao.insertShopingItem(shoppingItem5)
        dao.insertShopingItem(shoppingItem6)

        val totalPrice = dao.observeTotalPrice().getOrAwaitValue()
        assertThat(totalPrice).isEqualTo(229f)
    }


}