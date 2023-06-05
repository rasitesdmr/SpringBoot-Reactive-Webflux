# 🎯 Reactive Programlama

* Reactive programlama, geleneksel programlama yaklaşımlarının aksine, asenkron veri akışlarına ve gerçek zamanlı
  olaylara dayanan bir yazılım geliştirme paradigmasıdır.

* Bu yaklaşımın temel özelliği, işlemlerin birbirlerini beklemeksizin, yani asenkron bir şekilde yürütülmesidir.

* Bu, reactive programlama modelinin, herhangi bir duraklama olmaksızın çok sayıda işlemi aynı anda çalıştırabilme
  kapasitesini ortaya koyar.

* Modern yazılım uygulamaları, çok sayıda eşzamanlı talebi hızlı ve etkin bir şekilde karşılayabilme kabiliyetine sahip
  olmalıdır.

* Bu, geleneksel yöntemlerin zaman zaman yetersiz kaldığı durumları beraberinde getirir.

* Bu durumlarla başa çıkmak için, donanım kaynaklarını en verimli şekilde kullanmayı amaçlayan yazılım mühendisliği
  prensipleri gerekmektedir.

* Günümüzde microservisler, cloud-native uygulamalar ve distrubuted sistemler oldukça yaygın, resourceları daha iyi
  kullanmak ve low latency (düşük bekleme süreleri) ile birlikte responsive (hızlı tepki verebilmeyi) olmayı istiyor.

| Kavramlar     | Anlamları                                                                                                                       |
|---------------|---------------------------------------------------------------------------------------------------------------------------------|
| `Asenkron`    | Bir işlem yapılırken o işlemin sonucu beklenmeden başka bir işlem yapabilmeye yani eşzamansız işlemlere denir.                  |
| `Data Stream` | Zaman içinde sırayla gerçekleşen bir event/olay dizisidir. Streamler bize değerler, hatalar ve tamamlandı gibi sinyaller verir. |

* Reactive programlama, bir veri akışını (stream) dinler ve bu akıştaki olaylara (event) yanıt verir. Bu tepkisel
  yaklaşım, yazılımın belirli durumlara hızlı ve etkin bir şekilde yanıt vermesini sağlar. Bu sürecin altında yatan ana
  mekanizma ise Observer (Gözlemci) Tasarım Şablonudur.

* Observer Design Pattern, bir nesnenin durumundaki değişikliklerin otomatik olarak ilgili bağımlı nesnelerine
  bildirilmesi ve güncellenmesi için kullanılan bir tasarım kalıbıdır

* Bu modelde genellikle iki tür nesne bulunur: "Subject" (Konu) ve "Observer" (Gözlemci). Subject, durumu değişen ve bu
  durumu observerlara bildiren nesnedir. Observerlar ise bu değişikliklere tepki veren nesnelerdir.

* Observer Design Pattern, reactive programlama için idealdir çünkü bir stream üzerinde gerçekleşen değişikliklerin
  hızlı bir şekilde izlenmesi ve işlenmesi gerekmektedir.

* Observer'lar, bir stream'deki olayları dinler ve gerektiğinde hemen tepki verir. Bu da reactive programlamanın
  çekirdeğinde yer alan asenkron ve olay tabanlı yaklaşımı temsil eder.

* Örneğin, bir sosyal medya uygulamasında kullanıcılar, belirli bir hashtagi takip edebilirler (observer olurlar). Bu
  hashtag üzerinde yeni bir gönderi olduğunda (event), takip eden kullanıcılar (observerlar) otomatik olarak bildirim
  alır. Bu durum, Observer Tasarım Şablonunun ve Reactive Programlamanın günlük yaşamdaki bir örneğidir.

* Kod örneği : [observerDesignPattern](./src/main/java/observerDesignPattern)

* Reactive programlamayı anlamak için önce geleneksel programlama nedir ona bakalım.

# 🎯 Geleneksel Programlama

* İstekler bir thread-pool içinden her bir isteğe bir thread atanarak yürütülüyor.

* Ancak thread pool kapasitesi kadar istek işlenebiliyor.

