# Reyhan Zada Virgiwibowo

## 2206081723

## Pemrograman Lanjut - C

## <a href="https://eshop-lontonggg.koyeb.app/product/list">https://eshop-lontonggg.koyeb.app/product/list</a>

<details>
<summary><b><h2>Modul 1</h2></b></summary>

## Refleksi 1

Dalam pembuatan dua fitur baru yaitu Edit Product dan Delete Product, saya telah mengimplementasikan prinsip *Clean Coding* dengan mengikut beberapa standar utama *Clean Coding* dimulai dengan penamaan variabel yang jelas dan mudah dimengerti, dimana nama-nama variable yang saya gunakan bernama sesuai dengan tujuan dan fungsi dari variable tersebut. 
Selanjutnya adalah pembuatan fungsi-fungsi pada fitur Edit Product dan Delete Product yang terstruktur dengan baik dan berkerja untuk menyelesaikan task sesuai dengan nama fungsi itu sendiri. Dan yang terakhir adalah penerapan *Secure Coding* dengan menggunakan ID yang random dan unik untuk setiap product.

## Refleksi 2

Setelah menerapkan *Unit Test*, saya menjadi lebih mengerti dan percaya bahwa unit model beserta fungsionalitas kode saya telah dibuat dengan baik dan terhindar dari bugs atau error-error lainnya. Menurut saya, tidak ada batas untuk banyaknya unit test yang dibuat. Semakin banyak case untuk unit test, maka akan unit akan semakin terverifikasi keberhasilannya dan 
terhindar dari segala kemungkinan bugs dan error-error lainnya. *Code Coverage* adalah alat ukur utama untuk mengukur usaha pengujian pada code yang sudah di-develop sebelumnya. Meskipun sebuah kode memiliki 100% *code coverage*, belum tentu kode tersebut terbebas dari errors dan bugs. Menurut saya, *Code Coverage* juga bergantung terhadap seberapa banyak bugs dan errors case yang sudah dicover oleh testing tersebut.

Pembuatan *functional test* yang baru ini menurunkan kualitas *clean code*. Hal ini disebabkan munculnya potensi repetisi dalam kode fungsi yang kita buat, terutama ketika tes ini memiliki setup procedures dan instance variables yang sama dengan tes yang sebelumnya, serta munculnya potensi untuk melanggar *Single Responsibility Principle*. Seharusnya test baru ini dapat digabungkan kedalam satu file yang sama dengan satu file *Controller* untuk memperkecil kemungkinan repetisi dan meningkatkan
kualitas *clean code*.

</details>

<details>
<summary><b><h2>Modul 2</h2></b></summary>

## Refleksi

## List dari Code Issues

Dengan bantuan SonarCloud, terdapat beberapa issues yang terdeteksi dan berikut adalah list code issues yang telah saya perbaiki.

### 1. Penulisan dependencies pada file build.gradle.kts

SonarCloud mendeteksi bahwa penulisan dependency pada file `build.gradle.kts` berantakan dan tidak teratur. Seharusnya penulisan dependency-dependency pada file `build.gradle.kts` dikelompokkan sesuai destinationnya agar dependencies menjadi lebih readable dan maintainable. 

Berikut adalah before and after dari file `build.gradle.kts` :

Before :

```
dependencies{
    implementation("org.springframework.boot:spring-boot-starter-web")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    testImplementation("org.seleniumhq.selenium:selenium-java:$seleniumJavaVersion")
    testImplementation("io.github.bonigarcia:selenium-jupiter:$seleniumJupiterVersion")
    testImplementation("io.github.bonigarcia:webdrivermanager:$webdrivermanagerVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
}
```

After :

```
dependencies{
  implementation("org.springframework.boot:spring-boot-starter-web")
  implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
  compileOnly("org.projectlombok:lombok")
  developmentOnly("org.springframework.boot:spring-boot-devtools")
  annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
  annotationProcessor("org.projectlombok:lombok")
  testImplementation("org.springframework.boot:spring-boot-starter-test")
  testImplementation("org.seleniumhq.selenium:selenium-java:$seleniumJavaVersion")
  testImplementation("io.github.bonigarcia:selenium-jupiter:$seleniumJupiterVersion")
  testImplementation("io.github.bonigarcia:webdrivermanager:$webdrivermanagerVersion")
  testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
  testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
}
  
```

### 2. Penggunaan anotasi `@Autowired` pada ProductController dan ProductServiceImpl

SonarCloud mendeteksi bahwa terdapat field injection menggunakan anotasi `@Autowired` pada file `ProductController.java` dan `ProductServiceImpl.java`. SonarCloud tidak menyarankan menggunakan field injection. Hal tersebut disebabkan oleh munculnya kemungkinan pembuatan objek dalam keadaan tidak valid dan dapat membuat testing menjadi lebih sulit yang juga disebabkan oleh dependency yang tidak eksplisit saat menginisialisasi sebuah kelas yang menggunakan field injection. Dengan demikian, penggunaan anotasi `@Autowired` saya ubah untuk diinject ke constructor. 

Berikut adalah before and after dari file `ProductController.java` :

Before :

```java
...
public class ProductController {
  @Autowired
  private ProductService service;

...
```

After :

```java
...
public class ProductController{
  private final ProductService service;

  @Autowired
  public ProductController(ProductService service){
    this.service = service;
  }

...
```

### 3. Unnecessary modifier pada interface ProductService

