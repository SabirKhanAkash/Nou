/**
 * In the name of Allah, the Most Beneficent, the Most Merciful
 * Written by Sabir Khan Akash
 */

sealed class GenericApiResponse<out T> {
    data class Success<out T>(val data: T) : GenericApiResponse<T>()
    data class Error(val message: String?) : GenericApiResponse<Nothing>()
}
