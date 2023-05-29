package com.example.buyerapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.buyerapp.domain.model.UserInfo

@Entity(tableName = UserEntity.TABLE_NAME)
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID) val id: Long = 0,
    @ColumnInfo(name = COLUMN_FIRSTNAME) val firstname: String,
    @ColumnInfo(name = COLUMN_LASTNAME) val lastname: String,
    @ColumnInfo(name = COLUMN_PHONE) val cellphone: String
) {

    companion object {
        const val TABLE_NAME = "user"
        const val COLUMN_ID = "id"
        const val COLUMN_FIRSTNAME = "firstname"
        const val COLUMN_LASTNAME = "lastname"
        const val COLUMN_PHONE = "cellphone"
    }
}

fun UserEntity.toDomain() =
    UserInfo(
        firstname,
        lastname,
        cellphone
    )