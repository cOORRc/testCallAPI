package com.example.testCallAPI.data.api


import com.example.testCallAPI.data.Album
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

val BASE_URL = "https://jsonplaceholder.typicode.com"
interface ApiService {
    @GET("albums")
    fun getAlbums(): Call<List<Album>>

    @GET("albums/{no}")
    fun getAlbumWithID(@Path("no")no:Int): Call<Album>

    @GET("albums/")
    //("userId") เป็น link : https://jsonplaceholder.typicode.com/albums/?userId=5
    fun getAlbumWithUserID(@Query("userId") userID:Int): Call<List<Album>>

    @GET("albums/")
    //For complex query parameter เป็น link : https://jsonplaceholder.typicode.com/albums/?id=46&userId=5
    //id=46&userId=5 ชื่อลิ้งในส่วน id, userId เป็น HashMap กำหนดในหน้าที่เรียกใช้งานฟังก์ชันนี้
    fun getAlbumsMap(@QueryMap map:HashMap<String,Int>): Call<List<Album>>

    companion object{
        operator fun invoke():ApiService{ // สร้างแบบ invoke
        //fun retroBuilder():ApiService{ //retroBuilder() ชื่อที่เราตั้งเองเพื่อที่จะสร้าง Retrofit
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}