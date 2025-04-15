# Minesweeper Swing 🎮

This is a Java implementation of the classic Minesweeper game, featuring a fully interactive graphical interface built using **Java Swing**. The project is structured around the **Model-View-Observer** design pattern and showcases fundamental Java concepts, including:

- Object-oriented programming
- Event-driven programming
- Encapsulation and abstraction
- Enumerations and interfaces
- Basic GUI layout management

---

## 🎥 Demo
Watch a video demonstration of the game in action: [Minesweeper Swing Demo](https://www.youtube.com/watch?v=WKF4BkrsRTs)

---

## 🧠 Project Structure

```
src/
 ├── com.github.gv.ms
 │    ├── model          # Game logic (Tile, Board, Events)
 │    └── view           # Swing UI components
 └── img                 # Game icons (flag, mine)
```

### Key Components
- **Tile.java**: Represents a single tile (cell) on the board. Tracks state: open, mine, flagged.
- **Board.java**: Manages tile grid, game logic, and win/loss conditions.
- **TileEvent.java**: Enum for events (OPEN, FLAG, EXPLODE, etc).
- **TileObserver.java**: Interface for components observing tile changes.
- **ButtonTile.java**: JButton subclass styled for game interaction.
- **PanelBoard.java**: Grid layout of tile buttons.
- **MainScreen.java**: Main JFrame, starts the game.

---

## 🧩 Features
- Custom board size and mine count
- Recursive tile reveal when neighbors are safe
- Visual feedback for flags, revealed numbers, and mines
- End-game dialog for win or loss
- Auto-reset board after game ends

---

## ✅ How to Run

### Requirements
- Java JDK 17 or newer
- IntelliJ IDEA (or other Java IDE)

### Steps
1. Clone this repository:
   ```bash
   git clone https://github.com/your-username/minesweeper-swing.git
   ```
2. Open the project in IntelliJ
3. Run the `MainScreen.java` file

Enjoy playing Minesweeper! ☠️💣🚩

---

## 📸 Icons
Place your image resources (`flag.jpg`, `mine.png`) in:
```
src/img/
```
Make sure paths like `/img/flag.jpg` match correctly.

---

## 📚 Credits
Created as part of a Java learning journey and practice exercise from a programming course.

---

## 📜 License
MIT License (or add your license here)

---

Happy coding and good luck not stepping on a mine!
