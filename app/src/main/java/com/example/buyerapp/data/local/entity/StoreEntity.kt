package com.example.buyerapp.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.buyerapp.domain.model.Store

@Entity(tableName = StoreEntity.TABLE_NAME)
data class StoreEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID)  val id: Long,
    @ColumnInfo(name = COLUMN_TITLE)  val title: String,
    @ColumnInfo(name = COLUMN_DESCR)  val descr: String,
    @ColumnInfo(name = COLUMN_COLOR)  val color: String
) {
    companion object {
        const val TABLE_NAME = "store"
        const val COLUMN_ID = "id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_DESCR = "descr"
        const val COLUMN_COLOR = "color"
    }
}

fun Store.toEntity() = StoreEntity(id, title, "", color)

fun StoreEntity.toDomain() = Store(id, title, "", color)

