package uz.abbosbek.neco_lesson_2.retrofit

import retrofit2.http.GET
import retrofit2.http.Path
import uz.abbosbek.neco_lesson_2.models.Product

interface ProductApiService {
    //todo: Api dan ma'lumotlarni olib keladi
    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product

}