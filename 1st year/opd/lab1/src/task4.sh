cd lab0
mkdir tmp
touch tmp/errors
touch tmp/count
cat roserade5 | wc -m > tmp/count
ls -R cleffa5 2>tmp/errors | sort 
cd ..
ls -R lab0 2>/dev/null | grep "^t" | sort -r
cd lab0
cat -n cleffa5/bellsprout cleffa5/pidove cleffa5/servine porygon20/mantyke porygon20/alakazam porygon20/gligar 2>/dev/null | sort -r
ls cleffa5/bellsprout cleffa5/pidove cleffa5/servine porygon20/mantyke porygon20/alakazam porygon20/gligar 2>tmp/errors | grep -i "e$"
