package com.article.app

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.io.Reader
import java.net.URL
import java.net.URLConnection
import java.nio.charset.Charset

/**
 * @author vikas.grover
 * Api Availability Test
 */
@RunWith(JUnit4::class)
class ApiAvailablityTest{

    @Test
    fun articleAvailability(){

        val connection: URLConnection =
            URL("https://5dc28a6487479d001443662d.mockapi.io/article/list").openConnection()
        val response: InputStream = connection.getInputStream()

        val buffer = StringBuffer()
        BufferedReader(InputStreamReader(response, Charset.defaultCharset()) as Reader).use { reader ->
            var line: String? =  ""
            while (reader.readLine().also({ line = it }) != null) {
                buffer.append(line)
            }
        }
        assert(buffer.length > 0)
    }
}