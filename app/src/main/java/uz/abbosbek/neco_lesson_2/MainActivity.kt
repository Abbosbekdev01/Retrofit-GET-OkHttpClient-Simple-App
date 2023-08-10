package uz.abbosbek.neco_lesson_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.abbosbek.neco_lesson_2.databinding.ActivityMainBinding
import uz.abbosbek.neco_lesson_2.retrofit.ProductApiClient
import uz.abbosbek.neco_lesson_2.retrofit.ProductApiService

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val BASE_URL = "https://dummyjson.com"

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL).client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val productApi = retrofit.create(ProductApiService::class.java)

        binding.apply {
            button.setOnClickListener {
                val defOuldId = edtId.text.toString()
                var id = 0
                if (defOuldId.isNotEmpty()) {
                    id = defOuldId.toInt()
                    CoroutineScope(Dispatchers.IO).launch {
                        val product = productApi.getProductById(id)

                        runOnUiThread {
                            tv1.text = product.title
                            textView2.text = product.category
                            Glide.with(this@MainActivity).load(product.images[0])
                                .placeholder(R.drawable.baseline_hide_image_24).into(imageView)
                        }
                    }
                }

            }
        }

    }
}