* Bu yöntemde her thread blocking anlamına geliyor ve bu da bekleme, daha çok kaynak kullanımı gibi durumlara yol
  açabiliyor.

* Geleneksel imperative programlanan uygulamaların genel özellikleri:

| Kavramlar                  | Anlamları                                                                                                                                                                                                        |
|----------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `Senkron ve Blocking`      | İstekler sekron ve blocking olarak işleniyor. İstek yapıldığında bir thread herhangi bir I/O işlemi bloklandığında onu bekler. Thread başka işler için bloke durumda olur ve işlem tamamlanınca cevap dönebilir. |
| `İstek başına bir thread ` | İstek başına bir thread ayrılması aynı zamanda gelen istek sayısını sınırlar. Thread pool kadar istek alınabilir hale gelir. Aynı zamanda çok istek gelmesi performansı da etkileyebilir.                        |
| `Data Stream`              | Zaman içinde sırayla gerçekleşen bir event/olay dizisidir. Streamler bize değerler, hatalar ve tamamlandı gibi sinyaller verir.                                                                                  |

* Tabloya eklediğim Blocking yapısı ve eklemediğim Backpressure yapılarına değinelim

## Backpressure

* Reaktif kodla çalışırken, sıkça "backpressure" terimine rastlarız.

* Bu, verinin istenen akışına direnen veya karşı çıkan direnç veya kuvvet anlamına gelen sıvı dinamiklerinden türetilmiş
  bir benzetmedir.

* Reaktif Akışlarda, backpressure veri iletimini akışlar arasında düzenleme mekanizmasını tanımlar.

* Server A'nın her saniye 1000 olay (events per second - EPS) gönderdiğini, ancak Server B'nin yalnızca 800 EPS
  işleyebildiğini ve bu nedenle 200 EPS açığı olduğunu düşünün.

* Server B şimdi, açık verileri işlemek ve onları aşağıya doğru göndermek veya belki de bir veritabanında saklamak
  zorunda olduğu için geride kalma eğilimindedir.

* Böylece, Server B backpressure ile uğraşır ve kısa sürede bellekten düşer ve başarısız olur.

* Dolayısıyla, bu backpressure aşağıdaki seçenekler veya stratejiler ile ele alınabilir veya yönetilebilir:

| Kavramlar  | Açıklamaları                                                                                                                                                                                                                   |
|------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `Buffer `  | Açık verileri kolayca ara bellekte saklayabilir ve sunucunun kapasitesi olduğunda daha sonra işleyebiliriz. Ancak büyük miktarda verinin gelmesi ile birlikte, bu buffer artabilir ve sunucu kısa sürede bellekten düşebilir.  |
| `Drop  `   | Olayların işlenmemesi, yani düşürülmesi son seçenek olmalıdır. Genellikle, veri örnekleme kavramını ara bellekleme ile birleştirerek daha az veri kaybını gerçekleştirebiliriz.                                                |
| `Kontrol ` | Veriyi gönderen üreticiyi kontrol etme kavramı şimdiye kadar en iyi seçenektir. Reaktif Akışlar, üretilen ve tüketiciye gönderilen veriyi kontrol etmek için hem itme hem de çekme tabanlı akışlarda çeşitli seçenekler sunar. |

* Buffer'ı hatırlayalım : Buffer, ya da Türkçesiyle ara bellek, genellikle bilgisayar hafızasının bir bölümünü temsil
  eder ve geçici olarak veri saklamak için kullanılır. Bu mekanizma, veri üreten bir kaynak ve veriyi işleyen bir hedef
  arasındaki hız farkını dengelemeye yardımcı olur.Backpressure kontekstinde, buffer mekanizması, bir kaynaktan gelen
  verinin hedef tarafından işlenebilmesi için bir ara bellekte saklanmasını sağlar. Örneğin, hızlı bir şekilde veri
  üreten bir kaynak (server A) ve bu veriyi daha yavaş bir hızda işleyen bir hedef (server B) düşünün. Buffer, server
  B'nin daha yavaş hızına ayak uydurmak için gelen verinin bir kısmını "tutarak" server A'nın tüm veriyi hemen
  göndermesini engeller.Ancak, ara bellekte saklanan verinin miktarı kontrolsüz bir şekilde artarsa, sunucu belleğinin
  tamamen dolması ve sonunda hafıza hatası vermesi durumu söz konusu olabilir. Bu yüzden, buffer stratejisi dikkatli bir
  şekilde yönetilmeli ve uygun boyut sınırlamaları belirlenmelidir.

