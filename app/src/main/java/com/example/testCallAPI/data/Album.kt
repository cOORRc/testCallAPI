package com.example.testCallAPI.data

import com.google.gson.annotations.SerializedName

data class Album (
    val userId: Int,
    val id: Int,
    //ถ้าชื่อที่เราตั้งในดาต้าไฟล์นี้ ไม่ตรงกันกับชื่อที่อยู่ใน json ให้ใส่ @SerializedName("ชื่อใน json")
    // แต่ถา้ตรงกันเหมือน userId, id สามารถกำหนดตัวแปร(Variables)และ data type ได้เลย
    @SerializedName("title")
    val album: String
)