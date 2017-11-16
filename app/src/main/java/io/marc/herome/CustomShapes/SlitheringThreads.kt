package io.marc.herome.CustomShapes

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.ViewGroup
import android.widget.FrameLayout


/**
 *  Project HeroMe
 *  Created by Marc on 16/11/2017.
 */

class SlitheringThreads : FrameLayout {
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initPaint()
    }
    constructor(context: Context) : super(context) {
        initPaint()
    }

    data class Point(var x: Float, var y: Float)
    data class Line(var start: Point, var end: Point)

    private val lines: ArrayList<Line> = arrayListOf()
    private val linesPaint = Paint()
    private val headsPaint = Paint()

    var headSize: Float = 3.0f

    private fun initPaint() {
        linesPaint.color = Color.WHITE
        headsPaint.color = Color.WHITE
        headsPaint.style = Paint.Style.FILL
    }

    fun addLine(start: Point, end: Point) {
        lines.add(Line(start, end))
    }

    fun addLine(startx: Float, starty: Float, endx: Float, endy: Float) {
        lines.add(Line(Point(startx, starty), Point(endx, endy)))
    }

    fun addLine(line: Line) {
        lines.add(line)
    }

    override fun onDraw(canvas: Canvas?) {
        if (canvas != null && lines.isNotEmpty()) {
            canvas.drawCircle(lines.first().start.x, lines.first().start.y, headSize, headsPaint)
            lines.forEach { canvas.drawLine(it.start.x, it.start.y, it.end.x, it.end.y, linesPaint) }
            canvas.drawCircle(lines.last().start.x, lines.last().start.y, headSize, headsPaint)
        }
    }
}