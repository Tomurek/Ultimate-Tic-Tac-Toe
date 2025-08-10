# Ultimate Tic Tac Toe

## Opis projektu
Ultimate Tic Tac Toe to rozbudowana wersja klasycznej gry w kółko i krzyżyk, w której plansza składa się z dziewięciu mniejszych plansz ułożonych w siatkę 3×3. Każdy ruch na małej planszy decyduje, gdzie przeciwnik zagra swój kolejny ruch. Wygrywa gracz, który jako pierwszy ułoży trzy wygrane małe plansze w linii na dużej planszy.

---

## Funkcje
- Gra dla dwóch graczy lub przeciwko botowi
- Wybór poziomu trudności
- Interaktywny interfejs graficzny
- Informacje o graczu i aktualnym stanie gry
- Okno dialogowe zwycięzcy
- Tryb developerski (opis poniżej)

---

## Zrzuty ekranu
<img width="686" height="693" alt="obraz" src="https://github.com/user-attachments/assets/67a77f88-0e75-46e3-a98c-6cd2b41a8f28" />
<img width="686" height="693" alt="obraz" src="https://github.com/user-attachments/assets/0260cac3-951c-4b4e-ad99-f7b5a6c74a7e" />
<img width="686" height="693" alt="obraz" src="https://github.com/user-attachments/assets/c57dfbcc-3067-4338-a063-f397a706eb7e" />
<img width="681" height="685" alt="obraz" src="https://github.com/user-attachments/assets/c3f8b47f-b16b-4d66-8ced-323b31c08417" />
<img width="686" height="693" alt="obraz" src="https://github.com/user-attachments/assets/48986977-4ff5-4b26-9e0f-cd3abecac3c5" />

---

## Instalacja
1. Sklonuj repozytorium:
   ```sh
   git clone https://github.com/Tomurek/Ultimate-Tic-Tac-Toe.git
   ```
2. Otwórz projekt w IDE obsługującym Javę (np. IntelliJ IDEA, Eclipse).
3. Uruchom plik `UltimateTicTacToe.java`.

---

## Użycie
- Po uruchomieniu gry wybierz tryb gry (gracz vs gracz lub gracz vs bot).
- Wybierz poziom trudności oraz kto zaczyna rozgrywkę.
- Graj, klikając na wybrane pole na aktywnej planszy.
- Wygrywa gracz, który jako pierwszy zdobędzie trzy małe plansze w linii.

---

## Tryb developerski
Tryb developerski pozwala na testowanie i debugowanie gry. Umożliwia podgląd dodatkowych informacji oraz funkcji pomocniczych dla programisty. Aby włączyć tryb developerski, przejdź do pliku `UltimateTicTacToe.java` i w 83. linijce usuń komentarz przy odpowiedniej linii (np. odkomentuj `mainPanel.add(devPanel, BorderLayout.SOUTH);`).

---

## Opis plików
- `UltimateTicTacToe.java` – główny plik uruchamiający grę, zarządza logiką i interfejsem
- `UltimateBot.java` – implementacja bota, obsługuje ruchy AI
- `StartScreen.java` – ekran startowy gry
- `MenuPanel.java` – panel menu głównego
- `ModePanel.java` – wybór trybu gry (gracz vs gracz, gracz vs bot)
- `DifficultyPanel.java` – wybór poziomu trudności bota
- `WhoStartsPanel.java` – wybór, który gracz zaczyna
- `SmallBoardPanel.java` – obsługa pojedynczej małej planszy
- `RightPanel.java` – panel boczny z informacjami o grze
- `PlayerInfoPanel.java` – panel z informacjami o graczach
- `WinnerDialog.java` – okno dialogowe informujące o zwycięstwie
- `XOIcon.java` – grafika ikon X i O
- `Colors.java` – definicje kolorów używanych w grze
- `DevPanelFactory.java` – panel developerski, narzędzia do debugowania i testowania

---

## Autor
Projekt stworzony przez [Tomurek](https://github.com/Tomurek).

---

## English

### Project Description
Ultimate Tic Tac Toe is an advanced version of classic Tic Tac Toe, featuring a board made of nine smaller boards arranged in a 3×3 grid. Each move on a small board determines where the opponent will play next. The winner is the first to win three small boards in a row on the main board.

### Features
- Two-player or bot mode
- Difficulty selection
- Interactive GUI
- Player info and game status
- Winner dialog
- Developer mode (see below)

### Installation
1. Clone the repository:
   ```sh
   git clone https://github.com/Tomurek/Ultimate-Tic-Tac-Toe.git
   ```
2. Open the project in a Java IDE (e.g., IntelliJ IDEA, Eclipse).
3. Run `UltimateTicTacToe.java`.

### Usage
- Select game mode and difficulty
- Choose who starts
- Play by clicking on the active board
- Win by claiming three small boards in a row

### Developer Mode
Developer mode allows for testing and debugging the game. It provides additional information and tools for programmers. To enable developer mode, go to `UltimateTicTacToe.java` and uncomment the appropriate line at line 83 (e.g., uncomment `DEV_MODE = true;`).

### File Descriptions
- `UltimateTicTacToe.java` – main game file, manages logic and UI
- `UltimateBot.java` – bot implementation, handles AI moves
- `StartScreen.java` – game start screen
- `MenuPanel.java` – main menu panel
- `ModePanel.java` – game mode selection (player vs player, player vs bot)
- `DifficultyPanel.java` – bot difficulty selection
- `WhoStartsPanel.java` – choose who starts the game
- `SmallBoardPanel.java` – handles a single small board
- `RightPanel.java` – side panel with game info
- `PlayerInfoPanel.java` – player info panel
- `WinnerDialog.java` – winner dialog window
- `XOIcon.java` – X and O icon graphics
- `Colors.java` – color definitions used in the game
- `DevPanelFactory.java` – developer panel, debugging and testing tools

### Author
Created by [Tomurek](https://github.com/Tomurek).

