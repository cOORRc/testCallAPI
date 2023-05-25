package com.example.testCallAPI.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.testCallAPI.R
import com.example.testCallAPI.data.Album
import com.example.testCallAPI.data.api.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
     private lateinit var bt_test: Button
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bt_test = findViewById<Button>(R.id.button)

        bt_test.setOnClickListener{
            Toast.makeText(this,"click",Toast.LENGTH_SHORT).show()

            // val apiService: ApiService = ApiService.retroBuilder()
            // หรือเรียกใช้แบบ invoke บรรทัดด้านล่างนี้
            apiService = ApiService()
            //getAlbumWithNo(5)
            //getAlbumWithUserID(5)
            getAlbumWithMap(5)
        }


    }

    private fun getAlbumWithMap(num_userID: Int) {
        val call_hashMap = hashMapOf<String,Int>()
        // "userId", "id" เป็นชื่อตาม link : https://jsonplaceholder.typicode.com/albums/?id=46&userId=5
        call_hashMap.put("userId",num_userID)
        call_hashMap.put("id",46)

        val call = apiService.getAlbumsMap(call_hashMap)
        call.enqueue(object: Callback<List<Album>>{
            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                Log.e("API", "error getAlbumWithUserID >> "+ t.message.toString())
            }

            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                if (response.isSuccessful){
                    val list=response.body()
                    for (i in 0 until list!!.size){
                        val data_getAlbumsMap = "userID: ${list[i].userId} id: ${list[i].id} \n title: ${list[i].album}"
                        Log.e("API", "getAlbumWithUserID = $data_getAlbumsMap")
                    }

                }
            }
        })
    }

    private fun getAlbumWithUserID(num_userID: Int) {
        val call = apiService.getAlbumWithUserID(num_userID)
        call.enqueue(object: Callback<List<Album>>{
            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                Log.e("API", "error getAlbumWithUserID >> "+ t.message.toString())
            }

            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                if (response.isSuccessful){
                    val list=response.body()
                    for (i in 0 until list!!.size){
                        val data_getAlbumWithUserID = "userID: ${list[i].userId} id: ${list[i].id} \n title: ${list[i].album}"
                        Log.e("API", "getAlbumsMap = $data_getAlbumWithUserID")
                    }

                }
            }
        })
    }

    private fun getAlbumWithNo(int: Int) {
        val call = apiService.getAlbumWithID(int)
        call.enqueue(object : Callback<Album> {
            override fun onFailure(call: Call<Album>, t: Throwable) {
                Log.e("API", "error >> " + t.message.toString())
            }

            override fun onResponse(call: Call<Album>, response: Response<Album>) {
                if (response.isSuccessful) {
                    val item = response.body()
                    Log.e("API", item.toString())
                }
            }
        })
    }
    private fun getAlbum() {
/*       // val apiService: ApiService = ApiService.retroBuilder()
         //หรือเรียกใช้แบบ invoke บรรทัดด้านล่างนี้
        val apiService = ApiService()*/

        // สร้าง call
        val call = apiService.getAlbums()
        call.enqueue(object: Callback<List<Album>>{
            override fun onFailure(call: Call<List<Album>>, t: Throwable) {
                Log.e("API", "error >> "+ t.message.toString())
            }

            override fun onResponse(call: Call<List<Album>>, response: Response<List<Album>>) {
                if (response.isSuccessful){
                    val list=response.body()
                    Log.e("API","${list!!.size}")
                }
            }
        })
    }


}