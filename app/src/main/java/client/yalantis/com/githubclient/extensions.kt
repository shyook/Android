package client.yalantis.com.githubclient

import com.squareup.moshi.Moshi
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Alexey on 07.10.2016.
 */

fun String.formatDate(): String {
    val initialFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.ENGLISH)
    val format = SimpleDateFormat("yyyy-M-dd", Locale.US)
    val date = initialFormat.parse(this)
    return format.format(date).toString()
}

inline fun <reified T> Moshi.fromJson(json: String): T = this.adapter(T::class.java).fromJson(json)

