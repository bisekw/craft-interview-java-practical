# QA Java — zadanie praktyczne na 40 minut

Uzupełniasz scenariusze w gotowym frameworku Cucumber: cztery selektory, kroki scenariusza logowania i kroki scenariusza API. Pracujesz na własnej gałęzi Git. Polecenia i podział czasu: [TASKS.md](TASKS.md).

Aplikacja demo znajduje się w `app/`. Testy piszesz w Javie, a dołączona aplikacja działa na Pythonie i korzysta wyłącznie z jego biblioteki standardowej. Nie trzeba instalować paczek pip ani tworzyć środowiska `.venv`.

## Przygotowanie przed rozmową

Zainstaluj Git, JDK 17 lub nowszy, Python 3.11 lub nowszy oraz Google Chrome. Ustaw `JAVA_HOME` na katalog JDK. Pierwsze uruchomienie wymaga Internetu do pobrania Mavena, zależności i drivera Chrome. Maven Wrapper jest dołączony — osobna instalacja Mavena nie jest potrzebna.

Sklonuj repozytorium z adresu otrzymanego od prowadzącego. Otwórz **katalog projektu**, zawierający `pom.xml`, w IntelliJ IDEA jako projekt Maven. Ustaw Project SDK na zainstalowany JDK i poczekaj na import zależności. Wtyczka Cucumber for Java ułatwia przechodzenie z kroku w pliku `.feature` do jego metody, ale nie jest wymagana.

Wszystkie poniższe polecenia wykonuj z katalogu z `pom.xml`.

**Terminal 1 — uruchom aplikację i pozostaw terminal otwarty:**

```powershell
python .\app\run.py --port 8000
```

**Gdy Windows zgłasza „nie znaleziono Python; uruchom bez argumentów, aby zainstalować”** (w angielskiej wersji: „Python was not found”), Python nie jest zainstalowany, a polecenie `python` jest tylko skrótem do Microsoft Store. Zainstaluj Pythona w PowerShell, zamknij i otwórz terminal ponownie, a aplikację uruchom przez launcher `py`:

```powershell
winget install -e --id Python.Python.3.14
py -3 .\app\run.py --port 8000
```

Jeśli po instalacji `py` nadal nie jest rozpoznawany, uruchom interpreter pełną ścieżką domyślnej instalacji dla bieżącego użytkownika:

```powershell
& "$env:LOCALAPPDATA\Programs\Python\Python314\python.exe" .\app\run.py --port 8000
```

Jeśli masz już Pythona dostępnego pod nazwą z numerem wersji, np. zainstalowanego przez uv, możesz użyć go bez instalacji: `python3.14 .\app\run.py --port 8000`. Sprawdzisz to poleceniem `Get-Command python3*`.

**Terminal 2 — sprawdź środowisko:**

```powershell
.\mvnw.cmd test "-Dtest=EnvironmentTest"
```

Oczekiwany wynik: **2 testy, 0 błędów i 0 niepowodzeń**. Kontrola sprawdza API aplikacji i uruchomienie Chrome; nie wymaga uzupełnienia zadań. Przygotuj środowisko przed rozmową.

## Strona i dane do logowania

- Strona: <http://127.0.0.1:8000>
- Odwrócona kolejność formularzy: <http://127.0.0.1:8000/?layout=reversed>
- Login gracza: `tester`
- Hasło: `Interview123!`

Są to wyłącznie dane lokalnej aplikacji demo. Kod aplikacji i gotowe klasy frameworka możesz czytać. Zakres zmian opisuje TASKS.md.

## Uruchamianie testów

```powershell
# Scenariusz logowania w dwóch układach, z widoczną przeglądarką:
.\mvnw.cmd test "-Dtest=UiTestRunner" "-Dheadless=false"

# Scenariusz API:
.\mvnw.cmd test "-Dtest=ApiTestRunner"

# Całość, domyślnie bez widocznego okna Chrome:
.\mvnw.cmd test
```

Cucumber wypisuje w konsoli każdy wykonany krok oraz podsumowanie scenariuszy i kroków. Krok bez definicji w Javie kończy scenariusz błędem i podpowiedzią szkieletu metody. Po rozwiązaniu całego zadania oczekujemy **5 poprawnych testów**: 2 kontrole środowiska, 1 scenariusz API i 2 przykłady scenariusza logowania, w których wykonują się wszystkie kroki z plików `.feature`.

