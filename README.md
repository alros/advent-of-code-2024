# Advent Of Code 2024 🎄

![Stars](https://img.shields.io/badge/stars%20⭐-38-yellow)
![Days Completed](https://img.shields.io/badge/days%20completed-18-green)
![Days Partially Completed](https://img.shields.io/badge/days%20partially%20completed-2-silver)

[Advent of Code](https://adventofcode.com) is an Advent calendar of small programming puzzles for a variety of skill
sets and skill levels that can be solved in any programming language you like. People use them as interview prep,
company training, university coursework, practice problems, a speed contest, or to challenge each other.

# Render

The solution of [Day14](render/day14.txt)

First 600 movements of [Day15](render/day15compressed.gif) compressed with

```bash
gifsicle -i day15.gif -O3 --colors 10 -o day15compressed.gif
```

# In progress 😅

- day 16, part 2: I need to fix the costs in the longer loops..


- day 17, part 2: I have to revert this somehow...

```
out = (((a % 8).xor(3)).xor(5).xor(a / 2.0.pow((a.mod(8)).xor(3)).toLong())).mod(8)
a /= 2.0.pow(3).toLong()
```

`

