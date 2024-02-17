# Tutorial 2
link web app: https://eshop-dhafinfk.koyeb.app/

[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=DhafinFK_tutorial-1&metric=coverage)](https://sonarcloud.io/summary/new_code?id=DhafinFK_tutorial-1)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=DhafinFK_tutorial-1&metric=bugs)](https://sonarcloud.io/summary/new_code?id=DhafinFK_tutorial-1)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=DhafinFK_tutorial-1&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=DhafinFK_tutorial-1)


## List Quality Code Issues

### 1. Renaming bad function names
Dari kode sebelumnya pmd scanner memberikan peringatan bahwa masih ada beberapa penamaan function yang tidak menggunakan
camel case jadi saya ganti menggunakan camel case sesuai dengan best practice java.

### 2. Removing public access modifier in interface class
Dari hasil analisis pmd method-method pada interface sudah public jadi saya tidak perlu memberikan public modifier lagi
ke method method dalam interface

### 3. Removing unnecessary imports
Dari hasil analisis pmd ternyata dari kode original masih terdapat beberapa import yang tidak digunakan
sehingga saya menghapus import tersebut untuk mengurangi kotornya kode

## Current implementation of CI/CD
Menurut saya repository github saya sudah memenuhi konsep dasar dari CI/CD dimana codebase saya setiap kali terjadi
perubahan akan melakukan sebuah workflow yang memeriksa integrasi kode baru dan melakukan deploy ulang atau CI/CD.

# Reflection 1
## Implementasi Clean Code Practice Pada Tugas:

### 1. Meaningful Names
* Memberikan nama-nama pada variabel yang _self explanatory_ yaitu dengan nama-nama yang sesuai dengan  kegunaan dan
konten dari variabel. Contohnya seperti variabel yang diberi nama `productData`, `productId`, `productName`, dan 
lain-lain
* Menggunakan nama-nama pada _function_ yang sesuai dengan kegunaannya. Terutama pada _funtion_ yang terkait dengan
operasi data pada Repository seperti `create`, `delete`, dan `edit`. Tiap nama function yang telah disebut merupakan
oprasi pada model `Product` yang sesuai dengan namanya.

### 2. Function
* Function yang telah disebut pada poin 1. Meaningful Names hanya melakukan satu hal dan independen dari funcion-
function lain seperti sebagaimana prinsip "_Function should do one thing. They should do it well. They should do it 
only_". 
* Ketiga function `create`, `delete`, dan `edit` ditulis dengan singkat sesuai dengan prinsip "_Functions should be 
small_".
* Function-function yang dibuat juga saling indpenden sehingga tidak memberikan efek pada hal-hal lain dalam projek 
sesuai dengan prinsip "_Functions should have no side effects_".

### 3. Comments
* Kode yang dibuat belum mengandung comment karena semua kode yang sudah ditulis cukup _self explanatory_ sehingga
bila ditambahkan comment hanya akan membuat kode semakin ramai dan mempersulit pemahaman. Sesuai dengan prinsip 
"_Don't comment bad code, rewrite it_"

## Perbaikan kode:
* Menambahkan sistem pemberian Id pada produk kedalam variabel productId yang sebelumnya tidak di set sama sekali 
di program

# Reflection 2
## Implementasi unit test pada project
* Setelah menulis berbagai unit test pada program saya merasa bahwa automasi test sangat bermanfaat dalam pengembangan
suatu program atau project. Karena kita bisa memastikan kode kita sudah siap untuk digunakan sesuai kebutuhannya dan
bahkan kemampuan program mengatasi perilaku aneh.
* Sebenarnya tidak ada definisi baku suatu kumpulan test sudah cukup untuk menguji program kita. Tetapi paling minimal
dapat meng-_handle_ perilaku umum dengan baik
* 100% code coverage bukan berarti kode sudah _fool-proof_ kode akan selalu memiliki bug. 100% code coverage hanya
berarti kode yang kita tulis telah lulus pengujian sampai kasus-kasus tertentu

## Impelementasi functional test pada project
Menurut saya membuat functional test yang mirip dengan functional test lain yang telah dibuat akan menimbulkan sebuah
rasa "untuk apa?". Karena:
* Kode yang melakukan testing yang sama berarti kode tersebut ditulis ulang dan hanya akan mengotori kode dengan
melanggar prinsip DRY.
* Kode yang melakukan testing yang sama dan ditambah kompleksitasnya akan mengotori kumpulan testing yang seharusnya
bisa dibaca.

