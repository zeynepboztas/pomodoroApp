# Pomodoro Ders Takibi

## Proje Hakkında
Pomodoro Ders Takibi, öğrencilerin ders çalışma süreçlerini daha verimli hâle getirmek amacıyla geliştirilmiş bir Android mobil uygulamasıdır. Uygulama, Pomodoro çalışma tekniğini temel alarak kullanıcıların derslerini planlamasına, çalışma ve mola sürelerini takip etmesine olanak tanır.

Bu proje, Android Studio geliştirme ortamında Kotlin programlama dili kullanılarak geliştirilmiştir.

---

## Proje Sahipleri
- **Zeynep Boztaş** – 23010207012  
- **Beren Kolay** – 23010207034  

---

## Kullanılan Teknolojiler
- **Programlama Dili:** Kotlin  
- **Geliştirme Ortamı:** Android Studio  
- **Arayüz Tasarımı:** XML  
- **Zamanlayıcı:** CountDownTimer  
- **Tasarım Bileşenleri:** Material Design, AndroidX  

---

## Uygulama Özellikleri
- Kullanıcı giriş ekranı
- Ders adı, çalışma süresi ve mola süresi ekleme
- Pomodoro tekniğine uygun geri sayım zamanlayıcısı
- Çalışma, mola, durdurma ve sıfırlama işlemleri
- Çalışılan derslerin ve sürelerin özetlenmesi
- Dinamik ders kartları oluşturma

---

## Proje Yapısı
app/
└── src/
└── main/
├── java/com/example/pomodoroapp/
│ ├── MainActivity.kt
│ ├── LoginActivity.kt
│ └── ResetpasswordActivity.kt
├── res/layout/
│ ├── activity_main.xml
│ ├── activity_login.xml
│ └── ...
└── AndroidManifest.xml

yaml
Kodu kopyala

---

## Kurulum ve Çalıştırma
1. Bu repoyu bilgisayarınıza klonlayınız:
   ```bash
   git clone https://github.com/kullaniciadi/pomodoro-ders-takibi.git
Android Studio ile projeyi açınız.

Gerekli bağımlılıkların yüklenmesini bekleyiniz.

Emulator veya fiziksel cihaz üzerinde uygulamayı çalıştırınız.

Proje Amacı
Bu projenin amacı, öğrencilerin zaman yönetimini iyileştirmek, düzenli çalışma alışkanlığı kazandırmak ve ders takip süreçlerini kolaylaştıran kullanıcı dostu bir mobil uygulama geliştirmektir.
