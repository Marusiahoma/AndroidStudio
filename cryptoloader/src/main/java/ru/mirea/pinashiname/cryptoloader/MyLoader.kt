package ru.mirea.pinashiname.cryptoloader

import android.content.Context
import android.os.Bundle
import android.os.SystemClock
import androidx.loader.content.AsyncTaskLoader
import java.security.InvalidKeyException
import java.security.NoSuchAlgorithmException
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec


class MyLoader(context: Context, args: Bundle?) :
    AsyncTaskLoader<String?>(context) {
    private var firstName: String? = null
    private var cryptText: ByteArray? = null
    private var originalKey: SecretKey? = null

    init {
        if (args != null) {
            firstName = args.getString("EXP")
            if (args.getByteArray("key") != null) {
                //	Обработка данных в oader
                cryptText = args.getByteArray(ARG_WORD)
                val key = args.getByteArray("key")
                //	Восстановление ключа
                originalKey = SecretKeySpec(key, 0, key!!.size, "AES")
            }
        }
    }

    override fun onStartLoading() {
        super.onStartLoading()
        forceLoad()
    }

    override fun loadInBackground(): String? {
        SystemClock.sleep(5000)
        return firstName
    }

    companion object {
        const val ARG_WORD = "word"
        fun decryptMsg(cipherText: ByteArray?, secret: SecretKey?): String {
            return try {
                val cipher = Cipher.getInstance("AES")
                cipher.init(Cipher.DECRYPT_MODE, secret)
                String(cipher.doFinal(cipherText))
            } catch (e: NoSuchAlgorithmException) {
                throw RuntimeException(e)
            } catch (e: NoSuchPaddingException) {
                throw RuntimeException(e)
            } catch (e: IllegalBlockSizeException) {
                throw RuntimeException(e)
            } catch (e: BadPaddingException) {
                throw RuntimeException(e)
            } catch (e: InvalidKeyException) {
                throw RuntimeException(e)
            }
        }
    }
}