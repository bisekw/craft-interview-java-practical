# Zadanie praktyczne — maksymalnie 40 minut

| Etap | Czas |
|---|---:|
| Start: przeczytanie poleceń, gałąź Git i kontrola gotowości | 5 min |
| 1. Dwa selektory | 10 min |
| 2. Scenariusz API | 15 min |
| 3. Scenariusz logowania | 5 min |
| Commit i omówienie rozwiązania | 5 min |
| **Razem** | **40 min** |

Środowisko i klon repozytorium przygotuj wcześniej zgodnie z README. Aplikacja, konfiguracja, runnery, hooki, uruchamianie Chrome oraz metody Page Object są gotowe. Możesz korzystać z dokumentacji i podpowiedzi IDE.

Zmieniasz wyłącznie: dwa pliki `.feature` (tylko odkomentowanie kroków), `LoginPage.java`, `PlayerApiSteps.java` i `LoginSteps.java`. Nie zmieniaj treści kroków w plikach `.feature`; to uzgodniony opis zachowania.

## Start — gałąź Git

Utwórz gałąź roboczą `task/<twoje-inicjały>` od aktualnego stanu repozytorium i na niej wykonuj wszystkie zmiany. Polecenia Git dobierasz samodzielnie.

## 1. Selektory — `src/test/java/interview/pages/LoginPage.java`

Otwórz stronę i zbadaj DOM. Zastąp `null` w dwóch miejscach selektorami Selenium `By`:

- `PASSWORD_INPUT` — pole hasła w formularzu **gracza**.
- `LOGIN_BUTTON` — przycisk „Zaloguj” w formularzu **gracza**.

Strona zawiera podobny formularz operatora. ID pola hasła zmienia się po ponownym załadowaniu strony. Wybierz stabilny kontekst lub relację do etykiety. Nie używaj skopiowanego losowego ID, absolutnego XPath ani indeksu formularza/przycisku. CSS i XPath są dopuszczalne.

Ten sam kod ma działać dla obu układów: `normal` oraz `reversed`. Pozostałe selektory i metody `openPage`, `login`, `isLoggedIn` są gotowe. Nie dodawaj `Thread.sleep`.

## 2. Scenariusz API — `PlayerApi.feature` i `PlayerApiSteps.java`

Plik `src/test/resources/features/api/PlayerApi.feature` zawiera scenariusz pobrania gracza. Pierwszy krok ma już metodę w `src/test/java/interview/api/steps/PlayerApiSteps.java`, ale jej ciało jest puste. Pozostałe kroki są zakomentowane.

1. Uzupełnij krok `When I request player "P-102"`: wykonaj **jedno żądanie GET `/api/players/{id}`** z identyfikatorem z kroku i zapisz odpowiedź w polu klasy. Użyj `Config.BASE_URL` oraz jawnego timeoutu.
2. Odkomentuj kroki scenariusza i dopisz ich definicje w `PlayerApiSteps.java`.

Kroki sprawdzają cztery warunki:

1. Status HTTP to **200**.
2. JSON zawiera **data** będące obiektem.
3. **data.id** to **P-102**.
4. **data.email** kończy się na **@example.test**.

Fragment odpowiedzi:

```json
{"data":{"id":"P-102","email":"ala.nova@example.test"}}
```

Odpowiedź zawiera też inne pola. Dodaj czytelne komunikaty asercji.

Wybierz REST Assured albo zwykły klient HTTP, np. `java.net.http.HttpClient`. Nie trzeba dodawać zależności. W REST Assured timeouty można ustawić przez `RestAssuredConfig` / `HttpClientConfig` (`http.connection.timeout` i `http.socket.timeout`, wartości w milisekundach). W kliencie JDK są dostępne `connectTimeout(Duration)` i `HttpRequest.Builder.timeout(Duration)`. Test nie wymaga logowania, tworzenia kont ani sprzątania danych.

## 3. Scenariusz logowania — `Login.feature` i `LoginSteps.java`

Plik `src/test/resources/features/ui/Login.feature` zawiera scenariusz poprawnego logowania z przykładami dla obu układów strony. Krok `Given` jest gotowy w `src/test/java/interview/ui/steps/LoginSteps.java`.

1. Odkomentuj pozostałe kroki scenariusza.
2. Dopisz ich definicje w `LoginSteps.java`: zaloguj gracza danymi `Config.VALID_USER` i `Config.VALID_PASSWORD`, a następnie sprawdź wynik logowania asercją z czytelnym komunikatem.

Page Object jest dostępny przez `context.loginPage()`. Przeglądarkę uruchamia i zamyka gotowy hook. Nie piszesz drugiego scenariusza ani obsługi drivera.

## Zakończenie — commit

Zatwierdź swoje zmiany na gałęzi roboczej commitem z opisem. Nie wysyłaj zmian do zdalnego repozytorium. Pokaż historię gałęzi i listę zmienionych plików.

## Omówienie

Uruchom testy i wyjaśnij dobór selektorów, połączenie kroków z plików `.feature` z metodami Java, znaczenie asercji API oraz sposób oczekiwania na wynik logowania. Powiedz, co zostało potwierdzone wykonaniem, a czego nie udało się sprawdzić.