* Gelin backpressure görsel olarak bakalım.

<p align ="center">
<img src = "https://github.com/rasitesdmr/SpringBoot-Reactive-Webflux/blob/master/SpringBoot-Reactive/images/r6.png">
</p>

* Sistem, Üretici (Publisher), Tüketici (Consumer) ve Grafiksel Kullanıcı Arayüzü (GUI) olmak üzere üç servis içerir.

* Üretici, her saniye Tüketiciye 10000 olay gönderir.

* Tüketici, bu olayları işler ve sonucu GUI'ye gönderir

* GUI, sonuçları kullanıcılara gösterir

* Tüketici, sadece her saniye 7500 olayı işleyebilir

* Bu hız oranında, tüketici olayları yönetemez (backpressure). Sonuç olarak, sistem çöker ve kullanıcılar sonuçları
  görmezler.

<p align ="center">
<img src = "https://github.com/rasitesdmr/SpringBoot-Reactive-Webflux/blob/master/SpringBoot-Reactive/images/r7.png">
</p>

* Gönderilen veri akışını kontrol etmek ilk seçenektir. Temelde, yayıncının olayların hızını yavaşlatması gerekmektedir.
  Bu sayede tüketici aşırı yüklenmez. Ne yazık ki, bu her zaman mümkün olmayabilir ve diğer mevcut seçenekleri bulmamız
  gerekir.

* Ekstra veri miktarını ara bellekte tutmak ikinci tercihtir. Bu yaklaşımla, tüketici kalan olayları geçici olarak
  saklar, ta ki onları işleyebilir hale gelene kadar. Buradaki ana dezavantaj, ara belleği serbest bırakmanın hafızada
  çöküşe neden olmasıdır.

* Ekstra olayları düşürmek ve onların izini kaybetmek. Bu çözüm ideal olmaktan uzak olsa da, bu teknikle sistem
  çökmeyecektir.

<p align ="center">
<img src = "https://github.com/rasitesdmr/SpringBoot-Reactive-Webflux/blob/master/SpringBoot-Reactive/images/r8.png">
</p>

* Yeni olayları sadece abone onları talep ettiğinde gönderin. Bu, yayıcı isteğinde elemanları toplamak için bir çekme
  stratejisidir.

* Alıcı tarafında alınacak olayların sayısını sınırlayın. Sınırlı bir itme stratejisi olarak çalışan yayıncı, bir
  seferde müşteriye sadece maksimum miktarda öğe gönderebilir.

* Tüketicinin daha fazla olayı işleyemeyeceği durumlarda veri akışını iptal edin. Bu durumda, alıcı, herhangi bir
  zamanda iletimi iptal edebilir ve daha sonra tekrar akışa abone olabilir.

* Görsel olarakda göz attık şimdi Blocking yapıya göz atalım

## 📌 Blocking Request

<p align ="center">
<img src = "https://github.com/rasitesdmr/SpringBoot-Reactive-Webflux/blob/master/SpringBoot-Reactive/images/r1.png">
</p>

 ```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```

* Geleneksel Model-View-Controller (MVC) mimarisinde, bir istemci bir istekte bulunduğunda, bu istek bir Servlet
  container tarafından işlenir.

* Her istek için, bir iş parçacığı (genellikle bir servlet iş parçacığı olarak adlandırılır) ayrılır ve talep boyunca
  gereken tüm işlemler için sorumludur.

* Bu iş parçacığı, istek boyunca çeşitli işlemler gerçekleştirir, örneğin bir veritabanından veri okuma, bir dosyadan
  bilgi okuma veya bir ağ hizmetine bağlanma gibi.

