package uz.abbosbek.neco_lesson_2.models

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val discountPercentage: Float,
    val rating: Double,
    val stock: Float,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>
)
