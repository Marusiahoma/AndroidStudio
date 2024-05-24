package ru.mirea.pinashiname.cryptoloader

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import ru.mirea.pinashiname.cryptoloader.databinding.ActivityMainBinding
import java.security.InvalidKeyException
import java.security.InvalidParameterException
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.KeyGenerator
import javax.crypto.NoSuchPaddingException
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec


class MainActivity : AppCompatActivity(),
    LoaderManager.LoaderCallbacks<String> {
    val TAG = this.javaClass.simpleName
    private val LoaderID = 1234
    private var binding: ActivityMainBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.getRoot())
    }

    fun onCLickButton(view: View?) {
        val bundle = Bundle()
        bundle.putString("EXP", "mirea")
        LoaderManager.getInstance(this).initLoader(LoaderID, bundle, this)
    }

    override fun onLoaderReset(loader: Loader<String>) {
        Log.d(TAG, "onLoaderReset")
    }

    override fun onCreateLoader(id: Int, bundle: Bundle?): MyLoader {
        if (id == LoaderID) {
            Toast.makeText(this, "onCreateLoader$id", Toast.LENGTH_SHORT).show()
            return MyLoader(this, bundle)
        }
        throw InvalidParameterException("Invalid parametr id")
    }

    override fun onLoadFinished(loader: Loader<String>, s: String) {
        if (loader.id == LoaderID) {
            Log.d(TAG, "onLoadFinished$s")
            Toast.makeText(this, "onLoadFinished$s", Toast.LENGTH_SHORT).show()
        }
    }

    fun onClickEncrypt(view: View?) {
        val message: String = binding!!.editTextText.getText().toString()

        // Генерация ключа
        val secretKey = generateKey()

        // Шифрование сообщения
        val encryptedMessage = encryptMsg(message, secretKey)

        //	Отправка данных в Loader
        val bundle = Bundle()
        bundle.putByteArray(MyLoader.ARG_WORD, encryptedMessage)
        bundle.putByteArray("key", secretKey.encoded)
        LoaderManager.getInstance(this).initLoader(LoaderID, bundle, this)
        val s: String = MyLoader.decryptMsg(encryptedMessage, secretKey)
        Toast.makeText(this, "Расшифрованная фраза: $s", Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun generateKey(): SecretKey {
            return try {
                val sr = SecureRandom.getInstance("SHA1PRNG")
                sr.setSeed("any	data	used	as	random	seed".toByteArray())
                val kg = KeyGenerator.getInstance("AES")
                kg.init(256, sr)
                SecretKeySpec(kg.generateKey().encoded, "AES")
            } catch (e: NoSuchAlgorithmException) {
                throw RuntimeException(e)
            }
        }

        fun encryptMsg(message: String, secret: SecretKey?): ByteArray {
            var cipher: Cipher? = null
            return try {
                cipher = Cipher.getInstance("AES")
                cipher.init(Cipher.ENCRYPT_MODE, secret)
                cipher.doFinal(message.toByteArray())
            } catch (e: NoSuchAlgorithmException) {
                throw RuntimeException(e)
            } catch (e: NoSuchPaddingException) {
                throw RuntimeException(e)
            } catch (e: InvalidKeyException) {
                throw RuntimeException(e)
            } catch (e: BadPaddingException) {
                throw RuntimeException(e)
            } catch (e: IllegalBlockSizeException) {
                throw RuntimeException(e)
            }
        }
    }
}