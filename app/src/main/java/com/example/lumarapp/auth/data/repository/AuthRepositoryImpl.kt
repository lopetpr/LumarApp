package com.example.lumarapp.auth.data.repository

import com.example.lumarapp.auth.data.local.TokenManager
import com.example.lumarapp.auth.data.remote.AuthApiService
import com.example.lumarapp.auth.data.remote.LoginRequest
import com.example.lumarapp.auth.domain.model.User
import com.example.lumarapp.auth.domain.repository.AuthRepository
import org.json.JSONObject
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val apiService: AuthApiService,
    private val tokenManager: TokenManager
) : AuthRepository {

    override suspend fun login(user_name: String, password: String): Result<User> {
        return try {
            val response = apiService.login(LoginRequest(user_name, password))

            if (response.isSuccessful && response.body() != null) {
                val dto = response.body()!!
                tokenManager.saveSession(
                    token = dto.token,
                    id = dto.userId,
                    name = dto.name,
                    rol = dto.rol
                )
                Result.success(User(id = dto.userId, name = dto.name, rol = dto.rol))
            } else {

                // 👇 Leer el error real del backend
                val errorBody = response.errorBody()?.string()

                val backendMessage = try {
                    val json = JSONObject(errorBody ?: "")
                    json.getString("message")
                } catch (e: Exception) {
                    null
                }

                // 👇 fallback si algo falla
                val errorMessage = backendMessage ?: when (response.code()) {
                    401 -> "Usuario o contraseña incorrectos"
                    403 -> "Tu cuenta ha sido desactivada"
                    429 -> "Demasiados intentos. Espera un momento"
                    in 500..599 -> "Error en el servidor. Intenta más tarde"
                    else -> "Error inesperado: ${response.code()}"
                }

                Result.failure(Exception(errorMessage))
            }

        } catch (e: UnknownHostException) {
            Result.failure(Exception("Sin conexión a internet"))
        } catch (e: SocketTimeoutException) {
            Result.failure(Exception("El servidor no responde. Intenta más tarde"))
        } catch (e: Exception) {
            Result.failure(Exception("Error de conexión: ${e.localizedMessage}"))
        }
    }

    override suspend fun logout() {
        tokenManager.clearSession()
    }

    override suspend fun isLoggedIn(): Boolean {
        return tokenManager.getToken() != null
    }

    override suspend fun getCurrentToken(): String? {
        return tokenManager.getToken()
    }
}
