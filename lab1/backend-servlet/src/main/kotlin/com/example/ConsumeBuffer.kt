package com.example.servlets

import jakarta.servlet.ServletOutputStream
import jakarta.servlet.WriteListener
import java.io.IOException
import java.nio.ByteBuffer

class ConsumeBuffer(private val buffer: ByteBuffer) : ServletOutputStream() {

    override fun isReady(): Boolean {
        return buffer.hasRemaining()
    }

    override fun setWriteListener(writeListener: WriteListener?) {
        // Not implemented
    }

    @Throws(IOException::class)
    override fun write(b: Int) {
        if (isReady) {
            buffer.put(b.toByte())
        } else {
            throw IOException("Buffer is full")
        }
    }
}
