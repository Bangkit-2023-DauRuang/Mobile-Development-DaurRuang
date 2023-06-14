package com.capstone.dauruang.ui.screen.scan

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.capstone.dauruang.MainActivity
import com.capstone.dauruang.R
import com.capstone.dauruang.ui.components.content.InfoScanCard
import com.capstone.dauruang.ui.screen.home.HomeScreen
import com.capstone.dauruang.ui.screen.profile.ProfileActivity
import com.capstone.dauruang.ui.screen.transaction.TransactionSummaryActivity
import com.capstone.dauruang.ui.theme.DauRuangTheme
import org.tensorflow.lite.DataType
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.label.TensorLabel
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class ScanActivity: ComponentActivity() {

    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    private var shouldShowCamera: MutableState<Boolean> = mutableStateOf(false)

    private var label: MutableState<String> = mutableStateOf("Undifined")

    private lateinit var photoUri: Uri
    private var shouldShowPhoto: MutableState<Boolean> = mutableStateOf(false)

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Log.i("kilo", "Permission granted")
            shouldShowCamera.value = true
        } else {
            Log.i("kilo", "Permission denied")
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DauRuangTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    if (shouldShowCamera.value) {
                        ScanScreen(
                            navigateBack = { onBackPressed() },
                            outputDirectory = outputDirectory,
                            executor = cameraExecutor,
                            onImageCaptured = ::handleImageCapture,
                            onError = { Log.e("kilo", "View error:", it) }
                        )
                    }

                    if (shouldShowPhoto.value) {

                        val bitmap = uriToBitmap(photoUri)
                        val hasil = bitmap?.let { ouputGenerator(it) }
                        label.value = hasil.toString()

                        if(label.value.isNotEmpty()){
                            Box(modifier = Modifier.fillMaxSize()) {
                                Column(modifier = Modifier
                                    .align(Alignment.TopCenter)
                                    .padding(
                                        top = 12.dp,
                                        bottom = 12.dp,
                                        end = 12.dp,
                                        start = 12.dp
                                    )
                                    .border(
                                        width = 4.dp,
                                        color = colorResource(R.color.green_primary),
                                        shape = RoundedCornerShape(24.dp)
                                    )
                                ) {
                                    Image(
                                        painter = rememberAsyncImagePainter(photoUri),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(24.dp))
                                            .fillMaxWidth()
                                    )
                                }
                                InfoScanCard(
                                    titleTrash = label.value,
                                    description = "Daurkan ${label.value} mu untuk keberlangsungan yang lebih baik",
                                    navigateNext = {

                                        // Toast.makeText(this@ScanActivity, "Ini Uri : ${photoUri}", Toast.LENGTH_SHORT).show()

                                        // Membuat Intent
                                        val intent = Intent(this@ScanActivity, TransactionSummaryActivity::class.java)
                                        intent.putExtra("imageUri", photoUri)
                                        intent.putExtra("label", label.value.toString())
                                        // Menjalankan activity tujuan
                                        startActivity(intent)
                                    },
                                    onScanClick = {
                                        val intent = Intent(this@ScanActivity, ScanActivity::class.java)
                                        startActivity(intent)
                                        finish()
                                    },
                                    modifier = Modifier
                                        .align(Alignment.BottomCenter)
                                        .padding(bottom = 8.dp)
                                )
                            }
                        }
                    }


                    requestCameraPermission()

                    outputDirectory = getOutputDirectory()
                    cameraExecutor = Executors.newSingleThreadExecutor()
                }
            }
        }
    }

    // Rubah ke bitmap
    private fun uriToBitmap(uri: Uri): Bitmap? {
        return try {
            val inputStream = contentResolver.openInputStream(uri)
            BitmapFactory.decodeStream(inputStream)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.i("kilo", "Permission previously granted")
                shouldShowCamera.value = true
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            ) -> Log.i("kilo", "Show camera permissions dialog")

            else -> requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun handleImageCapture(uri: Uri) {
        Log.i("kilo", "Image captured: $uri")
        shouldShowCamera.value = false

        photoUri = uri
        shouldShowPhoto.value = true
    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }

    private fun loadModelFile(): ByteBuffer {
        val assetManager = applicationContext.assets
        val modelPath = "model.tflite"

        val inputStream = assetManager.open(modelPath)
        val fileSize = inputStream.available()
        val modelBuffer = ByteBuffer.allocateDirect(fileSize)

        inputStream.use { stream ->
            val buffer = ByteArray(fileSize)
            stream.read(buffer)
            modelBuffer.put(buffer)
        }

        modelBuffer.rewind()
        return modelBuffer
    }


    private fun ouputGenerator(bitmap: Bitmap): String {
        val model = loadModelFile()

        // Load labels
        val labels = listOf("Logam", "Kertas", "Minyak", "Organik")

        val resizedBitmap = Bitmap.createScaledBitmap(bitmap, 225, 225, true)
        val byteBuffer = ByteBuffer.allocateDirect(225 * 225 * 3 * 4) // 224x224 RGB image with 4 bytes per channel (FLOAT32)
        byteBuffer.order(ByteOrder.nativeOrder())
        val intValues = IntArray(225 * 225)
        resizedBitmap.getPixels(intValues, 0, resizedBitmap.width, 0, 0, resizedBitmap.width, resizedBitmap.height)
        var pixel = 0
        for (i in 0 until 225) {
            for (j in 0 until 225) {
                val value = intValues[pixel++]
                byteBuffer.putFloat(((value shr 16) and 0xFF) / 255.0f)
                byteBuffer.putFloat(((value shr 8) and 0xFF) / 255.0f)
                byteBuffer.putFloat((value and 0xFF) / 255.0f)
            }
        }
        byteBuffer.rewind()

        // Create input tensor
        val inputTensor = TensorBuffer.createFixedSize(intArrayOf(1, 225, 225, 3), DataType.FLOAT32)
        inputTensor.loadBuffer(byteBuffer)

        val outputTensor = TensorBuffer.createFixedSize(intArrayOf(1, labels.size), DataType.FLOAT32)

        // Create interpreter
        val options = Interpreter.Options()
        val interpreter = Interpreter(model, options)

        // Run inference
        interpreter.run(inputTensor.buffer, outputTensor.buffer)

        // Process output
        val outputArray = outputTensor.floatArray
        // Find the index with the highest probability
        val predictedClassIndex = outputArray.indices.maxByOrNull { outputArray[it] } ?: -1

        // Get the predicted class
        val predictedClass = labels.getOrNull(predictedClassIndex)

        // Print prediction
        predictedClass?.let { println("Prediction: $it") }

        return predictedClass ?: "Unknown"
    }



    companion object {
        fun newIntent(context: Context) = Intent(context, ScanActivity::class.java)

//        fun newIntent(context: Context) = Intent(context, ScanActivity::class.java).also {
//            Toast.makeText(context, "Ini activity baru ${context.toString()}", Toast.LENGTH_SHORT).show()
//        }
    }
}