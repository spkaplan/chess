# Chess

Play chess against a friend locally, or challenge yourself to a game to improve your skills.

## Install

### Option 1: Build from source

```
$ git clone https://github.com/spkaplan/chess.git
$ cd chess/
$ ./gradlew build
```

## Usage

### Begin game

```sh
$ java -jar build/libs/chess.jar
```

### Commands

```
move a2 a3        # move piece at position a2 to a3
validmoves a3     # return a list of the positions that the piece at a3 is allowed to move
castle e1 h1      # perform the castle maneuver, where e1 and h1 are the positions of your king and rook (or visa versa)
refresh           # Redisplay the board and header, which contains relevant game info (e.g. who's turn it is)
help              # display a message enumerating the valid commands
exit              # end the game
quit              # end the game
```

## License

MIT
