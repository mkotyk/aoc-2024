# aoc-2024
Advent of Code - 2024 - Kotlin Notebook

### Take-aways using Kotlin Notebook


Would I use Kotlin Notebook again for AOC... no.  Here's why:

- Using standard `println` does not work correctly, and often repeats and 
    interleaves output.  You must use `DISPLAY`.
- No debugging ability.  Despite there being a button for it, the button does not do anything!
  I often had to copy-paste the code into a standard Kotlin file to debug.
- No git/local history from inside the editor. (Available from the file tree)
- Issues with references to global scoped code from inside classes.
- I thought I would use the cool graphing/bitmap features, but AOC lends itself more
  to plain text output or animated graphics.  Adding the puzzle text from AOC might be nice 5 years from now,
  but it was just time-consuming and made the code less navigable.
- Any tight loops often makes the IDE unresponsive.  Memory usage is also high.  The only
  fix is to close IntelliJ and restart.
- Any changes to library code requires a restart of the notebook server.  This should be automatic.
- Flaky behaviour in general.  Sometimes `Clear Output` just wouldn't do anything
  until a restart.  Lots of glitches with navigating.. sometimes PgUp will take you
  to the top of your code block, sometimes it will switch blocks.  Renaming symbols often didn't work.

### Shout outs

Whenever I was stuck, I referred to https://github.com/Jadarma/advent-of-code-kotlin-solutions/tree/main
who always came up with elegant, clean solutions in a code style similar to my own.  I learned a lot from this code,
and will hopefully know more for next year.