package com.villalva.stefano.laboratoriocalificado03
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.villalva.stefano.laboratoriocalificado03.databinding.ActivityEjercicio01Binding
import kotlinx.coroutines.launch
class Ejercicio01Activity : AppCompatActivity() {
    private lateinit var binding: ActivityEjercicio01Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEjercicio01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        fetchProfesores()
    }
    private fun fetchProfesores() {
        lifecycleScope.launch {
            try {
                val responseWrapper = RetrofitInstance.api.getProfesores()

                if (responseWrapper.teachers.isNotEmpty()) {
                    println("Datos recibidos de la API: ${responseWrapper.teachers}")

                    binding.recyclerView.adapter = ProfesorAdapter(
                        responseWrapper.teachers,
                        onClick = { profesor ->
                            println("Clic en profesor: ${profesor.name} ${profesor.last_name}")
                            val intent = Intent(Intent.ACTION_DIAL)
                            intent.data = Uri.parse("tel:${profesor.phone_number}")
                            startActivity(intent)
                        },
                        onLongClick = { profesor ->
                            println("Clic largo en profesor: ${profesor.name} ${profesor.last_name}")
                            val intent = Intent(Intent.ACTION_SENDTO)
                            intent.data = Uri.parse("mailto:${profesor.email}")
                            startActivity(intent)
                        }
                    )
                } else {
                    println("La lista de profesores está vacía.")
                    Toast.makeText(
                        this@Ejercicio01Activity,
                        "No se encontraron profesores.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (e: retrofit2.HttpException) {
                println("Error HTTP: Código ${e.code()}, Mensaje: ${e.message()}")
                Toast.makeText(
                    this@Ejercicio01Activity,
                    "Error HTTP: ${e.code()}",
                    Toast.LENGTH_LONG
                ).show()
            } catch (e: Exception) {
                println("Error general: ${e.message}")
                Toast.makeText(
                    this@Ejercicio01Activity,
                    "Error al obtener los datos: ${e.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
