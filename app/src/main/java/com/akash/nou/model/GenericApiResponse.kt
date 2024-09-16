/**
 * IN THE NAME OF ALLAH, THE MOST BENEFICENT, THE MOST MERCIFUL
 * COPYRIGHT (C) SABIR KHAN AKASH - 2023, 2024
 */

sealed class GenericApiResponse<out T> {
    data class Success<out T>(val data: T) : GenericApiResponse<T>()
    data class Error(val message: String?) : GenericApiResponse<Nothing>()
    data class Forbidden(val message: String?) : GenericApiResponse<Nothing>()
}