* Bu işlemler genellikle I/O ağırlıklı işlemlerdir ve genellikle belirli bir süre beklemeyi gerektirirler, çünkü
  veritabanından veri getirmek veya bir ağ hizmetine bağlanmak genellikle biraz zaman alır.

* Bu bekleyen zaman süresince, servlet iş parçacığı bloke olur.

* Yani, daha fazla işlem yapamaz veya başka bir isteği işleyemez.

* Bu, bir tür kaynak kullanımı açısından verimsizliktir, çünkü iş parçacığı, çoğunlukla bekleme durumunda, belirli bir
  süre boyunca meşguldür ve bu süre zarfında başka bir işlem yapamaz.

* Bunun ötesinde, bu blokaj durumu, büyük ölçekli bir uygulamada, aynı anda çok sayıda isteği işlemeyi zorlaştırabilir.

* Çünkü bir sunucunun aynı anda aktif olarak işleyebileceği iş parçacığı sayısı genellikle sınırlıdır.

* Bu durum, bir iş parçacığının bloke olması ve diğer isteklerin işlenmesi için kullanılamaması durumunda, diğer
  isteklerin iş parçacığının mevcut olmasını beklemesi gerekeceği anlamına gelir.

* Bu, eşzamanlı istek işlemeye engel olabilir ve genel olarak uygulamanın yanıt süresini ve ölçeklenebilirliğini olumsuz
  etkileyebilir.

### 💻 Kod Üzerinde Deneyelim

```java

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("")
    public List<User> getAllUser() {
        System.out.printf("Thread %s Started ---- Count : %s%n", Thread.currentThread().getId(), a++);
        var a = userService.getAllUsers();
        System.out.printf("Thread %s Stop %n", Thread.currentThread().getId());
        return a;
    }
}
```

* Veritabanına kayıtlı 10000 tane kullanıcım var ve burada kullanıcıları çekiyorum.

* Asıl önemli kısma gelelim.

* spring-boot-starter-web bağımlılığı tomcat'i kullanır ve default yapılandırmasında iş parçacığı havuzunda aynı anda
  200 iş parçacığı oluşturur.

* Buda demek oluyor ki ben default ayarlarda aynı anda sadece 200 istek atabilirim. Eğer aynı anda 300 tane istek
  atsaydım, 200 thread tetiklenecek ve geri kalan 100 istek beklemeye geçecek.

* Bunu daha iyi anlayabilmek adına JMeter kullandım.

* Daha iyi görebilmek adına default iş parçacık sayımı 2 yaptım.

 ```properties
server.tomcat.threads.max=2
```

<p align ="center">
<img src = "https://github.com/rasitesdmr/SpringBoot-Reactive-Webflux/blob/master/SpringBoot-Reactive/images/r2.png">
</p>

* İsteğimin yolunu belirtiyorum

<p align ="center">
<img src = "https://github.com/rasitesdmr/SpringBoot-Reactive-Webflux/blob/master/SpringBoot-Reactive/images/r3.png">
</p>

* Aynı anda kaç tane istek atacağımı belirtiyorum

* İsteği çalıştırdığım zaman konsol çıktım şu şekilde:

 ```text
Thread 38 Started ---- Count : 16
Thread 37 Started ---- Count : 17
Thread 38 Stop 
Thread 38 Started ---- Count : 18
Thread 37 Stop 
Thread 37 Started ---- Count : 19
Thread 38 Stop 
Thread 38 Started ---- Count : 20
Thread 37 Stop 
Thread 37 Started ---- Count : 21
Thread 38 Stop 
Thread 38 Started ---- Count : 22
Thread 37 Stop 
Thread 37 Started ---- Count : 23
Thread 38 Stop 
```

* Burada görüldüğü gibi aynı anda sadece 2 isteği işleyebildi ve diğer isteğin başlaması için herhangi bir thread'in
  bitmesini beklidiğini görüyoruz. Thread isteği alıp db den kullanıcı çekene kadar engellendiğini görüyoruz.