Testy można też uruchamiać bezpośrednio z IDE, wybierając klasę runnera. Raporty wykonania przez Maven znajdują się w `target/surefire-reports/`. Framework otwiera i zamyka Chrome dla każdego scenariusza UI. Aplikację zatrzymasz przez `Ctrl+C` w terminalu 1.

Na Linux/macOS użyj `python3 app/run.py --port 8000` oraz `sh ./mvnw test -Dtest=EnvironmentTest`. Pozostałe polecenia Maven mają te same argumenty.

## Konfiguracja

Inny port aplikacji:

```powershell
# Terminal 1:
python .\app\run.py --port 8001

# Terminal 2:
.\mvnw.cmd test "-Dtest=EnvironmentTest" "-DbaseUrl=http://127.0.0.1:8001"
```

Adres można też podać przez zmienną `QA_BASE_URL`; właściwość `baseUrl` ma pierwszeństwo. Jeśli automatyczne pobieranie drivera jest niedostępne, ustaw `QA_DRIVER_PATH` na zgodny z Chrome plik `chromedriver`. Opcjonalne `QA_BROWSER_BINARY` wskazuje plik wykonywalny Chrome. Zmienne ustaw w terminalu lub konfiguracji uruchomienia IDE.

Jeśli na Windows pobieranie zależności kończy się `PKIX path building failed`, a certyfikat sieci firmowej jest już zaufany przez Windows, można użyć systemowego magazynu certyfikatów w bieżącym terminalu:

```powershell
$env:MAVEN_OPTS = "$env:MAVEN_OPTS -Djavax.net.ssl.trustStoreType=Windows-ROOT -Djavax.net.ssl.trustStore=NONE"
.\mvnw.cmd test "-Dtest=EnvironmentTest"
```

Ta opcja zachowuje weryfikację certyfikatów. Nie jest potrzebna przy prawidłowo skonfigurowanym magazynie certyfikatów JDK.

## Pliki

```text
app/                                     dołączona aplikacja demo
src/test/resources/features/
  api/PlayerApi.feature                  ZADANIE: scenariusz API
  ui/Login.feature                       ZADANIE: scenariusz logowania
src/test/java/interview/
  pages/LoginPage.java                   ZADANIE: cztery selektory
  api/steps/PlayerApiSteps.java          ZADANIE: kroki scenariusza API
  ui/steps/LoginSteps.java               ZADANIE: kroki scenariusza logowania
  runner/ApiTestRunner.java              uruchamia scenariusze z tagiem @api
  runner/UiTestRunner.java               uruchamia scenariusze z tagiem @ui
  ui/hooks/Hooks.java                    przeglądarka dla każdego scenariusza UI
  ui/context/UiContext.java              stan scenariusza UI przekazywany do kroków
  pages/BasePage.java                    operacje na elementach z oczekiwaniem
  support/                               konfiguracja i uruchamianie Chrome
  EnvironmentTest.java                   kontrola środowiska
src/test/resources/junit-platform.properties   ustawienia Cucumber
```

Framework: Cucumber 7 na JUnit 5, PicoContainer, Selenium i REST Assured. W krokach API możesz wybrać REST Assured albo standardowy `java.net.http.HttpClient`. Do odczytu JSON jest dostępny również `io.restassured.path.json.JsonPath`.

Dokumentacja: [Cucumber — definicje kroków](https://cucumber.io/docs/cucumber/step-definitions/), [REST Assured](https://github.com/rest-assured/rest-assured/wiki/Usage), [HttpClient](https://docs.oracle.com/en/java/javase/17/docs/api/java.net.http/java/net/http/HttpClient.html), [Selenium](https://www.selenium.dev/documentation/webdriver/).

Do repozytorium należą źródła aplikacji, testów, instrukcje, `pom.xml`, skrypty `mvnw`/`mvnw.cmd` i `.mvn/wrapper/maven-wrapper.properties`. `.gitignore` wyłącza raporty, cache i ustawienia IDE.
