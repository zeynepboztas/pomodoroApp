package com.example.pomodoroapp

import android.app.AlertDialog
import android.os.Bundle
import android.os.CountDownTimer
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etLessonName: EditText
    private lateinit var etWorkTime: EditText
    private lateinit var etBreakTime: EditText
    private lateinit var btnAddLesson: Button
    private lateinit var lessonContainer: LinearLayout
    private lateinit var summaryContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etLessonName = findViewById(R.id.etLessonName)
        etWorkTime = findViewById(R.id.etWorkTime)
        etBreakTime = findViewById(R.id.etBreakTime)
        btnAddLesson = findViewById(R.id.btnAddLesson)
        lessonContainer = findViewById(R.id.lessonContainer)
        summaryContainer = findViewById(R.id.summaryContainer)

        btnAddLesson.setOnClickListener {
            val name = etLessonName.text.toString()
            val work = etWorkTime.text.toString().toLongOrNull()
            val brk = etBreakTime.text.toString().toLongOrNull()

            if (name.isNotEmpty() && work != null && brk != null) {
                addLesson(name, work, brk)
                etLessonName.text.clear()
                etWorkTime.text.clear()
                etBreakTime.text.clear()
            } else {
                Toast.makeText(this, "Tüm alanları doldurunuz", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun addLesson(name: String, workMin: Long, breakMin: Long) {

        // ===== KART =====
        val card = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 24, 24, 24)
            setBackgroundColor(0xFFFFFFFF.toInt())
        }

        // ===== ÜST SATIR (DERS ADI + SİL) =====
        val header = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
        }

        val title = TextView(this).apply {
            text = name
            textSize = 18f
            layoutParams =
                LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
        }

        val btnDelete = Button(this).apply {
            text = "Sil"
        }

        header.addView(title)
        header.addView(btnDelete)

        // ===== TIMER =====
        val timerText = TextView(this).apply {
            text = "%02d:00".format(workMin)
            textSize = 24f
            gravity = Gravity.CENTER
        }

        // ===== BUTONLAR =====
        val btnStart = Button(this).apply { text = "Başlat" }
        val btnStop = Button(this).apply { text = "Durdur" }
        val btnReset = Button(this).apply { text = "Sıfırla" }
        val btnBreak = Button(this).apply { text = "Mola" }

        val buttonRow = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER
        }

        buttonRow.addView(btnStart)
        buttonRow.addView(btnStop)
        buttonRow.addView(btnReset)
        buttonRow.addView(btnBreak)

        card.addView(header)
        card.addView(timerText)
        card.addView(buttonRow)
        lessonContainer.addView(card)

        // ===== TIMER MANTIĞI =====
        var timer: CountDownTimer? = null
        val workMillis = workMin * 60 * 1000
        val breakMillis = breakMin * 60 * 1000
        var timeLeft = workMillis
        var totalWorked = 0L
        var lastStart = 0L

        fun updateText(time: Long) {
            val m = time / 1000 / 60
            val s = (time / 1000) % 60
            timerText.text = String.format("%02d:%02d", m, s)
        }

        fun startTimer(duration: Long) {
            lastStart = System.currentTimeMillis()
            timer?.cancel()
            timer = object : CountDownTimer(duration, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    timeLeft = millisUntilFinished
                    updateText(millisUntilFinished)
                }

                override fun onFinish() {
                    totalWorked += System.currentTimeMillis() - lastStart
                    timerText.text = "Bitti"
                }
            }.start()
        }

        btnStart.setOnClickListener { startTimer(timeLeft) }

        btnStop.setOnClickListener {
            timer?.cancel()
            totalWorked += System.currentTimeMillis() - lastStart
        }

        btnReset.setOnClickListener {
            timer?.cancel()
            timeLeft = workMillis
            updateText(timeLeft)
        }

        btnBreak.setOnClickListener {
            startTimer(breakMillis)
        }

        // ===== SİL (UYARI İLE) =====
        btnDelete.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Dersi Sil")
                .setMessage("Bu dersi silmek ister misiniz?")
                .setPositiveButton("Evet") { _, _ ->
                    timer?.cancel()
                    addSummary(name, totalWorked)
                    lessonContainer.removeView(card)
                }
                .setNegativeButton("Hayır", null)
                .show()
        }
    }

    // ===== ÇALIŞILAN DERSLER =====
    private fun addSummary(name: String, time: Long) {
        val minutes = time / 1000 / 60
        val text = TextView(this)
        text.text = "• $name → $minutes dk"
        summaryContainer.addView(text)
    }
}
