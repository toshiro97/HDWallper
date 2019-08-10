package com.toshiro.hdwallper.database

import android.content.Context
import android.database.sqlite.SQLiteQueryBuilder
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper
import com.toshiro.hdwallper.model.HDWALLPAPERItem
import com.toshiro.hdwallper.model.giftImage.GIFItem
import java.util.ArrayList

class Database(context: Context) : SQLiteAssetHelper(context, DB_NAME, null, DB_VER) {

    companion object {
        private const val DB_NAME = "HDWallpaper.db"
        private const val DB_VER = 1
    }

    fun addToImage(item: HDWALLPAPERItem) {
        val db = readableDatabase
        val query = String.format("INSERT OR REPLACE INTO Image(categoryImage,categoryName, categoryImageThumb,wallpaperImageThumb, catId, wallpaperImage, totalViews, id, cid) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s');",
                item.categoryImage,
                item.categoryName,
                item.categoryImageThumb,
                item.wallpaperImageThumb,
                item.catId,
                item.wallpaperImage,
                item.totalViews,
                item.id,
                item.cid
        )
        db.execSQL(query)
    }

    fun addToGif(item: GIFItem) {
        val db = readableDatabase
        val query = String.format("INSERT OR REPLACE INTO Gif(gifImage, totalViews, id) VALUES ('%s','%s','%s');",
                item.gifImage,
                item.totalViews,
                item.id
        )
        db.execSQL(query)
    }

    fun removeFromImage(imageID : String) {
        val db = readableDatabase
        val query = String.format("DELETE FROM Image WHERE id='%s';", imageID)
        db.execSQL(query)
    }

    fun removeFromGif(gifID : String) {
        val db = readableDatabase
        val query = String.format("DELETE FROM Gif WHERE id='%s';", gifID)
        db.execSQL(query)
    }

    fun getAllFavoritesImage(): List<HDWALLPAPERItem> {
        val db = readableDatabase
        val qb = SQLiteQueryBuilder()

        val sqlSelect = arrayOf("categoryImage", "categoryName" , "categoryImageThumb", "wallpaperImageThumb", "catId", "wallpaperImage", "totalViews", "id", "cid")
        val sqlTable = "Image"

        qb.tables = sqlTable
        val c = qb.query(db, sqlSelect,null, null, null, null, null)

        val result = ArrayList<HDWALLPAPERItem>()
        if (c.moveToFirst()) {
            do {
                result.add(HDWALLPAPERItem(
                        c.getString(c.getColumnIndex("categoryImage")),
                        c.getString(c.getColumnIndex("categoryName")),
                        c.getString(c.getColumnIndex("categoryImageThumb")),
                        c.getString(c.getColumnIndex("wallpaperImageThumb")),
                        c.getString(c.getColumnIndex("catId")),
                        c.getString(c.getColumnIndex("wallpaperImage")),
                        c.getString(c.getColumnIndex("totalViews")),
                        c.getString(c.getColumnIndex("id")),
                        c.getString(c.getColumnIndex("cid")))
                )
            } while (c.moveToNext())
        }

        return result
    }

    fun getAllFavoritesGif(): List<GIFItem> {
        val db = readableDatabase
        val qb = SQLiteQueryBuilder()

        val sqlSelect = arrayOf("gifImage", "totalViews", "id")
        val sqlTable = "Gif"

        qb.tables = sqlTable
        val c = qb.query(db, sqlSelect,null, null, null, null, null)

        val result = ArrayList<GIFItem>()
        if (c.moveToFirst()) {
            do {
                result.add(GIFItem(
                        c.getString(c.getColumnIndex("gifImage")),
                        c.getString(c.getColumnIndex("totalViews")),
                        c.getString(c.getColumnIndex("id")))
                )
            } while (c.moveToNext())
        }
        return result
    }

    fun isFavoriteImage(imgID: String): Boolean {
        val db = readableDatabase
        val query = String.format("SELECT * FROM Image WHERE id='%s';", imgID)
        val cursor = db.rawQuery(query, null)
        if (cursor.count <= 0) {
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }

    fun isFavoriteGif(gifID: String): Boolean {
        val db = readableDatabase
        val query = String.format("SELECT * FROM Gif WHERE id='%s';", gifID)
        val cursor = db.rawQuery(query, null)
        if (cursor.count <= 0) {
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }
}