SonarCloud juga mendeteksi bahwa terdapat unnecessary modifier pada interface ProductService. Modifier public tidak diperlukan karena dalam interface sudah secara default bersifat public. 

Berikut adalah before and after dari file `ProductService.java` :

Before :

```java
public interface ProductService {
    public Product create(Product product);
    public List<Product> findAll();
    public Product findProductById(String id);
    public void edit(Product currentProduct, Product editedProduct);
    public void delete(Product product);
}
```

After :


```java
public interface ProductService {
    Product create(Product product);
    List<Product> findAll();
    Product findProductById(String id);
    void edit(Product currentProduct, Product editedProduct);
    void delete(Product product);
}
```

Berikut adalah hasil analisis dari SonarCloud setelah memperbaiki issue-issue diatas :
<img width="800" alt="Screenshot 2024-02-14 181010" src="https://github.com/lontonggg/adpro-tutorial/assets/124903418/fff6fa40-2171-4878-8d9c-be935a494744">

## Implementasi CI/CD

Menurut saya, saya telah menerapkan workflows CI/CD dengan baik pada proyek saya. Saya berhasil membuat dan menjalankan workflow pada proyek saya yaitu `ci.yml`, `scorecard.yml`, dan `sonarcloud.yml` dengan bantuan Github Actions. Workflows tersebut akan dijalankan secara otomatis ketika terjadi push atau pull request. Proses testing dalam workflow CI (Continuous Integration) ini melibatkan langkah-langkah seperti checkout code, setup Java toolchain, dan eksekusi unit tests. Selain itu dengan adanya tambahan SonarCloud menghasilkan pengujian keamanan dan analisis kode yang lebih mendalam. Setelah berhasil menerapkan CI dengan baik, selanjutnya saya menerapkan CD (Continuous Deployment) dengan menggunakan `Koyeb` sebagai platform yang akan mendeploy aplikasi saya secara otomatis ketika terjadi push atau pull request.

</details>

<details>
<summary><b><h2>Modul 3</h2></b></summary>

## Refleksi

### Prinsip SOLID yang saya aplikasikan

Pada proyek ini, saya telah mengimplementasikan `Single Responsibility Principle (SRP)`, `Open-Closed Principle (OCP)`, dan juga `Dependency Inversions Principle (DIP)`. 

#### Single Responsibility Principle (SRP)
Prinsip SRP menyatakan bahwa sebuah class atau module sebaiknya hanya memiliki satu tanggung jawab dalam sebuah class atau modul. Saya mengimplementasikan SRP dengan memisahkan `CarController` dan `ProductController` ke 2 file class yang berbeda yaitu `CarController.java` dan `ProductController.java`. 

#### Open-Closed Principle (OCP)
OCP adalah prinsip yang menyatakan bahwa suatu class harus bisa diekstensi dan tidak boleh dimodifikasi. Saya mengimplementasikan OCP dengan membuat interface `RepositoryInterface` sebagai Repository utama. Kemudian, untuk membuat repository untuk Product, Car, atau model lainnya dapat dilakukan dengan membuat class baru seperti `ProductRepository` dan `CarRepository` yang mengextends/implements `RepositoryInterface` tanpa perlu memodifikasi Repository utama. 

#### Dependency Inversions Principle (DIP)
Terakhir, DIP adalah prinsip yang menyatakan bahwa sebuah entitas itu seharusnya bergantung pada abstraksi, dan high-level module tidak boleh bergantung pada low-level module, akan tetapi bergantung kepada abstraksi. Saya mengimplementasikan DIP dengan mengubah dependency kepada repository yang digunakan pada `CarServiceImpl` dan `ProductServiceImpl` yang awalnya bergantung pada low-level module `CarRepository` dan `ProductRepository` menjadi bergantung pada high-level module yang berupa interface RepositoryInterface. Selain itu, saya juga mengimplementasikan DIP dengan mengubah `CarController` yang awalnya memiliki dependensi kepada low-level module `CarServiceImpl` menjadi dependen kepada interface `CarService`.

### Kelebihan mengaplikasikan prinsip SOLID
Salah satu kelebihan mengaplikasikan prinsip SOLID adalah memudahkan developer lain untuk melakukan development dan maintenance. Contohnya, dengan memisahkan Controller untuk Car dan Product, developer dapat melakukan development dan maintenance dengan mudah karena untuk setiap model memiliki controller yang terpisah sesuai dengan tujuan dan fungsionalitasnya masing-masing. Contoh lain, jika developer ingin membuat repository baru untuk sebuah model baru, developer dapat langsung membuat class baru yang mengimplements RepositoryInterface tanpa perlu memodifikasi Repository Utama sehingga memperkecil kemungkinan munculnya permasalahan pada repository lainnya jika terjadi error pada pembuatan repository yang baru.

### Kekurangan tidak mengaplikasikan prinsip SOLID
Kekurangan dengan tidak mengaplikasikan prinsip SOLID adalah tingginya potensi untuk terjadi permasalahan error dan sulit untuk melakukan maintenance. Misalnya, seorang developer ingin menambahkan fitur pada repository tetapi tidak mengikuti prinsip OCP dengan langsung memodifikasi file Repository utama, jika terjadi kesalahan saat modifikasi, keseluruhan repository dapat bermasalah dan membuat proyek tidak dapat berjalan. Hal ini dapat dihindari jika developer membuat class baru yang mengextend Repository utama dan membuat fitur pada class tersebut tanpa mengubah Repository utama.

    
</details>
