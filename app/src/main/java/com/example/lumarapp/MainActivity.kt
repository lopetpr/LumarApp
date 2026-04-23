package com.example.lumarapp

import android.media.Image
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lumarapp.ui.theme.LumarAppTheme
import java.util.Collections.copy

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LumarAppTheme {

                PreviewApp()

            }
        }
    }
}


@Composable
fun LoginContent(){


    Box(

        modifier = Modifier.fillMaxSize()
    ){

        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center
            ) {

            Image(
                modifier = Modifier.weight(1.25f),
                painter= painterResource(id = R.drawable.pexels_mathilde),
                contentDescription = "Imagen de fondo",
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { setToScale(redScale = 0.75f, greenScale = 0.75f, blueScale = 0.75f, alphaScale = 0.95f) })
            )


            Spacer(
                modifier = Modifier
                    .weight(0.5f)
                    .background(color = Color.White)
            )



        }
        Column(modifier =Modifier.fillMaxWidth().padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally

            ) {
            Image(
                modifier = Modifier.padding(25.dp)
                    .size(160.dp).clip(CircleShape).border(4.dp, Color.White, CircleShape)
                ,
                painter= painterResource(id = R.drawable.loginlumar),
                contentDescription = "Logo de Lumar",
                contentScale = ContentScale.Crop


                )


            Spacer(modifier = Modifier.fillMaxWidth().height(180.dp))


            Card(
                modifier =Modifier.fillMaxWidth().height(400.dp),
                shape = RoundedCornerShape(
                    topEnd = 40.dp,
                    topStart = 40.dp
                ),
                colors = CardDefaults.cardColors(Color.White )
            ) {

                Column( modifier = Modifier.padding(30.dp)) {


                    Text(text = "Ingresar",
                        modifier = Modifier.padding(bottom = 20.dp),
                        style = MaterialTheme.typography.headlineMedium.copy(

                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                        )



                    TextField(
                        modifier = Modifier.padding(vertical = 15.dp).fillMaxWidth(),
                        value = "",
                        onValueChange = { },
                        label = { Text("Usuario") },
                        singleLine = true,

                    )

                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = "",
                        onValueChange = { },
                        label = { Text("Contraseña") },
                        singleLine = true
                    )


                    Button(

                        modifier= Modifier.fillMaxWidth().padding(top = 15.dp),

                        onClick = {}
                    ) {
                        Text(text = "ENVIAR")
                    }

                }



            }
        }






    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewApp() {
    LoginContent()

}



