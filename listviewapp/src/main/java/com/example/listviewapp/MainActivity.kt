package com.example.listviewapp

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val listView: ListView = findViewById(R.id.listViewBooks)

        // Список книг и авторов
        val books = listOf(
            Book("Дмитрий Глуховский", "Метро 2033"),
            Book("Сергей Лукьяненко", "Черновик"),
            Book("Антуан де Сент-Экзюпери", "Маленький принц"),
            Book("Роберт Кийосаки", "Богатый папа, бедный папа"),
            Book("Эрих Мария Ремарк", "Три товарища"),
            Book("Анджей Сапковский", "Ведьмак"),
            Book("Джордж Р. Р. Мартин", "Игра престолов"),
            Book("Федор Достоевский", "Преступление и наказание"),
            Book("Лев Толстой", "Война и мир"),
            Book("Стивен Кинг", "Оно"),
            Book("Говард Лавкрафт", "Зов Ктулху"),
            Book("Ричард Матесон", "Я – легенда"),
            Book("Джон Толкин", "Властелин колец"),
            Book("Даниэль Дефо", "Робинзон Крузо"),
            Book("Джордж Оруэлл", "1984"),
            Book("Михаил Булгаков", "Мастер и Маргарита"),
            Book("Борис Пастернак", "Доктор Живаго"),
            Book("Франц Кафка", "Замок"),
            Book("Эрнест Хемингуэй", "Старик и море"),
            Book("Даниэл Киз", "Цветы для Элджернона"),
            Book("Виктор Пелевин", "Generation П"),
            Book("Кир Булычев", "Сто лет тому вперед"),
            Book("Рей Брэдбери", "451 градус по Фаренгейту"),
            Book("Айзек Азимов", "Основание"),
            Book("Аркадий и Борис Стругацкие", "Пикник на обочине"),
            Book("Филип Дик", "Мечтают ли андроиды об электроовцах?"),
            Book("Эдгар Аллан По", "Собрание сочинений"),
            Book("Марк Твен", "Приключения Тома Сойера"),
            Book("Чарльз Диккенс", "Оливер Твист"),
            Book("Агата Кристи", "Десять негритят"),
            Book("Умберто Эко", "Имя розы"),
        )

        // Устанавливаем адаптер для ListView
        val adapter = BookAdapter(this, books)
        listView.adapter = adapter
    }
}