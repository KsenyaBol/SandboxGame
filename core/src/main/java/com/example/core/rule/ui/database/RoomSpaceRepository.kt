package com.example.core.rule.ui.database

import android.accounts.AuthenticatorException
import com.example.core.rule.ui.objects.space.SpaceDao
import com.example.core.rule.ui.objects.space.SpaceObject
import kotlinx.coroutines.CoroutineDispatcher

//class RoomSpaceRepository (
//    private val spaceDao: SpaceDao,
//    private val ioDispatcher: CoroutineDispatcher
//    ) : SpaceRepository {
//    override suspend fun gameId(id: Int): Int {
//        val result = spaceDao.findById(id) ?: throw AuthenticatorException()
//        return result.id
//
//    }
//}


