package com.byounghong.weatherforecast.ui.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.ImageView
import androidx.core.graphics.applyCanvas
import coil.ImageLoader
import coil.bitmap.BitmapPool
import coil.decode.SvgDecoder
import coil.load
import coil.size.Size
import coil.transform.Transformation
import com.byounghong.weatherforecast.R
import kotlin.math.min

/**
 * Coil 라이브러리를 이용하여 URL에 해당하는 이미지를 ImageView에 표시
 */
@SuppressLint("AppCompatCustomView")
class ImageViewCoil : ImageView {

    var url: String = ""
        set(value){
            field = value
            val imageLoader = ImageLoader.Builder(context)
                .componentRegistry {
                    add(SvgDecoder(context))
                }
                .build()
            load(value, imageLoader) {
                crossfade(true)
                placeholder(R.drawable.ic_empty_thumbnail)
                transformations(MinSizeTransformation())
            }
        }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs : AttributeSet) : super(context,attrs){
        val array = context.obtainStyledAttributes(attrs, R.styleable.ImageViewCoil)
        val url = array.getString(R.styleable.ImageViewCoil_url)
        load(url)
        array.recycle()
    }

    constructor(context: Context,  attrs: AttributeSet , defStyleAttr : Int) :
            super(context, attrs, defStyleAttr)

}

/**
 * 해상도별 이미지의 기본적인 사이즈를 위해서 사이즈를 변경하는 Custom Transformation
 */
class MinSizeTransformation : Transformation {
    override fun key(): String = MinSizeTransformation::class.java.name

    override suspend fun transform(pool: BitmapPool, input: Bitmap, size: Size): Bitmap {
        val paint = Paint()
        val minSize = min(input.width, input.height)
        val output = pool.get(minSize, minSize,  input.config ?: Bitmap.Config.ARGB_8888)
        output.applyCanvas {
            drawBitmap(input, minSize / 2f - input.width / 2f,
                minSize / 2f - input.height / 2f, paint)
        }

        return output
    }
}