<p align ="center">
<img src = "https://github.com/rasitesdmr/SpringBoot-Reactive-Webflux/blob/master/SpringBoot-Reactive/images/r4.png">
</p>

* İlk gelen istek 56 ms sürerken.

<p align ="center">
<img src = "https://github.com/rasitesdmr/SpringBoot-Reactive-Webflux/blob/master/SpringBoot-Reactive/images/r5.png">
</p>

* Son gelen isteğe baktığımızda istek 1159 ms sürdüğünü görüyoruz.

# 🎯 Reactive Programlama Nasıl Çalışır?

<p align ="center">
<img src = "https://github.com/rasitesdmr/SpringBoot-Reactive-Webflux/blob/master/SpringBoot-Reactive/images/r9.png">
</p>

* Reactive programlamada tüm mesele streamler ile veriyi olay bazlı olarak yönetmekten geçer.

* Eventlar (olaylar), mesajlar, çağrılar ve hatta hatalar bir data stream üzerinden iletilir.

* Reactive programlama ile bu akışlar sürekli olarak gözlenir/izlenir/observe ve bir değer değişiminde direkt olarak
  tepki verir ve sıradaki işlemi gerçekleştirir.

* Bir uygulama programlarken herhangi bir şeyden/her şeyden veri akışları oluşturmalısınız: Kullanıcı işlemleri, HTTP
  istekleri, alınan mesajlar, verilecek mesajlar, bildirimler, bir değişkendeki değişiklikler, cacheleme olayları,
  database işlemleri; değişebilecek ve oluşabilecek her şey için diyebiliriz.

* Data sourcetan (Veri kaynağı) alınan her sonuç için bir tane event yada mesaj oluşturulur. Data Source: External
  Service (Herhangi bir başka servis), Database (Veritabanı) yada File (Dosya) olabilir. Eğer data source sonucunda
  tamamlanmış ya da hata alınmışsa bir tane event ya da mesaj oluşmuştur. Yani her iki durumda da bir eventımız mevcut.

## 📌 Hata Olursa ?

<p align ="center">
<img src = "https://github.com/rasitesdmr/SpringBoot-Reactive-Webflux/blob/master/SpringBoot-Reactive/images/r10.png">
</p>

* Akış ile ilgili oluşan her şey bir event ya da mesaja karşılık geliyor. Dolayısıyla oluşan hata da event olarak
  oluşuyor.

* Itemlar alınırken hata ile karşılaştık ve bu hata bir event olarak onError’a düştü. Exception’ı nasıl handle
  edeceğimizi de onError kısmında çözümleyebiliyoruz.

* Database’e sorgu attık ve hiçbir sonuç yok. Bu durumda yine de onComplete eventı oluşur.

* Kayıt için ise kayıt isteğini attık, çağrımız hızlıca cevap döndü ve başarılı tamamlandıysa onComplete event’ı ile
  bunu anlayabiliriz.

* onNext ile stream ederken bir sonraki item’a geçebiliriz.

* onComplete başarıyla tamamlandığını belirtirken

* onError hata durumu oldugunu gösterir.

## 📌 Okuduğum Makaleler

* https://medium.com/yemeksepeti-teknoloji/spring-webflux-ile-reactive-programlamaya-giris-f2b73449a0d0#:~:text=Nedir%20Spring%20Webflux%3F,Reactor%20%C3%BCzerine%20kurulu%20bir%20frameworkt%C3%BCr.
* https://medium.com/yemeksepeti-teknoloji/reactive-programlama-nedir-server-side-reactive-programlama-f485d9179df9
* https://reflectoring.io/getting-started-with-spring-webflux/
* https://www.reactive-streams.org/
* https://hackernoon.com/an-intro-to-spring-webflux-threading-model
* https://www.baeldung.com/spring-webflux-concurrency
* https://reflectoring.io/spring-webclient/
* https://www.westagilelabs.com/blog/five-interesting-facts-about-reactive-programming-frameworks/
* https://www.zibtek.com/blog/java-reactive-programming-everything-you-need-to-know/
* https://www.baeldung.com/spring-webflux-backpressure


