package com.example.sandboxgame

import com.example.core.rule.ui.objects.planet.Planet
import com.example.core.rule.ui.objects.space.Space
import com.example.sandboxgame.ui.App.Companion.database
import com.omega_r.libs.extensions.list.toArrayList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

//    @Test
//    fun usingDB() {
//        GlobalScope.launch{
//
//            withContext(Dispatchers.Main) {
//                database.planetDao.insertPlanet(Planet(0,0, Space.PlanetImage.PLANET1, 0, 0, 0))
//            }
//        }
//        val planet: ArrayList<Planet> = database.planetDao.getAllPlanet().toArrayList()
//        if(planet.size == 1) {
//            println("all right")
//        } else println("ou no!")
//    }

//    public void insertAndGetUser() {
//        // Добавление пользователя в базу данных
//        mDatabase.userDao().insertUser(USER);
//
//        // Проверка возможности получения пользователя из базы данных
//        List<User> users = mDatabase.userDao().getUsers();
//        assertThat(users.size(), is(1));
//        User dbUser = users.get(0);
//        assertEquals(dbUser.getId(), USER.getId());
//        assertEquals(dbUser.getUserName(), USER.getUserName());
//    }
}