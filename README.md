# Reyhan Zada Virgiwibowo

# 2206081723

<details>
<summary><b><h2>Tutorial 1</h2></b></summary>

## Refleksi 1

Dalam pembuatan dua fitur baru yaitu Edit Product dan Delete Product, saya telah mengimplementasikan prinsip *Clean Coding* dengan mengikut beberapa standar utama *Clean Coding* dimulai dengan penamaan variabel yang jelas dan mudah dimengerti, dimana nama-nama variable yang saya gunakan bernama sesuai dengan tujuan dan fungsi dari variable tersebut. 
Selanjutnya adalah pembuatan fungsi-fungsi pada fitur Edit Product dan Delete Product yang terstruktur dengan baik dan berkerja untuk menyelesaikan task sesuai dengan nama fungsi itu sendiri. Dan yang terakhir adalah penerapan *Secure Coding* dengan menggunakan ID yang random dan unik untuk setiap product.

## Refleksi 2

Setelah menerapkan *Unit Test*, saya menjadi lebih mengerti dan percaya bahwa unit model beserta fungsionalitas kode saya telah dibuat dengan baik dan terhindar dari bugs atau error-error lainnya. Menurut saya, tidak ada batas untuk banyaknya unit test yang dibuat. Semakin banyak case untuk unit test, maka akan unit akan semakin terverifikasi keberhasilannya dan 
terhindar dari segala kemungkinan bugs dan error-error lainnya. *Code Coverage* adalah alat ukur utama untuk mengukur usaha pengujian pada code yang sudah di-develop sebelumnya. Meskipun sebuah kode memiliki 100% *code coverage*, belum tentu kode tersebut terbebas dari errors dan bugs. Menurut saya, *Code Coverage* juga bergantung terhadap seberapa banyak bugs dan errors case yang sudah dicover oleh testing tersebut.

Pembuatan *functional test* yang baru ini menurunkan kualitas *clean code*. Hal ini disebabkan munculnya potensi repetisi dalam kode fungsi yang kita buat, terutama ketika tes ini memiliki setup procedures dan instance variables yang sama dengan tes yang sebelumnya, serta munculnya potensi untuk melanggar *Single Responsibility Principle*. Seharusnya test baru ini dapat digabungkan kedalam satu file yang sama dengan satu file *Controller* untuk memperkecil kemungkinan repetisi dan meningkatkan
kualitas *clean code*.

</details>
