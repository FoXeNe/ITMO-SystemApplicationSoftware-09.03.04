org 0x3DB

start:
  cla
  st res

  ld x
  dec
  push
  call $program
  pop
  dec
  sub res
  st res

  ld y
  push
  call $program
  pop
  sub res
  st res

  ld z
  dec
  push
  call $program
  pop
  inc
  add res
  st res
  hlt

z: word 0xDCD7
y: word 0x0856
x: word 0x4DB0
res: word 0x000

org 0x728
program:
  ld &1
  bmi skip
  cmp const1
  bge skip
  asl
  asl
  sub &1
  sub const2
  jump stStack
skip:  ld const1
stStack: st &1
  ret

const1: word 0xAC3
const2: word 0x006D
